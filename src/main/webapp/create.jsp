<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <html>
 <head>
     <title>Create</title>
 </head>
 <body>
 <section>
     <jsp:useBean id="carBot" scope="request" type="com.petrenko.model.CarBot"/>
     <form method="post" action="carBot?action=submit">
         <dl>
             <dt>Set type (only CAR or TRUCK): </dt>
             <dd><input type="text" name="type" value="${carBot.type}" placeholder="${carBot.type}" /></dd>
         </dl>
         <button type="submit">Ok</button>
     </form>
 </section>
 </body>
 </html>