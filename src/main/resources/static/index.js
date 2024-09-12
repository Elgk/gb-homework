angular.module('app', []).controller('indexController', function ($scope, $http) {

    const contextPath = 'http://localhost:8189/appt/api/v1';

    $scope.modifyProduct = function () {
// функция не работает, не формируется тело запроса, body request, из p
        $http.put(contextPath +  '/products', $scope.p)
            .then(function (resp) {
                $scope.fillTable()
            });
    };

    $scope.saveProduct = function () {
        $http.post(contextPath + '/products', $scope.NewProduct)
            .then(function (resp) {
                $scope.fillTable()
            });
    };

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params:{
                namePart: $scope.filter ? $scope.filter.name_part : null,
                min:      $scope.filter ? $scope.filter.min_price : null,
                max:      $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (resp){
            $scope.Products = resp.data.content; // т.к. возвращается pageable object {"content":[{"id":1,"name":"milk","price":56},....}
        });
    };

    $scope.removeProduct = function (id){
        $http.delete(contextPath + '/products/' + id)
            .then(function (resp) {
                $scope.fillTable()
            });
    };

    $scope.fillTable()

});