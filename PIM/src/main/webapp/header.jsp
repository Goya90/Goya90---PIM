<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="header.css">
    </head>
    <header>
        <div class="container">
            <img src="https://inco.dk/app/images/inco/logo_small.png" alt="logo" class="logo">
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="#">Products</a>
                        <ul>
                            <li><a href="http://localhost:8080/PIM/FrontController?cmd=gotoAddProduct">Add Products</a></li>
                            <li><a href="http://localhost:8080/PIM/FrontController?cmd=gotoEditProduct">Edit Products</a></li>
                            <li><a href="http://localhost:8080/PIM/FrontController?cmd=gotoDeleteProduct">Remove Products</a></li>
                            <li><a href="http://localhost:8080/PIM/FrontController?cmd=gotoViewAllProducts">View Products</a></li>
                        </ul>
                    </li>


                    <li><a href="#">Categories</a>
                        <ul>
                            <li><a href="http://localhost:8080/PIM/FrontController?cmd=addCategory">Add Categories</a></li>
                            <li><a href="http://localhost:8080/PIM/FrontController?cmd=editCategory">Edit Categories</a></li>
                            <li><a href="http://localhost:8080/PIM/FrontController?cmd=gotoDeleteMainCategory">Remove Categories</a></li>
                        </ul>
                    <li><a href="#">Upload or Download files</a>
                        <ul>
                            <li><a href="http://localhost:8080/PIM/FrontController?cmd=gotoUploadFile">Upload file or pictures</a></li>
                            <li><a href="http://localhost:8080/PIM/FrontController?cmd=gotoDownloadFile">Download products</a></li>
                        </ul>
                    <li><a href="#">Contact</a></li>
                </ul>
            </nav>
        </div>

    </header>
</html>
