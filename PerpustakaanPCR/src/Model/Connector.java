package Model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Connector {
    private static java.sql.Connection MySQLConfig;
    
    public static java.sql.Connection configDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/perpustakaanPCR";//url database
            String user = "root";//user database
            String pass = "";//password database

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            MySQLConfig = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Koneksi gagal " + e.getMessage());//perintah menampilkan error pada koneksi
        }
        return MySQLConfig;
    }
}
