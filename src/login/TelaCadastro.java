
package login;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class TelaCadastro extends JFrame{
    
    //declaracao atributos de tela
    private final JPanel tela;
    private final JTextField txtNome;
    private final JTextField txtUsuario;
    private final JPasswordField passSenha;
    private final JPasswordField passConfSenha;
    
    //validacoes de usuario e cadstro corretos
    private boolean usuarioValido;
    private boolean cadastroValido;
    //string de mensaagens
    private String mensagemJOption;
    private int mensagemTipo = 0;
    
    
    
    //metodo construtor da classe
    public TelaCadastro(){
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 526, 330);
        
        tela = new JPanel();
        tela.setBackground(SystemColor.CYAN);
        
        setContentPane(tela);
        tela.setLayout(null);
        
        //adcionando elementos na tela        
        JLabel lblIdentificacao = new JLabel ("Informar Campos para cadastro");
        lblIdentificacao.setBounds(60, 0, 500, 39);
        lblIdentificacao.setFont(new Font("Calibri", 3, 19));
        tela.add(lblIdentificacao);
        
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(24, 50, 70, 15);
        tela.add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds(160, 50, 219, 19);
        tela.add(txtNome);
        txtNome.setColumns(10);
        
        JLabel lblUsuario = new JLabel ("Usuario");
        lblUsuario.setBounds(24, 75, 70, 15);
        tela.add(lblUsuario);
        
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(160, 75, 219, 19);
        tela.add(txtUsuario);
        txtUsuario.setColumns(10);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(24, 100, 70, 15);
        tela.add(lblSenha);
        
        passSenha = new JPasswordField();
        passSenha.setBounds(160, 100, 219, 19);
        tela.add(passSenha);
        
        JLabel lblconfsenha = new JLabel("Confirmar Senha");
        lblconfsenha.setBounds(24, 125, 100, 15);
        tela.add(lblconfsenha);
        
        passConfSenha = new JPasswordField();
        passConfSenha.setBounds(160, 125, 219, 19);
        tela.add(passConfSenha);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(200, 156, 117, 25);
        tela.add(btnCadastrar);
        
        //implementando o botao cadastrar
        btnCadastrar.addActionListener((ActionEvent e) -> {
        try{
            //instanciando o objeto usuario
            Usuario usu = new Usuario();
            
            //setters
            usu.setNome(txtNome.getText());
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(passSenha.getName());
            
            //validacoes de preenchimento dos dados
            if("".equals(usu.getNome())){
                mensagemJOption = "Campo nome do usuario precisa ser informado!";
                mensagemTipo = 0;
            }else if("".equals(usu.getUsuario())){
                mensagemJOption = "Campo usuario do usuario precisa ser informado!";
            }else if("".equals(usu.getSenha())){
                mensagemJOption = "Campo senha do usuario precisa ser informado!";
                mensagemTipo = 0;
            }else if(!usu.getSenha().equals(passConfSenha.getText())){
                mensagemJOption = "As senhas não coincidem";
                mensagemTipo = 0;
            }else{
                //verificando se somente o usuario consta no banco
                //neste caso sera feito a sobrecarga de metodo *****
                usuarioValido = usu.verificaUsuario(usu.getUsuario());
               
                
                if(usuarioValido == true){
                    //se existir nao pode ser colocado no BD
                    mensagemJOption = "Esse usuŕio já está cadastrado no banco de dados";
                    mensagemTipo = 0;
                }else{
                    cadastroValido = usu.cadastraUsuario(usu.getNome(),
                                                            usu.getUsuario(),
                                                            usu.getSenha());
                    if(cadastroValido == true){
                        //usuario cadastrado na base de dados
                        mensagemJOption = "Usuario cadastrado corretamente!";
                        mensagemTipo = 1;
                    }else{
                        //caso ocorrer algum erro
                        mensagemJOption = "Problemas ao inserir usuario!";
                        mensagemTipo = 0;
                    }
                }
                
            }
            
           
         // mostrar a mensagem 
                JOptionPane.showMessageDialog(null, mensagemJOption, "Atenção", mensagemTipo);
                if (mensagemTipo == JOptionPane.INFORMATION_MESSAGE) {
                    // voltamos para a tela de login
                    TelaLogin tLogin = new TelaLogin();
                    tLogin.abreTela();
                    // fechar a tela de cadastro
                    dispose();
                }
                
            } catch (HeadlessException ec) {
                System.out.println("Erro no cadastro de usuario: " + ec.getMessage());
            }
        });
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50,156,117,25);
        tela.add(btnCancelar);
        
        //implementando o botao cancelar
        btnCancelar.addActionListener ((ActionEvent e) -> {
            TelaLogin tLogin = new TelaLogin();
            tLogin.abreTela();
        });
      
    }
    
    public void abreTela(){
        TelaCadastro panelCadastro = new TelaCadastro();
        panelCadastro.setVisible(true);
    }}
   

