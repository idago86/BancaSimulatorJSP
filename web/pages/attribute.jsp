
<%@page import="model.Client"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="web.FrontCrontroller"%>
<jsp:useBean class="model.Cont" id="cont" scope="request"/>  
<jsp:setProperty name="cont" property="*"/>
<jsp:useBean class="model.Client" id="client" scope="request"/>  
<jsp:setProperty name="client" property="*"/>

<form class="form-inline" method="post" action="?action=3&do=1">
  <div class="form-group">
      <input type="text" class="form-control" name="clientSelectat" placeholder="Insert Client's ID">
  </div>
  <div class="form-group">
    <input type="text" class="form-control" name="contSelectat" placeholder="Insert Account's ID">
  </div>  
  <button type="submit" class="btn btn-info">Attribuite account to this client</button>  
</form>
s

<%
    String clientSelectat = request.getParameter("clientSelectat");
    String contSelectat = request.getParameter("contSelectat");   
   
    if (clientSelectat != null && contSelectat != null) {       
          FrontCrontroller.getInstance().attributeContToClient(clientSelectat, contSelectat);
    }   

%>    

<br/><br/>
<div class="container">
  <h2>Actuals Unused Bank Accounts</h2> <br/> 
  <table class="table table-hover">
    <thead>
      <tr>
        <th>ID</th>
        <th>IBAN</th>
        <th>DESCRIERE</th>        
        <th>CREATION DATE</th>        
        <th>SOLD</th>
      </tr>
    </thead>
    <c:forEach var="cont" items="${cont.inactiveConturi}"> 
        <tbody>
        <td>${cont.id}</td>
        <td>${cont.iban}</td>
        <td>${cont.descriere}</td>        
        <td>${cont.dataCreation}</td>        
        <td>${cont.sold}</td>        
        </tbody>
    </c:forEach>       
  </table>
</div>

<br/><br/>
<div class="container">
  <h2>Active Bank Accounts</h2> <br/> 
  <table class="table table-hover">
    <thead>
      <tr>
        <th>ID</th>
        <th>IBAN</th>
        <th>DESCRIERE</th>
        <th>SOLD</th>
        <th>CREATION DATE</th>
        <th>HOLDER</th>
        <th>SOLD</th>
      </tr>
    </thead>
    <c:forEach var="cont" items="${cont.activeConturi}"> 
        <tbody>
        <td>${cont.id}</td>
        <td>${cont.iban}</td>
        <td>${cont.descriere}</td>
        <td>${cont.sold}</td>
        <td>${cont.dataCreation}</td>
        <td>${cont.client.nume} ${cont.client.prenume}</td>
        <td>${cont.sold}</td>        
        </tbody>
    </c:forEach>       
  </table>
</div>



<br/><br/>
<div class="container">
  <h2>All Banck Clients</h2> <br/> 
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
        </tbody>
    </c:forEach>   
    
  </table>
</div>