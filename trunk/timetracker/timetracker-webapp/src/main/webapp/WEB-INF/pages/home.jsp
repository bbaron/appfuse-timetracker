<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags/mytags" prefix="my"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>TimeTracker Home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="styles/global.css" type="text/css" />
</head>
<body>

	<%@ include file="/common/menubar.jsp" %>

    <div class="content">
        <h2>Welcome to TimeTracker</h2>
        <!--
            The table looks much better than separating the links with breaks or
            putting them in a list.
        -->
        <table>
            <tbody>
                <tr>
                    <td><a href="timecard-new.htm">New timecard</a></td>
                </tr>
                <c:if test="${hasLatest}">
                <tr>
                    <td><a href="timecard-edit.htm">Last saved timecard</a></td>
                </tr>
                </c:if>
                <tr>
                    <td><a href="timecard-search.htm">Search timecards</a></td>
                </tr>
                <tr>
                    <td><a href="timecard-approve.htm">Approve timecards</a></td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>