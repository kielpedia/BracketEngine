'use strict';

/* Services */

var bracketEngineServices = angular.module('bracketEngineServices', ['ngResource']);

bracketEngineServices.factory('Tournament', ['$resource',
    function ($resource) {
        return $resource('/api/tournaments/:tournamentId', {}, {
            query: {method: 'GET', isArray: true},
            create: {method: 'POST'},
            get: {method: 'GET'},
            update: {method: 'PUT'}
        });
    }]);

bracketEngineServices.factory('Bracket', ['$resource',
    function ($resource) {
        return $resource('/api/tournaments/:tournamentId/brackets/:bracketId', {tournamentId: "@tournamentId"}, {
            query: {method: 'GET', isArray: true},
            create: {method: 'POST'},
            update: {method: 'PUT'}
        });
    }]);

bracketEngineServices.factory('Actor', ['$resource',
    function ($resource) {
        return $resource('/api/tournaments/:tournamentId/actors/:actorId', {
            tournamentId: "@tournamentId"
        }, {
            query: {method: 'GET', isArray: true},
            create: {method: 'POST'},
            update: {method: 'PUT'}
        });
    }]);