package hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conn {
    
    Connection c;
    Statement s;
    
    // hotel_ms = Database table name
    // root = Database username
    // admin@123 = Database user password
    
    public conn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_ms", "root", "admin@123");
            s = c.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection Error: " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
