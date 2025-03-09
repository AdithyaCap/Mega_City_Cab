<%--
  Created by IntelliJ IDEA.
  User: poorn
  Date: 2/13/2025
  Time: 4:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    session.invalidate();  // Invalidate the session on logout
    response.sendRedirect("login.jsp");
%>