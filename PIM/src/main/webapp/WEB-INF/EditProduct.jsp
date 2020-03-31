<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
    </head>
    <link rel="stylesheet" href="EditProduct.css">
    <%@ include file = "../header.jsp" %>
    <body>

        <h1>Edit product</h1>

        <form action="FrontController" method="POST">
            <input type="hidden" name="cmd" value="editProductSearch" />
            <div class="searchContainer">
                <p>Search by Product ID:</p>
                <input class="searchInput" type="number" min="1" name="ProductID" value="" required onkeydown="return event.keyCode !== 69"/>
                <input class="search" type="submit" value="Search" />
            </div>
        </form>

        <div class="editor">
            <div class="responsevalue">
            <% if (session.getAttribute("resulthits").equals("empty")) { %>
            <p>No product found with this id.</p>
            <%}else if (session.getAttribute("resulthits").equals("gotoempty")){
        } else {%>
            <% if (session.getAttribute("productupdated").equals("Productupdated")) { %>
            <h3>Product has been updated</h3>
            </div>
            <%} else { %>
            <form action="FrontController" name="editProduct" method="POST">

                <input type="hidden" name="cmd" value="editProduct" /><br>
                <c:forEach var="productarray" items="${productarray}">
                    <div class="productidF">
                    Product ID: <br />
                    <input type="number" name="ProductId" value="${productarray.getId()}" readonly style="color:#888;"/><br>
                    </div>
                    <br>
                    <div class="productDescriptionF">
                    Edit Product Description: <br />
                    <textarea class="proDescBox" name="ProductDescription">${productarray.getDescription()}</textarea><br>
                    </div>
                    <br>
                    <div class="companyNameF">
                    Edit Company Name:
                    <input type="text" name="CompanyName" value="${productarray.getCompanyName()}" required/><br>
                    </div>
                    <br>
                    <div class="productNameF">
                    Edit Product Name: <br />
                    <input type="text" name="ProductName" value="${productarray.getName()}" required/><br>
                    </div>
                    <br>
                    <div class="pictureNameF">
                    Edit Picture Name: <br />
                    <input type="text" name="PictureName" value="${productarray.getPictureName()}" required/><br>
                    </div>
                    <br>
                    <div class="productNameDescriptionF">
                    Edit Product Name Description: <br />
                    <textarea class="nameDescBox" type="text" name="ProductNameDescription" required>${productarray.getNameDescription()}</textarea><br>
                    </div>
                    <br>
                    <div class="priceF">
                    Edit Price: <br />
                    <input type="number" min="0" step="0.01" name="Price" value="${productarray.getPrice()}" onkeydown="return event.keyCode !== 69" step=".01" required/><br>
                    </div>
                    <br>
                    <div class="quantityF">
                    Edit Quantity: 
                    <br>
                    <input type="number" min="0" name="Quantity" value="${productarray.getQty()}" onkeydown="return event.keyCode !== 69" step="" required/><br>
                    </div>
                    
                </c:forEach>
        </div>
        <div class="categories">
                <p>Main Category</p>
                <select id="maincategory" name="maincategory">
                    <option>${currentmain}</option>
                    <c:forEach var="maincategoriesarray" items="${maincategoriesarray}">
                        <option>${maincategoriesarray.getName()}</option>
                    </c:forEach>
                </select>

                <p>Minor Category</p>
                <select id="minorcategory" name="minorcategory">
                    <option>${currentminor}</option>
                    <c:forEach var="minorcategoriesarray" items="${minorcategoriesarray}">
                        <option>${minorcategoriesarray.getName()}</option>
                    </c:forEach>
                </select>
                <br> <br>
</div>
                <input class="apply" type="submit" value="Apply" />
                <br><br>
        
            </form>
            <%}%>
            <%}%>
    </body>
</html>
