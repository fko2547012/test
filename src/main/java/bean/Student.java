package bean;

public class Student {

    // 学生番号
    private String no;

    // 氏名
    private String name;

    // 入学年度
    private int entYear;

    // クラス番号
    private String classNum;

    // ===== getter / setter =====

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}