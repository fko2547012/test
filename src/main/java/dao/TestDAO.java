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

public class TestDAO extends Dao {

    /**
     * テスト結果をデータベースに登録する (成績登録画面用)
     */
    public boolean save(Test test) throws Exception {
        Connection con = getConnection();

        String sql =
            "INSERT INTO TEST "
          + "(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) "
          + "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, test.getStudent().getNo());
        st.setString(2, test.getSubject().getCd());
        st.setString(3, test.getSchool().getCd());
        st.setInt(4, test.getNo());
        st.setInt(5, test.getPoint());
        st.setString(6, test.getClassNum());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result > 0;
    }
  
    public List<Test> getTestListByStudent(String studentNo) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection con = getConnection();

        // 💡 画面で科目名（SUBJECT.NAME）を表示できるようにJOINしています
        String sql = 
            "SELECT t.STUDENT_NO, t.SUBJECT_CD, sub.NAME AS SUBJECT_NAME, t.SCHOOL_CD, t.NO, t.POINT, t.CLASS_NUM "
          + "FROM TEST t "
          + "JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD "
          + "WHERE t.STUDENT_NO = ? "
          + "ORDER BY t.NO ASC, t.SUBJECT_CD ASC"; // 回数順、科目コード順に並び替え

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, studentNo); // 引数で受け取った学生番号をセット

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Test test = new Test();
            test.setClassNum(rs.getString("CLASS_NUM"));
            test.setNo(rs.getInt("NO"));
            test.setPoint(rs.getInt("POINT"));

            // 学生情報をセット（番号のみ）
            Student student = new Student();
            student.setNo(rs.getString("STUDENT_NO"));
            test.setStudent(student);

            // 科目情報をセット（コードと科目名）
            Subject subject = new Subject();
            subject.setCd(rs.getString("SUBJECT_CD"));
            subject.setName(rs.getString("SUBJECT_NAME"));
            test.setSubject(subject);

            // 学校情報をセット
            School school = new School();
            school.setCd(rs.getString("SCHOOL_CD"));
            test.setSchool(school);

            list.add(test);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}
