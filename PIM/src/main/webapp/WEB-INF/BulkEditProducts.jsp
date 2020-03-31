<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bulk Edit Products</title>
    </head>
    <%@ include file = "../header.jsp" %>
    <script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
    <script src="ShowProducts.js"></script>
    <link rel="stylesheet" href="ShowProducts.css">
    <script src="BulkEditProducts.js"></script>
    <link rel="stylesheet" href="BulkEditProducts.css">
    <body>
        <h1>Bulk Edit Multiple Products:</h1>
        <p class="chooseheader">Choose which attribute you want to edit for all selected products: </p>
        <form action="FrontController">
        <div class="wrapper">
        
        
            <div class="dropdownattributes">
            <select id="chosenAttribute" name="chosenAttribute" onchange="myFunction(event)">
                <option>Product Name</option>
                <option>Product Name Description</option>
                <option>Product Description</option>
                <option>Company Name</option>
                <option>Price</option>
                <option>Quantity</option>
                <option>Published Status</option>
                <option>Minor Category</option>
                <option>Main Category</option>
            </select>
            </div> 
            <div class="label">New input:</div>
            
            <div class="main">
            <select id="maincategories" name="maincategories" style="display:none;">
                <c:forEach var="maincategories" items="${maincategories}">
                <option>${maincategories.getName()}</option>
                </c:forEach>
            </select>
            </div>
            <div class="minor">
            <select id="minorcategories" name="minorcategories" style="display:none;">
                <c:forEach var="minorcategories" items="${minorcategories}">
                <option>${minorcategories.getName()}</option>
                </c:forEach>
            </select>
            </div>
            <input type="hidden" name="cmd" value="bulkEditProducts" />
            <div class="normalinputtest">
            <input type="text" id="normalinput" name="bulkEditProducts" value="" required/>
            </div> 
       </div>
       <div class="button-wrapper">
         <input class="button" type="submit" value="Apply Edit" />
       </div>
            <% if (session.getAttribute("callback").toString().equals("success")) { %>
            <div class="responserequest">
            <p>Edit successfully applied!</p>
            </div>
            <%} else if (session.getAttribute("callback").toString().equals("error")) { %>
            <div class="responserequest">
            <p>Error: Something went wrong. Please contact IT support.</p>
            </div>
            <%} else if (session.getAttribute("callback").toString().equals("empty")) {
                    }%>
            <br><br><br>
            <table width = "50%" border = "1" align = "center">
                <thead bgcolor="#C0C0C0">
                    <tr>
                        <td>ID</td>
                        <td>Name</td>
                        <td>Name Description</td>
                        <td>Description</td>
                        <td>Company Name</td>
                        <td>Price</td>
                        <td>Qty</td>
                        <td>Picture Name</td>
                        <td>Published Status</td>
                        <td>Minor Category</td>
                        <td>Main Category</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="selected" items="${selected}">
                        <tr class="tr">
                            <td><c:out value="${selected.getId()}" /></td>
                            <td><c:out value="${selected.getName()}" /></td>
                            <td><c:out value="${selected.getNameDescription()}" /></td>
                            <td><c:out value="${selected.getDescription()}" /></td>
                            <td><c:out value="${selected.getCompanyName()}" /></td>
                            <td><c:out value="${selected.getPrice()}" /></td>
                            <td><c:out value="${selected.getQty()}" /></td>
                            <td><c:out value="${selected.getPictureName()}" /></td>
                            <td><c:out value="${selected.getPublishedStatus()}" /></td>
                            <td><c:out value="${selected.getMinorCategory()}" /></td>
                            <td><c:out value="${selected.getMainCategory()}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>
