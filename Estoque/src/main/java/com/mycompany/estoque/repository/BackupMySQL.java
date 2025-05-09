/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.estoque.repository;

/**
 *
 * @author guilh
 */
public interface BackupMySQL {
    boolean realizarBackup(String caminhoBackup);
    boolean restaurarBackup(String caminhoBackup);    
}
