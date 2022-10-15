<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="beans_entity.Category, java.util.List, java.util.ArrayList" %>
<%

String msg=(String) request.getAttribute("msg");
List<Category> categoryList=(List<Category>) request.getAttribute("categoryList");
List<Category> delCategoryList=(List<Category>) request.getAttribute("delCategoryList");
%>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">





<title>カテゴリ登録</title>
</head>
<body>



<h1>カテゴリ設定画面</h1>

<p>既存カテゴリーリスト一覧</p>
<br>
<% if(categoryList!=null){ %>
 <select size="10">
  <% for(Category ca: categoryList){ %>
   <option>カテゴリID= <%=ca.getCategoryId()%> カテゴリ名= <%=ca.getCategoryName() %></option>
  <% } %>
 </select>
<% } %>
<br>



























<form action="/new/CategoryServlet?action=renewal" method="post">

<select name="sort" size="2">
<option value="category_id">カテゴリID  </option>
<option value="category_name">カテゴリ名 </option>
</select>
<br>
<br>
<select name="type" size="2">
<option value="asc"> 降順</option>
<option value="desc"> 昇順 </option>
</select>

<input type="submit" value="更新">

</form>




















<br>













<% if(msg!=null){ %>
<p><%=msg %></p>
<% } %>



<p>カテゴリー登録</p>


<form action="/new/CategoryServlet?action=register" method="post">
<p>カテゴリーID：<input type="text" name="categoryId"></p>
<p>カテゴリー名:<input type="text" name="categoryName"></p>
<br>
<input type="submit" value="登録">


</form>



<br>



<form action="/new/CategoryServlet?action=delete" method="post">
<select name="categoryId" size="10">

<% for(Category d: delCategoryList){%>
<option value="<%= d%>">カテゴリID=<%= d.getCategoryId()%> カテゴリ名前=<%= d.getCategoryName()%></option>
<% } %>

</select>
<p>
<br>
<input type="submit" value="削除">
</p>
</form>




<br>



<form action="/new/CategoryServlet?action=edit" method="post">

<select name="categoryId" size="10">
<% for( Category category:categoryList){%>
<option value="<%= category.getCategoryId()%>">カテゴリID<%= category.getCategoryId()%>カテゴリ名<%= category.getCategoryName()%>  </option>
<% } %>
</select>
<p>編集後のカテゴリー名：<input type="text" name="categoryName"></p>
<input type="submit" value="編集">
</form>




</body>
</html>