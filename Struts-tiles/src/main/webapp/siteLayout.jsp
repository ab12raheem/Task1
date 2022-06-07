<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@taglib uri="http://struts.apache.org/tags-tiles-el" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
 <head>
 <html:base />
 <title><tiles:getAsString name="title"/> </title>
 </head>
 <body>
 <table border="1" width="600" cellspacing="5">
 <tbody>
 <tr><td colspan="2"><tiles:insert attribute="header" /></td></tr>
 <tr>
 <td width="200"><tiles:insert attribute="navigation" /></td>
 <td width="400"><tiles:insert attribute="body" /></td>
 </tr>
 <tr><td colspan="2"><tiles:insert attribute="footer" /></td></tr>
 </tbody>
 </table>
 </body>
</html:html>