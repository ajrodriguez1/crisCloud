<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Publication View</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<h2>Publication info</h2>
	<p>ID: ${publication.getId() }</p>
	<p>Title: ${publication.getTitle()}</p>
	<p>Publication date: ${publication.getPublicationDate() }</p>
	<p>Cite count: ${publication.getCiteCount() }</p>
	<p>First author: ${publication.getFirstAuthor() }</p>
	<h2>Authors: </h2>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>email</th>
		</tr>
		<c:forEach items="${authors}" var="p_i">
			<tr>
				<td><a href="ResearcherServlet?id=${p_i.getId()}">${p_i.getId()}</a></td>
				<td>${p_i.getName()}</td>
				<td>${p_i.getEmail()}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${researcher.getId() == firstAuthor || userAdmin}">

		<h3>Update publication</h3>
			<form action="UpdatePublicationServlet" method="post">
				<input type="hidden" name="id" value="${publication.getId()}" />
				<p>
					Name: <input type="text" name="name" value="${publication.getPublicationName()}" />
				</p>
				<p>
					Title: <input type="text" name="title" value="${publication.getTitle()}" />
				</p>
				<p>
					Publication date: <input type="publication_date" name="email" value="${publication.getPublicationDate()}" />
				</p>
				<p>
					Cite count: <input type="text" name="cite_count" value="${publication.getCiteCount()}" />
				</p>
				<p>
					First author: <input type="text" name="first_author" value="${publication.getFirstAuthor()}" />
				</p>
				<button type="submit">Update</button>
			</form>
	</c:if>
</body>
</html>