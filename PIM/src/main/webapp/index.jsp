<!DOCTYPE HTML>
<html>
    <%@page import="persistence.DBFacade"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.google.gson.Gson"%>
    <%@ page import="com.google.gson.JsonObject"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="index.css">
    <%
        DBFacade db = new DBFacade();
        //Minor
        String minorvalue = db.chartMinorCategory("/db.properties");
        //Main
        String mainvalue = db.chartMainCategory("/db.properties");
        //PublishedStatusDataGraph
        String publishedStatus = db.chartPublishedStatusDiagram("/db.properties");
        //Count of all products
        String allProducts = db.getProductCountChart("/db.properties");
    %>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        <script src="index.js"></script>
        <script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
        <script type="text/javascript">
            $(window).ready (function(){ 
                createMinorAndMainCategoryGraph(<%out.print(minorvalue);%>, "Minor categories", "minorcategory");
                createMinorAndMainCategoryGraph(<%out.print(mainvalue);%>, "Main categories", "maincategory");
                createDiagramGraph(<%out.print(publishedStatus);%>, "Publish status of products", "publishedstatusData");
                createDiagramGraph(<%out.print(allProducts);%>, "Unique products in stock", "uniqueavailableproducts"); 
            });
        </script>
      
            </head>
            <body>
                <%@ include file = "header.jsp" %>
                <% if (minorvalue.equals("[]")) {
                    //do nothing
                } else {%>
                <div class="roughcontainer">
                    <div class="graph" id="minorcategory" style="height: 370px; width: 500px;"></div>
                    <div class="graph" id="maincategory" style="height: 370px; width: 500px;"></div>
                </div>
                <div class="secondrow">
                    <div class="diagram" id="publishedstatusData" style="height: 370px; width: 500px;"></div>
                    <div class="diagram" id="uniqueavailableproducts" style="height: 370px; width: 500px;"></div>
                </div>
                <% } %>
            </body>
        </html>                  