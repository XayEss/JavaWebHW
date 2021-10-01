<%@ page import="ua.itea.Authorizator" %>
<%@ page import="ua.itea.DbExecutor"%>
<%@ page import="ua.itea.DataBaseDAOInterface" %>
<%@ page import="ua.itea.DAOFactory" %>
<a href="withauthorizator.jsp">Authorization</a> | <a href="registration.jsp">Registration</a>
<%
    if (request.getParameter("logout") != null) {
        session.setAttribute("authorized", null);
    }
    String loginFromSession = (String) session.getAttribute("authorized");
    boolean showForm = true;
    if (loginFromSession != null) {
        showForm = false;
    } else {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
		DAOFactory df = new DAOFactory();
		DataBaseDAOInterface de = df.createObject(0);
        //Authorizator auth = new Authorizator();
        String userName = de.checkLogin(login, password);
        if (userName != null) {
            showForm = false;
            session.setAttribute("authorized", userName);
            loginFromSession = userName;
            out.write("<center><font color='green'>Access granted</font>");
        }
    }
    if (showForm) {
%>
<center>
<table>
<form action="withauthorizator.jsp" method="post">
    <tr><td>Login:</td><td><input type="text" name="login"/></td></tr>
    <tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
    <tr><td> </td><td><input type="submit" value="SEND"/></td></tr>
</form>
</table>
</center>
<%
    } else {
        out.write("Hello, " + loginFromSession + " <a href='?logout'>logout</a>");
    }
%>