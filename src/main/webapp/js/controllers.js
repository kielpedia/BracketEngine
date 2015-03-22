'use strict';

/* Controllers */

var bracketEngineControllers = angular.module('bracketEngineControllers', []);

bracketEngineControllers.controller('TournamentListCtrl', ['$scope', 'Tournament',
    function ($scope, Tournament) {
        $scope.tournaments = Tournament.query();
        //$scope.orderProp = 'age';

        $scope.createTournament = function () {
            Tournament.create();
        };
    }]);

bracketEngineControllers.controller('TournamentDetailCtrl', ['$scope', '$routeParams', 'Tournament',
    function ($scope, $routeParams, Tournament) {

        $scope.tournament = Tournament.get({tournamentId: $routeParams.tournamentId});

        $scope.updateTournament = function () {
            Tournament.update({tournamentId: $routeParams.tournamentId}, $scope.tournament);
        }
    }]);
