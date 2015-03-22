'use strict';

/* App Module */

var bracketEngineApp = angular.module('bracketEngineApp', [
    'ngRoute',

    'bracketEngineControllers',
    'bracketEngineServices'
]);

bracketEngineApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/tournaments', {
                templateUrl: 'partials/tournament-list.html',
                controller: 'TournamentListCtrl'
            }).
            when('/tournaments/:tournamentId', {
                templateUrl: 'partials/tournament-detail.html',
                controller: 'TournamentDetailCtrl'
            }).
            otherwise({
                redirectTo: '/tournaments'
            });
    }]);