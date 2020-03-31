<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add product</title>
    </head>
    <link rel="stylesheet" href="AddProduct.css"> 
    <%@ include file = "../header.jsp" %>
    <body>

        <h1>Add a product</h1>
        <% if (session.getAttribute("returnproductvalue") == null) {
            } else if (session.getAttribute("returnproductvalue").toString().equals("productadded")) { %>
        <h2>Product created!</h2>
        <%} else if (session.getAttribute("returnproductvalue").toString().equals("alreadyexists")) {%>
        <h2>Unable to execute request: Product with this ID already exists.</h2>
        <%} else if (session.getAttribute("returnproductvalue").toString().equals("something went wrong")) {%>
        <h2> Something went wrong. Please contact IT .</h2>
            <% } else {
                }%>

        <form action="FrontController" name="addProduct" method="POST">
 
            <input type="hidden" name="cmd" value="addProduct" required />
            <div class="backgroundColor">
            <ul class="nameAndInput">
                <li class="form-row">
                    <label for="productid">Add Product ID:</label>
                    <input type="number" min="1" id="productid" name="ProductId" value="" required onkeydown="return event.keyCode !== 69" step=""/>
                </li>
                <li class="form-row">
                    <label for="productname">Add Product Name:</label>
                    <input type="text" id="productname" name="ProductName" value="" required />
                </li>
                <li class="form-row">
                    <label for="productnamedesc">Add Product Name Description:</label>
                    <input type="text" id="productnamedesc" name="ProductNameDescription" value="" required />
                </li>
                <li class="form-row">
                    <label for="productdesc">Add Product Description:</label>
                    <input type="text" id="productdesc" name="ProductDescription" value="" />
                </li>
                <li class="form-row">
                    <label for="companyname">Add Company Name:</label>
                    <input type="text" id="companyname" name="CompanyName" value="" required />
                </li>
                <li class="form-row">
                    <label for="price">Add Price:</label>
                    <input type="number" id="price" min="0" step="0.01" name="Price" value="" required onkeydown="return event.keyCode !== 69"/>
                </li>
                <li class="form-row">
                    <label for="quantity">Add Quantity:</label>
                    <input type="number" id="quantity" min="0" name="Quantity" value="" required onkeydown="return event.keyCode !== 69"/>
                </li>
                <li class="form-row">
                    <label for="quantity">Add Picture Name:</label>
                    <input type="text" name="PictureName" value="" required />
            </ul>
            
                <div class="mainandminorbinder">
                    Choose Main category:
            <% if (session.getAttribute("mainCategories") != null) { %>
            <select id="maincategory" name="maincategory">
                <c:forEach var="mainCategories" items="${mainCategories}">
                    <option>${mainCategories.getName()}</option>
                </c:forEach>
            </select>
            <% } else {%>
            <p>There are no main categories!</p>
            <% }%>
            
                <div class="spaceinbetween">
                    </div>
            Choose Minor category:
            <% if (session.getAttribute("minorCategories") != null) { %>
            <select id="minorcategory" name="minorcategory">
                <c:forEach var="minorCategories" items="${minorCategories}">
                    <option>${minorCategories.getName()}</option>
                </c:forEach>
            </select>
            </div>
            <br>
            <br>
            </div>
            <div class="buttonAlign">
            <input class="button" type="submit" value="Submit" />
            </div>
            <% } else {%>
            <p>There are no minor categories!</p>
            <% }%>
        </form>
    </body>
</html>
