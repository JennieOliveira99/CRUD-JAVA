package login;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static login.Usuario.usuarioSistema;
import java.awt.HeadlessException;

public class TelaAlteracao extends JFrame {

    //implementando a tela alteração
    //criando atributos globais
    private final JPanel tela;
    private final JTextField txtNome;
    private final JPasswordField passAtual;
    private final JPasswordField passSenha;
    private final JPasswordField confPassSenha;
    private boolean atualizacaoValida;

    public TelaAlteracao() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Fatec - tualização");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 300, 526, 312);

        tela = new JPanel();
        tela.setBackground(SystemColor.green);
        setContentPane(tela);
        tela.setLayout(null);

        //adcionando os elemntos na tela
        JLabel lblIdentificacao = new JLabel("Informar campos para alteração");
        lblIdentificacao.setBounds(60, 0, 500, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        tela.add(lblIdentificacao);

        //campo nome
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(24, 35, 100, 15);
        tela.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 35, 218, 20);
        tela.add(txtNome);
        txtNome.setColumns(10);

        //campo senha
        JLabel lblSenhaAtual = new JLabel("Senha Atual");
        lblSenhaAtual.setBounds(24, 60, 70, 15);
        tela.add(lblSenhaAtual);

        passAtual = new JPasswordField();
        passAtual.setBounds(120, 60, 219, 19);
        tela.add(passAtual);

        //campo nova senha
        JLabel lblNovaSenha = new JLabel("Nova Senha");
        lblNovaSenha.setBounds(24, 85, 170, 15);
        tela.add(lblNovaSenha);

        passSenha = new JPasswordField();
        passSenha.setBounds(120, 80, 219, 19);
        tela.add(passSenha);

        //campo confirmar senha
        JLabel lblConfSenha = new JLabel("Confirmar Senha");
        lblConfSenha.setBounds(24, 110, 100, 15);
        tela.add(lblConfSenha);

        confPassSenha = new JPasswordField();
        confPassSenha.setBounds(120, 110, 219, 19);
        tela.add(confPassSenha);

        //adcionando botoes
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(200, 136, 117, 25);
        tela.add(btnAlterar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 136, 117, 25);
        tela.add(btnCancelar);

        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaInicio telaIni = new TelaInicio();
            telaIni.setVisible(true);
            dispose();
        });
        
        //botao alterar
        btnAlterar.addActionListener((ActionEvent e ) -> {
            
            try{
                //instanciando a classe usuario
                Usuario usu = new Usuario();
                
                //validacoes antes de efetivar a alteracao
                //setando a senha e usuario
                usu.setSenha(confPassSenha.getText());
                usu.setUsuario(usuarioSistema);
                
                //se o nome ou senha estiver vazio
                if ("".equals(usu.getNome())){
                    //retonando uma mensagem na tela
                    JOptionPane.showMessageDialog(null, 
                            "Campo nome do suario precisa ser informado!", 
                    "Atenção", 
                    JOptionPane.ERROR_MESSAGE);
                    //voltar o cursor para o campo textNome
                    txtNome.grabFocus();
                }
                else if("".equals(usu.getUsuario())){
                        //retonando uma mensagem na tela
                    JOptionPane.showMessageDialog(null, 
                            "Campo senha precisa ser informado!", 
                    "Atenção", 
                    JOptionPane.ERROR_MESSAGE);
                    //voltar o cursor para o campo passSenha
                    passSenha.grabFocus();
                }
                else if(usu.verificaUsuario(usu.getUsuario(), 
                        passAtual.getText()) == false){
                        //retonando uma mensagem na tela
                    JOptionPane.showMessageDialog(null, 
                            "Senha inválida! Verifique", 
                    "Atenção", 
                    JOptionPane.ERROR_MESSAGE);
                    //voltar o cursor para o campo passSenha
                    passSenha.grabFocus();
                }
                  else if(!passSenha.getText().equals(confPassSenha.getText())){
                        //retonando uma mensagem na tela
                    JOptionPane.showMessageDialog(null, 
                            "Campos de Senha e Confirmação de Senha não são iguasi!", 
                    "Atenção", 
                    JOptionPane.ERROR_MESSAGE);
                    //voltar o cursor para o campo passSenha
                    passSenha.grabFocus();
                } else{
                      //efetivando a alteracao do usuario
                      atualizacaoValida = 
                              usu.alteraUsuario(txtNome.getText(), 
                                      usu.getUsuario(),
                                      usu.getSenha());
                      
                      if (atualizacaoValida == true){
                          //usuario cadastrado na base de dados
                          JOptionPane.showMessageDialog(null, 
                                  "Dado(s) do usuario alterado(s) com sucesso, retornando... " 
                          + "a tela de login.", 
                                  "ATenção",
                                  JOptionPane.INFORMATION_MESSAGE);
                          
                          //arimos a tel de login novamente
                          TelaLogin tLogin = new TelaLogin();
                          tLogin.abreTela();
                          
                          //fechando a tela de cadastro
                          dispose();
                      }
                      else{
                             JOptionPane.showMessageDialog(null, 
                                  "Problemas ao atualizar o usuário" , 
                                  "ATenção",
                                  JOptionPane.ERROR_MESSAGE);
                          
                      }
                  }
                
            } catch (HeadlessException ec){
                System.out.println("Erro ao alterar usuário" 
                + ec.getMessage());
                
            }
        });
        //atribuindo o atributo global ao objeto
        txtNome.setText(Usuario.nomeUsuario);
    }
    public void abreTela(){
        TelaAlteracao telaAlteracao = new TelaAlteracao();
        telaAlteracao.setVisible(true);
    }
}
