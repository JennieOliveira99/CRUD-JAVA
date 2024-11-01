package login;

import java.sql.SQLException; // tratando exceções no banco de dados

public class Usuario {

    // criação dos atributos privados de classe
    private String usuario;
    private String nome;
    private String senha;

    // atributo que armazenará o retorno do BD    
    private boolean resultUsuario;
    private boolean resultCadastro;
    //atributos de retorno para alteração e exclusao
    private boolean resultAlteracao;
    private boolean resultExclusao;

    // atributos estaticos do sistema
    public static String nomeUsuario;//alterando para public para tirar o erro da tela alteracao
    public static String usuarioSistema;

    // criação de getters e setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /*
    //testeeer
    public static String getUsuarioSistema() {
        return usuarioSistema;
    }

    //testeeer tambem
    public static String getNometUsuario() {
        return nomeUsuario;
    }*/

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

    // método de verificação da autenticidade do usuário
    public boolean verificaUsuario(String usuario, String senha) {
        // instancia da conexão com BD
        Conexao banco = new Conexao();

        try {
            // abrindo conexão com banco de dados
            banco.abrirConexao();
            // criando parâmetros de retorno
            banco.stmt = banco.con.createStatement();
            // executando a consulta no BD
            banco.resultset = banco.stmt.executeQuery(
                    "SELECT * FROM usuario "
                    + "WHERE usuario = '" + usuario + "' "
                    + "AND senha = md5('" + senha + "')"
            );

            // verificando se existe retorno de dados no banco
            if (banco.resultset.next()) {
                // se tiver
                resultUsuario = true;
                // setters em nome e usuario
                setNome(banco.resultset.getString(1));
                setUsuario(banco.resultset.getString(2));

                // nos atributos estáticos, realiza atribuições(atribuindo getters)
                nomeUsuario = this.getNome();
                usuarioSistema = this.getUsuario();
            } else {
                // se não tiver
                resultUsuario = false;
            }

            banco.fecharConexao(); // fechando a conexão com o BD

        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuario: " + ec.getMessage());
        }

        return resultUsuario;
    }

    // método de verificação do usuário, fazendo aqui a sobrecarga de método****
    public boolean verificaUsuario(String usuario) {
        // instanciando a conexão com o BD
        Conexao banco = new Conexao();

        try {
            // abrindo conexão
            banco.abrirConexao();
            // criando parâmetro de retorno
            banco.stmt = banco.con.createStatement();
            // executando a consulta no BD
            banco.resultset = banco.stmt.executeQuery(
                    "SELECT * FROM usuario "
                    + "WHERE usuario = '" + usuario + "'"
            );

            // verificando se existe retorno de dados no banco
            if (banco.resultset.next()) {
                // se tiver
                resultUsuario = true;
            } else {
                // se não tiver
                resultUsuario = false;
            }

            banco.fecharConexao(); // fechando a conexão com o BD

        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuario: " + ec.getMessage());
        }

        return resultUsuario;
    }

    // método para cadastro de usuário em nosso sistema
    public boolean cadastraUsuario(String nome, String usuario, String senha) {
        // fazer a instância da conexão com banco de dados
        Conexao banco = new Conexao();

        try {
            // abertura da conexão com o BD
            banco.abrirConexao();
            // criando parâmetros de retorno
            banco.stmt = banco.con.createStatement();
            // executando a inserção de dados
            banco.stmt.execute(
                    "INSERT INTO usuario (nome, usuario, senha) "
                    + "VALUES ('" + nome + "', '" + usuario + "', md5('" + senha + "'))"
            );

            resultCadastro = true;

        } catch (SQLException ec) {
            System.out.println("Erro ao inserir usuario: " + ec.getMessage());
            resultCadastro = false;
        }

        return resultCadastro; // alterado para retornar o resultado correto do cadastro
    }

    //acrescentando metodo de alteracao do usuario
    public boolean alteraUsuario(String nome, String usuario, String senha) {
        //fazendo a instancia da conexao com o banco de dados
        Conexao banco = new Conexao();

        try {
            //abrindo conexao com o  banco de dados
            banco.abrirConexao();
            //criando parametro de retorno
            banco.stmt = banco.con.createStatement();
            //executando alteração no banco de dados
            // testeeee aplicando o MD5 na senha nova
            banco.stmt.execute("UPDATE usuario SET nome = '" + nome
                    + "', senha = md5('" + senha + "') WHERE usuario = '"
                    + usuario + "'");
            resultAlteracao = true;
        } catch (SQLException ec) {
            System.out.println("Erro ao atualizar usuario " + ec.getMessage());
            resultAlteracao = false;
        }

        banco.fecharConexao();
        return resultAlteracao;

    }

    //acrescentando metodo para exclusao do usuario no sistema
    public boolean excluiUsuario(String usuario) {
        //fazendo a instancia da conexao com BD
        Conexao banco = new Conexao();

        try {
            //abrindo a conexao com o banco de dados
            banco.abrirConexao();

            //criando o parametro de retorno
            banco.stmt = banco.con.createStatement();

            //executando a alteracao no banco de dados
            banco.stmt.execute("DELETE FROM usuario WHERE usuario = '"
                    + usuario + "'");

            //caso exclua
            resultExclusao = true;
        } catch (SQLException ec) {
            System.out.println("Erro ao excluir usuário" + ec.getMessage());
            resultExclusao = false;

        }
        banco.fecharConexao();
        return resultExclusao;
    }
}
