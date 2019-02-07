package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static Connection CONEXION = null;

    public static Connection getConnection() {
        
        if (CONEXION != null) {
            
            return CONEXION;
            
        } else {
            
            URI dbUri;
            
            try {
                
                dbUri = new URI(System.getenv("DATABASE_URL"));
                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

                if (CONEXION == null) {
                    try {
                        CONEXION = DriverManager.getConnection(dbUrl, username, password);
                    } catch (SQLException e) {
                        System.out.println("Connection Failed! Check output console");
                    }
                }
                
            } catch (URISyntaxException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }

            return CONEXION;

        }

    }

    public static void closeConnection() throws SQLException {
        try {
            if (CONEXION != null) {
                CONEXION.close();
                CONEXION = null;
            }

        } catch (SQLException e) {
            //Integracion Log4J
            throw new SQLException(e);
        }

    }

}
