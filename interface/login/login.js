'use strict';

angular.module('myApp.login', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
      templateUrl: 'login/login.html',
      controller: 'LoginCtrl'
  });
}])

    .controller('LoginCtrl', function ($scope, $rootScope, $location, $http) {
        $scope.user = {};

        $scope.login = function(){
            $http.post('/mocks/login.php', $scope.user)
                .then(() => {
                    console.log('success');
                    $rootScope.user = $scope.user;
                    $location.path("/mainForm");
                },
                () => {
                    $rootScope.user = null;
                    alert("Login failed");
                });
        }
});