<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 30-Jul-21
  Time: 12:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data">
        Select File to Upload:<input type="file" name="fileName">
        <input type="submit" value="Upload">
    </form>
</body>
</html>
