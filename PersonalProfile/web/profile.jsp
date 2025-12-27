<%-- 
    Document   : profile
    Created on : Nov 26, 2025, 11:38:54 AM
    Author     : Afrina Natasha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Profile</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="card">
    <h2>Personal Profile Summary</h2>

    <%-- Message area --%>
    <div class="msg-wrap">
        <c:if test="${not empty error}">
            <div class="msg msg-error">${error}</div>
        </c:if>

        <c:if test="${not empty success}">
            <div class="msg msg-success">${success}</div>
        </c:if>
    </div>

    <p><span class="label">Name:</span> ${profile.name}</p>
    <p><span class="label">Student ID:</span> ${profile.studentId}</p>
    <p><span class="label">Programme:</span> ${profile.programme}</p>
    <p><span class="label">Email:</span> ${profile.email}</p>
    <p><span class="label">Hobbies:</span> ${profile.hobbies}</p>

    <div class="intro-box">
        <span class="label">Self Introduction:</span>
        <p>${profile.intro}</p>
    </div>

    <p class="center-link">
        <a href="ViewProfilesServlet">View All Profiles</a>
    </p>
</div>

</body>
</html>
