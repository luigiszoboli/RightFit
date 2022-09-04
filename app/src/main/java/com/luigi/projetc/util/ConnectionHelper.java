package com.luigi.projetc.util;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.BreakIterator;

public class ConnectionHelper {

    // Método que estabele a conexão com o banco de dados
     public static Connection getConnection() throws
            ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException {

        String userName = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/right-fit";
        String driver = "com.mysql.cj.jdbc.Driver";

        // Registra o driver do banco de dados
        Class.forName(driver).newInstance();

        // Estabele a conexão passando url, usuário e senha

        try {
            Connection conn = DriverManager.getConnection(
                    url, userName, password);
            System.out.println(conn);
            return conn;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }


}
}
