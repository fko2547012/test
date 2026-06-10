package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tool.DBUtil;

public class ClassNumDAO {

    public List<String> findAll() throws Exception {

        List<String> list =
                new ArrayList<>();

        Connection con =
                DBUtil.getConnection();

        String sql =
                "SELECT CLASS_NUM " +
                "FROM CLASS_NUM " +
                "ORDER BY CLASS_NUM";

        PreparedStatement st =
                con.prepareStatement(sql);

        ResultSet rs =
                st.executeQuery();

        while (rs.next()) {

            list.add(
                rs.getString("CLASS_NUM"));
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}