package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import tool.DBUtil;

public class SubjectDAO {

    public List<Subject> findAll() {

        List<Subject> list = new ArrayList<>();

        try (
            Connection con = DBUtil.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT CD, NAME FROM SUBJECT ORDER BY CD")
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Subject s = new Subject();

                s.setCode(rs.getString("CD"));
                s.setName(rs.getString("NAME"));

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}