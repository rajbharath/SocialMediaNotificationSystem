 
var socialMediaModule = angular.module('socialMediaApp',[]);


socialMediaModule.controller('welcome',function($scope,$http){
	$scope.users  = [];
$http.get('http://localhost:8080/user/').success(function(response){
    $scope.users = response;
})

});

