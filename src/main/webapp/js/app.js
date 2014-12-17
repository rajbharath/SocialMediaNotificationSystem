var socialmediaApp = angular.module('socialmediaApp',['ngRoute','ngAnimate']).
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
socialmediaApp.controller('userController',function($scope,$routeParams,$http,$rootScope){
	$scope.pageClass = 'page-user';
    $rootScope.user = {};
    $scope.results = [];
    $scope.value="";
    $scope.posts = [];


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
            alert('already added');
        });
    };

 $scope.delete = function(userId,friendId,index){

     $http.delete("http://localhost:8080/user/"+$routeParams.userId+"/friend/"+friendId).success(function(data){
             $rootScope.user.friends.splice(index,1);
        }).error(function(){
            alert(userId+"  "+friendId);
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
                 $scope.posts.splice(0,0,data);
            }).error(function(){
                alert('something went wrong');
            });
 };


});