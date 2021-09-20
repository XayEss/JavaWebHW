<%
	String rows = request.getParameter("rows");
	String columns = request.getParameter("columns");
	String result = "";
	result += "<table>";
	int counter = 1;
	try{
	for(int i = 0; i<Integer.valueOf(rows); i++){
		result += "<tr>";
		for(int j = 0; j<Integer.valueOf(columns); j++){
			if(counter % 2 == 0){
				result += "<td style='background-color:yellow'>" + counter++ + "</td>";
			}else{
				result += "<td>" + counter++ + "</td>";
			}
		}
		result += "</tr>";
	}
	result += "</table>";
	out.write(result);
	}catch (NumberFormatException e){
		out.write("Please type in a valid number");
	}
%>