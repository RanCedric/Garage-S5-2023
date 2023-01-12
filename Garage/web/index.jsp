<%-- 
    Document   : login
    Created on : Jan 12, 2023, 4:04:03 PM
    Author     : tafit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body class="login_personal_body">
     <div class="login_personal">
        <form action="Controller_login" method="post">
            <input type="text" name="admin_name" id="email" class="input_login" placeholder="Username"><br>
            <input type="password" name="admin_mdp" id="password"class="input_login" placeholder="Password"><br>
            <button type="submit" name="login_submit" id="login_submit"class="input_login">
                submit
            </button>
        </form> 
    </div>
    </body>
</html>
