
<%@page import="model.Cont"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="web.FrontCrontroller"%>
<jsp:useBean class="model.Cont" id="cont" scope="request"/>  
<jsp:setProperty name="cont" property="*"/>



<form class="form-horizontal" method="post" action="?action=2">
    <div class="form-group">
        <input type="text" class="form-control" name="iban" placeholder="Iban">
    </div>
    <div class="form-group">    
        <label for="sel1">Select Account Description:</label>
        <select class="form-control" name="descriere">
            <option>Current Account</option>
            <option>Economy Account</option>
            <option>Enterprise Account</option>        
        </select>    
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-info">Add Account</button> 
    </div>   
</form>

<%
    String iban = request.getParameter("iban");
    String descriere = request.getParameter("descriere");

    if (iban != null && descriere != null) {
        FrontCrontroller.getInstance().adaugaCont(iban, descriere);
    }
    ///for delete

    String ContId = request.getParameter("delete");
    if (ContId != null) {
        String rez = FrontCrontroller.getInstance().findContActive(ContId);
        if ("false".equals(rez)) {
            FrontCrontroller.getInstance().deleteCont(ContId);
%>
<div class="container"><h3><span class="text-success"> <c:out value="Account Deleted with success !!!" /></span></h3></div>
            <%
            } else {
            %>
<div class="container"><h3><span class="text-danger"> <c:out value="Delete Impossible because the account you want to delete is ACTIVE and USED by A Client!!!" /></span></h3></div>
            <%
                    }
                }

            %>    
<br/><br/>
<div class="container">
    <h2>All Bank Accounts</h2> <br/> 
    <table class="table table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>IBAN</th>
                <th>DESCRIERE</th>
                <th>SOLD</th>
                <th>CREATION DATE</th>
                <th>ACTIVE</th>        
            </tr>
        </thead>
        <c:forEach var="cont" items="${cont.allConturi}"> 
            <tbody>
            <td>${cont.id}</td>
            <td>${cont.iban}</td>
            <td>${cont.descriere}</td>
            <td>${cont.sold}</td>
            <td>${cont.dataCreation}</td>
            <td>${cont.active}</td>           
            <td><a href="?action=2&delete=${cont.id}">Delete</a> </td>
            </tbody>
        </c:forEach>       
    </table>
</div>






















<!-- 
 <td>
<div class="container"><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">  <a href="?action=2&edit=${cont.id}&goto=doit">Edit</a>
<c:choose>
    
    <c:when test="${param['goto'] == 'doit'}">
        <jsp:include page="modal.jsp"/>
    </c:when>                  
</c:choose>
</div></td>

-->