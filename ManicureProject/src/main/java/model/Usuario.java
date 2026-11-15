package model;

/**
 *
 * @author thiag
 */
public class Usuario {

    //VARIAVEIS GLOBAIS
    private int idUsuario;
    private int status = 1;
    private String email, nome, senha, endereco, data_nascimento, telefone,
            tipo_perfil = "CLIENTE";

    //CONSTRUTOR PADRAO
    public Usuario() {
    }

    //CONSTRUTOR COMPLETO DO OBJETO USUARIO COM ID
    public Usuario(int idUsuario, String email, String nome, String senha, String endereco, String data_nascimento, String telefone, int status, String tipo_perfil) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.status = status;
        this.tipo_perfil = tipo_perfil;
    }

    //CONSTRUTOR DO OBJETO USUARIO COM ID (SEM STATUS E PERFIL EXPLICITOS)
    public Usuario(int idUsuario, String email, String nome, String senha, String endereco, String data_nascimento, String telefone) {
        this(idUsuario, email, nome, senha, endereco, data_nascimento, telefone, 1, "CLIENTE");
    }

    //CONSTRUTOR COMPLETO SEM ID
    public Usuario(String email, String nome, String senha, String endereco, String data_nascimento, String telefone, int status, String tipo_perfil) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.status = status;
        this.tipo_perfil = tipo_perfil;
    }

    //CONSTRUTOR DO USUARIO SEM ID (STATUS NO INICIO)
    public Usuario(int status, String email, String nome, String senha, String endereco, String data_nascimento, String telefone, String tipo_perfil) {
        this(email, nome, senha, endereco, data_nascimento, telefone, status, tipo_perfil);
    }

    //CONSTRUTOR SEM ID (SEM STATUS E PERFIL EXPLICITOS)
    public Usuario(String email, String nome, String senha, String endereco, String data_nascimento, String telefone) {
        this(email, nome, senha, endereco, data_nascimento, telefone, 1, "CLIENTE");
    }

    //CONSTRUTOR SIMPLIFICADO COM ID
    public Usuario(int idUsuario, String email, String nome, String senha, String telefone) {
        this(idUsuario, email, nome, senha, null, null, telefone, 1, "CLIENTE");
    }

    //CONSTRUTOR SIMPLIFICADO SEM ID
    public Usuario(String email, String nome, String senha, String telefone) {
        this(email, nome, senha, null, null, telefone, 1, "CLIENTE");
    }

    //GETTERS AND SETTERS
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo_perfil() {
        return tipo_perfil;
    }

    public void setTipo_perfil(String tipo_perfil) {
        this.tipo_perfil = tipo_perfil;
    }

    @Override
    public String toString() {
        return "O usuario se chama " + getNome();
    }

    //VALIDACAO DE REGRA DE NEGOCIOS
    //VALIDACAO DE INSERCAO
    public void salvar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O oobjeto usuario nao pode ser nulo.");
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

    //VALIDACAO DE ALTERACAO DO BANCO
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
