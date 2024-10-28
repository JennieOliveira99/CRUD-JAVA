/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;
import java.sql.SQLException;//tratando excecoes no banco de dados

public class Usuario {
    //criacao dos atributos privados de classes
    private String usuario;
    private String nome;
    private String senha;
    
     //atributo que armazenara o retornno do BD    
    private boolean resultUsuario;
    private boolean resultCadastro;
    
 //criacao getters e setters
    
        public String getUsuario() {
        return usuario;
    }
        
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

   
    //metodo de verificacao da autenticidade do usuario
    public boolean verificaUsuario(String usuario, String senha){
        //instancia da conexao com BD
        Conexao banco = new Conexao();
        
        try{
            //abrindo conexao com banco de dados
            banco.abrirConexao();
            //criando parametros de retorno
            banco.stmt=banco.con.createStatement();
            //executando a consulta no BD
            banco.resultset =
                    banco.stmt.executeQuery("SELECT * FROM usuario "
                                                + "WHERE usuario = '" + usuario + "'"
                                                + "AND senha = md5('" + senha + "')");
            
        //verificando se existe retorno de dados no banco
        if (banco.resultset.next()){
            //se tiver
            resultUsuario = true;
        }else{
            //se nao tiver
            resultUsuario = false;
        }
        
        banco.fecharConexao();
        
        }catch (SQLException ec) {
            System.out.println("Erro ao consultar usuario" + ec.getMessage());
        }
        
        return resultUsuario;
    } 
   
    //metodo de verificacao do usuario, fazendo aqui a sobrecarga de metodo****
    public boolean verificaUsuario(String usuario){
        //instanciando a conexao com o BD
        Conexao banco = new Conexao();
        
        try{
            //abrindo conexao
            banco.abrirConexao();
            //criando parametro de retorno
            banco.stmt = banco.con.createStatement();
            //executando a consulta no BD
            banco.resultset = 
                    banco.stmt.executeQuery("SELECT * FROM usuario"
                                            + "WHERE usuario = '" + usuario + "'");
            //verificando se existe retorno de dados no banco
            if (banco.resultset.next()){
                //se tiver
                resultUsuario = true;
            }else{
                //se nao tiver
                resultUsuario = false;
            }
            banco.fecharConexao(); //fechando a conexao com o  BD
        }catch (SQLException ec){
            System.out.println("Erro ao consultar usuario" + ec.getMessage());
        }
        return resultUsuario;
    }
    
    //metodo para cadastro par cadastro de usuario em nosso sistema
    public boolean cadastraUsuario(String nome, String usuario, String senha){
        //fazer a instancia da conexao com banco de dados
        Conexao banco = new Conexao();
        
        try{
            //abertura da conexao com o BD
            banco.abrirConexao();
            //criando parametros de retorno
            banco.stmt = banco.con.createStatement();
            //executando a inserção de dados
            banco.stmt.execute("INSERT INTO usuario (nome, usuario, senha)"
                                + "VALUES ('" + nome + "','" + usuario + "', md5 ('"
                                + senha + "'))");
            
       
        resultCadastro = true;
        
        }catch(SQLException ec){
         System.out.println("Erro ao inserir usuario" + ec.getMessage());
          resultCadastro = false;
         
        }
        return resultUsuario;
    }
}
