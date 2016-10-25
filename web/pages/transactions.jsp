<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="nav nav-tabs">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li><a href="?action=3&do=1">Attribute</a></li>
                    <li><a href="?action=3&do=2">Depunere</a></li>
                    <li><a href="?action=3&do=3">Retragere</a></li>
                    <li><a href="?action=3&do=4">Transfers</a></li>
                </ul>
            </div>
        </nav>

        <div class="container">            
            <c:choose>
                  
                <c:when test="${param['do'] == '2'}">
                    <jsp:include page="depunere.jsp"/>
                </c:when>   
                <c:when test="${param['do'] == '3'}">
                    <jsp:include page="retragere.jsp"/>
                </c:when>   
                <c:when test="${param['do'] == '4'}">
                    <jsp:include page="transfers.jsp"/>
                </c:when>  
                <c:when test="${param['do'] == '1'}">
                    <jsp:include page="attribute.jsp"/>
                </c:when> 
                    
            </c:choose>
        </div>