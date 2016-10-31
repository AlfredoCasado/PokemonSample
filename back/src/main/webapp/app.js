(function(angular) {
  'use strict';

  function PokemonList($http) {
    var ctrl = this;

    ctrl.$onInit = function() {
      console.log("Init ok");
    }
  }

  angular.module('app', [])
    .component('pokemonList', {
      templateUrl: 'pokemonList.html',
      controller: PokemonList
    });
})(window.angular);
