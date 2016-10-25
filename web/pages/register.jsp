<%-- 
    Document   : register
    Created on : 17-Oct-2016, 15:06:04
    Author     : Israel Dago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="web.FrontCrontroller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <title>JSP Page</title>
    </head>

    <div class="jumbotron text-center">
        <h1>Registration Form</h1>
    </div>

    <div class="container">
        <form class="form-inline" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="username" placeholder="Your Username">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="parola" placeholder="Your Parola">
            </div>  

            <button type="submit" class="btn btn-primary">Register</button>  
            <input type="hidden" name="register"/>


        </form>  
        
        <div class="form-group">
            <%
                String register = request.getParameter("register");
                String username = request.getParameter("username");
                String parola = request.getParameter("parola");

                if (register != null) {
                    if (username != null && parola != null) {
                        boolean rez = FrontCrontroller.getInstance().register(username, parola);
                        if (rez) {
            %>
            <c:out value="Admin Adaugat cu sucess"/>
            <%
            } else {
            %>
            <c:out value="Erroare la Adaugare"/>
            <%
                        }
                    }
                }

            %>  
            <button type="button" class="btn btn-sm btn-link pull-left"><a href="index.jsp">GoTo HomePage</a></button>
        </div>

        
                    
       


    </div>

</html>
