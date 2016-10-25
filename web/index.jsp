<%-- 
    Document   : index
    Created on : 17-Oct-2016, 14:31:41
    Author     : Israel Dago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Admin"%>
<%@page import="web.FrontCrontroller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BancaSimulator</title>
    </head>
    <body>
        <%
            String reason = request.getParameter("reason");
            String username = request.getParameter("username");
            String parola = request.getParameter("parola");            
            Admin userLogat = null;

            if(reason!=null){
                if (username != null && parola != null) {
                userLogat = FrontCrontroller.getInstance().login(username, parola);
                session.setAttribute("userLogat", userLogat);
            } else {
                if (session.getAttribute("userLogat") != null) {
                    userLogat = (Admin) session.getAttribute("userLogat");
                }
            }
            }      

            if(request.getParameter("logout") != null){
                session.setAttribute("userLogat", null);
                response.sendRedirect("index.jsp");
            }
            


        %>
        
        <c:choose>
            <c:when test="${userLogat != null}">
                <jsp:include page="pages/main.jsp" />
            </c:when>
            <c:when test="${param['page'] == 'register'}">
                <jsp:forward page="pages/register.jsp"/>
            </c:when>            
            <c:otherwise>                
                <jsp:include page="pages/login.jsp" />                
            </c:otherwise>
        </c:choose>

    </body>
</html>
