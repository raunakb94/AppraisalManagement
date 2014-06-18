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
		document.cookie="appid="+appid2;
		jQuery.getJSON("http://localhost:9999/apptrack/login?id=pending&q="+appid2)
		.done(function(data){
			alert("Done");
			var jsonData = eval(data);
			for(i in jsonData)
				{
				var newdiv = document.createElement('div');
				var txt = document.createTextNode("GoalId::"+jsonData[i]["goalId"] + "  Category::  " +jsonData[i]["type"]+"  Description:  "+jsonData[i]["description"]);
				var textbox = document.createElement("input");
				textbox.setAttribute("type","text");
				textbox.setAttribute("name",jsonData[i]["goalId"]);
				newdiv.appendChild(txt);
				newdiv.appendChild(textbox);
				document.getElementById("dynamicInput").appendChild(newdiv);

				}
		});
});
</script>
</head>
<body>
<h1>Appraisal Form</h1>
<h2>Please Input Your Details</h2>
<form action="appraisalSubmit" method = "POST">
	<div id="dynamicInput">
     </div>
     <input type="submit" value="Submit Form After Completion">
</form>
<div id="tagg"></div>
</body>
</html>