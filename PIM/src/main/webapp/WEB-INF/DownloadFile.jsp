<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload File</title>
    </head>
    <%@ include file = "../header.jsp" %>
    <link rel="stylesheet" href="DownloadFile.css">
    <body>
        
        <h3>Download</h3>
        <div class="fullbinder">

            <div class="binder">
                <h3>Download products as a .json file</h3>
                <div class="img-wrapper">
                 <img class="lazyImgThumb" alt="" onerror="this.style.display='none'" style="max-width: 100%;  max-height: 100%;" src="https://s3.amazonaws.com/assets.mockflow.com/app/wireframepro/company/C2fbcf12f03a63b0f75732c6c95d3e61e/projects/M8688461e9c03aab1b425ee1a433896611574718915827/images/thumbnails/M1d0d95cad5cb527f9f9e0b2c5c67e4131526462421183">
                </div>
               <br>
                <form class="form-centered" action = "DownloadJson">
                    <input class="apply" type = "submit" value = "Download File" />
                </form>
            </div>

            <div class="binder2">
                <h3>Download products as a .xlsx file</h3>
                <div class="img-wrapper">
                <img class="lazyImgThumb" alt="" onerror="this.style.display='none'" style="max-width: 100%;  max-height: 100%;" src="https://s3.amazonaws.com/assets.mockflow.com/app/wireframepro/company/C2fbcf12f03a63b0f75732c6c95d3e61e/projects/M8688461e9c03aab1b425ee1a433896611574718915827/images/thumbnails/D290239777ac0fe27d45ef839b7944505">
                </div>
                <br>
                <form class="form-centered" action = "DownloadExcel">
                    <input class="apply" type = "submit" value = "Download File" />
                </form>
            </div>
        </div>
    </body>
</html>

