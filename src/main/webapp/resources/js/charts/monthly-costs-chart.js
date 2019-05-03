window.onload = function () {

    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        title:{
            text: "My Car Costs"
        },
        axisY: {
            title: "Costs"
        },
        legend: {
            cursor:"pointer",
            itemclick : toggleDataSeries
        },
        toolTip: {
            shared: true,
            content: toolTipFormatter
        },
        data: [{
            type: "bar",
            showInLegend: true,
            name: "Total costs",
            color: "orange",
            dataPoints: [
                { y: 243, label: "2019-01" },
                { y: 236, label: "2019-02" },
                { y: 243, label: "2019-03" },
                { y: 273, label: "2019-04" },
                { y: 269, label: "2019-05" }
            ]
        },
            {
                type: "bar",
                showInLegend: true,
                name: "Fueling costs",
                color: "black",
                dataPoints: [
                    { y: 212, label: "2019-01" },
                    { y: 186, label: "2019-02" },
                    { y: 272, label: "2019-03" },
                    { y: 299, label: "2019-04" },
                    { y: 270, label: "2019-05" },
                ]
            },
            {
                type: "bar",
                showInLegend: true,
                name: "Extra costs",
                color: "grey",
                dataPoints: [
                    { y: 236, label: "2019-01" },
                    { y: 172, label: "2019-02" },
                    { y: 309, label: "2019-03" },
                    { y: 302, label: "2019-04" },
                    { y: 285, label: "2019-05" },
                ]
            }]
    });
    chart.render();

    function toolTipFormatter(e) {
        var str = "";
        var total = 0 ;
        var str3;
        var str2 ;
        for (var i = 0; i < e.entries.length; i++){
            var str1 = "<span style= \"color:"+e.entries[i].dataSeries.color + "\">" + e.entries[i].dataSeries.name + "</span>: <strong>"+  e.entries[i].dataPoint.y + "</strong> <br/>" ;
            total = e.entries[i].dataPoint.y + total;
            str = str.concat(str1);
        }
        str2 = "<strong>" + e.entries[0].dataPoint.label + "</strong> <br/>";
        str3 = "<span style = \"color:Tomato\">Total: </span><strong>" + total + "</strong><br/>";
        return (str2.concat(str)).concat(str3);
    }

    function toggleDataSeries(e) {
        if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
            e.dataSeries.visible = false;
        }
        else {
            e.dataSeries.visible = true;
        }
        chart.render();
    }

}