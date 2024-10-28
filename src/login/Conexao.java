/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

/**
 *
 * @author pc-jelly
 */

import java.sql.Connection; //conexao banco de dados
import java.sql.DriverManager; //Driver de conexao com mysql
import java.sql.ResultSet; //resultado dasoperacoes no BD
import java.sql.SQLException;
import java.sql.Statement; //interperetacao de comandos sql


public class Conexao {
    
    //atributos de conexao ligados as bibliotecas importadas
    
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;
    
    //atributos de conexao
    
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/bd_login"; //servidor do BD
    private final String usuario = "root";//user
    private final String senha = ""; //senha
    private final String driver = "com.mysql.cj.jdbc.Driver";
    
    //metodo para abertura conexao com BD
    
    public boolean abrirConexao(){
        try{
            Class.forName(driver); //driver de atualizacao
            
            //atributos da conexao
            con = DriverManager.getConnection(servidor, usuario, senha);
            stmt = con.createStatement(); //fazendo conexao com BD
            
            //se conexao for sucesso
            System.out.println("Conexao aberta com sucesso");
            return  true;
        }catch ( ClassNotFoundException | SQLException e){
             System.out.println("Erro ao abrir Conexao " + e.getMessage());
        }
        return false;
    }
    
    //metodo fechamento do BD
    
    public void fecharConexao(){
        try {
            con.close(); //fechamento de conexao com BD
             System.out.println("Conexao encerrada com sucesso");
            
        }catch(SQLException e){
            System.out.println("Erro ao encerrar Conexao " + e.getMessage());
        }
    }
    
}
