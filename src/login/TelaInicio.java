/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaInicio extends JFrame {

    private final JPanel tela;

    //atributo para validar a exclusão
    private boolean exclusaoValida;

    public TelaInicio() {
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Menu - Fatec");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(700, 300, 526, 313);

        tela = new JPanel();
        tela.setBackground(SystemColor.red);
        setContentPane(tela);
        tela.setLayout(null);

        // Concatenando o label com o atributo global nome da classe usuario
        JLabel lblUsuario = new JLabel("Bem Vindo " + Usuario.nomeUsuario);
        lblUsuario.setBounds(24, 65, 200, 15);
        tela.add(lblUsuario);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(2, 130, 117, 25);
        tela.add(btnExcluir);

        JButton btnAlterar = new JButton("Alterar Dados");
        btnAlterar.setBounds(150, 130, 117, 25);
        tela.add(btnAlterar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(300, 130, 117, 25);
        tela.add(btnVoltar);

        //adcionando o evento de voltar
        btnVoltar.addActionListener((ActionEvent e) -> {

            try {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
                dispose();
            } catch (Exception ec) {
                System.out.println("Erro ao acessar Tela de Login" + ec.getMessage());
            }
        });

        //adcionando o alterar
        btnAlterar.addActionListener((ActionEvent e) -> {

            try {
                TelaAlteracao telaAlterar = new TelaAlteracao();
                telaAlterar.setVisible(true);
                dispose();
            } catch (Exception ec) {
                System.out.println("Erro ao acessar Tela de Alteração" + ec.getMessage());
            }
        });

        //adicionando evento de excluir
        btnExcluir.addActionListener((ActionEvent e) -> {

            try {
                //array que recebe as opçoes
                Object[] options = {"Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null,
                        Usuario.nomeUsuario
                        + ", deseja se excluir?", "Atenção",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        options, options[0]);

                if (opcao == 0) {
                    //escluir usuario
                    //instanciando a classe usuario
                    Usuario usu = new Usuario();

                    exclusaoValida
                            = usu.excluiUsuario(Usuario.usuarioSistema);

                    if (exclusaoValida == true) {
                        //usuario excluido na base de dados
                        JOptionPane.showMessageDialog(null,
                                "Usuário excluído corretamente, voltaremos "
                                + "a tela de Login.",
                                "Atenção",
                                JOptionPane.INFORMATION_MESSAGE);
                        //abrindo a tela de login
                        TelaLogin tLogin = new TelaLogin();
                        tLogin.abreTela();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Problemas ao excluir usuário",
                                "Atenção",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }

            } catch (HeadlessException ec) {
                System.out.println("Erro ao excluir");
            }
        });
    }

    public void abreTela() {
        TelaInicio telaInicio = new TelaInicio();
        telaInicio.setVisible(true);
    }

}
