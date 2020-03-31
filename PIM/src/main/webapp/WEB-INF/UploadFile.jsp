<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload File</title>
    </head>
    <%@ include file = "../header.jsp" %>
    <link rel="stylesheet" href="UploadFile.css">
    <body>
        <h3 class="upload">Upload</h3>
        <div class="fullbinder">
    
    <div class="binder">
        <h3>Upload File</h3>
        <div class="img-wrapper">
        <img class="lazyImgThumb" alt="" onerror="this.style.display='none'" style="max-width: 100%;  max-height: 100%;" src="https://s3.amazonaws.com/assets.mockflow.com/app/wireframepro/company/C2fbcf12f03a63b0f75732c6c95d3e61e/projects/M8688461e9c03aab1b425ee1a433896611574718915827/images/thumbnails/M1d0d95cad5cb527f9f9e0b2c5c67e4131526462421183">
        <img class="lazyImgThumb" alt="" onerror="this.style.display='none'" style="max-width: 100%;  max-height: 100%;" src="https://s3.amazonaws.com/assets.mockflow.com/app/wireframepro/company/C2fbcf12f03a63b0f75732c6c95d3e61e/projects/M8688461e9c03aab1b425ee1a433896611574718915827/images/thumbnails/D290239777ac0fe27d45ef839b7944505">
        </div>
        Select a <b>JSON</b> or <b>EXCEL</b> file to upload: 
        <form action = "Upload" method = "POST" enctype = "multipart/form-data">
         <input type = "file" name = "file" size = "50" required accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,.csv,.json"/>
         <br><br>
         <input class="apply" type = "submit" value = "Upload File to Website" />
      </form>
          </div>
          
          <div class="binder2">
        <h3>Upload Picture</h3>
        <div class="img-wrapper">
        <img class="lazyImgThumb" alt="" onerror="this.style.display='none'" style="max-width: 100%;  max-height: 100%;" src="https://s3.amazonaws.com/assets.mockflow.com/app/wireframepro/company/C2fbcf12f03a63b0f75732c6c95d3e61e/projects/M8688461e9c03aab1b425ee1a433896611574718915827/images/thumbnails/Mb36c09ff95b4cf327fc29badc4ca252d1526462422660">
        </div>
        Select pictures to upload: 
        <form action = "UploadImage" method = "POST" enctype = "multipart/form-data">
         <input name="file" type="file" id="file" multiple required accept=".JPG">
         <br><br>
         <input class="apply" type = "submit" value = "Upload File to Website" />
      </form>
      </div>
      </div>
    </body>
</html>
