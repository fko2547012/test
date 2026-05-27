package tool;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getConnection() throws Exception {

        // H2ドライバ読み込み
        Class.forName("org.h2.Driver");

        String url = "jdbc:h2:file:C:/data/example";

        String user = "sa";

        String password = "";

        Connection con = DriverManager.getConnection(
                url,
                user,
                password
        );

        return con;
    }
}