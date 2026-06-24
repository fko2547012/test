package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.StudentResult;
import bean.SubjectResult;
import tool.DBUtil;

public class TestListDAO {

    /**
     * 科目検索
     */
    public List<SubjectResult> findBySubject(
            int entYear,
            String classNum,
            String subjectCd) throws Exception {

        List<SubjectResult> list =
                new ArrayList<>();

        Connection con =
                DBUtil.getConnection();

        String sql =
            "SELECT " +
            "    S.ENT_YEAR, " +
            "    T.CLASS_NUM, " +
            "    S.NO, " +
            "    S.NAME, " +
            "    T.NO AS TEST_NO, " +
            "    T.POINT " +
            "FROM TEST T " +
            "INNER JOIN STUDENT S " +
            "    ON T.STUDENT_NO = S.NO " +
            "WHERE S.ENT_YEAR = ? " +
            "  AND T.CLASS_NUM = ? " +
            "  AND T.SUBJECT_NO = ? " +
            "ORDER BY S.NO, T.NO";

        PreparedStatement st =
                con.prepareStatement(sql);

        st.setInt(1, entYear);
        st.setString(2, classNum);
        st.setString(3, subjectCd);

        ResultSet rs =
                st.executeQuery();

        Map<String, SubjectResult> map =
                new LinkedHashMap<>();

        while (rs.next()) {

            String studentNo =
                    rs.getString("NO");

            SubjectResult result =
                    map.get(studentNo);

            if (result == null) {

                result = new SubjectResult();

                result.setEntYear(
                        rs.getInt("ENT_YEAR"));

                result.setClassNum(
                        rs.getString("CLASS_NUM"));

                result.setStudentNo(studentNo);

                result.setStudentName(
                        rs.getString("NAME"));

                result.setPoint1("-");
                result.setPoint2("-");

                map.put(studentNo, result);
            }

            int testNo =
                    rs.getInt("TEST_NO");

            int point =
                    rs.getInt("POINT");

            if (testNo == 1) {

                result.setPoint1(
                        String.valueOf(point));

            } else if (testNo == 2) {

                result.setPoint2(
                        String.valueOf(point));
            }
        }

        rs.close();
        st.close();
        con.close();

        list.addAll(map.values());

        return list;
    }

    /**
     * 学生検索
     */
    public List<StudentResult> findByStudent(
            String studentNo) throws Exception {

        List<StudentResult> list =
                new ArrayList<>();

        Connection con =
                DBUtil.getConnection();

        String sql =
            "SELECT " +
            "    SUB.NAME, " +
            "    SUB.CD, " +
            "    T.NO AS TEST_NO, " +
            "    T.POINT " +
            "FROM TEST T " +
            "INNER JOIN SUBJECT SUB " +
            "    ON T.SUBJECT_NO = SUB.CD " +
            "WHERE T.STUDENT_NO = ? " +
            "ORDER BY SUB.CD, T.NO";

        PreparedStatement st =
                con.prepareStatement(sql);

        st.setString(1, studentNo);

        ResultSet rs =
                st.executeQuery();

        while (rs.next()) {

            StudentResult result =
                    new StudentResult();

            result.setSubjectName(
                    rs.getString("NAME"));

            result.setSubjectCd(
                    rs.getString("CD"));

            result.setTestNo(
                    rs.getInt("TEST_NO"));

            result.setPoint(
                    rs.getInt("POINT"));

            list.add(result);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}