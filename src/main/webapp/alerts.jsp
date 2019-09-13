<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="refresh" content="10"/>
<title>Patient Vitals</title>
</head>
<body>
		<p>Temperature: ${temperature}</p>
		<p>Spo2: ${spo2}</p>
		<p>PulseRate: ${pulseRate}</p>
</body>
</html>