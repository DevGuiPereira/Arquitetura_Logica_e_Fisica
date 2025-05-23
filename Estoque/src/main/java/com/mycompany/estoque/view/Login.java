/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.estoque.view;

import com.mycompany.estoque.model.Log;
import com.mycompany.estoque.model.Usuario;
import com.mycompany.estoque.repository.Conexao;
import com.mycompany.estoque.repository.ConexaoMySQL;
import com.mycompany.estoque.repository.ProdutoRepository;
import com.mycompany.estoque.repository.UsuarioRepository;
import java.sql.Connection;
import javax.swing.JOptionPane;



/**
 *
 * @author guilh
 */
public class Login extends javax.swing.JFrame {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();
    private Connection connection;
    
    /**
     * Creates new form Loginn
     */
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        configurarConexao();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo1 = new javax.swing.JLabel();
        Titulo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoUsuario = new javax.swing.JTextField();
        campoSenha = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Titulo1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo1.setText("Login");
        Titulo1.setAlignmentY(0.0F);

        Titulo2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Titulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo2.setText("CONTROLE DE ESTOQUE");
        Titulo2.setAlignmentY(0.0F);

        jLabel1.setText("Usuário:");

        jLabel2.setText("Senha:");

        campoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSenhaActionPerformed(evt);
            }
        });

        jButton1.setText("Acessar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Titulo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(10, 10, 10)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(campoSenha))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 316, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(312, 312, 312))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Titulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(Titulo1)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(132, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(97, 97, 97)
                    .addComponent(Titulo2)
                    .addContainerGap(310, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getText()); // use getPassword() corretamente para campos de senha

        UsuarioRepository usuarioRepo = new UsuarioRepository();
        Connection connection = null;

        try {
            connection = ConexaoMySQL.connection;
            Usuario usuarioAutenticado = usuarioRepo.autenticar(connection, usuario, senha);

            if (usuarioAutenticado != null) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");

                // Registra o log
                Log log = new Log();
                log.setLogscol("Usuário realizou login no sistema");
                log.setIdusuario(usuarioAutenticado.getId()); // precisa ter o ID no objeto autenticado

                usuarioRepository.inserirLog(connection, log);

                // Abre a tela inicial
                Inicio inicio = new Inicio(usuarioAutenticado); // passe o usuário logado se necessário
                inicio.setVisible(true);

                // Fecha a tela de login
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro na conexão com o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void campoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoSenhaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    private void configurarConexao(){
        // Configurando a conexão
        Conexao conexao = new Conexao(
                "localhost",
                "root",
                "Guirp007007!",  //configure sua conexão com o banco de dados
                3306,
                "estoque"
        );

        ConexaoMySQL conexaoMySQL = new ConexaoMySQL(conexao); //iniciando a com os dados do objeto conexao
        if (conexaoMySQL.conectar()) {  
            Connection connection = ConexaoMySQL.connection; //faz a conexão
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE); //mensagem de erro
            return;  
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titulo1;
    private javax.swing.JLabel Titulo2;
    private javax.swing.JTextField campoSenha;
    private javax.swing.JTextField campoUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
