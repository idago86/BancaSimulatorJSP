
<%@page import="model.Client"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="web.FrontCrontroller"%>
<jsp:useBean class="model.Cont" id="cont" scope="request"/>  
<jsp:setProperty name="cont" property="*"/>


<%
    String suma = request.getParameter("suma");
    String contDebitat = request.getParameter("contDebitat"); 
    String contCreditat = request.getParameter("contCreditat");         
    String mesaj = null;
    
    if (suma != null && contDebitat != null && contCreditat != null) {        
          mesaj = FrontCrontroller.getInstance().transferNumar(suma, contDebitat, contCreditat);
    }   

%> 
<form class="form-inline" method="post" action="?action=3&do=4">  
  <div class="form-group">
    <input type="text" class="form-control" name="suma" placeholder="Amount To Transfer">
  </div>  
    <div class="form-group">
      <input type="text" class="form-control" name="contDebitat" placeholder="FROM Account's ID">
  </div>
    <div class="form-group">
      <input type="text" class="form-control" name="contCreditat" placeholder="TO Account's ID">
  </div>
  <button type="submit" class="btn btn-info">Transfer</button>  
</form>


<%
    if(mesaj!=null){
        %>
        <div class="container"><h5><span class="text-danger"> <c:out value="Insufficient funds for this transaction"/></span></h5></div>
        <%
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