<%! private int countLogins = 0; %>
<%! private long timeOfLock = 0; %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta charset="UTF-8" />
    <link href="style.css" rel="stylesheet" />
</head>

<body>
<%
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    boolean showForm = true;
    if (login != null) {
        if ("admin".equals(login) && "123".equals(password)) {
            showForm = false;
            out.write("<center><font color=\"green\">Access granted.</text>");
        } else {
            countLogins++;
            if (countLogins == 3) {
                timeOfLock = System.currentTimeMillis();
                showForm = false;
            }
            out.write("<center><font color=\"red\">Access denied. countLogins: " + countLogins + "</text>");
        }
    }
    if (timeOfLock != 0) {
        showForm = false;
        long rez = ((timeOfLock + 10000) - System.currentTimeMillis())/1000;
        out.write("<center><font color=\"red\">You locked for " + rez + " seconds.</text>");
        if (rez <= 0) {
            timeOfLock = 0;
            countLogins = 0;
            showForm = true;
        }
    }
    if (showForm) {
%>
<form id="loginForm" action="index.jsp" method="post">
    <div class="field">
        <label>Enter your login:</label>
        <div class="input"><input type="text" name="login" value="" id="login" /></div>
    </div>

    <div class="field">
        <a href="#" id="forgot">Forgot your password?</a>
        <label>Enter your password:</label>
        <div class="input"><input type="password" name="password" value="" id="password" /></div>
    </div>

    <div class="submit">
        <button type="submit">Enter</button>
        <label id="remember"><input name="" type="checkbox" value="" /> Remember me</label>
    </div>
</form>
<%
    }
%>
</body>
</html>