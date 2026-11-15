
import dao.UsuarioDAO;
import dao.UsuarioImpl;
import java.sql.Connection;
import java.sql.SQLException;
import model.Usuario;
import util.ConnectionFactory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author thiag
 */
public class Testes {

    public static void main(String[] args) {
        //INSTANCIAR O USUARIO DAO
        UsuarioDAO dao = new UsuarioImpl();

        try (Connection conn
                = ConnectionFactory.getConnection()) {
            System.out.println("Conectado ao banco!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: "
                    + e.getMessage());
        }

        //INSTANCIAR UM OBJETO DE USUARIO
        Usuario user1 = new Usuario("thiago@gmail.com" ,"Thiago Monteiro", "thiago123", "4971239273");
        System.out.println(user1);
        
        Usuario userTemp = new Usuario(2,"helena@gmail.com", "Helena Matias", "helena123", "4971239273");
        
        
       dao.inserir(user1);
       dao.atualizar(userTemp);
    }
}
