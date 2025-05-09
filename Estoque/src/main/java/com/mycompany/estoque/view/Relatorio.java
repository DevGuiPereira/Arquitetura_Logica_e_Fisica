/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.estoque.view;

import com.mycompany.estoque.model.Produto;
import com.mycompany.estoque.model.Usuario;
import com.mycompany.estoque.repository.Conexao;
import com.mycompany.estoque.repository.ConexaoMySQL;
import com.mycompany.estoque.repository.ProdutoRepository;
import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author guilh
 */
public class Relatorio extends javax.swing.JFrame {

    private ProdutoRepository produtoRepository = new ProdutoRepository();
    private Connection connection;
    private Usuario usuarioLogado;
    
    
    public Relatorio(Usuario usuario) {
        initComponents();
        setLocationRelativeTo(null);
        configurarConexao();
        this.usuarioLogado = usuario ;
    }
    
    public Relatorio() {
        initComponents();
        setLocationRelativeTo(null);
        configurarConexao();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        relatorioProdutos = new javax.swing.JTable();
        FecharTela = new javax.swing.JButton();
        relatorioCompletoButton = new javax.swing.JButton();
        relatorioCompletoButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Relatórios");

        relatorioProdutos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        relatorioProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Descrição", "Preço", "Qtda"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(relatorioProdutos);

        FecharTela.setText("Fechar");
        FecharTela.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        FecharTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FecharTela(evt);
            }
        });

        relatorioCompletoButton.setText("Relatório Completo");
        relatorioCompletoButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        relatorioCompletoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioCompletoButtonActionPerformed(evt);
            }
        });

        relatorioCompletoButton1.setText("Relatório Pouco Estoque ( < 5 )");
        relatorioCompletoButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        relatorioCompletoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioCompletoButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(relatorioCompletoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(relatorioCompletoButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FecharTela)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(35, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(FecharTela))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(relatorioCompletoButton)
                            .addComponent(relatorioCompletoButton1))))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FecharTela(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FecharTela
        Inicio inicio = new Inicio(usuarioLogado);
        inicio.setVisible(true);
        dispose();
    }//GEN-LAST:event_FecharTela

    private void relatorioCompletoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioCompletoButtonActionPerformed
        carregarTabelaTodos();
    }//GEN-LAST:event_relatorioCompletoButtonActionPerformed

    private void relatorioCompletoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioCompletoButton1ActionPerformed
        carregarTabelaPoucoEstoque();
    }//GEN-LAST:event_relatorioCompletoButton1ActionPerformed

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
            connection = ConexaoMySQL.connection;  //faz a conexão
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE); //mensagem de erro
            return;  
        }
    }
    
    private void carregarTabelaTodos() { //função para buscar todos os produtos do banco e mostrar no JTable
        
              
        // Selecionando os produtos
        List<Produto> produtos = produtoRepository.selecionarTodos(connection);

        if (produtos == null || produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum produto encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE); //mensagem avisando que não tem produtos cadastrados no banco
            return;
        }

        // Criando o modelo da tabela
        DefaultTableModel modeloTabela = (DefaultTableModel) relatorioProdutos.getModel();
        modeloTabela.setRowCount(0); // Limpa os dados anteriores da tabela

        for (Produto produto : produtos) {

            modeloTabela.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),    
                    produto.getDescricao(),   //adiciona os dados na tabela
                    produto.getPreco(),
                    produto.getQuantidade()
            });
        }

    }
    
    private void carregarTabelaPoucoEstoque() { //função para buscar todos os produtos com pouco estoque no banco de dados e mostrar no JTable

        // Selecionando os produtos
        List<Produto> produtos = produtoRepository.selecionarPoucoEstoque(connection);

        if (produtos == null || produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum produto encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE); //mensagem avisando que não foi encontrado produtos com pouco estoque
            return;
        }

        // Criando o modelo da tabela
        DefaultTableModel modeloTabela = (DefaultTableModel) relatorioProdutos.getModel();
        modeloTabela.setRowCount(0); // Limpa os dados anteriores da tabela

        for (Produto produto : produtos) {

            modeloTabela.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),    //adicionando os dados na tabela
                    produto.getPreco(),
                    produto.getQuantidade()
            });
        }

    }
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
            java.util.logging.Logger.getLogger(Relatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Relatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Relatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Relatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Relatorio().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FecharTela;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton relatorioCompletoButton;
    private javax.swing.JButton relatorioCompletoButton1;
    private javax.swing.JTable relatorioProdutos;
    // End of variables declaration//GEN-END:variables
}
