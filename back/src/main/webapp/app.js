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
      ctrl.httpService.post('/pokemons', pokemon)
        .then(function successCallback(response) {
          ctrl.showNew = false;
          ctrl.load();
        })
        .catch(function errorCallback(response) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
        });

    }

    ctrl.delete = function(pokemon) {
      ctrl.httpService.delete('/pokemons/' + pokemon.id)
        .then(function successCallback(response) {
        })
        .catch(function errorCallback(response) {
        });
      ctrl.load();
    }

    ctrl.favorite = function(pokemon) {
      ctrl.httpService.put('/pokemons/' + pokemon.id + '/favorite')
        .then(function successCallback(response) {
          console.log('favorite edited');
          ctrl.load();
        })
        .catch(function errorCallback(response) {
        });
    }
  }


  function NewController() {
    var ctrl = this;

    ctrl.create = function(pokemon) {
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
        onDelete: '&',
        onFavorite: '&'
      },
      controller: function() {
        var ctrl = this;

        ctrl.favorite = function(pokemon) {
          ctrl.onFavorite({pokemon: pokemon});
        }
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
