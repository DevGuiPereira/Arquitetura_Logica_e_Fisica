package com.mycompany.estoque.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexaoMySQL {
    
    private Conexao conexao = null;
    public static Connection connection = null;

    public ConexaoMySQL(Conexao conexao) {
        this.conexao = conexao;              //construtor com a variável do objeto
    }

    public boolean conectar() {  //função para criar o endereço de conexão com o banco de dados, utilizando as funções do objeto Conexão.
        if (conexao != null) {
            try {
                String url = "jdbc:mysql://" + conexao.getEndereco() +
                             ":" + conexao.getPorta() +                  //criando a URL para conexão com o banco
                             "/" + conexao.getNomeBanco();
                        
                        
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Estabelece a conexão
                connection = DriverManager.getConnection(
                        url,
                        conexao.getUser(),
                        conexao.getPassword()
                );

                return true;

            } catch (Exception ex) {
                // Exibe a mensagem de erro e imprime
                JOptionPane.showMessageDialog(
                        null,
                        "Erro ao conectar ao banco de dados: " + ex.getMessage(),
                        "Erro ao conectar",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
                return false;
            }
        } else {
            // Exibe a mensagem de erro e imprime
            JOptionPane.showMessageDialog(
                    null,
                    "Configuração de conexão inválida!",
                    "Erro ao conectar",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }
}
