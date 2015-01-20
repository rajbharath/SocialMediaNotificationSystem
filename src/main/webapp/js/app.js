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
    $scope.activities = [];

  var socket;

  var request = {
    url: '/users/posts/'+$routeParams.userId,
    contentType: 'json',
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
//    alert('connected');
  };

    request.onClientTimeout = function(response){
      //alert('timeout');
      setTimeout(function(){
        socket = atmosphereService.subscribe(request);
      }, request.reconnectInterval);
    };

    request.onReopen = function(response){
    };

    request.onTransportFailure = function(errorMsg, request){
      alert(errorMsg);
      this.fallbackTransport = 'long-polling';
    };

  request.onMessage = function(response){
    console.log(JSON.stringify(response.responseBody));
    var post = {};
    post = JSON.parse(response.responseBody);
    $scope.posts.splice(0,0,post);
    console.log(response.responseBody);
    this.onReopen();
  };


 socket = atmosphereService.subscribe(request);

    getUserPostsAsync = function(data) {
        console.log(JSON.stringify(data));
//        data.user.friends = [];
        console.log(JSON.stringify(data));
//      socket.push(JSON.stringify({"id":516,"message":"asda","user":{"id":1,"mail":"raj@priya.com","name":"rajpriya","password":"1","friends":[{"id":6,"mail":"prabu@gmail.com","name":"prabu","password":"1","friends":[1]},{"id":7,"mail":"ramya@gmail.com","name":"ramya","password":"1","friends":[{"id":8,"mail":"gautham@gmail.com","name":"gautham","password":"1","friends":[{"id":10,"mail":"ganes@gmail.com","name":"ganes","password":"1","friends":[]}]}]},8,10]},"createdAt":1420912674189}));
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

       //GETTING THE ACTIVITIES FOR THAT USER
        $http.get("http://localhost:8080/user/"+$routeParams.userId+"/activities").success(function(response){
            $scope.activities = [];
            $scope.activities = response;
            console.log(JSON.stringify($scope.activities));
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
                alert('something went wrong');
            });
 };

$scope.create_like = function(userId,postId){

     $http.post("http://localhost:8080/user/"+userId+"/activities/",{'postId':postId,'activityType':'like'},{headers:{'Content-Type':'application/x-www-form-urlencoded','Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8'},
             transformRequest: function(obj) {
              var str = [];
              for(var p in obj)
              str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
              return str.join("&");
              }}).success(function(data){
    //                 $scope.posts.splice(0,0,data);
//                    getUserPostsAsync(data);
                    alert("success");
                }).error(function(){
                    alert('something went wrong');
                });
};


});

