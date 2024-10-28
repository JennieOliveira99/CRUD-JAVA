/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package login;

public class TestaConexao {
    
    public static void main(String[] args) {
       Conexao c = new Conexao(); //instanciando objeto de conexao
       c.abrirConexao(); //chamando o metodo de abrir conexao do BD
       
       try{
           Thread.sleep(4000); //fazendo pausa de 4segundos
           c.fecharConexao();//fechando coenxao
    } catch (InterruptedException ex){
        //se tiver algum erro
        System.out.println("Houve um problema no teste de Conexao" + ex.getMessage());
    }
    
}
}
