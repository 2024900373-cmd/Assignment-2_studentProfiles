<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.profile.ProfileBean"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>All Profiles</title>
        <link rel="stylesheet" href="css/style.css?v=2">
    </head>
    <body>

        <h2>All Profiles</h2>

        <div class="box">

            <div class="filter">
                <form action="ViewProfilesServlet" method="get">
                    <label><b>Filter by Programme:</b></label>

                    <%
                        String selectedProgramme = (String) request.getAttribute("selectedProgramme");
                        if (selectedProgramme == null) {
                            selectedProgramme = "";
                        }

                        List<String> programmes = (List<String>) request.getAttribute("programmes");
                    %>

                    <select name="programme" onchange="this.form.submit()">
                        <option value="" <%= selectedProgramme.equals("") ? "selected" : ""%>>Show All</option>

                        <%
                            if (programmes != null) {
                                for (String prg : programmes) {
                        %>
                        <option value="<%= prg%>" <%= prg.equals(selectedProgramme) ? "selected" : ""%>>
                            <%= prg%>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </form>
            </div>

            <div class="table-wrap">
                <table>
                    <tr>
                        <th>No</th>
                        <th>Student ID</th>
                        <th>Name</th>
                        <th>Programme</th>
                        <th>Email</th>
                        <th>Hobbies</th>
                        <th>Introduction</th>
                    </tr>

                    <%
                        List<ProfileBean> profiles = (List<ProfileBean>) request.getAttribute("profiles");
                        int no = 1;

                        if (profiles != null) {
                            for (ProfileBean p : profiles) {
                    %>
                    <tr>
                        <td><%= no++%></td>
                        <td><%= p.getStudentId()%></td>
                        <td><%= p.getName()%></td>
                        <td><%= p.getProgramme()%></td>

                        <td class="email-col">
                            <a href="mailto:<%= p.getEmail()%>"><%= p.getEmail()%></a>
                        </td>

                        <td class="hobbies-col"><%= p.getHobbies()%></td>
                        <td class="intro-col"><%= p.getIntro()%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>

            <div class="link">
                <a href="index.html">Back to Form</a>
            </div>

        </div>

    </body>
</html>
