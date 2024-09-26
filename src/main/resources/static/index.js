angular.module('app', []).controller('indexController', function ($scope, $http) {

    const contextPath = 'http://localhost:8189/app';

    $scope.page = 1;
    $scope.min = 1;
    $scope.max = 10000;

    $scope.changePrice = function (id, newPrice) {
        $http.get(contextPath +  '/products/'+id + '/' + newPrice)
            .then(function (resp) {
                $scope.fillTable();
            });
    };

    $scope.saveProduct = function () {
        $http.post(contextPath + '/products', $scope.NewProduct)
            .then(function (resp) {
                $scope.fillTable();
            });
    };

    $scope.fillTable = function () {
      /*  $http.get(contextPath + '/products')
            .then(function (resp) {
                $scope.Products = resp.data
            });*/
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                page: $scope.page,
                min: $scope.min,
                max: $scope.max
            }
            }).then(function (resp){
              //  console.log(resp.data);
                $scope.Products = resp.data;

        });
    };


    $scope.removeProduct = function (id){
        $http.delete(contextPath + '/products/'+id)
            .then(function (resp) {
                $scope.fillTable();
            });
    }

    $scope.fillTable();

});