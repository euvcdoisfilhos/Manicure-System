/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import model.Usuario;
import util.ConnectionFactory;

/**
 *
 * @author thiag
 */
public class UsuarioImpl implements UsuarioDAO {

    @Override
    public void inserir(Usuario usuario) {
        //CRIAR A QUERRY MYSQL E ATRIBUIR ELA A VARIAVEL QUE SERA EXECUTADA
        String sql = "INSERT INTO usuario (nome, email, senha) "
                + "VALUES ("
                + "'" + usuario.getNome() + "', "
                + "'" + usuario.getEmail() + "', "
                + "'" + usuario.getSenha() + "', "
                + ")";

        try (Connection conn = ConnectionFactory.getConnection(); Statement stmt = conn.createStatement()) {
            int linhas = stmt.executeUpdate(sql);
            System.out.println("Aluno inserido. Linhas afetadas: " + linhas);
        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
        }
    }

}
