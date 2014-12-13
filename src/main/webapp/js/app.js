var socialmediaApp = angular.module('socialmediaApp',['ngRoute','ngAnimate']);
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
socialmediaApp.controller('userController',function($scope,$routeParams,$http){
	$scope.pageClass = 'page-user';
    $scope.user = {};
    $scope.results = [];
    $scope.value="";



    $scope.search = function(){
     $http.get("http://localhost:8080/user?mail="+$scope.value).success(function(response){
            $scope.results= response;
        });
    };

    $http.get("http://localhost:8080/user/"+$routeParams.userId).success(function(response){
        $scope.user = response;
    });



});