package DatabaseMetaInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {

    private static final String password = "root";
    private static final String username = "root";
    private static final String url = "jdbc:mysql://localhost:3306/binder";

    private static Connection con;

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }

    }

    public static Connection getConnection(){
        return con;
    }

    public void destroy() throws SQLException {
        con.close();
    }
}
