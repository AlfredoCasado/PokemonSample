(function(angular) {
  'use strict';

  function PokemonList($http) {
    var ctrl = this;
    ctrl.$http = $http;

    ctrl.list = ['Loading...'];
    ctrl.root = 'http://localhost:8080/springrest'

    ctrl.$onInit = function() {
      ctrl.load();
    }

    ctrl.load = function() {
      ctrl.$http.get(ctrl.root + '/pokemons')
        .then(function successCallback(response) {
          console.log(response);
          ctrl.list = response.data;
        }, function errorCallback(response) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
    }
  }

  angular.module('app', [])
    .component('pokemonList', {
      templateUrl: 'pokemonList.html',
      controller: PokemonList
    });
})(window.angular);
