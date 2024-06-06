import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static String host = "servicop2poo-p2poo.f.aivencloud.com";
    public static String db = "defaultdb";
    public static String port = "10794";
    public static String password = "AVNS_90mlA8zIeTQt1r2KN94";
    public static String user = "avnadmin";

    public static Connection getConnection() throws Exception{
        var s = String.format("jdbc:postgresql://%s:%s/%s", host, port, db);
        Connection c = DriverManager.getConnection(s, user, password);
        return c;
    }
}
