package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public void inserirUsuario(Usuario usuario) {
        //CRIAR A QUERRY MYSQL E ATRIBUIR ELA A VARIAVEL QUE SERA EXECUTADA
        String sql = "INSERT INTO usuario (nome, email, senha) "
                + "VALUES (?,?,?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
        }
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {

        try {
            usuario.validarUsuario(usuario);
        } catch (IllegalArgumentException e) {
            System.err.println("A validacao falhou: " + e.getMessage());
            return;
        }

        String sql = "UPDATE usuario SET nome=?, email=?,senha=? WHERE idUsuario=?";

        try (
                Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setInt(4, usuario.getIdUsuario());

            int linhas = pstmt.executeUpdate();
            System.out.println("Usuario atualizado. Linhas afetadas: " + linhas);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o usuario: " + e.getMessage());
        }
    }

    @Override
    public void deletarUsuario(int id) {

        String sql = "DELETE FROM usuario WHERE idUsuario= ? ";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, id);

            int linhas = pstmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Usuario removido.");
            } else {
                System.out.println("Nenhum usuario com id " + id + " encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover usuario:" + e.getMessage());
        }
    }

}
