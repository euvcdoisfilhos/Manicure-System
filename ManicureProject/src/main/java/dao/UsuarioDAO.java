/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import model.Usuario;

/**
 *
 * @author thiag
 */
public interface UsuarioDAO {

    public void inserirUsuario(Usuario usuario);

    public void atualizarUsuario(Usuario usuario);

    public void deletarUsuario(int id);

    public Usuario buscarUsuarioPorId(int id);
    
    public void listarUsuarios();
}
