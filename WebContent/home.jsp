<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%out.print(request.getParameter("userName")); %></title>
<script type='text/javascript' src='http://code.jquery.com/jquery-1.6.2.js'></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/base/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="http://trirand.com/blog/jqgrid/themes/ui.jqgrid.css">
    <script type='text/javascript' src="http://trirand.com/blog/jqgrid/js/i18n/grid.locale-en.js"></script>
    <script type='text/javascript' src="http://trirand.com/blog/jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
jQuery("#list4").empty().jqGrid({
	url : "http://localhost:9999/apptrack/login",
	datatype: "json",
	height: 250,
	colNames:['First Name','Last Name', 'Email Id', 'Your Employee Id','Manager Id','Rating'],
	jsonReader: {id: "empId",
        root: function (obj) { return obj.rows; },
        page: function () { return 1; },
        total: function () { return 1; },
        records: function (obj) { return obj.rows.length; },
        repeatitems: false },
   	colModel:[
   				{name:"firstName", width:100},
				{name:"lastName", width:100},
				{name:"eMail",width:100},
				{name:"empId",width:100},
				{name:"managerID",width:100},
				{name:"rating",width:100}		
   	],
   	mtype : "get",
   	loadonce: true,
   	viewrecords: true,
    gridview: true,
   	caption: "Manipulating Array Data"});
});
</script>
</head>
<body >
	<H1>Welcome To Your Home Page <% out.println(request.getParameter("userName"));%> ${json}</H1>
	
	<table id="list4"></table>
</body>
</html>