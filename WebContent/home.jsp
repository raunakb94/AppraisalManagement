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
	jQuery("#list4").jqGrid({
		url : "http://localhost:9999/apptrack/login?id=details&q=0",
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
	   	caption: "Your Personal Details As From Our Databases"
	   	});
	$("#pendingdata").click(function(){
		jQuery("#pendingappraisals").jqGrid({
			url : "http://localhost:9999/apptrack/login?id=pending&q=0",
			datatype: "json",
			colNames:['Appraisal Id','Start Date','Actions'],
		   	colModel:[
		   				{name:"appraisalId", width:100,editable:true},
						{name:"startDate", width:100,editable:true},
						{name:"act",width:100}
						],
		   	mtype : "get",
		   	height : "100%",
		   	width : 500,
		   	viewrecords: true,
		    gridview: true,
		   	caption: "List OF Pending Appraisal",
		   	jsonReader : {
		   		repeatitems : false,
		   		id : 'appraisalId'
				},
			gridComplete: function(){
				var ids = jQuery("#pendingappraisals").jqGrid('getDataIDs');
				for(var i=0;i < ids.length;i++){
					var cl = ids[i];
					be = "<input type='button' value='Attend' onclick = \"$(\"#dialog\").dialog(\"open\")\">";
					jQuery("#pendingappraisals").jqGrid('setRowData',cl,{act:be});
				}
			},
			onSelectRow : function(id){
				window.open("http://localhost:9999/apptrack/form.jsp?appid="+id,600,1000);
			}
	  });
		
	});
	 
	$("#completedappraisalbutton").click(function(){
		jQuery("#completedappraisal").jqGrid({
			url : "http://localhost:9999/apptrack/login?id=completed&q=0",
			datatype: "json",
			colNames:['Appraisal Id','Start Date','Score'],
		   	colModel:[
		   				{name:"appraisalId", width:100},
						{name:"startDate", width:100},
						{name:"score",width:100}
						],
		   	mtype : "get",
		   	height : "100%",
		   	width : 500,
		   	loadonce: false,
		   	viewrecords: true,
		    gridview: true,
		   	caption: "List OF Completed Appraisal",
		   	jsonReader : {
		   		repeatitems : false,
		   		id : 'appraisalId'
				}
			});
	  });
	});
</script>
</head>
<body >
	<H1>Welcome To Your Home Page</H1>
	<table id="list4"></table>
	<input type="button" value="List Of Pending Appraisal" id="pendingdata">
	<input type="button" value="List Of Completed Appraisal" id="completedappraisalbutton">
	<table id="pendingappraisals">Pending Appraisals</table>
	<table id="completedappraisal">Completed Appraisal</table>
	<% 	
	Object	managerId = request.getAttribute("employee");
		out.print(managerId);
	%>
</body>
</html>