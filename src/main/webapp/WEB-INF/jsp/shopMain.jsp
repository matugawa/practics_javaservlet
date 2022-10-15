<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="beans_entity.Product, java.util.List" %>
<%
List<Product> productList=(List<Product>) request.getAttribute("productList");
List<Product> bagList=(List<Product>) session.getAttribute("bagList");
String msg =(String) request.getAttribute("msg");
%>
    

<!doctype html>
<html lang="jp">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Hello, world!</title>
  
  
  </head>
  <body>


<% if(msg!=null){ %>
<p><%=msg %></p>
<% } %>

<h>かいものぺーじ</h>

<br>
<br>

<div class="d-grid gap-2 col-6 mx-auto"> 
<form action="/new/ShopServlet?action=toBag" method="post">
 <select name="bag" class="form-select" multiple aria-label="multiple select example">
  <%for(Product pr: productList){ %>
   <option value=<%= pr.getProductId()%>><%=pr.getProductName()%> : <%=pr.getIntPrice() %>円 </option>
   <%} %>
 </select>
 <br>
<input class="btn btn-outline-primary btn-lg" type="submit" value="かごへ入れる">
</div>



<h>かごの中身</h>
    
    
<br>
    
<% if(bagList!=null){ %>
 <div class="d-grid gap-2 col-6 mx-auto"> 
  <form action="/new/ShopServlet?action=toBuy" method="post">
   <select size="5" class="form-select">
    <%for(Product pr: bagList){ %>
     <option><%=pr.getProductName()%> : <%=pr.getIntPrice() %>円</option>
    <%} %>
   </select>
  <input class="btn btn-outline-primary btn-lg" type="submit" value="購入">
 </div>
<% } %>
    
    
    
    
    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  </body>
</html>