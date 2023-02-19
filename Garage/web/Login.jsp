<%-- 
    Document   : Login
    Created on : 14 janv. 2023, 14:54:48
    Author     : allan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    
    <body>
       <body class="login_personal_body">
     <div class="login_personal">
        <form action="./ControllerLogin" method="post">
            <input type="text"password id="name"class="input_login" placeholder="name" name="name" value="<%if(request.getSession().getAttribute("name")!=null){out.println(request.getSession().getAttribute("name"));}%>" required >
            <input type="password"password id="password"class="input_login" placeholder="password" name="password"value="<%if(request.getSession().getAttribute("password")!=null){out.println(request.getSession().getAttribute("password"));}%>" required >
            <button type="submit" name="login_submit" id="login_submit"class="input_login">
                submit
            </button>

        </form> 
    </div>
    </body>
    </body>
</html>
