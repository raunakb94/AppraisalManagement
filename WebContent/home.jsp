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
var lastsel;
$(document).ready(function(){
	jQuery("#list4").empty().jqGrid({
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
		jQuery("#pendingappraisals").empty().jqGrid({
			url : "http://localhost:9999/apptrack/login?id=pending&q=0",
			datatype: "json",
			colNames:['Appraisal Id','Start Date'],
		   	colModel:[
		   				{name:"appraisalId", width:100,editable:true},
						{name:"startDate", width:100,editable:true}
						],
		   	mtype : "get",
		   	height : "100%",
		   	width : 500,
		   	loadonce: true,
		   	viewrecords: true,
		    gridview: true,
		   	caption: "List OF Pending Appraisal",
		   	subGrid : true,
		   	jsonReader : {
		   		repeatitems : false,
		   		id : 'appraisalId'
				},
				subGridRowExpanded: function(subgrid_id, row_id) {
					var subgrid_table_id;
					subgrid_table_id = subgrid_id+"_t";
					$("#"+subgrid_id).html("<table id='"+subgrid_table_id+"'></table>");
					jQuery("#"+subgrid_table_id).jqGrid({
						url : 'http://localhost:9999/apptrack/login?id=pending&q='+row_id,
						datatype : 'json',
						colNames: ['Id','Category','Description'],
						colModel: [
						     {name : "goalId",width:10},
							 {name:"type",width:80},
							 {name:"description",width:130,editable:true}],
					    height: '100%',
					    jsonReader:{
					    	repeatitems:false,
					    	id : 'goalId'
					    },
					    onSelectRow: function(id){
							if(id && id!==lastsel){
								jQuery("#"+subgrid_table_id).jqGrid('restoreRow',lastsel);
								jQuery("#"+subgrid_table_id).jqGrid('editGridRow',id,{reloadAfterSubmit : false,closeAfterEdit :true});
								lastsel=id;
							}
						},
						editurl : "http://localhost:9999/apptrack/transaction",
					    viewrecords : true,
					    loadonce : true,
					gridview : true,
					height : "100%",
					width : '100%'
					});
				}
	  });
		
	});
	$("#completedappraisal").click(function(){
	    	alert("Pendinggggg");
	  });
	});

</script>
</head>
<body >
	<H1>Welcome To Your Home Page</H1>
	<table id="list4"></table>
	<input type="button" value="List Of Pending Appraisal" id="pendingdata">
	<input type="button" value="List Of Completed Appraisal" id="completedappraisal">
	<table id="pendingappraisals">Pending Appraisals</table>
	<table id="completedappraisal">Completed Appraisal</table>
</body>
</html>