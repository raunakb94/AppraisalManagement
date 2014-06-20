<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rating Form</title>
<script src="/apptrack/gridJS/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	var appid = <% out.write(request.getParameter("appid"));%>;
	var empid = <% out.write(request.getParameter("empid"));%>;
	document.cookie="forempid="+empid;
	document.cookie="appid="+appid;
		alert(appid);
		jQuery.getJSON("http://localhost:9999/apptrack/managerdata?criteria=rating&empid="+empid+"&appid="+appid)
		.done(function(data){
			alert("Done");
			var jsonData = eval(data);
			for(i in jsonData)
				{
				var newtr = document.createElement('tr');
				var txt = document.createTextNode("GoalId::"+jsonData[i]["goalId"] + "  Category::  " +jsonData[i]["category"]+"  Description:  "+jsonData[i]["description"]+"Employee Comment::"+jsonData[i]["empComment"]);
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");
				var td5 = document.createElement("td");
				var td6 = document.createElement("td");
				td1.appendChild(document.createTextNode(jsonData[i]["goalId"]));
				td2.appendChild(document.createTextNode(jsonData[i]["category"]));
				td3.appendChild(document.createTextNode(jsonData[i]["description"]));
				td4.appendChild(document.createTextNode(jsonData[i]["empComment"]));
				var textbox = document.createElement("input");
				textbox.setAttribute("type","text");
				textbox.setAttribute("name",jsonData[i]["goalId"]);
				td5.appendChild(textbox);
				var tempdiv = document.createElement("div");
				for(var j=0;j<6;j++)
					{
					var radio = document.createElement("input");
					radio.setAttribute("type","radio");
					radio.setAttribute("name",jsonData[i]["goalId"]);
					radio.setAttribute("group",jsonData[i]["goalId"]);
					radio.setAttribute("value", j);
					tempdiv.appendChild(radio);
					}
				newtr.appendChild(td1);
				newtr.appendChild(td2);
				newtr.appendChild(td3);
				newtr.appendChild(td4);
				newtr.appendChild(td5);
				newtr.appendChild(tempdiv);
				document.getElementById("dynamicTable").appendChild(newtr);		
				}
			
		});
});
</script>
</head>
<body>
<H1>Manager Rating Form</H1>
<h1 id="employee">Rating For Emloyee</h1>
<form action="managerdata" method = "POST">
	<table id="dynamicTable" border ="1">
	<tr>
	<td>Goal Id</td>
	<td>For Category</td>
	<td>Goal Description</td>
	<td>Employee Comment</td>
	<td>Your Comment</td>
	<td>Your Rating</td>
	</tr>
	</table>
     </div>
     <input type="submit" value="Submit Form After Completion">
</form>
<input type="radio"> 
</body>
</html>