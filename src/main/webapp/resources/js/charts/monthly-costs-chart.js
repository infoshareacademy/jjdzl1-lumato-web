var rootURL = restApiContextPath;

function showMonthlyCostChart(uid, ut) {
    getMonthlyCosts(uid, ut);
}

function getMonthlyCosts(uid, ut) {
    console.log('getMonthlyCosts was invoked');
    document.getElementById("chartContainer").html = "<h1 class='text-light'>Loading...</h1>"
    $.ajax({
        type: 'GET',
        beforeSend: function(request) {
            request.setRequestHeader("UID", uid);
            request.setRequestHeader("FT", ut);
        },
        url: rootURL + "/charts/costs-per-month?limit=10&user_id="+uid,
        dataType: "json", // data type of response
        success: defineChart,
        error: function() {
            document.getElementById("chartContainer").html = "Could not load chart."
        }
    });
}

function defineChart(data) {
    var costsArray = data == null ? [] : (data instanceof Array ? data : [data]);
    console.log(costsArray);

    var totalCosts = [];
    var extraCosts = [];
    var fuelCosts = [];
    for (var index in costsArray) {
        totalCosts.push({
            y: costsArray[index].monthTotalCosts,
            label: costsArray[index].year + "-" + costsArray[index].month
        });
        extraCosts.push({
            y: costsArray[index].monthExtraCosts,
            label: costsArray[index].year + "-" + costsArray[index].month
        });
        fuelCosts.push({
            y: costsArray[index].monthFuelCosts,
            label: costsArray[index].year + "-" + costsArray[index].month
        });
    }


    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        title:{
            text: "My Car Monthly Costs"
        },
        axisY: {
            title: "Costs [PLN]"
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
            dataPoints: totalCosts
        },
        {
            type: "bar",
            showInLegend: true,
            name: "Fueling costs",
            color: "black",
            dataPoints: fuelCosts
        },
        {
            type: "bar",
            showInLegend: true,
            name: "Extra costs",
            color: "grey",
            dataPoints: extraCosts
        }]
    });
    chart.render();

    totalCosts = [];
    extraCosts = [];
    fuelCosts = [];
}

function toolTipFormatter(e) {
    var str = "";
    var str2 ;
    for (var i = 0; i < e.entries.length; i++){
        var str1 = "<span style= \"color:"+e.entries[i].dataSeries.color + "\">" + e.entries[i].dataSeries.name + "</span>: <strong>"+  e.entries[i].dataPoint.y + "</strong> <br/>" ;
        str = str.concat(str1);
    }
    str2 = "<strong>" + e.entries[0].dataPoint.label + "</strong> <br/>";
    return (str2.concat(str));
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