package bean;

import java.io.Serializable;

public class SubjectScore implements Serializable {
    private static final long serialVersionUID = 1L;

    // フィールド（データベースのカラムに対応）
    private String studentId;   // 学生ID
    private String studentName; // 学生名（一覧表示であると便利な場合）
    private String subjectName; // 科目名
    private int score;          // 点数

    // 引数なしのコンストラクタ（JavaBeansの規定）
    public SubjectScore() {}

    // コンストラクタ
    public SubjectScore(String studentId, String studentName, String subjectName, int score) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.score = score;
    }

    // ゲッター・セッター
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
