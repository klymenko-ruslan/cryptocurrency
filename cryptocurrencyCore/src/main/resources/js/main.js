/**
 * Created by rklimemnko on 27.05.2016.
 */
$(document).ready(function() {
    function start()  {
        $.ajax({
            type: 'GET',
            url: 'value',
            success: function (data) {
                var dataArray = [];
                data.forEach(function (item) {
                    dataArray.push([new Date(item.date), item["eth"].price.usd]);
                });
                new Dygraph(
                    document.getElementById("graphdiv"), dataArray,
                    {labels: ["Date", "ETH"]}
                );
            }
        });
    }

    start();
});