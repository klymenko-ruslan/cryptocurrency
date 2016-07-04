/**
 * Created by rklimemnko on 27.05.2016.
 */
$(document).ready(function() {

    function playSound() {
        return $(
            '<audio autoplay="autoplay" style="display:none;">'
            + '<source src="' + arguments[0] + '.mp3" />'
            + '<source src="' + arguments[0] + '.ogg" />'
            + '<embed src="' + arguments[0] + '.mp3" hidden="true" autostart="true" loop="false" class="playSound" />'
            + '</audio>'
        ).appendTo('body');
    }

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

    function getNotificationTemplate(message, color, numberOfNotification) {
        var notificationId = "notification"+numberOfNotification;
        //
        return  "<ul class='menu' id='"+notificationId+"' >" +
                    "<li>" +
                        "<a href='#' >" +
                            "<i class='fa fa-shopping-cart text-"+color+"'></i> " + message +
                            "<i class='fa fa-times text-red' aria-hidden='true' onclick=\"if(confirm('Do you want to remove notification?')){$('#"+notificationId+"').remove();$('#numberOfNotifications').text(parseInt($('#numberOfNotifications').text()) - 1);}\"></i>" +
                        "</a>" +
                    "</li>" +
                "</ul>";
    }

    function getBuyMessage(currency) {
        return getFormattedDate() + ": Time to buy " + currency;
    }

    function getSellMessage(currency) {
        return getFormattedDate() + ": Time to sell " + currency;
    }

    function getFormattedDate() {
        var date = new Date();
        return formattedDate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " +  date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    }

    var buyNotified = {};
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
                    if(parseFloat(data[item.currency.$name].price[userCurrency]) <
                        parseFloat(document.getElementById(item.currency.$name+"NotificationRule").value)) {
                        if(!buyNotified[item.currency.$name]) {
                            buyNotified[item.currency.$name] = true;
                            var numberOfNotification = parseInt($("#numberOfNotifications").text()) + 1;
                            $("#numberOfNotifications").text(numberOfNotification);
                            $("#notificationList").append(getNotificationTemplate(getBuyMessage(item.currency.$name),"green", numberOfNotification));
                            playSound('sounds/buy');
                        }
                    } else {
                        if(typeof buyNotified[item.currency.$name] === 'undefined'
                               || buyNotified[item.currency.$name]) {
                            buyNotified[item.currency.$name] = false;
                            var numberOfNotification = parseInt($("#numberOfNotifications").text()) + 1;
                            $("#numberOfNotifications").text(numberOfNotification);
                            $("#notificationList").append(getNotificationTemplate(getSellMessage(item.currency.$name),"red",numberOfNotification));
                            playSound('sounds/sell');
                        }
                    }
                });
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
                $(".userCurrency").text(userCurrency);
                buyNotified = {};
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