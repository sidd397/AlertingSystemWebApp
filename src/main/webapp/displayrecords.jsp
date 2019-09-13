<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ICU Record</title>
</head>
<body>
	<h3>Patient Record</h3>
	<br>
	<br>
	<c:forEach items="${patients}" var="p">
		<p>Id: ${p.patientId} Name: ${p.name}</p>
		<p>Age: ${p.age} Gender: ${p.gender}</p>
		<button onclick = "window.location.href='generatevitals?patientId=${p.patientId}'">Show Vitals</button>
	</c:forEach>
</body>
</html>