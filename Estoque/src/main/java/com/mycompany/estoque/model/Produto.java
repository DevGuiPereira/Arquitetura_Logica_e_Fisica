/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estoque.model;

/**
 *
 * @author guilh
 */
public class Produto {
    private int id;
    private String nome;
    private String descricao;   //variáveis privadas do objeto Produto
    private double preco;
    private int quantidade;
    
    public Produto() {     //construtor vazio, para não ter a necessiade de fornecer valores iniciais
    }

    public Produto(int id, String nome, String descricao, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;       //construtor com as variáveis do objeto, incluindo o id
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public Produto(String nome, String descricao, double preco, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;          ///construtor com as variáveis do objeto, excluindo o id
        this.preco = preco;
        this.quantidade = quantidade;
    }

    //gets de todas as variáveis do objeto
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    //sets de todas as variáveis do objeto
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descrição) {
        this.descricao = descrição;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
  
}
