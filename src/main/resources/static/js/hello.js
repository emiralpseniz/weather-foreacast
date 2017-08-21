angular.module('forecast', [])
    .controller('forecastController', function ($scope, $http) {

        $scope.searchUnit = 'metric';

        $scope.getForecast = function() {

            if ($scope.searchCity != null && $scope.searchCity !== '') {
                var url = '/forecast/city/'+ $scope.searchCity;

                if ($scope.searchUnit !== '') {
                    url = url + '?unit=' + $scope.searchUnit;
                }

                $http.get(url).success(function(data){
                    $scope.data = data;
                    $scope.city = data.city;

                    if (data.unit) {
                        $scope.unit = data.unit.substring(0, 1);
                    } else {
                        $scope.unit = 'K';
                    }

                    $scope.lowestTemperatures = data.lowestTemperatures;
                    $scope.highestTemperatures = data.highestTemperatures;
                    $scope.forecasts = data.forecasts;

                    $scope.showForecast = true;
                }).error(function() {
                    $scope.showForecast = false;
                });
            }
        };
    });
