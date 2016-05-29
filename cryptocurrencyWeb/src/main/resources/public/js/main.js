/**
 * Created by rklimemnko on 27.05.2016.
 */
$(document).ready(function() {

    var chartData;
    var graph;

    function loadData(period)  {
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
            dataArray.push([new Date(item.date), item[currency].price.usd]);
        });
        graph = new Dygraph(
            document.getElementById("chart"), dataArray,
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
                var dataArray = [];
                chartData.push(data);
                chartData.forEach(function (item) {
                    dataArray.push([new Date(item.date), item[currency].price.usd]);
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
            if(item[currency].price.usd > dailyHigh) dailyHigh = item[currency].price.usd;
            if(item[currency].price.usd < dailyLow) dailyLow = item[currency].price.usd;
        });
        $("#periodLastPrice").html(chartData[chartData.length - 1][currency].price.usd);
        var dailyChange = chartData[chartData.length - 1][currency].price.usd - chartData[0][currency].price.usd;
        $("#periodDailyChange").html(dailyChange);
        $("#periodDailyHigh").html(dailyHigh);
        $("#periodDailyLow").html(dailyLow);

        $("#periodDailyChangeLabel").html($("#period").val() + " change");
        $("#periodDailyHighLabel").html($("#period").val() + " change");
        $("#periodDailyLowLabel").html($("#period").val() + " change");
    }


    $.when(loadData("all")).then(function() {

        setUpChart(chartData,$("#currency").val());
        setInterval(function(){ updateChart(chartData, $("#currency").val()); }, 1000);

        $("#currency").on("change", function() {
            updateChart(chartData,$(this).val());
        });

        $("#period").on("change", function() {
            loadData($(this).val());
        });

    });
});