(function(angular) {
  'use strict';

  function MainController($http, httpService) {
    var ctrl = this;
    ctrl.$http = $http;
    ctrl.httpService = httpService;

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

    ctrl.view = function(id) {
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
      ctrl.load();
    };

    ctrl.create = function(pokemon) {
      console.log("up:", pokemon);

      ctrl.httpService.post('/pokemons', pokemon)
        .then(function successCallback(response) {
          console.log('ok');
          ctrl.showNew = false;
          ctrl.load();
        })
        .catch(function errorCallback(response) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });

    }

    ctrl.delete = function(pokemon) {

      ctrl.$http.delete(ctrl.root + '/pokemons/' + pokemon.id)
        .then(function successCallback(response) {
          console.log('ok');
        }, function errorCallback(response) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.

          console.error(response);
          ctrl.load();
        });
    }
  }


  function NewController() {
    var ctrl = this;

    ctrl.create = function(pokemon) {
      console.log(pokemon);

      ctrl.onCreate({pokemon: pokemon});
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
        onView: '&',
        onDelete: '&'
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
      bindings: {
        onCreate: '&'
      },
      controller: NewController
    })
  ;
})(window.angular);
