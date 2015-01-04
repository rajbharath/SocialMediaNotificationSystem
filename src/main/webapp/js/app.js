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
    url: '/user/'+$routeParams.userId+'/posts',
    contentType: 'application/json',
    logLevel: 'debug',
    method: 'GET',
    transport: 'long-polling',
    trackMessageLength: true,
    reconnectInterval: 5000,
    fallbackTransport: 'long-polling',
    enableXDR: true,
    timeout: 60000
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
      this.method = 'GET';
    };
//
    request.onMessage = function(response){
    alert('message');
    $scope.posts = response.responseBody;
    console.log(response.responseBody);
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

  getUserPostsAsync = function(){
            //var me = request;
//          $scope.$apply(function(){
            alert('getUserposts');
            socket.push(JSON.stringify({message: 'message'}));
          //  $(me).val('');
//          });
  }
getUserPostsAsync();
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

                 $scope.posts.splice(0,0,data);
            }).error(function(){
                //alert('something went wrong');
            });


 };

});

