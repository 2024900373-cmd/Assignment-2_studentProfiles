package com.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO {

    // semak student id dah wujud atau belum
    public boolean existsStudentId(String studentId) throws Exception {
        String sql = "SELECT 1 FROM profiles WHERE student_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // insert profile baru
    public void insert(ProfileBean profile) throws Exception {
        String sql = "INSERT INTO profiles (name, student_id, programme, email, hobbies, intro) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, profile.getName());
            ps.setString(2, profile.getStudentId());
            ps.setString(3, profile.getProgramme());
            ps.setString(4, profile.getEmail());
            ps.setString(5, profile.getHobbies());
            ps.setString(6, profile.getIntro());

            ps.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException dup) {
            // bila student_id kena UNIQUE constraint
            throw new Exception("Student ID already exists. Please use a different Student ID.", dup);
        }
    }

    // dropdown programme
    public List<String> getProgrammes() throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT programme FROM profiles "
                   + "WHERE programme IS NOT NULL AND TRIM(programme) <> '' "
                   + "ORDER BY programme";

        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(rs.getString(1));
            }
        }
        return list;
    }

    // list semua
    public List<ProfileBean> getAll() throws Exception {
        List<ProfileBean> list = new ArrayList<>();
        String sql = "SELECT id, name, student_id, programme, email, hobbies, intro "
                   + "FROM profiles ORDER BY id DESC";

        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    // filter ikut programme
    public List<ProfileBean> getByProgramme(String programme) throws Exception {
        List<ProfileBean> list = new ArrayList<>();
        String sql = "SELECT id, name, student_id, programme, email, hobbies, intro "
                   + "FROM profiles WHERE programme = ? ORDER BY id DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, programme);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    // optional guna kalau nak cari 1 record ikut student id
    public ProfileBean getByStudentId(String studentId) throws Exception {
        String sql = "SELECT id, name, student_id, programme, email, hobbies, intro "
                   + "FROM profiles WHERE student_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // helper map resultset ke bean
    private ProfileBean mapRow(ResultSet rs) throws Exception {
        ProfileBean p = new ProfileBean();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setStudentId(rs.getString("student_id"));
        p.setProgramme(rs.getString("programme"));
        p.setEmail(rs.getString("email"));
        p.setHobbies(rs.getString("hobbies"));
        p.setIntro(rs.getString("intro"));
        return p;
    }
}
