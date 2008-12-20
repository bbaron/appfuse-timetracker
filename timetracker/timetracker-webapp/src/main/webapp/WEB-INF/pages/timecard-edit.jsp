<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE 
    html
    PUBLIC
    "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
  <head>
    <title>
      Edit Timecard
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="styles/global.css" type="text/css" />
  </head>
  <body>

	<%@ include file="/common/menubar.jsp" %>

<div class="content">
  <h2>
    Timecard
  </h2>

  <div class="timecardstatus">
    <table class="timecardtable">
      <tr>
        <th>
          Submitter
        </th>
        <th>
          Approver
        </th>
        <th>
          Status
        </th>
        <th>
          Start Date
        </th>
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

  <div class="timecard">
    <table class="full_width">
      <colgroup span="4" style="width:22%;" />
      <colgroup span="1" style="width:12%;" />
      <thead>
        <tr>
          <th>
            Date
          </th>
          <th>
            Hours
          </th>
          <th>
            Minutes
          </th>
          <th>
            Task
          </th>
          <th class="align_center">
            <a class="button" href="">
              Delete
            </a>
          </th>
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
            <td class="align_center">
              <input class="checkbox" type="checkbox" />
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <form:form commandName="timeAllocation" action="timecard-enter-time-allocation.htm?timecardId=${timecard.id}">
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
            <td>
              <form:input path="hours" size="2"/>
            </td>
            <td>
              <form:input path="minutes" size="2"/>
            </td>
            <td>
              <form:select path="task">
                <form:option value="" label="--Please Select"/>
                <form:options items="${tasks}"/>
              </form:select>
              <form:errors path="task" cssClass="error"/>
            </td>
            <td class="align_center">
              <input type="submit" value="Add" />
            </td>
          </tr>
        </tbody>
      </table>
    </form:form>
  </div>

  <form:form action="timecard-save.htm?timecardId=${timecard.id}" commandName="timecard">
  <div class="comments">
    <label for="comments">
      Comments
    </label>
    <br />
    <form:textarea path="comments" rows="2" cols="80"/> 
  </div>

  <div>
    <input type="submit" value="Save" />
    <a class="button" href="timecard-search.htm">
      Delete
    </a>
    <a class="button" href="timecard-submit.htm?timecardId=${timecard.id}">
      Submit
    </a>
  </div>
  </form:form>

</div>
</body>
</html>
