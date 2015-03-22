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