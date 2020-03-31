<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link rel="stylesheet" href="DeleteProduct.css">
    <%@ include file = "../header.jsp" %>
    <body>
      
       <h1>Delete product</h1>
        
        <form action="FrontController" name="deleteProduct" method="POST">
            
            <input type="hidden" name="cmd" value="deleteProduct" />
            <div class="productIDContainer">
             Delete a Product by Product ID: <br />
            <input type="number" name="ProductId" value="" required onkeydown="return event.keyCode !== 69" step=""/><br>
            </div>
            <div class="deleteAlign">
            <input class="delete" type="submit" value="Delete" />
            </div>
        </form>
          <% if (session.getAttribute("returndeleteproductvalue").toString().equals("empty")) {
                
            } else if (session.getAttribute("returndeleteproductvalue").toString().equals("deleteproduct")) { %>
            <p> Product has been deleted</p>                                  
            <%} else if (session.getAttribute("returndeleteproductvalue").toString().equals("deletealreadyexists")) {%>
            <p>Product ID doesn't exist, the product has not been deleted!</p>
            <%} else { %>
            <h1>Ups..</h1>
            <%}%>
    </body>
</html>
