

<%@page import="model.Client"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="web.FrontCrontroller"%>
<jsp:useBean class="model.Client" id="client" scope="request"/>  
<jsp:setProperty name="client" property="*"/>

<form class="form-inline" method="post" action="?action=1">
  <div class="form-group">
      <input type="text" class="form-control" name="nume" placeholder="Aici numele">
  </div>
  <div class="form-group">
    <input type="text" class="form-control" name="prenume" placeholder="Aici prenumele">
  </div>
  <div class="form-group">
    <input type="text" class="form-control" name="cnp" placeholder="Aici cnp">
  </div>
  <button type="submit" class="btn btn-info">Adauga un Client</button>  
</form>

<%
    String nume = request.getParameter("nume");
    String prenume = request.getParameter("prenume");
    String cnp = request.getParameter("cnp");
    if (nume != null && prenume != null && cnp != null) {
          FrontCrontroller.getInstance().adaugaClient(nume, prenume, cnp);                
    }   

%>    
<br/><br/><br/>
<div class="container">
  <h2>All Banck Clients</h2> 
  <%
    ///for deleting    
    String ClientId = request.getParameter("delete");
    if (ClientId != null) {      
            FrontCrontroller.getInstance().deleteClient(ClientId);
            %>
            <div class="container"><h3><span class="text-success"> <c:out value="Client Deleted with success !!!" /></span></h3></div>
            <%            
    }
%>    
  <table class="table table-hover">
    <thead>
      <tr>
        <th>ID</th>
        <th>NUME</th>
        <th>PRENUME</th>
        <th>CNP</th>
      </tr>
    </thead>
    <c:forEach var="client" items="${client.allClients}"> 
        <tbody>
        <td>${client.id}</td>
        <td>${client.nume}</td>
        <td>${client.prenume}</td>
        <td>${client.cnp}</td>        
        <td><a href="?action=1&delete=${client.id}">Delete</a> </td>
        </tbody>
    </c:forEach>   
    
  </table>
</div>