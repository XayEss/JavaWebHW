<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.regex.Pattern"%>
<%@ page import="java.util.Objects"%>
<%! private int countLogins = 0; %>
<%
    boolean isError = false;
	boolean showForm = true;
    String errorText = "<ul style='float:right'>";
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    String passwordRepeat = request.getParameter("passwordRepeat");
    String gender = request.getParameter("gender");
    String region = request.getParameter("region");
    String comment = request.getParameter("comment");
    String browser = request.getParameter("browser");
	Pattern pattern;
	Matcher matcher;
    //out.write("login = " + login + ", password = " + password + ", passwordRepeat = " + passwordRepeat +
    //    ", gender = " + gender + ", region = " + region + ", comment = " + comment + ", browser = " + browser);
    if (login != null && !login.isEmpty()) {
		pattern = Pattern.compile(".+@.+");
		matcher = pattern.matcher(login);
		if(!matcher.matches()){
			isError = true;
			errorText += "<li>Login doesnt match regex </li>";
		}
    }else{
		isError = true;
        errorText += "<li>Login is empty!</li>";
	}
	if(password != null && !password.isEmpty()){
		Pattern pattern2 = Pattern.compile("[0-9]");
	    Matcher matcher2 = pattern2.matcher(password);
	    Pattern pattern3 = Pattern.compile("[A-Z]");
	    Matcher matcher3 = pattern3.matcher(password);
		if(!Objects.equals(password, passwordRepeat)){
			isError = true;
			errorText += "<li>Passwords don't match!</li>";
		}
		if (!(password.length() > 8)) {
			isError = true;
			errorText += "<li>Password lenght must be more than 8!</li>";
		}
		if (!matcher2.find()) {
			isError = true;
			errorText += "<li>Password must contain a number!</li>";
		}
		if (!matcher3.find()) {
			isError = true;
			errorText += "<li>Password must contain a capital letter</li>";
		}
	}else{
		isError = true;
        errorText += "<li>Password is empty!</li>";
	}

	if(gender == null || gender.isEmpty()){
		isError = true;
        errorText += "<li>Gender field is empty</li>";
	}
	if(region == null || region.isEmpty()){
		isError = true;
        errorText += "<li>Region field is empty</li>";
	}
    if(comment == null || comment.isEmpty()){
		isError = true;
        errorText += "<li>Comment is empty</li>";
	}
	if(browser == null || browser.isEmpty()){
		isError = true;
        errorText += "<li>Browser is empty</li>";
	}
	
	errorText += "</ul>";
    if (!isError) {
        out.write("Registration succeeded.");
		showForm = false;
		countLogins = 0;
    } else if (countLogins > 0){
        out.write(errorText);
    }
	if(showForm){
		countLogins++;
%>
<center>
<table>
<form action="form03.jsp" method="post">
    <tr><td>Email:</td><td><input type="email" name="login"/></td></tr>
    <tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
    <tr><td>Repeat password:</td><td><input type="password" name="passwordRepeat"/></td></tr>
    <tr><td>Gender:</td><td>M<input type="radio" name="gender" value="M"/>F<input type="radio" name="gender" value="F"/></td></tr>
    <tr><td>Region:</td><td><select name="region">
        <option value="DNR">ДНР</option>
        <option value="LNR">ЛНР</option>
        <option value="Crimea">Крым</option>
    </select></td></tr>
    <tr><td>Comment:</td><td><textarea rows=10 cols=20 name="comment"/></textarea></td></tr>
    <tr><td>I agree to install an Amigo Browser:</td><td><input type="checkbox" name="browser" checked="true"/></td></tr>
    <tr><td> </td><td><input type="submit" value="SEND"/></td></tr>
</form>
</table>
</center>
<% } %>