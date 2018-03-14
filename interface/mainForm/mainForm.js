'use strict';

angular.module('myApp.mainForm', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/mainForm', {
      templateUrl: 'mainForm/mainForm.html',
      controller: 'mainFormCtrl'
  });
}])

    .controller('mainFormCtrl', function ($rootScope, $location) {
    if (!$rootScope.user) {
        $location.path("/login");
    }
});