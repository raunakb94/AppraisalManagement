<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel = "stylesheet" href="/apptrack/gridJS/css/ui.jqgrid.css" type = "text/css" /> 
<link rel = "stylesheet" href="/apptrack/gridJS/css/jquery-ui-1.10.4.custom.css" type = "text/css" /> 
<script src="/apptrack/gridJS/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="/apptrack/gridJS/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="/apptrack/gridJS/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<title>Welcome To your Managerial Section</title>
<script>
$(document).ready(function(){
	var empid = "";
	var name = "empid" + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) 
        	{
        	empid = c.substring(name.length,c.length);
        	}
    }
    jQuery("#list4").jqGrid({
		url : "http://localhost:9999/apptrack/managerdata?criteria=details&empid="+empid,
		datatype: "json",
		colNames:['First Name','Last Name', 'dateJoin', 'Your Employee Id','Manager Id','Post'],
	   	colModel:[
	   				{name:"firstName", width:100},
					{name:"lastName", width:100},
					{name:"dateJoin",width:100},
					{name:"empId",width:100},
					{name:"managerId",width:100},
					{name:"post",width:100}
	   	],
	   	jsonReader: {id: "empId",
	   	         repeatitems: false },
	   	mtype : "get",
	   	height : "100%",
	   	loadonce: true,
	   	viewrecords: true,
	    gridview: true,
	   	caption: "List Of Employees Under You"
	   	});
    $("#pendingRatingsButton").click(function(){
    	
    	jQuery("#pendingRating").jqGrid({
    		url : "http://localhost:9999/apptrack/managerdata?criteria=pending&empid="+empid,
    		datatype: "json",
    		colNames:['Employee ID','Appraisal ID'],
    	   	colModel:[
    	   				{name:"empId", width:100},
    					{name:"appId",width:100}
    	   	],
    	   	jsonReader: {
    	   	         repeatitems: false },
    	   	mtype : "get",
    	   	height : "100%",
    	   	loadonce: true,
    	   	viewrecords: true,
    	    gridview: true,
    	   	caption: "Pending Appraisals To Rate For Different Employees",
    	   	onSelectRow:function(id){
    	   		
    	   		var empId = jQuery("#pendingRating").jqGrid('getCell',id,0);
    	   		var appId = jQuery("#pendingRating").jqGrid('getCell',id,1);
    	   		alert(id);
    	   		window.open("http://localhost:9999/apptrack/ratingForm.jsp?appid="+appId+"&empid="+empId,600,1000);
    	   	}
    	   	});
    	
    });
});
</script>

</head>
<body>
<table id="list4"></table>
<input type="button" id="pendingRatingsButton" value="Pending to Rate" >
<table id="pendingRating" ></table>
</body>
</html>