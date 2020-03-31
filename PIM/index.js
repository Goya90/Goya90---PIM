 <script type="text/javascript">
window.onload = function () {

                var chart = new CanvasJS.Chart("minorcategory", {
                    theme: "light2",
                    exportEnabled: true,
                    animationEnabled: true,
                    title: {
                        text: "Minor categories"
                    },
                    data: [{
                            type: "pie",
                            toolTipContent: "<b>{label}</b>: {y}%",
                            indexLabelFontSize: 16,
                            indexLabel: "{label} - {y}%",
                           dataPoints: <%out.print(minorvalue);%>
                        }]
                });
                chart.render();

                var chart2 = new CanvasJS.Chart("maincategory", {
                    theme: "light2",
                    exportEnabled: true,
                    animationEnabled: true,
                    title: {
                        text: "Main categories"
                    },
                    data: [{
                            type: "pie",
                            toolTipContent: "<b>{label}</b>: {y}%",
                            indexLabelFontSize: 16,
                            indexLabel: "{label} - {y}%",
                           dataPoints: <%out.print(mainvalue);%>
                        }]
                });
                chart2.render();

                var chart = new CanvasJS.Chart("publishedstatusData", {
                    animationEnabled: true,
                    exportEnabled: true,
                    title: {
                        text: "Publish status of products"
                    },
                    data: [{
                            type: "column", //change type to bar, line, area, pie, etc
                            //indexLabel: "{y}", //Shows y value on all Data Points
                            indexLabelFontColor: "#5A5757",
                           indexLabelPlacement: "outside" ,
                            dataPoints: <%out.print(publishedStatus);%>
                        }]
                });
                chart.render();
                
                var chart = new CanvasJS.Chart("uniqueavailableproducts", {
                    animationEnabled: true,
                    exportEnabled: true,
                    title: {
                        text: "Unique products in stock"
                    },
                    data: [{
                            type: "column", //change type to bar, line, area, pie, etc
                            //indexLabel: "{y}", //Shows y value on all Data Points
                            indexLabelFontColor: "#5A5757",
                           indexLabelPlacement: "outside" ,
                            dataPoints: <%out.print(allProducts);%>
                        }]
                });
                chart.render();
            };
            </script>