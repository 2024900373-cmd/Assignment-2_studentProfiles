package com.profile;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = trim(request.getParameter("name"));
        String studentId = trim(request.getParameter("studentId"));
        String programme = trim(request.getParameter("programme"));
        String email = trim(request.getParameter("email"));
        String hobbies = trim(request.getParameter("hobbies"));
        String intro = trim(request.getParameter("intro"));

        ProfileBean profile = new ProfileBean();
        profile.setName(name);
        profile.setStudentId(studentId);
        profile.setProgramme(programme);
        profile.setEmail(email);
        profile.setHobbies(hobbies);
        profile.setIntro(intro);

        try {
            ProfileDAO dao = new ProfileDAO();

            // kalau student id dah ada, bagi mesej dan stay kat profile.jsp
            if (dao.existsStudentId(studentId)) {
                request.setAttribute("profile", profile);
                request.setAttribute("error", "Student ID already exists. Please use a different Student ID.");
                RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
                rd.forward(request, response);
                return;
            }

            dao.insert(profile);

            request.setAttribute("profile", profile);
            request.setAttribute("success", "Profile saved successfully.");
            RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            // fallback supaya tak jadi 500 tanpa mesej
            request.setAttribute("profile", profile);
            request.setAttribute("error", "Failed to save profile: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    private String trim(String s) {
        return s == null ? "" : s.trim();
    }
}
