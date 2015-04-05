'use strict';

/* Controllers */

var bracketEngineControllers = angular.module('bracketEngineControllers', []);

bracketEngineControllers.controller('TournamentListCtrl', ['$scope', '$route', 'Tournament',
    function ($scope, $route, Tournament) {
        $scope.tournaments = Tournament.query();

        $scope.createTournament = function () {
            Tournament.create();
            $route.reload();
        };
    }]);

bracketEngineControllers.controller('TournamentDetailCtrl', ['$scope', '$routeParams', '$route', 'Tournament', 'Actor', 'Bracket',
    function ($scope, $routeParams, $route, Tournament, Actor, Bracket) {

        $scope.tournament = Tournament.get({tournamentId: $routeParams.tournamentId});
        $scope.actors = Actor.query({tournamentId: $routeParams.tournamentId});
        $scope.brackets = Bracket.query({tournamentId: $routeParams.tournamentId});
        $scope.newActor = new Actor({tournamentId: $routeParams.tournamentId});


        $scope.updateTournament = function () {
            Tournament.update({tournamentId: $routeParams.tournamentId}, $scope.tournament);
            $route.reload();
        };

        $scope.addActor = function (actor) {
            Actor.create({name: actor.name}, actor);
            $route.reload();
        };

        $scope.deleteActor = function (actor) {
            Actor.delete({tournamentId: actor.tournamentId, actorId: actor.id});
            $route.reload();
        };

        $scope.addBracket = function () {
            Bracket.create({tournamentId: $routeParams.tournamentId});
            $route.reload();
        };
    }]);
