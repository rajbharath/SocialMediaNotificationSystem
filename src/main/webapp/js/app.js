var socialmediaApp = angular.module('socialmediaApp',['angular.atmosphere','ngRoute','ngAnimate']).
filter('fromNow',function(){
return function(date) {
return moment(date).fromNow();
}
});
var baseUrl = 'http://localhost:8080/#/';


socialmediaApp.config(function($routeProvider){
	$routeProvider

		.when('/',{
			templateUrl: 'page-home.html',
			controller: 'mainController'
		})

		.when('/user/:userId',{
			templateUrl: 'page-user.html',
			controller: 'userController'

		});
});

//mainController
socialmediaApp.controller('mainController',function($scope){
	$scope.pageClass = 'page-home';
});

//userController
socialmediaApp.controller('userController',function($scope,$routeParams,$http,$rootScope,atmosphereService){
	$scope.pageClass = 'page-user';
    $rootScope.user = {};
    $scope.results = [];
    $scope.value="";
    $scope.posts = [];

  var socket;

  var request = {
    url: '/users/posts/'+$routeParams.userId,
    contentType: 'application/json',
    logLevel: 'debug',
    shared: true,
    transport: 'websocket',
    trackMessageLength: true,
    reconnectInterval: 50000,
    fallbackTransport: 'long-polling',
    enableXDR: true,
    timeout: 60000,
    messageDelimiter: '|'
  };

  request.onOpen = function(response){
    alert('connected');
  };

    request.onClientTimeout = function(response){
      //alert('timeout');
      setTimeout(function(){
        socket = atmosphereService.subscribe(request);
      }, request.reconnectInterval);
    };

    request.onReopen = function(response){
      // alert('reopen');
    };
//
//    //For demonstration of how you can customize the fallbackTransport using the onTransportFailure function
    request.onTransportFailure = function(errorMsg, request){
//      atmosphere.util.info(errorMsg);
      alert(errorMsg);
      this.fallbackTransport = 'long-polling';
    };
//
  request.onMessage = function(response){
    alert('message'+JSON.stringify(response.responseBody));
    var post = {};
    post = JSON.stringify(response.responseBody);
    alert(post.id +' '+post.message);
//    var post.id = response.responseBody.id;
//    var post.message = response.responseBody.message;
    $scope.posts.splice(0,0,response);
    console.log(response.responseBody);
    this.onReopen();
  };

//  request.onClose = function(response){
//    //alert('close');
//    //socket.push(atmosphere.util.stringifyJSON({ author: $scope.model.name, message: 'disconnecting' }));
//  };
//
  request.onError = function(response){
    //alert('error');
  };
//
  request.onReconnect = function(request, response){
    //alert('reconnect');
  };
//
 socket = atmosphereService.subscribe(request);

    getUserPostsAsync = function(data){
    alert(JSON.stringify(data));
//            socket.push(JSON.stringify({"message":"message", "id":345, "user": {"id":1,"name":"raj"}}));
            var string = JSON.stringify(data, function( key, value) {
                           if(key == 'user') {
                             return value.id;
                           } else {
                             return value;
                           };
                         })
            socket.push(JSON.stringify(data));
  }

    $scope.search = function(){
        $scope.results= [];
     $http.get("http://localhost:8080/user?mail="+$scope.value).success(function(response){
            $scope.results= response;
        });
    };

    //GETTING THE USER
    $http.get("http://localhost:8080/user/"+$routeParams.userId).success(function(response){
        $rootScope.user = response;
    });

    //GETTING THE POSTS FOR THAT USER
    $http.get("http://localhost:8080/user/"+$routeParams.userId+"/posts").success(function(response){
        $scope.posts = [];
        $scope.posts = response;
    });

    $scope.add = function(userId,friendId){

     $http.post("http://localhost:8080/user/"+$routeParams.userId+"/friend",{'friendId':friendId},{headers:{'Content-Type':'application/x-www-form-urlencoded','Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8'},
     transformRequest: function(obj) {
      var str = [];
      for(var p in obj)
      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
      return str.join("&");
      }}).success(function(data){
             $rootScope.user.friends.push(data);
        }).error(function(){
            //alert('already added');
        });
    };

 $scope.delete = function(userId,friendId,index){

     $http.delete("http://localhost:8080/user/"+$routeParams.userId+"/friend/"+friendId).success(function(data){
             $rootScope.user.friends.splice(index,1);
        }).error(function(){
            //alert(userId+"  "+friendId);
        });
    };

 $scope.add_post = function(userId,message){
//socket.push(JSON.stringify({'message':message}));
    $http.post("http://localhost:8080/user/"+userId+"/posts/",{'message':message},{headers:{'Content-Type':'application/x-www-form-urlencoded','Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8'},
         transformRequest: function(obj) {
          var str = [];
          for(var p in obj)
          str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
          return str.join("&");
          }}).success(function(data){
//                 $scope.posts.splice(0,0,data);

                getUserPostsAsync(data);
            }).error(function(){
                //alert('something went wrong');
            });


 };

});

