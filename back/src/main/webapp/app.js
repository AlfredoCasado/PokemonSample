(function(angular) {
  'use strict';

  function MainController($http) {
    var ctrl = this;
    ctrl.$http = $http;

    ctrl.list = ['Loading...'];
    ctrl.root = 'http://localhost:8080/springrest';
    ctrl.showDetail = false;
    ctrl.showNew = false;

    ctrl.$onInit = function() {
      ctrl.load();
    };

    ctrl.load = function() {
      ctrl.$http.get(ctrl.root + '/pokemons')
        .then(function successCallback(response) {
          ctrl.list = response.data;
        }, function errorCallback(response) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
    };

    ctrl.viewPokemon = function(id) {
      ctrl.$http.get(ctrl.root + '/pokemons/' + id)
        .then(function successCallback(response) {
          ctrl.showDetail = true;
          ctrl.pokemon = response.data;
        }, function errorCallback(response) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });
    };

    ctrl.showList = function() {
      ctrl.showDetail = false;
    };
  }


  function NewController() {
    var ctrl = this;

    ctrl.create = function(pokemon) {
      console.log(pokemon);
    };
  }

  angular.module('app', [])
    .component('main', {
      templateUrl: 'main.html',
      controller: MainController
    })
    .component('pokemonList', {
      templateUrl: 'pokemonList.html',
      bindings: {
        list: '<',
        onView: '&'
      }
    })
    .component('pokemonDetail', {
      templateUrl: 'pokemonDetail.html',
      bindings: {
        pokemon: '<'
      }
    })
    .component('pokemonNew', {
      templateUrl: 'pokemonNew.html',
      controller: NewController
    })
  ;
})(window.angular);
