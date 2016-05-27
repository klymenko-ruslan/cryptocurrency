/**
 * Created by rklimemnko on 27.05.2016.
 */
$(document).ready(function() {
    var chartData;
    var graph;
    function loadData()  {
        return $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/value',
            async: false,
            success: function (data) {
                chartData = data;
            }
        });
    }

    function setUpChartForCurrency(chartData, currency) {
        var dataArray = [];
        chartData.forEach(function (item) {
            dataArray.push([new Date(item.date), item[currency].price.usd]);
        });
        graph = new Dygraph(
            document.getElementById("chart"), dataArray,
            {labels: ["Date", currency.toUpperCase()]}
        );
    }
    function updateChartForCurrency(chartData, currency) {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/last',
            async: false,
            success: function (data) {
                var dataArray = [];
                chartData.forEach(function (item) {
                    dataArray.push([new Date(item.date), item[currency].price.usd]);
                });
                dataArray.push([new Date(data.date), data[currency].price.usd]);
                graph.updateOptions({'file': dataArray});
            }
        });
    }
    $.when(loadData()).then(function() {
        setUpChartForCurrency(chartData,$("#currency").val());
        setInterval(function(){ updateChartForCurrency(chartData, $("#currency").val()); }, 30000);

        $("#currency").on("change", function() {
            updateChartForCurrency(chartData,$(this).val());
        });
    });
});