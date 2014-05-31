<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${firstName}</title>
<link rel = "stylesheet" href="/apptrack/gridJS/css/ui.jqgrid.css" type = "text/css" /> 
<link rel = "stylesheet" href="/apptrack/gridJS/css/jquery-ui-1.10.4.custom.css" type = "text/css" /> 
<script src="/apptrack/gridJS/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="/apptrack/gridJS/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="/apptrack/gridJS/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	  $("#hide").click(function(){
	    $("p").hide();
	  });
	  $("#show").click(function(){
	    $("p").show();
	  });
	  jQuery("#datagrid").jqGrid({
		   	url: '/apptrack/login',
		    datatype: 'json',
		    colNames:['First Name','Last Name', 'Email Id', 'Your Employee Id','Manager Id'],
		    colModel:[
				{name:'firstName', width:100},
				{name:'lastName', width:100},
				{name:'eMail',width:100},
				{name:'empId',width:100},
				{name:'managerID',width:100}
			],
			mtype:'POST',
			rowNum:1,
			viewrecords: true,
			gridview: true,
	        autoencode: true,
			caption:"Personal Details"				
		});
		jQuery("#datagrid").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
	});
</script>
</head>
<body >
	<H1>Welcome To Your Home Page <% out.println(request.getParameter("userName"));%></H1>
	<table id="datagrid"></table>
	<div id="pager2"></div>
</body>
</html>