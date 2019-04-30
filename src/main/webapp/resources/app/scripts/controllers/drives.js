'use strict';


clientApp.controller('DrivesCtrl', function($scope, $resource, $routeParams, $http, $dialog) {
    $scope.folders = $resource('/drive/listFiles').get()
    $scope.uploading = false;

    $scope.createFolder = function() {
        $http.post('/drive/createFolder/'+ $scope.folderName).success(function() {
            alert("success");
            $scope.folders = $resource('/drive/listFiles').get();
        });
    };

    $scope.uploadFile = function() {
        $scope.uploading = true;
        $http.get('/drive/uploadFile').success(function() {
            alert("success");

            $scope.folders = $resource('/drive/listFiles').get();
            $scope.uploading = false;

        });
    };

    $scope.deleteFile = function(fileID) {

        $dialog.messageBox(null, 'Delete this file?', [{result:true, label: 'Yes', cssClass: 'btn-danger'}, {label: 'No'}])
            .open()
            .then(function(result){
                if(result) {
                    $http.get('/drive/deleteFile?fileID=' + fileID).success(function() {
                        $scope.folders = $resource('/drive/listFiles').get();
                        alert("success");
                    });
                }
            });
    };


});

