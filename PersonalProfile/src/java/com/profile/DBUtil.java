/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profile;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Afrina Natasha
 */
public class DBUtil {

    private static final String URL
            = "jdbc:derby://localhost:1527/student_profiles";
    private static final String USER = "app";
    private static final String PASS = "app";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
