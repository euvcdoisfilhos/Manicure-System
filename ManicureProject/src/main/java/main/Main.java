/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import dao.UsuarioImpl;
import java.sql.Connection;
import java.sql.SQLException;
import model.Usuario;
import util.ConnectionFactory;

/**
 *
 * @author thiag
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");

        //INSTANCIAR UM OBJETO DE USUARIO
        Usuario user1 = new Usuario(1, "thiago@gmail.com", "Thiago Monteiro", "", "4971239273");
        System.out.println(user1);

        try (Connection conn
                = ConnectionFactory.getConnection()) {
            System.out.println("Conectado ao banco!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: "
                    + e.getMessage());
        }

    }

}
