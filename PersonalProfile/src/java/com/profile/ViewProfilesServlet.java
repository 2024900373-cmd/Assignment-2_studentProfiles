/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profile;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewProfilesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String programme = request.getParameter("programme");
            if (programme == null) {
                programme = "";
            } else {
                programme = programme.trim();
            }

            ProfileDAO dao = new ProfileDAO();

            List<String> programmes = dao.getProgrammes();
            List<ProfileBean> profiles;

            if (!programme.isEmpty()) {
                profiles = dao.getByProgramme(programme);
            } else {
                profiles = dao.getAll();
            }

            request.setAttribute("programmes", programmes);
            request.setAttribute("selectedProgramme", programme);
            request.setAttribute("profiles", profiles);

            RequestDispatcher rd = request.getRequestDispatcher("viewProfiles.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
