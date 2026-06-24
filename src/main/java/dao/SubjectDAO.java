package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import tool.DBUtil;

public class SubjectDAO {

    // 科目一覧取得
	public List<Subject> findAll() {
		
		List<Subject> list = new ArrayList<>();

	    String sql =
	            "SELECT SCHOOL_CD, CD, NAME " +
	            "FROM SUBJECT " +
	            "ORDER BY CD";

	    try (
	        Connection con = DBUtil.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery()
	    ) {

	        while (rs.next()) {

	            System.out.println(
	                rs.getString("CD") + " : " +
	                rs.getString("NAME")
	            );

	            Subject subject = new Subject();

	            subject.setCd(rs.getString("CD"));
	            subject.setName(rs.getString("NAME"));

	            list.add(subject);
	        }

	        System.out.println("取得件数 = " + list.size());

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

    // 科目コードで検索
    public Subject findByCd(String cd) {

        Subject subject = null;

        String sql =
                "SELECT SCHOOL_CD, CD, NAME " +
                "FROM SUBJECT " +
                "WHERE CD = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, cd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                subject = new Subject();

                subject.setCd(rs.getString("CD"));
                subject.setName(rs.getString("NAME"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    // 科目登録
    public boolean insert(Subject subject) {

        String sql =
                "INSERT INTO SUBJECT (SCHOOL_CD, CD, NAME) " +
                "VALUES (?, ?, ?)";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            // 学校コードは固定値
            ps.setString(1, "oom");

            ps.setString(2, subject.getCd());
            ps.setString(3, subject.getName());

            int count = ps.executeUpdate();

            return count > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 科目更新
    public boolean update(Subject subject) {

        String sql =
            "UPDATE SUBJECT " +
            "SET NAME = ? " +
            "WHERE CD = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, subject.getName());
            ps.setString(2, subject.getCd());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 科目削除
    public boolean delete(String cd) {

        String sql =
                "DELETE FROM SUBJECT " +
                "WHERE CD = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, cd);

            int count = ps.executeUpdate();

            return count > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}