<%-- 
    Document   : login
    Created on : 17-Oct-2016, 14:32:51
    Author     : Israel Dago
--%>

<%@page import="model.Admin"%>
<%@page import="web.FrontCrontroller"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <title>BancaSimulator</title>
    </head>
    <body>
        <div class="jumbotron text-center">
            <h1>Authentification Form</h1>
        </div>

        <div class="container">  
            <form class="form-inline" method="post" action="index.jsp?reason=login">
                <div class="form-group">
                    <input type="text" class="form-control" name="username" placeholder="Your Username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="parola" placeholder="Your Parola">
                </div>  
                <button type="submit" class="btn btn-primary">Login</button>  
            </form>


            <%
                String username = request.getParameter("username");
                String parola = request.getParameter("parola");

                if ("login".equals(request.getParameter("reason"))) {
                    if (username != null && parola != null) {
                        Admin rez = FrontCrontroller.getInstance().login(username, parola);
                        if (rez == null) {
            %>
            <c:out value="Username sau parola gresite"/>
            <%
                        }
                    }
                }
            %>
            
            <button type="button" class="btn btn-sm btn-link pull-left"><a href="index.jsp?page=register">Register</a></button>  
        </div>  
    </body>
</html>
