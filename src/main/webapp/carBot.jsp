<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <html>
 <head>
     <title>Car Bot</title>
 </head>
 <body>
 <section>
     <h3>Info:</h3>
     <jsp:useBean id="carBot" scope="request" class="com.petrenko.model.CarBot"/>
     <p><a href="carBot?action=create">Create new car</a></p>
     <p>Type: ${carBot.type} </p>
     <p>New car: ${carBot.car} </p>
     <h3>All cars:  </h3>
     <p>${carBot.cars}</p>
 </section>
 </html>