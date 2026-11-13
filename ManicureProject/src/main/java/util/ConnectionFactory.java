/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        try {
            Properties props = new Properties();
            InputStream is = ConnectionFactory.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");
            if (is == null) {
                throw new SQLException("Arquivo db.properties não encontrado");
            }
            props.load(is);
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            return DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            throw new SQLException("Erro ao carregar db.properties", e);
        }
    }
}
