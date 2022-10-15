<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="beans_entity.Product, beans_entity.Category, java.util.List" %>
<%

String msg=(String) request.getAttribute("msg");

List<Category> categoryList=(List<Category>) request.getAttribute("categoryList");

List<Product> productList=(List<Product>) request.getAttribute("productList");

%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロダクト登録</title>
</head>
<body>

<h1>プロダクト登録</h1>

<p>既存プロダクトリスト一覧</p>
<br>
<% if(productList!=null){ %>
 <select size="10">
  <% for(Product pr: productList){ %>
   <option>プロダクトID= <%=pr.getProductId()%>カテゴリID <%=pr.getCategoryId() %> プロダクト名= <%=pr.getProductName() %>単価 <%=pr.getIntPrice() %></option>
  <% } %>
 </select>
<% } %>
<br>
<br>


<form action="/new/ProductServlet?action=renewal" method="post">

<select name="sort" size="2">
<option value="product_id">productID  </option>
<option value="product_name">product名 </option>
</select>
<br>
<br>
<select name="type" size="2">
<option value="asc"> 降順</option>
<option value="desc"> 昇順 </option>
</select>

<input type="submit" value="更新">

</form>











<% if(msg!=null){ %>
<p><%=msg %></p>
<% } %>

<br>


登録
<br>

<br>
<br>

<form action="/new/ProductServlet?action=register" method="post">

<select name="categoryId" size="10">
<% for( Category category:categoryList){%>
<option value="<%= category.getCategoryId()%>">カテゴリID<%= category.getCategoryId()%> カテゴリ名<%=category.getCategoryName() %></option>
<% } %>
</select>
<p>商品ID：<input type="text" name="productId"></p>
<p>商品名:<input type="text" name="productName"></p>
<p>単価:<input type="text" name="price"></p>
<input type="submit" value="登録">
</form>
<br>
<br>
<form action="/new/ProductServlet?action=delete" method="post">
<select name="productId" size="10">
<% for( Product pr: productList){%>
<option value="<%= pr.getProductId()%>">プロダクトID＝<%= pr.getProductId()%> </option>
<% } %>
</select>
<input type="submit" value="削除">
</form>

<br>
<br>



<form action="/new/ProductServlet?action=edit" method="post">

<select name="productId" size="10">
<% for( Product pr: productList){%>
<option value="<%= pr.getProductId()%>">プロダクトID＝<%= pr.getProductId()%> </option>
<% } %>

</select>

<br>
変更後のカテゴリIDを選択
<br>

<select name="categoryId" size="10">
<% for( Category category:categoryList){%>
<option value="<%= category.getCategoryId()%>">カテゴリID<%= category.getCategoryId()%> </option>
<% } %>
</select>

<p>変更後商品名:<input type="text" name="productName"></p>
<p>変更後単価:<input type="text" name="price"></p>

<input type="submit" value="編集">
</form>






</body>
</html>