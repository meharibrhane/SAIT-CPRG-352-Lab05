 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>home</title>
    </head>
    <body>
        <h1>Home Page</h1>
        <h3>Hello ${user.username}.</h3>
        <div> 
             <a href="login?action=logout">Log out</a>
        </div>
       
    </body>
</html>
