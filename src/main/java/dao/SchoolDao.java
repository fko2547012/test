package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import tool.DBUtil;

public class SchoolDao {

    /**
     * 学校コードを元に、学校情報を1件取得する
     */
    public School get(String cd) throws Exception {
        School school = null;
        Connection con = null;
        PreparedStatement st = null;

        try {
            // DBUtilを使って kaadaidb に接続
            con = DBUtil.getConnection();

            // SQL文の準備
            st = con.prepareStatement("SELECT * FROM school WHERE cd = ?");
            st.setString(1, cd);

            // SQLの実行
            ResultSet rs = st.executeQuery();

            // データが存在した場合、Beanに値をセット
            if (rs.next()) {
                school = new School();
                school.setCd(rs.getString("cd"));
                school.setName(rs.getString("name"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            // 接続を閉じる（クローズ処理）
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return school;
    }
}
