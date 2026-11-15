package model;

/**
 *
 * @author thiag
 */
public class Usuario {

    //VARIAVEIS GLOBAIS
    private int idUsuario;
    private String email, nome, senha, telefone;

    //CONSTRUTOR DO OBJETO USUARIO
    public Usuario(int idUsuario, String email, String nome, String senha, String telefone) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
    }

    //CONSTRUTOR DO USUARIO SEM ID
    public Usuario(String email, String nome, String senha, String telefone) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
    }

    //GETTERS AND SETTERS
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "O usuario se chama " + getNome();
    }

    //VALIDACAO DE REGRA DE NEGOCIOS
    public void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O oobjeto usuario nao pode ser nulo.");
        }

        if (usuario.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("ID do usuario invalido para a atualizacao " + usuario.getIdUsuario());
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuario nao pode ser vazio.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O email do usuario nao pode ser vazio.");
        }

        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha do usuario nao pode ser vazio.");
        }

    }

}
