package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import tool.DBUtil;

public class TestDAO {

/**
 * 成績登録
 */
	public boolean save(Test test) throws Exception {

	    Connection con =
	            DBUtil.getConnection();

	    String sql =
	        "INSERT INTO TEST "
	      + "(STUDENT_NO, SUBJECT_NO, SCHOOL_CD, NO, POINT, CLASS_NUM) "
	      + "VALUES (?, ?, ?, ?, ?, ?)";

	    PreparedStatement st =
	            con.prepareStatement(sql);

	    st.setString(1,
	            test.getStudent().getNo());

	    st.setString(2,
	            test.getSubject().getCd());

	    st.setString(3,
	            test.getSchool().getCd());

	    st.setString(4,
	            String.valueOf(test.getNo()));

	    st.setInt(5,
	            test.getPoint());

	    st.setString(6,
	            test.getClassNum());

	    int result =
	            st.executeUpdate();

	    st.close();
	    con.close();

	    return result > 0;
	}
/**
 * 成績更新
 */
public boolean update(Test test)
        throws Exception {

    Connection con =
            DBUtil.getConnection();

    String sql =
        "UPDATE TEST "
      + "SET POINT = ? "
      + "WHERE STUDENT_NO = ? "
      + "AND SUBJECT_NO = ? "
      + "AND NO = ?";

    PreparedStatement st =
            con.prepareStatement(sql);

    st.setInt(
            1,
            test.getPoint());

    st.setString(
            2,
            test.getStudent().getNo());

    st.setString(
            3,
            test.getSubject().getCd());

    st.setString(
            4,
            String.valueOf(test.getNo()));

    int result =
            st.executeUpdate();

    st.close();
    con.close();

    return result > 0;
}

/**
 * 成績削除（POINTをNULLにする）
 */
public boolean clearPoint(
        String studentNo,
        String subjectNo,
        int no)
        throws Exception {

    Connection con =
            DBUtil.getConnection();

    String sql =
        "UPDATE TEST "
      + "SET POINT = NULL "
      + "WHERE STUDENT_NO = ? "
      + "AND SUBJECT_NO = ? "
      + "AND NO = ?";

    PreparedStatement st =
            con.prepareStatement(sql);

    st.setString(1, studentNo);
    st.setString(2, subjectNo);
    st.setString(3,String.valueOf(no));

    int result =
            st.executeUpdate();

    st.close();
    con.close();

    return result > 0;
}

/**
 * 成績登録・変更画面用検索
 */
public List<Test> findForRegist(
        int entYear,
        String classNum,
        String subjectNo,
        int no)
        throws Exception {

    List<Test> list =
            new ArrayList<>();

    Connection con =
            DBUtil.getConnection();

    String sql =
        "SELECT "
      + "S.NO, "
      + "S.NAME, "
      + "S.CLASS_NUM, "
      + "T.POINT "
      + "FROM STUDENT S "
      + "LEFT JOIN TEST T "
      + "ON S.NO = T.STUDENT_NO "
      + "AND T.SUBJECT_NO = ? "
      + "AND T.NO = ? "
      + "WHERE S.ENT_YEAR = ? "
      + "AND S.CLASS_NUM = ? "
      + "AND S.IS_ATTEND = TRUE "
      + "ORDER BY S.NO";

    PreparedStatement st =
            con.prepareStatement(sql);

    st.setString(1, subjectNo);
    st.setString(2,String.valueOf(no));
    st.setInt(3, entYear);
    st.setString(4, classNum);

    ResultSet rs =
            st.executeQuery();

    while (rs.next()) {

        Test test =
                new Test();

        Student student =
                new Student();

        student.setNo(
                rs.getString("NO"));

        student.setName(
                rs.getString("NAME"));

        test.setStudent(student);

        test.setClassNum(
                rs.getString("CLASS_NUM"));

        test.setNo(no);

        Integer point = (Integer) rs.getObject("POINT");

        if (point != null) {
            test.setPoint(point);
        } else {
            test.setPoint(-1);
        }

        list.add(test);
    }

    rs.close();
    st.close();
    con.close();

    return list;
}

/**
 * 学生ごとの成績取得
 */
public List<Test> getTestListByStudent(
        String studentNo)
        throws Exception {

    List<Test> list =
            new ArrayList<>();

    Connection con =
            DBUtil.getConnection();

    String sql =
        "SELECT "
      + "t.STUDENT_NO, "
      + "t.SUBJECT_NO, "
      + "sub.NAME AS SUBJECT_NAME, "
      + "t.SCHOOL_CD, "
      + "t.NO, "
      + "t.POINT, "
      + "t.CLASS_NUM "
      + "FROM TEST t "
      + "JOIN SUBJECT sub "
      + "ON t.SUBJECT_NO = sub.CD "
      + "WHERE t.STUDENT_NO = ? "
      + "ORDER BY t.NO ASC, "
      + "t.SUBJECT_NO ASC";

    PreparedStatement st =
            con.prepareStatement(sql);

    st.setString(
            1,
            studentNo);

    ResultSet rs =
            st.executeQuery();

    while (rs.next()) {

        Test test =
                new Test();

        test.setClassNum(
                rs.getString("CLASS_NUM"));

        test.setNo(
                rs.getInt("NO"));

        test.setPoint(
                rs.getInt("POINT"));

        Student student =
                new Student();

        student.setNo(
                rs.getString("STUDENT_NO"));

        test.setStudent(student);

        Subject subject =
                new Subject();

        subject.setCd(
                rs.getString("SUBJECT_NO"));

        subject.setName(
                rs.getString("SUBJECT_NAME"));

        test.setSubject(subject);

        School school =
                new School();

        school.setCd(
                rs.getString("SCHOOL_CD"));

        test.setSchool(school);

        list.add(test);
    }

    rs.close();
    st.close();
    con.close();

    return list;
}

/**
 * 成績変更画面用
 */
public List<Test> findForUpdate(
        int entYear,
        String classNum,
        String subjectNo,
        int no)
        throws Exception {

    List<Test> list =
            new ArrayList<>();

    Connection con =
            DBUtil.getConnection();

    String sql =
        "SELECT "
      + "S.NO, "
      + "S.NAME, "
      + "S.CLASS_NUM, "
      + "T.POINT "
      + "FROM STUDENT S "
      + "INNER JOIN TEST T "
      + "ON S.NO = T.STUDENT_NO "
      + "WHERE S.ENT_YEAR = ? "
      + "AND S.CLASS_NUM = ? "
      + "AND T.SUBJECT_NO = ? "
      + "AND T.NO = ? "
      + "ORDER BY S.NO";

    PreparedStatement st =
            con.prepareStatement(sql);

    st.setInt(1, entYear);
    st.setString(2, classNum);
    st.setString(3, subjectNo);
    st.setString(4, String.valueOf(no));

    ResultSet rs =
            st.executeQuery();

    while (rs.next()) {

        Test test = new Test();

        Student student =
                new Student();

        student.setNo(
                rs.getString("NO"));

        student.setName(
                rs.getString("NAME"));

        test.setStudent(student);

        test.setClassNum(
                rs.getString("CLASS_NUM"));

        test.setNo(no);

        test.setPoint(
                rs.getInt("POINT"));

        list.add(test);
    }

    rs.close();
    st.close();
    con.close();

    return list;
}

}
