(function(angular) {
  'use strict';

  function HttpService($http, $log) {
    var ctrl = this;

    ctrl.$http = $http;
    ctrl.$log = $log;

    ctrl.httpMethod = function(method) {
      return function requestUrl(url, ...args) {
        return ctrl.$http[method](ctrl.apiUrl + url, ...args)
          .then(response => {
            return response.data;
          })
          .catch(error => {
            ctrl.$log.error('HTTP ERROR STATUS:', error.status);

            ctrl.$log.error('HTTP ERROR:', error);
            throw error.data;
          });
      };
    };

    ctrl.get = ctrl.httpMethod('get');
    ctrl.post = ctrl.httpMethod('post');
    ctrl.put = ctrl.httpMethod('put');
    ctrl.patch = ctrl.httpMethod('patch');
    ctrl.delete = ctrl.httpMethod('delete');

    ctrl.apiUrl = 'http://localhost:8080/springrest';

    ctrl.$log.log(`[HttpService] api url points to '${ctrl.apiUrl}'`);
  }

  angular
    .module('app')
    .service('httpService', HttpService);
})(window.angular);
