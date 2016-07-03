/**
 * Created by rklimemnko on 27.05.2016.
 */
$(document).ready(function() {

    var chartData;
    var graph;

    function loadUserData() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8081/user/get',
            async: false,
            success: function (user) {
                generateWalletTable(user)
            }
        });
    }

    function loadChartData(period)  {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/'+period,
            async: false,
            success: function (data) {
                chartData = data;
            }
        });
    }

    function setUpChart(chartData, currency) {
        var dataArray = [];
        chartData.forEach(function (item) {
            dataArray.push([new Date(item.date), item[currency].price[userCurrency]]);
        });
        graph = new Dygraph(
            document.getElementById("userChart"), dataArray,
            {labels: ["Date", currency.toUpperCase()]}
        );
        updateTable(chartData, currency);
    }

    function updateChart(chartData, currency) {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/last',
            async: false,
            success: function (data) {
                var notifications = [];
                notificationRules.forEach(function (item) {
                    var currencyName = item.currency.$name;
                    notifications.push([{'currency' : currencyName}, {'value':parseFloat(data[currencyName].price[userCurrency])}]);
                   // if(parseFloat(data[item.currency.$name].price[userCurrency]) <
                  //      parseFloat(document.getElementById(item.currency.$name+"NotificationRule").value)) {

                 //   }
                });
             /*   $.ajax({
                    type: 'POST',
                    url: 'http://localhost:8081/user/updateNotifications',
                    dataType: 'json',
                    contentType: "application/json",
                    data: JSON.stringify({username: username,
                        notifications: notifications}),
                    success: function (user) {
                        updateNotificationRules(user.entity);
                        alert("Notification rules were successfully updated.");
                        $("#notificationRules").dialog("close");
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });*/
                var dataArray = [];
                chartData.push(data);
                chartData.forEach(function (item) {
                    dataArray.push([new Date(item.date), item[currency].price[userCurrency]]);
                });
                graph.updateOptions({"file": dataArray,labels: ["Date", currency.toUpperCase()]});
                updateTable(chartData, currency);
            }
        });
    }

    function updateTable(chartData, currency) {
        var dailyHigh = Number.MIN_VALUE;
        var dailyLow = Number.MAX_VALUE;
        chartData.forEach(function (item) {
            if(item[currency].price[userCurrency] > dailyHigh) dailyHigh = item[currency].price[userCurrency];
            if(item[currency].price[userCurrency] < dailyLow) dailyLow = item[currency].price[userCurrency];
        });
        $("#periodLastPrice").html(chartData[chartData.length - 1][currency].price[userCurrency]);
        var dailyChange = chartData[chartData.length - 1][currency].price[userCurrency] - chartData[0][currency].price[userCurrency];
        $("#periodDailyChange").html(dailyChange);
        $("#periodDailyHigh").html(dailyHigh);
        $("#periodDailyLow").html(dailyLow);

        $("#periodDailyChangeLabel").html($("#period").val() + " change");
        $("#periodDailyHighLabel").html($("#period").val() + " max");
        $("#periodDailyLowLabel").html($("#period").val() + " min");
    }

    function generateWalletTable(user) {
        var walletTable = "<p class='table__name'>Personal wallet</p>";
        walletTable += "<table border='1' class='info'>";
        user.wallet.accounts.forEach(function (item) {
            walletTable += "<tr class='info__data'>";
            walletTable += "<td>" + item.currency + "</td>";
            walletTable += "<td>" + item.amount + "</td>";
            walletTable += "</tr>";
        });
        walletTable += "</table>";
        $("#wallet").html(walletTable);
    }

    $.when(loadChartData("day")).then(function() {

        setUpChart(chartData,$("#currency").val());
        loadUserData();

        setInterval(function(){
            updateChart(chartData, $("#currency").val());
            loadUserData();
        }, 1000);

        $("#currency").on("change", function() {
            updateChart(chartData,$(this).val());
        });

        $("#period").on("change", function() {
            loadChartData($(this).val());
        });
    });

    $("#profileButton").on("click", function() {
        $("#profile").dialog();
    });

    function toggleEnableNotificationRulesButton() {
        if($("#enableNotification").prop('checked')) {
            $("#notificationRulesButton").css("display","block");
        } else {
            $("#notificationRulesButton").css("display","none");
        }
    }

    function updateNotificationRules(user) {
        user.notificationRules.forEach(function (item) {
            $("#"+item.currency+"NotificationRule").val(item.price);
        });
    }

    $("#notificationRulesButton").on("click", function() {
        $("#notificationRules").dialog();
    });

    $("#notificationRulesSaveButton").on("click", function() {
        var notificationRules = [{"currency":"btc", "price":$("#btcNotificationRule").val()},
            {"currency":"eth","price":$("#ethNotificationRule").val()}];
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8081/user/updateNotificationRules',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({username: username,
                notificationRules: notificationRules}),
            success: function (user) {
                updateNotificationRules(user.entity);
                alert("Notification rules were successfully updated.");
                $("#notificationRules").dialog("close");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            }
        });
    });

    toggleEnableNotificationRulesButton();

    $("#profileSaveButton").on("click", function() {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8081/user/updateProfile',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({username: username,
                firstName: $("#firstName").val(),
                lastName: $("#lastName").val(),
                profession: $("#profession").val(),
                userCurrency: $("#userCurrency").val(),
                enableNotification: $("#enableNotification").prop('checked')}),
            success: function (data) {
                toggleEnableNotificationRulesButton();
                $("#firstNamePlaceholder").text($("#firstName").val());
                $("#lastNamePlaceholder").text($("#lastName").val());
                $("#professionPlaceholder").text($("#profession").val());
                userCurrency = $("#userCurrency").val();
                alert("User was successfully updated.");
                $("#profile").dialog("close");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status);
                alert(thrownError);
            }
        });
    });

    $("#userImageButton").on("click", function() {
        $("#userImage").dialog();
    });

});