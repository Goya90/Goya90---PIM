<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Product</title>
    </head>
    <link rel="stylesheet" href="EditProduct.css">
    <%@ include file = "../header.jsp" %>
    <body>
        <br>
        <br>
        <form action="FrontController" method="POST">

            <c:forEach var="selected" items="${selected}">
                <div class="productidF">
                    Product ID: <br />
                    <input type="number" name="ProductId" value="${selected.getId()}" readonly style="color:#888;"/><br>
                </div>
                <br>
                <div class="productDescriptionF">
                    Product Description: <br />
                    <textarea class="proDescBox" name="ProductDescription" readonly>${selected.getDescription()}</textarea><br>
                </div>
                <br>
                <div class="companyNameF">
                    Company Name:
                    <input type="text" name="CompanyName" value="${selected.getCompanyName()}" readonly/><br>
                </div>
                <br>
                <div class="productNameF">
                    Product Name: <br />
                    <input type="text" name="ProductName" value="${selected.getName()}" readonly/><br>
                </div>
                <br>
                <div class="pictureNameF">
                    Picture: <br />
                    <img src="${selected.getPictureName()}" border="0" height=210px><br>
                </div>
                <br>
                <div class="productNameDescriptionS">
                    Product Name Description: <br />
                    <textarea class="nameDescBox" type="text" name="ProductNameDescription" readonly>${selected.getNameDescription()}</textarea><br>
                </div>
                <br>
                <div class="priceF">
                    Price: <br />
                    <input type="number" min="0" step="0.01" name="Price" value="${selected.getPrice()}" onkeydown="return event.keyCode !== 69" step=".01" readonly/><br>
                </div>
                <br>
                <div class="quantityF">
                    Quantity: <br />
                    <input type="number" min="0" name="Quantity" value="${selected.getQty()}" onkeydown="return event.keyCode !== 69" step="" readonly/><br>
                </div>
                <div class="categories">
                    <p>Main Category</p>
                    <input type="text" value="${selected.getMainCategory()}" readonly/><br>

                    <p>Minor Category</p>
                    <input type="text" value="${selected.getMinorCategory()}" readonly/><br>
                    <br>
                    <input class="none" type="text" size="0" readonly/>
                </div>
            </c:forEach>
    </form>
    <%-- <form>
        <table width = "50%" border = "1" align = "center" class="table">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Name Description</td>
                    <td>Description</td>
                    <td>Company Name</td>
                    <td>Price</td>
                    <td>Qty</td>
    <%--<td>Picture Name</td>--%>
    <%--<td>Published Status</td>
    <td>Minor Category</td>
    <td>Main Category</td>
    <td>Edit</td>
</tr>
</thead>
<tbody>
<c:forEach var="selected" items="${selected}">
    <tr id="test">
        <td><c:out value="${selected.getId()}" /></td>
        <td><c:out value="${selected.getName()}" /></td>
        <td><c:out value="${selected.getNameDescription()}" /></td>
        <td><c:out value="${selected.getDescription()}" /></td>
        <td><c:out value="${selected.getCompanyName()}" /></td>
        <td><c:out value="${selected.getPrice()}" /></td>
        <td><c:out value="${selected.getQty()}" /></td>
        <td><c:out value="${selected.getPictureName()}" /></td>
    <%--<td><img src="${selected.getPictureName()}" border="0" with=70px height=70px></td>--%>
    <%--<td><c:out value="${selected.getPublishedStatus()}" /></td>
    <td><c:out value="${selected.getMinorCategory()}" /></td>
    <td><c:out value="${selected.getMainCategory()}" /></td>
</tr>
</c:forEach>
</tbody>
</table>
</form> --%>
</body>
</html>
