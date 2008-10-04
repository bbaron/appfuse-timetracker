<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>New Timecard Setup</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="styles/global.css" type="text/css" />
</head>
<body>

    <div class="topmenu">
        <a class="menu" href="login.html">Log out [nbhatia]</a>
    </div>

    <div class="header">
        <h1><span>Time</span>Tracker</h1>
    </div>

    <div class="menubar">
        <ul>
            <li class="first"><a href="home.htm">Home</a></li>
            <li><a class="selected" href="timecard.htm">Timecard Details</a></li>
            <li><a href="search-timecards.htm">Search Timecards</a></li>
            <li><a href="approve-timecards.htm">Approve Timecards</a></li>
        </ul>
    </div>

    <div class="content">
        <h2>New Timecard Setup</h2>
        <div class="fieldgroup">
        <!--
            <label for="startdate">Start Date:</label>
            <input type="text" id="startdate"/>
            <br /><br />
            <a class="button" href="timecard.htm">Create New Timecard</a>
        -->
        <form:form method="POST" modelAttribute="timecard">
            <label for="startdate">Start Date:</label>
			<form:input path="startDate" />        
			<form:errors path="startDate" />
			<input type="submit" value="Create New Timecard" />        
        </form:form>
        </div>
    </div>
</body>
</html>
