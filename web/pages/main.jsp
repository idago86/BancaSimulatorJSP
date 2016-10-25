<%-- 
    Document   : main
    Created on : 17-Oct-2016, 15:03:10
    Author     : Israel Dago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
       
    </head>
    <body>
        <div class="jumbotron text-center">
            <h1>BancaWEB</h1>
            <p>The Most Impressive Bank Simulator!</p>

              <button type="button" class="btn btn-xs btn-link pull-right"><a href="index.jsp?logout=true">LOG OUT</a></button>
        </div>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li><a href="?action=1" >Clients</a></li>
                    <li><a href="?action=2">Accounts</a></li>
                    <li><a href="?action=3">Transactions</a></li>
                </ul>
            </div>
        </nav>


        <div class="container">            
            <c:choose>
                <c:when test="${param['action'] == '1'}">
                    <jsp:include page="clients.jsp"/>
                </c:when>  
                <c:when test="${param['action'] == '2'}">
                    <jsp:include page="accounts.jsp"/>
                </c:when> 
                <c:when test="${param['action'] == '3'}">
                    <jsp:include page="transactions.jsp"/>
                </c:when> 
            </c:choose>
        </div>
    </body>
</html>
