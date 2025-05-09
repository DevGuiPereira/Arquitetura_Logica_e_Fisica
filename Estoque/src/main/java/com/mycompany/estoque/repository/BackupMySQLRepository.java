/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estoque.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class BackupMySQLRepository implements BackupMySQL {

    private final Conexao conexao;

    public BackupMySQLRepository(Conexao conexao) {
        this.conexao = conexao;
    }

    @Override
    public boolean realizarBackup(String caminhoBackup) {
        String mysqldumpPath = "C:/xampp/mysql/bin/mysqldump.exe";

        List<String> comando = Arrays.asList(
            mysqldumpPath,
            "-u", conexao.getUser(),
            "--password=" + conexao.getPassword(), // Melhor para senhas com símbolos
            "-h", conexao.getEndereco(),
            "-P", String.valueOf(conexao.getPorta()),
            conexao.getNomeBanco(),
            "-r", caminhoBackup
        );

        System.out.println("Executando comando de backup:");
        for (String s : comando) {
            System.out.print(s + " ");
        }
        System.out.println();

        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.redirectErrorStream(true); // une stdout + stderr

        try {
            Process processo = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            String linha;
            System.out.println("Saída do processo:");
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha); // mostra erros ou sucesso
            }

            int status = processo.waitFor();
            System.out.println("Código de saída do processo: " + status);

            return status == 0;
        } catch (IOException | InterruptedException ex) {
            System.err.println("Exceção ao tentar fazer backup:");
            ex.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean restaurarBackup(String caminhoBackup) {
        String mysqlPath = "C:/xampp/mysql/bin/mysql.exe"; // Ajuste se necessário

        String[] comando = new String[]{
            mysqlPath,
            "-u", conexao.getUser(),
            "--password=" + conexao.getPassword(),
            "-h", conexao.getEndereco(),
            "-P", String.valueOf(conexao.getPorta()),
            conexao.getNomeBanco(),
            "-e", "source " + caminhoBackup
        };

        System.out.println("Executando comando de restauração:");
        for (String s : comando) {
            System.out.print(s + " ");
        }
        System.out.println();

        try {
            Process processo = new ProcessBuilder(comando).redirectErrorStream(true).start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            String linha;
            System.out.println("Saída do processo:");
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha); // mostra saída e erros
            }

            int status = processo.waitFor();
            System.out.println("Código de saída do processo: " + status);

            return status == 0;
        } catch (IOException | InterruptedException ex) {
            System.err.println("Erro ao restaurar backup:");
            ex.printStackTrace();
            return false;
        }
    }

}

