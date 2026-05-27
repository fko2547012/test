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

            // Listへ追加
            list.add(s);
        }

        // DB切断
        rs.close();
        st.close();
        con.close();

        return list;
    }
}