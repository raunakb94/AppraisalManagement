<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="/apptrack/gridJS/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<title>Appraisal Form</title>
<script type="text/javascript">
$(document).ready(function(){
	var appid2 = <% out.write(request.getParameter("appid"));%>;
		alert(appid2);
		jQuery.getJSON("http://localhost:9999/apptrack/login?id=pending&q="+appid2)
		.done(function(data){
			alert("Done");
			var jsonData = eval(data);
			for(i in jsonData)
				{
				var newTr = document.createElement("TR");
				var newTD1 = document.createElement("TD");
				var newTD2 = document.createElement("TD");
				var newTD3 = document.createElement("TD");
				newTD1.innerHtml=jsonData[i]["goalId"]);
				var text2 = document.createTextNode(jsonData[i]["type"]);
				var text3 = document.createTextNode(jsonData[i]["description"]);
				var textbox = document.createElement("input");
				textbox.setAttribute("type","text");
				textbox.setAttribute("id",jsonData[i]["goalId"]);
				newTr.appendChild(newTd1);
				newTr.appendChild(newTd2);
				newTr.appendChild(newTd3);
				newTr.appendChild(textbox);
				document.getElementById("dynamicdetails").appendChild(newTr);
				}
		});
});
</script>
</head>
<body>
<form action="" method = "POST">
	<div id="dynamicInput">
		<table id="dynamicdetails">
			<tr>
				<td>GoalId</td>
				<td>Category</td>
				<td>Description</td>
				<td>Your Comments</td>
			</tr>
		</table>
     </div>
     <input type="submit" value="Submit Form After Completion">
</form>
<div id="tagg"></div>
</body>
</html>