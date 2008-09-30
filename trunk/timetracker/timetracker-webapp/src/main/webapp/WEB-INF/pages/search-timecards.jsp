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
            <li><a href="timecard.htm">Timecard Details</a></li>
            <li><a class="selected" href="search-timecards.htm">Search Timecards</a></li>
            <li><a href="approve-timecards.htm">Approve Timecards</a></li>
        </ul>
    </div>

    <div class="content">

        <div class="sidebar">

                <h2>Search Criteria</h2>

                <h3>Submitter</h3>
                <select name="submitter">
                    <option value="">-- All --</option>
                    <option value="4">cmicali</option>
                    <option value="3">ecrutchfield</option>
                    <option value="2">lcoude</option>
                    <option value="1">nbhatia</option>
                </select>

                <h3>Approver</h3>
                <select name="approver">
                    <option value="">-- All --</option>
                    <option value="4">cmicali</option>
                    <option value="3">ecrutchfield</option>
                    <option value="2">lcoude</option>
                    <option value="1">nbhatia</option>
                </select>

                <h3>Status</h3>
                <select name="status">
                    <option value="">-- All --</option>
                    <option value="0">Draft</option>
                    <option value="1">Submitted</option>
                    <option value="2">Approved</option>
                    <option value="3">Rejected</option>
                </select>

                <h3>Start Date Minimum</h3>
                <input type="text" id="start_date_minimum" />

                <h3>Start Date Maximum</h3>
                <input type="text" id="start_date_maximum" />

                <br /><br />
                <a class="button" href="">Search</a>

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
                            <tr>
                                <td>cmicali</td>
                                <td>nbhatia</td>
                                <td>Draft</td>
                                <td>06/12/2006</td>
                                <td><a href="timecard.htm">Details</a></td>
                            </tr>
                            <tr>
                                <td>cmicali</td>
                                <td>nbhatia</td>
                                <td>Submitted</td>
                                <td>06/05/2006</td>
                                <td><a href="timecard.htm">Details</a></td>
                            </tr>
                            <tr>
                                <td>cmicali</td>
                                <td>nbhatia</td>
                                <td>Submitted</td>
                                <td>05/29/2006</td>
                                <td><a href="timecard.htm">Details</a></td>
                            </tr>
                            <tr>
                                <td>cmicali</td>
                                <td>nbhatia</td>
                                <td>Approved</td>
                                <td>05/22/2006</td>
                                <td><a href="timecard.htm">Details</a></td>
                            </tr>
                            <tr>
                                <td>cmicali</td>
                                <td>nbhatia</td>
                                <td>Approved</td>
                                <td>05/17/2006</td>
                                <td><a href="timecard.htm">Details</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

        </div>

    </div>
</body>
</html>