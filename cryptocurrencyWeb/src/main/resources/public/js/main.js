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
            document.getElementById("graphdiv"), dataArray,
            {labels: ["Date", currency.toUpperCase()]}
        );
    }
    function updateChart() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/last',
            async: false,
            success: function (data) {
              graph.updateOptions({Date: new Date(data.date),ETH: data["eth"].price.usd});
            }
        });
    }
    $.when(loadData()).then(function() {
        setUpChartForCurrency(chartData,"eth");
        var a = 0;
       // while(a++ < 100000) {
        //    updateChart();
       // }
       // setInterval(function(){
      //      updateChart();
      //  }, 1000);
    });
});