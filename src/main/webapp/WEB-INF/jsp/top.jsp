<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@page import="java.util.List, java.util.ArrayList" %>
<%
String perm=(String) session.getAttribute("permission");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理システム</title>
</head>
<body>
<%if(perm.equals("permissioned")){	%>
<p>ログインに成功しました</p>


<h1>商品管理メニュー</h1>
<ul>
<li><a href="/new/CategoryServlet">カテゴリー設定へ</a></li>

<li><a href="/new/ProductServlet">商品設定へ</a></li>

<li><a href="/new/ShopServlet">買物かごへ</a></li>
</ul>


<%}else{ %>
<p>ログインに失敗しました</p>
<a href="/new/">TOPへ</a>
<%} %>


<a href="/new/LoginServlet?action=logout">logout</a>

</body>
</html>