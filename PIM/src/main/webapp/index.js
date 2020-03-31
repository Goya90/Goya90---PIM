function createMinorAndMainCategoryGraph(data, text, charttype) {
  var chart = new CanvasJS.Chart(charttype, {
        
        theme: "light2",
        exportEnabled: true,
        animationEnabled: true,
        title: {
            text: text
        },
        data: [{
                type: "pie",
                toolTipContent: "<b>{label}</b>: {y}%",
                indexLabelFontSize: 16,
                indexLabel: "{label} - {y}%",
                dataPoints: data
            }]
    });
    chart.render();
}

function createDiagramGraph(data, text, charttype) {
  var chart = new CanvasJS.Chart(charttype, {
        theme: "light2",
        animationEnabled: true,
        exportEnabled: true,
        title: {
            text: text
        },
        data: [{
                type: "column", //change type to bar, line, area, pie, etc
                //indexLabel: "{y}", //Shows y value on all Data Points
                indexLabelFontColor: "#5A5757",
                indexLabelPlacement: "outside",
                dataPoints: data
            }]
    });
    chart.render();
}