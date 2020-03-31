<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Category</title>
    </head>
    <%@ include file = "../header.jsp" %>
    <link rel="stylesheet" href="Category.css">
    <body>
        <h3>Delete a main or a minor category:</h3>
        <div class="fullbinder">
            <div class="binder">
                    <h3>Delete a main category:</h3>
                    <form name="deleteMainCategory" action="FrontController" method="POST">
                        <input type="hidden" name="cmd" value="deleteMainCategory">
                        <% if (session.getAttribute("mainCategories") != null) { %>
                        <table width = "220px" border = "1" align = "center">
                            <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Select</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="Categories" items="${mainCategories}">
                                    <tr>
                                        <td><c:out value="${Categories.getName()}" /></td>
                                        <td><input type="radio" name="id" value="${Categories.getID()}" required></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <br>
                        <input class="apply2" type="submit" value="Submit">
                        <% } else {%>
                        <p>There are no main categories!</p>
                        <% }%>
                    </form>
                    </div>
                    <div class="binder2">
                    <h3>Delete a minor category:</h3>
                    <form name="deleteMinorCategory" action="FrontController" method="POST">
                        <input type="hidden" name="cmd" value="deleteMinorCategory">
                        <% if (session.getAttribute("minorCategories") != null) { %>
                        <table width = "220px" border = "1" align = "center">
                            <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Select</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="Categories" items="${minorCategories}">
                                    <tr>
                                        <td><c:out value="${Categories.getName()}" /></td>
                                        <td><input type="radio" name="id" value="${Categories.getID()}" required></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <br>
                        <input class="apply2" type="submit" value="Submit">
                        <% } else {%>
                        <p>There are no minor categories!</p>
                        <% }%>
                    </form>
                    </div>
                    </div>
    </body>
</html>
