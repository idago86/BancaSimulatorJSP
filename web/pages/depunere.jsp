
<%@page import="model.Client"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="web.FrontCrontroller"%>
<jsp:useBean class="model.Cont" id="cont" scope="request"/>  
<jsp:setProperty name="cont" property="*"/>


<form class="form-inline" method="post" action="?action=3&do=2">
  <div class="form-group">
      <input type="text" class="form-control" name="contSelectat" placeholder="Insert Account's ID">
  </div>
  <div class="form-group">
    <input type="text" class="form-control" name="suma" placeholder="Insert the amount">
  </div>  
  <button type="submit" class="btn btn-info">Depuneti</button>  
</form>


<%
    String suma = request.getParameter("suma");
    String contSelectat = request.getParameter("contSelectat");    
   
    if (suma != null && contSelectat != null) {        
          FrontCrontroller.getInstance().depunereNumar(suma, contSelectat);
    }   

%>    
<br/><br/>
<div class="container">
  <h2>Only Active Bank Accounts</h2> <br/> 
  <table class="table table-hover">
    <thead>
      <tr>
        <th>ID</th>
        <th>IBAN</th>
        <th>DESCRIERE</th>        
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
        <td>${cont.dataCreation}</td>
        <td>${cont.client.nume} ${cont.client.prenume}</td>
        <td>${cont.sold}</td>        
        </tbody>
    </c:forEach>       
  </table>
</div>