<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Search Timecards</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="styles/global.css" type="text/css" />
</head>
<body>

    <div class="topmenu">
        <a class="menu" href="login.htm">Log out [nbhatia]</a>
    </div>

    <div class="header">
        <h1><span>Time</span>Tracker</h1>
    </div>

    <div class="menubar">
        <ul>
            <li class="first"><a href="home.htm">Home</a></li>
            <li><a href="timecard-edit.htm">Timecard Details</a></li>
            <li><a class="selected" href="timecard-search.htm">Search Timecards</a></li>
            <li><a href="timecard-approve.htm">Approve Timecards</a></li>
        </ul>
    </div>

    <div class="content">

        <div class="sidebar">
	        <form:form commandName="criteria">

                <h2>Search Criteria</h2>

                <h3>Submitter</h3>
               <form:select path="submitterId">
                   <form:option value="" label="-- ALL --"/>
                   <form:options items="${users}"/>
               </form:select>

                <h3>Approver</h3>
               <form:select path="approverId">
                   <form:option value="" label="-- ALL --"/>
                   <form:options items="${users}"/>
               </form:select>

                <h3>Status</h3>
               <form:select path="status">
                   <form:option value="" label="-- ALL --"/>
                   <form:options items="${statuses}"/>
               </form:select>

                <h3>Start Date Minimum</h3>
                <form:input path="startDateMin" id="start_date_minimum" />

                <h3>Start Date Maximum</h3>
                <form:input path="startDateMax" id="start_date_maximum" />

                <br /><br />
                <input type="submit" value="Search" />
			</form:form>
        </div>

        <div>
                <h2>Search Results</h2>

                <div class="timecard">

                    <table class="half_width">
                        <thead>
                            <tr>
                                <th>Submitter</th>
                                <th>Approver</th>
                                <th>Status</th>
                                <th>Start Date</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="timecard" items="${timecards}">
                            <tr>
	                            <td><c:out value="${timecard.submitter.username}"/></td>
	                            <td><c:out value="${timecard.approver.username}"/></td>
	                            <td><c:out value="${timecard.status}"/></td>
	                            <td><fmt:formatDate value="${timecard.startDate}" type="date" pattern="yyyy-MM-dd"/></td>
                                <td><a href="<c:url value="timecard-edit.htm?timecardId=${timecard.id}" />">Details</a></td>
                            </tr>
                        </c:forEach>    
                        </tbody>
                    </table>
                </div>

        </div>

    </div>
</body>
</html>