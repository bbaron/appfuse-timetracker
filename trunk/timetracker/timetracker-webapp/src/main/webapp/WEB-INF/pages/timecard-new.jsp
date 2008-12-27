<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>New Timecard Setup</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="styles/global.css" type="text/css" />
</head>
<body>

	<%@ include file="/common/menubar.jsp" %>

    <div class="content">
        <h2>New Timecard Setup</h2>
        <div class="fieldgroup">
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
