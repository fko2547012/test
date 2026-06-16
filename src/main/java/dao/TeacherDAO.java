package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDAO extends Dao {

    public Teacher get(String id) throws Exception {
        Teacher teacher = null;
        String sql = "SELECT * FROM teacher WHERE id = ?";
        
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setId(rs.getString("id"));
                    teacher.setName(rs.getString("name"));
                    
                    // 学校情報の紐付け
                    SchoolDao schoolDao = new SchoolDao();
                    School school = schoolDao.get(rs.getString("school_cd"));
                    teacher.setSchool(school);
                }
            }
        }
        return teacher;
    }

    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;
        // パスワードとIDが一致する教員を検索
        String sql = "SELECT * FROM teacher WHERE id = ? AND password = ?";
        
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, id);
            st.setString(2, password);
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setId(rs.getString("id"));
                    teacher.setPassword(rs.getString("password"));
                    teacher.setName(rs.getString("name"));
                    teacher.setAuthenticated(true);
                    
                    SchoolDao schoolDao = new SchoolDao();
                    School school = schoolDao.get(rs.getString("school_cd"));
                    teacher.setSchool(school);
                }
            }
        }
        return teacher;
    }
}
