<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Approve Timecards</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="styles/global.css" type="text/css" />
</head>
<body>

	<%@ include file="/common/menubar.jsp" %>

    <div class="content">

        <div class="sidebar">
            <div class="timecardstatus">
                <h2>Approve Timecards</h2>
                <table class="timecardtable">
                    <tr>
                        <th>Start Date</th>
                        <th>Submitter</th>
                    </tr>
                    <c:forEach var="submittedTimecard" items="${submittedTimecards}">
                    <tr>
                        <td><a href="<c:url value="timecard-approve.htm?timecardId=${submittedTimecard.id}"/>">${submittedTimecard.startDate}</a></td>
                        <td>${submittedTimecard.submitter}</td>
                     </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <div>
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
					        <td>
					          ${timecard.submitter}
					        </td>
					        <td>
					          ${timecard.approver}
					        </td>
					        <td>
					          ${timecard.status}
					        </td>
					        <td>
					          ${timecard.startDate}
					        </td>
                        </tr>
                    </table>
                </div>

                <br />

                <div class="timecard">

                    <table class="half_width">
                        <colgroup span="4" style="width:25%;" />
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Hours</th>
                                <th>Minutes</th>
                                <th>Task</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="alloc" items="${timecard.timeAllocationList}">
                            <tr>
					            <td>
					              <c:out value="${alloc.taskDate}"/>
					            </td>
					            <td>
					              <c:out value="${alloc.hours}"/>
					            </td>
					            <td>
					              <c:out value="${alloc.minutes}"/>
					            </td>
					            <td>
					              <c:out value="${alloc.task}"/>
					            </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

             <form:form commandName="timecard" action="timecard-approve.htm">
                <div class="comments">
                    <label for="comments">Comments</label><br />
                    <form:textarea path="comments" rows="2" cols="80"/> 
                </div>

                <div>
                  <input type="submit" value="Submit" name="timecardAction"/>
                  <input type="submit" value="Reject" name="timecardAction"/>
                </div>
            </form:form>

        </div>
    
    </div>
</body>
</html>