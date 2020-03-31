<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Category</title>
    </head>
    <%@ include file = "../header.jsp" %>
    <link rel="stylesheet" href="Category.css">
    <body>
        <h3>Add a new category:</h3>
        <div class="fullbinder">
            <form name="addMainCategory" action="FrontController" method="POST">
                <input type="hidden" name="cmd" value="addMainCategory">
                <div class="binder">
                    <div class="enternewtext">
                    Name of new main category:
                    <br>
                    <input type="text" name="MainName" required>
                    </div>
                    <br>
                    <input class="apply" type="submit" value="Submit">
                </div>
            </form>
            <br>
            <form name="addMinorCategory" action="FrontController" method="POST">
                <input type="hidden" name="cmd" value="addMinorCategory">
                <div class="binder2">
                    <div class="enternewtext">
                    Name of new minor category:
                    <br>
                    <input type="text" name="MinorName" required>
                    </div>
                    <br>
                    <input class="apply" type="submit" value="Submit">
                </div>
            </form>
            <br>
        </div>
        <div class="responserequest">
            <% if (session.getAttribute("mainResponse").equals("Category added!")) { %>
            <h3>Main category has been added!</h3>
            <% } else if (session.getAttribute("mainResponse").equals("Category already exists!")) { %>
            <h3>Error: That main category already exists!</h3>
            <% } else {
                        } %>
            <% if (session.getAttribute("minorResponse").equals("Category added!")) { %>
            <h3>Minor category has been added!</h3>
            <% } else if (session.getAttribute("minorResponse").equals("Category already exists!")) { %>
            <h3>Error: That minor category already exists!</h3>
            <% } else {
                        }%>
        </div>
    </body>
</html>
