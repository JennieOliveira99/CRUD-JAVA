/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.awt.Font; //trabalhar com fontes
import java.awt.SystemColor;//trabalhar com cores
import java.awt.event.ActionEvent;//trabalhar com evento
import javax.swing.JButton;////trabalhar com botoes
import javax.swing.JFrame;//trabalhar com frames
import javax.swing.JLabel;//trabalhar com labels
import javax.swing.JOptionPane;//trabalhar com mensagens
import javax.swing.JPanel;//trabalhar com paineis
import javax.swing.JPasswordField;//trabalhar com campos de senha
import javax.swing.JTextField;//trabalhar com campos de texto

public class TelaLogin extends JFrame {

    private final JPanel panelTela;//tela
    private final JTextField txtUsuario;//txtusuario campo
    private final JPasswordField pswSenha;//campo de senha
    private boolean usuarioValido;//validar se usuario é correto

    public TelaLogin() {
        setLocationRelativeTo(null); //objeto na referencia do centro da tela
        setResizable(false);//nao sera expandido
        setTitle("Login - Fatec");//coloca titulo na caixa JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//quando clicar no X encerra o programa
        setBounds(500, 200, 526, 312);//posicionamento e tamanho

        panelTela = new JPanel();//cria o objeto jPanel e atribui a variavel tela
        panelTela.setBackground(SystemColor.MAGENTA);//define a cor
        setContentPane(panelTela);

        panelTela.setLayout(null);//utilizar o panel sem utilizar o padrao

        //adcionando elementos na tela
        JLabel lblIdentificacao = new JLabel("Identificação");//criando objeto do tipo JLabel e atribuindo o valor ao atributo
        lblIdentificacao.setBounds(144, 0, 160, 39);//localizacao na tela
        lblIdentificacao.setFont(new Font("Arial", 3, 19));//definindo fonte
        panelTela.add(lblIdentificacao);//add o meu label ao meu panel

        //identificacao e posicionamento dos labels
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(24, 65, 70, 15);
        panelTela.add(lblUsuario);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(24, 92, 70, 15);
        panelTela.add(lblSenha);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(112, 63, 219, 19);
        panelTela.add(txtUsuario);
        txtUsuario.setColumns(10);

        pswSenha = new JPasswordField();
        pswSenha.setBounds(112, 90, 219, 19);
        panelTela.add(pswSenha);

        //identificacao e posicionamento dos botoes
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(230, 136, 117, 25);
        panelTela.add(btnEntrar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(90, 136, 117, 25);
        panelTela.add(btnCadastrar);
        

        //acao do botao ENTRAR no sistema
        btnEntrar.addActionListener((ActionEvent e) -> {
            //instanciando a classe usuario
            Usuario usu = new Usuario();

            //setter usuario e senha
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(pswSenha.getText());

            if ("".equals(txtUsuario.getText())) {
                //passando mensagem na tela
                JOptionPane.showMessageDialog(null,
                        "Campo usuario precisa ser informado -",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE
                );
                //voltar cursor para txtUsuario
                txtUsuario.grabFocus();
            } else if ("".equals(pswSenha.getText())) {
                //mensagem na tela
                JOptionPane.showMessageDialog(null,
                        "Campo senha precisa ser informado -",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
                //voltar cursor para txtUsuario
                pswSenha.grabFocus();
            } else {
                //verificando se usuario consta no BD
                usuarioValido = usu.verificaUsuario(usu.getUsuario(),
                        usu.getSenha());

                if (usuarioValido == true) {
                    //se usuario e senha bateram no BD e estao coretos
                    JOptionPane.showMessageDialog(null,
                            "Usuário valido em nossa base de dados!!!!!!!",
                            "Atenção",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    //adicionando a ida para menu ou home
                    
                    TelaInicio telaInicio = new TelaInicio();
                    telaInicio.abreTela();
                    dispose();
                } else {
                    //usuario e senha bateram no BD mas estao incorretos
                    JOptionPane.showMessageDialog(null,
                            "Usuario invalido ou Inexistente",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);

                    //metodo para limpar os textos
                    limpaText();

                    //mandando o foo para o usuario
                    txtUsuario.grabFocus();
                }
            }

        });
        
        //acao do botso para CADASTRAR usuario
        btnCadastrar.addActionListener((ActionEvent e) -> {
            //instanciando a telaCadastro
            TelaCadastro tCadastro = new TelaCadastro();
            tCadastro.abreTela();
            dispose();
        });

    }

    public void abreTela() {
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
    }

    public void limpaText() {
        txtUsuario.setText("");
        pswSenha.setText("");
    }

}
