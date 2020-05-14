package adsd.app.ovapp.ovapp;


import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection
{
    public static Connection Connection() {

        Connection conn = null;

        try
        {

            String url ="jdbc:sqlserver://adsd1.database.windows.net:1433;database=ovapp;";
            String username = "ProgramUser@adsd1";
            String password = "%q*ztyBJxJ9u!";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to Store DB succesfull!");

        }

        catch (Exception ex)
        {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

        return conn;
    }
}
