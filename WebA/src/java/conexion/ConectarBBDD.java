/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author admin
 */
public class ConectarBBDD {
    
    
    public  static Connection conectaMariaDB(){
        // C:\Program Files\MariaDB 10.4\bin>mysql -u root -p
        final String DRIVERMariaDB ="org.mariadb.jdbc.Driver";
        final String URL_MariaDB ="jdbc:mariadb://127.0.0.1:3306/dgt";
        final String usuario="root";
        final String pass="";
        Connection conn=null;
       // Statement statement = null;
        try{
            Class.forName(DRIVERMariaDB);
            conn = DriverManager.getConnection(URL_MariaDB,usuario,pass);
          //  statement = conn.createStatement();
        }catch(SQLException e){
            e.getMessage();
        }catch(ClassNotFoundException e){
            e.getMessage();
        }
        return conn;
    }
    
}
