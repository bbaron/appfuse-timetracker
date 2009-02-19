<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="topmenu">
    <a class="menu" href="<c:url value="/logout" />">  Logout&nbsp;[<sec:authentication property="name" />]</a>
</div>

<div class="header">
    <h1><span>Time</span>Tracker</h1>
</div>

<div class="menubar">
    <ul>
        <li class="first"><a class="selected" href="home.htm">Home</a></li>
        <li><a href="timecard-search.htm">Search Timecards</a></li>
        <sec:authorize ifAllGranted="ROLE_APPROVER">
        <li><a href="timecard-approve.htm">Approve Timecards</a></li>
        </sec:authorize>
    </ul>
</div>
