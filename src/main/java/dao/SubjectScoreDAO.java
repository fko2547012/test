package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.SubjectScore;

public class SubjectScoreDAO {

    // データベース接続用の共通メソッド（環境に合わせて修正してください）
    private Connection getConnection() throws Exception {
        // 例: JDBCドライバーのロードと接続
        String url = "jdbc:mysql://localhost:3306/your_database"; // 実際のDB名に
        String user = "root";
        String password = "password";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 全ての科目スコアを取得するメソッド
     */
    public List<SubjectScore> findAll() throws Exception {
        List<SubjectScore> list = new ArrayList<>();
        
        // SQL文（テーブル名やカラム名は実際のDBに合わせて変更してください）
        String sql = "SELECT student_id, student_name, subject_name, score FROM subject_scores";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // レコードを1行ずつ読み込んでBeanに詰め、リストに追加
            while (rs.next()) {
                SubjectScore score = new SubjectScore();
                score.setStudentId(rs.getString("student_id"));
                score.setStudentName(rs.getString("student_name"));
                score.setSubjectName(rs.getString("subject_name"));
                score.setScore(rs.getInt("score"));
                
                list.add(score);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // サーブレット側でキャッチさせるために再スロー
        }

        return list;
    }
}
