var socialMediaModule = angular.module('socialMediaApp',['ng-animate']);

socialMediaModule.controller('socialMediaController',function($scope,$http){
var urlBase = "http://localhost:8080/"

$http.get(urlBase).success(function(data){
    $scope.users = data;
});

});