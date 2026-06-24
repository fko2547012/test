package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import tool.DBUtil;

public class StudentDAO {

    // 学生一覧取得
    public List<Student> findAll() throws Exception {

        // 学生リスト
        List<Student> list = new ArrayList<>();

        // DB接続
        Connection con = DBUtil.getConnection();

        // SQL
        String sql = "SELECT * FROM STUDENT";

        // SQL準備
        PreparedStatement st = con.prepareStatement(sql);

        // SQL実行
        ResultSet rs = st.executeQuery();

        // データ取得
        while (rs.next()) {

            // Student Bean作成
            Student s = new Student();

            // DBデータをBeanへセット
            s.setNo(rs.getString("NO"));
            s.setName(rs.getString("NAME"));
            s.setEntYear(rs.getInt("ENT_YEAR"));
            s.setClassNum(rs.getString("CLASS_NUM"));
            s.setAttend(rs.getBoolean("IS_ATTEND"));

            // Listへ追加
            list.add(s);
        }

        // DB切断
        rs.close();
        st.close();
        con.close();

        return list;
    }
    
    public boolean insert(Student student) throws Exception {

        Connection con = DBUtil.getConnection();

        String sql =
        	    "INSERT INTO STUDENT " +
        	    "(NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND) " +
        	    "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, student.getNo());
        st.setString(2, student.getName());
        st.setInt(3, student.getEntYear());
        st.setString(4, student.getClassNum());
        st.setBoolean(5, student.isAttend());

        int count = st.executeUpdate();

        st.close();
        con.close();

        return count > 0;
    }
    
    public Student findByNo(String no) throws Exception {

        Student student = null;

        Connection con = DBUtil.getConnection();

        String sql =
            "SELECT * FROM STUDENT " +
            "WHERE NO = ?";

        PreparedStatement st =
            con.prepareStatement(sql);

        st.setString(1, no);

        ResultSet rs =
            st.executeQuery();

        if (rs.next()) {

            student = new Student();

            student.setNo(
                rs.getString("NO"));

            student.setName(
                rs.getString("NAME"));

            student.setEntYear(
                rs.getInt("ENT_YEAR"));

            student.setClassNum(
                rs.getString("CLASS_NUM"));
            
            student.setAttend(
            	    rs.getBoolean("IS_ATTEND"));
        }

        rs.close();
        st.close();
        con.close();

        return student;
    }
    
    public boolean update(Student student)
            throws Exception {

        Connection con =
            DBUtil.getConnection();

        String sql =
        	    "UPDATE STUDENT " +
        	    "SET NAME = ?, " +
        	    "    ENT_YEAR = ?, " +
        	    "    CLASS_NUM = ?, " +
        	    "    IS_ATTEND = ? " +
        	    "WHERE NO = ?";

        PreparedStatement st =
            con.prepareStatement(sql);

        st.setString(1,
                student.getName());

        st.setInt(2,
                student.getEntYear());

        st.setString(3,
                student.getClassNum());

        st.setBoolean(4,
                student.isAttend());

        st.setString(5,
                student.getNo());
        
        int count =
            st.executeUpdate();

        st.close();
        con.close();

        return count > 0;
    }
    
}