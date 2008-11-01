<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>TimeTracker Home</title>
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
            <li class="first"><a class="selected" href="home.html">Home</a></li>
            <li><a href="timecard-edit.htm">Timecard Details</a></li>
            <li><a href="timecard-search.htm">Search Timecards</a></li>
            <li><a href="timecard-approve.htm">Approve Timecards</a></li>
        </ul>
    </div>

    <div class="content">
        <h2>Welcome to TimeTracker</h2>
        <!--
            The table looks much better than separating the links with breaks or
            putting them in a list.
        -->
        <table>
            <tbody>
                <tr>
                    <td><a href="timecard-new.htm?submitterId=2002">New timecard</a></td>
                </tr>
                <tr>
                    <td><a href="timecard-edit.htm?submitterId=2002">Last saved timecard</a></td>
                </tr>
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