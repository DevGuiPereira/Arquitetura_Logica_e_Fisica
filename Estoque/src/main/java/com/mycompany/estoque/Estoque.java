/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.estoque;

import com.mycompany.estoque.repository.BackupMySQLRepository;
import com.mycompany.estoque.repository.Conexao;
import com.mycompany.estoque.repository.ConexaoMySQL;
import com.mycompany.estoque.view.Inicio;
import com.mycompany.estoque.view.Login;
import java.io.File;

/**
 *
 * @author guilh
 */
public class Estoque {

    public static void main(String[] args) {       
    // Configura a conexão com o banco
        Conexao conexao = new Conexao(
                "localhost",
                "root",
                "Guirp007007!", //configure sua conexão com o banco de dados
                3306,
                "estoque"
        );
        BackupMySQLRepository backup = new BackupMySQLRepository(conexao);

        // Caminho do arquivo de backup
        String caminhoBackup = "C:/Users/guilh/Downloads/backupBanco.sql";

        // Restaura o backup ao iniciar
        if (new File(caminhoBackup).exists()) {
            boolean sucesso = backup.restaurarBackup(caminhoBackup);
            if (sucesso) {
                System.out.println("Backup restaurado com sucesso.");
            } else {
                System.err.println("Falha ao restaurar o backup.");
            }
        }

        // Inicia a interface gráfica
        Login login = new Login();
        login.setVisible(true);
        login.setTitle("Login");
        

        // Ao sair do programa, faz o backup automaticamente
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            boolean sucesso = backup.realizarBackup(caminhoBackup);
            if (sucesso) {
                System.out.println("Backup realizado com sucesso ao encerrar.");
            } else {
                System.err.println("Falha ao realizar o backup ao encerrar.");
            }
        }));
    }
}    


