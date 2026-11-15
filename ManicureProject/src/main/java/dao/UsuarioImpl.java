package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String sql = "INSERT INTO usuario (email, nome, senha, endereco, data_nascimento, telefone, status, tipo_perfil) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getNome());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setString(4, usuario.getEndereco());

            if (usuario.getData_nascimento() != null && !usuario.getData_nascimento().trim().isEmpty()) {
                pstmt.setString(5, usuario.getData_nascimento());
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }

            pstmt.setString(6, usuario.getTelefone());
            pstmt.setInt(7, usuario.getStatus() != 0 ? usuario.getStatus() : 1);
            pstmt.setString(8, (usuario.getTipo_perfil() != null && !usuario.getTipo_perfil().trim().isEmpty()) ? usuario.getTipo_perfil() : "CLIENTE");

            pstmt.executeUpdate();
            System.out.println("Usuario inserido com sucesso.");
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

        String sql = "UPDATE usuario SET email = ?, nome = ?, senha = ?, endereco = ?, data_nascimento = ?, telefone = ?, status = ?, tipo_perfil = ? "
                + "WHERE idUsuario = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getNome());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setString(4, usuario.getEndereco());

            if (usuario.getData_nascimento() != null && !usuario.getData_nascimento().trim().isEmpty()) {
                pstmt.setString(5, usuario.getData_nascimento());
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }

            pstmt.setString(6, usuario.getTelefone());
            pstmt.setInt(7, usuario.getStatus() != 0 ? usuario.getStatus() : 1);
            pstmt.setString(8, (usuario.getTipo_perfil() != null && !usuario.getTipo_perfil().trim().isEmpty()) ? usuario.getTipo_perfil() : "CLIENTE");
            pstmt.setInt(9, usuario.getIdUsuario());

            int linhas = pstmt.executeUpdate();
            System.out.println("Usuario atualizado. Linhas afetadas: " + linhas);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o usuario: " + e.getMessage());
        }
    }

    @Override
    public void deletarUsuario(int id) {

        String sql = "DELETE FROM usuario WHERE idUsuario = ?";

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

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT idUsuario, email, nome, senha, endereco, data_nascimento, telefone, status, tipo_perfil FROM usuario WHERE idUsuario = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getString("email"),
                            rs.getString("nome"),
                            rs.getString("senha"),
                            rs.getString("endereco"),
                            rs.getString("data_nascimento"),
                            rs.getString("telefone"),
                            rs.getInt("status"),
                            rs.getString("tipo_perfil")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Nao possivel ler o usuario: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void listarUsuarios() {
        String sql = "SELECT idUsuario, email, nome, senha, endereco, data_nascimento, telefone, status, tipo_perfil FROM usuario";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getString("endereco"),
                        rs.getString("data_nascimento"),
                        rs.getString("telefone"),
                        rs.getInt("status"),
                        rs.getString("tipo_perfil")
                );
                System.out.println(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuarios: " + e.getMessage());
        }
    }

}
