/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estoque.model;

import java.time.LocalDateTime;

/**
 *
 * @author guilh
 */
public class Log {
    
    private int id;
    private int idusuario;
    private String logscol;
    private LocalDateTime data;
    private String nomeUsuario;

    public Log(int id, int idusuario, String logscol, LocalDateTime data, String nomeUsuario) {
        this.id = id;
        this.idusuario = idusuario;
        this.logscol = logscol;
        this.data = data;
        this.nomeUsuario = nomeUsuario;
    }

    public Log() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getLogscol() {
        return logscol;
    }

    public void setLogscol(String logscol) {
        this.logscol = logscol;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
    
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
}
