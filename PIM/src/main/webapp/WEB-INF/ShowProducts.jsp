<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Products</title>
    </head>
    <%@ include file = "../header.jsp" %>
    <script src="sorttable.js"></script>
    <script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
   <script src="ShowProducts.js"></script>
    <link rel="stylesheet" href="ShowProducts.css">
    <body>
        <br>
        <div class="binder">
        <h4> What do you want to search for?: </h4>
        <form action="FrontController">
            <select id="searchCriteria" name="searchCriteria">

                <option>ProductID</option>
                <option>Product Name</option>
                <option>Product Name Description</option>
                <option>Product Description</option>
                <option>Company Name</option>
                <option>Price</option>
                <option>Quantity</option>
                <option>Picture Name (associated with product)</option>
                <option>Published Status</option>
                <option>Minor Category</option>
                <option>Main Category</option>
            </select>
            <br><br>
            <input class="searchInput" size="19.8" type="text" name="searchInput" value="" required/>
            <input type="hidden" name="cmd" value="searchResults">
            <input class="search" type="submit" value="Search" />
        </form>
        </div>
        <br>
        <form action="FrontController">
            <input type="hidden" name="cmd" value="gotoBulkEditProducts">
            <div class ="scrollbutton">
            <input class="editButton" type="submit" value="Edit Selected Products">
            </div>
            <% if (session.getAttribute("errormsg").toString().equals("noinput")) { %>
            <div class="responserequest">
            <p>Please select at least one product to edit.</p>
            </div>
            <%} else if (session.getAttribute("errormsg").toString().equals("empty")) { %>
            <%}%>
            <table width = "50%" border = "1" align = "center" class="sortable">
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
                        <td>Edit</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="viewallproducts" items="${viewallproducts}">
                        <tr class="tr">
                            <td><a href="http://localhost:8080/PIM/FrontController?cmd=gotoViewSingleProduct&selected=${viewallproducts.getId()}">${viewallproducts.getId()}</a></td>
                            <td class="expan"><c:out value="${viewallproducts.getName()}" /></td>
                            <td class="expan"><c:out value="${viewallproducts.getNameDescription()}" /></td>
                            <td class="expan"><c:out value="${viewallproducts.getDescription()}" /></td>
                            <td class="expan"><c:out value="${viewallproducts.getCompanyName()}" /></td>
                            <td class="expan"><c:out value="${viewallproducts.getPrice()}" /></td>
                            <td class="expan"><c:out value="${viewallproducts.getQty()}" /></td>
                            <td><img src="${viewallproducts.getPictureName()}" border="0" with=70px height=70px></td>
                            <td><c:out value="${viewallproducts.getPublishedStatus()}" /></td>
                            <td class="expan"><c:out value="${viewallproducts.getMinorCategory()}" /></td>
                            <td class="expan"><c:out value="${viewallproducts.getMainCategory()}" /></td>
                            <td><input type="checkbox" name="selected" value="${viewallproducts.getId()}"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>
