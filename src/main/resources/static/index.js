angular.module('app', []).controller('indexController', function ($scope, $http) {

    const contextPath = 'http://localhost:8189';

    $scope.modifyProduct = function () {
        $http.put(contextPath + + '/product', $scope.p)
            .then(function (resp) {
                $scope.fillTable()
            });
    };

    $scope.saveProduct = function () {
        $http.post(contextPath + '/product', $scope.NewProduct)
            .then(function (resp) {
                $scope.fillTable()
            });
    };

    $scope.fillTable = function () {
        $http.get(contextPath + '/product')
            .then(function (resp) {
                $scope.Products = resp.data
            })
    };

    $scope.removeProduct = function (id){
        $http.delete(contextPath + '/product/'+id)
            .then(function (resp) {
                $scope.fillTable()
            })
    }

    $scope.fillTable()

});