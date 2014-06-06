<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%out.print("Hello"); %></title>
<link rel = "stylesheet" href="/apptrack/gridJS/css/ui.jqgrid.css" type = "text/css" /> 
<link rel = "stylesheet" href="/apptrack/gridJS/css/jquery-ui-1.10.4.custom.css" type = "text/css" /> 
<script src="/apptrack/gridJS/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="/apptrack/gridJS/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="/apptrack/gridJS/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	jQuery("#list4").empty().jqGrid({
		url : "http://localhost:9999/apptrack/login",
		datatype: "json",
		colNames:['First Name','Last Name', 'dateJoin', 'Your Employee Id','Manager Id','Post'],
	   	colModel:[
	   				{name:"firstName", width:100,editable:true},
					{name:"lastName", width:100,editable:true},
					{name:"dateJoin",width:100,editable:true},
					{name:"empId",width:100,editable:false},
					{name:"managerID",width:100,editable:false},
					{name:"Post",width:100,editable:false}
	   	],
	   	mtype : "get",
	   	height : 30,
	   	loadonce: true,
	   	viewrecords: true,
	    gridview: true,
	   	caption: "Your Personal Details As From Our Databases",
	   	editurl :"/transactionservlet"
	   	});
	$("#bedata").click(function(){
   		var gr = jQuery("#list4").jqGrid('getGridParam','selrow');
   		if( gr != null ) jQuery("#list4").jqGrid('editGridRow',gr,{height:280,reloadAfterSubmit:false});
   		else alert("Please Select Row");});
});
</script>
</head>
<body >
	<H1>Welcome To Your Home Page</H1>
	
	<table id="list4"></table>
	<input type="button" value="Click to edit" id="bedata">
	<table id="pendingappraisals">dsadasdad</table>
</body>
</html>