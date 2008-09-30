<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Approve Timecards</title>
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
            <li><a href="search-timecards.htm">Search Timecards</a></li>
            <li><a class="selected" href="approve-timecards.htm">Approve Timecards</a></li>
        </ul>
    </div>

    <div class="content">

        <div class="sidebar">
            <div class="timecardstatus">
                <h2>Approve Timecards</h2>
                <table class="timecardtable">
                    <tr>
                        <th>Start Date</th>
                        <th>Submitter</th>
                    </tr>
                    <tr>
                        <td><a href="">05/29/2006</a></td>
                        <td>cmicali</td>
                    </tr>
                    <tr>
                        <td><a href="">05/29/2006</a></td>
                        <td>ecrutchfield</td>
                    </tr>
                    <tr>
                        <td><a href="">05/29/2006</a></td>
                        <td>lcoude</td>
                    </tr>
                    <tr>
                        <td><a href="">06/05/2006</a></td>
                        <td>cmicali</td>
                    </tr>
                    <tr>
                        <td><a href="">06/05/2006</a></td>
                        <td>ecrutchfield</td>
                    </tr>
                    <tr>
                        <td><a href="">06/05/2006</a></td>
                        <td>lcoude</td>
                    </tr>
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
                            <td>cmicali</td>
                            <td>nbhatia</td>
                            <td>Submitted</td>
                            <td>06/05/2006</td>
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
                                <th>Start</th>
                                <th>End</th>
                                <th>Task</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>06/05/2006</td>
                                <td>09:00 AM</td>
                                <td>12:00 PM</td>
                                <td>Analysis</td>
                            </tr>
                            <tr>
                                <td>06/05/2006</td>
                                <td>01:00 PM</td>
                                <td>05:00 PM</td>
                                <td>Research</td>
                            </tr>
                            <tr>
                                <td>06/06/2006</td>
                                <td>09:00 AM</td>
                                <td>12:00 PM</td>
                                <td>Analysis</td>
                            </tr>
                            <tr>
                                <td>06/06/2006</td>
                                <td>01:00 PM</td>
                                <td>05:00 PM</td>
                                <td>Analysis</td>
                            </tr>
                            <tr>
                                <td>06/07/2006</td>
                                <td>09:00 AM</td>
                                <td>12:00 PM</td>
                                <td>Analysis</td>
                            </tr>
                            <tr>
                                <td>06/07/2006</td>
                                <td>01:00 PM</td>
                                <td>05:00 PM</td>
                                <td>Research</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="comments">
                    <label for="comments">Comments</label><br />
                    <textarea id="comments" rows="2" cols="80"></textarea>
                </div>

                <div>
                    <a class="button" href="">Approve</a>
                    <a class="button" href="">Reject</a>
                </div>

        </div>
    </div>
</body>
</html>