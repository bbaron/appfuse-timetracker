<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Edit Timecard</title>
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
            <li><a class="selected" href="timecard.htm">Timecard Details</a></li>
            <li><a href="search-timecards.htm">Search Timecards</a></li>
            <li><a href="approve-timecards.htm">Approve Timecards</a></li>
        </ul>
    </div>

    <div class="content">
        <form:form commandName="timeAllocation">
            <h2>Timecard</h2>

            <div class="timecardstatus">
                <table class="timecardtable">
                    <tr>
                        <th>Submitter</th>
                        <th>Approver</th>
                        <th>Status</th>
                        <th>Start Date</th>
                    </tr>
                    <tr>
                        <td>${timecard.submitter}</td>
                        <td>${timecard.approver}</td>
                        <td>${timecard.status}</td>
                        <td><fmt:formatDate value="${timecard.startDate}" type="date" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </table>
            </div>

            <div class="timecard">
                <table class="full_width">
                    <colgroup span="4" style="width:22%;" />
                    <colgroup span="1" style="width:12%;" />
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Hours</th>
                            <th>Minutes</th>
                            <th>Task</th>
                            <th class="align_center"><a class="button" href="">Delete</a></th>
                        </tr>
                    </thead>
                    <tbody>
				        <c:forEach var="alloc" items="${timecard.timeAllocationList}">
                        <tr>
                            <td><fmt:formatDate value="${alloc.taskDate}" type="date" pattern="yyyy-MM-dd"/></td>
                            <td><c:out value="${alloc.hours}"/></td>
                            <td><c:out value="${alloc.minutes}"/></td>
                            <td><c:out value="${alloc.task}"/></td>
                            <td class="align_center"><input class="checkbox" type="checkbox" /></td>
                        </tr>
	        			</c:forEach>
                    </tbody>
                </table>

                <table class="add_allocation_table bordered_table full_width">
                    <colgroup span="4" style="width:22%;" />
                    <colgroup span="1" style="width:12%;" />
                    <tbody>
                        <tr>
                            <td>
				               <form:select path="taskDate">
				                   <form:options items="${timecard.dateSelection}"/>
				               </form:select>
                            </td>
                            <td><form:input path="hours" size="2"/></td>
                            <td><form:input path="minutes" size="2"/></td>
                            <td>
				               <form:select path="task">
				                   <form:option value="" label="--Please Select"/>
				                   <form:options items="${tasks}"/>
				               </form:select>
				               <form:errors path="task" cssClass="error"/>
                            </td>
                            <td class="align_center"><input type="submit" value="Add" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="comments">
                <label for="comments">Comments</label><br />
                <textarea id="comments" rows="2" cols="80"></textarea>
            </div>

            <div>
                <a class="button" href="timecard.htm">Save</a>
                <a class="button" href="search-timecards.htm">Delete</a>
                <a class="button" href="timecard.htm">Submit</a>
            </div>

        </form:form>
    </div>
</body>
</html>