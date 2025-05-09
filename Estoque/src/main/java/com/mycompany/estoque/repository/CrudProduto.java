/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.estoque.repository;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author guilh
 */
public interface CrudProduto<T> {
    // Interface para fazer a tipagem das funções, especificando quais funções existem, quais variáveis vão receber e como vão retornar o resultado da função
    public boolean inserir(Connection connection, T entity);
    public boolean atualizar(Connection connection, T entity);
    public boolean deletar(Connection connection, int id);
    public T selecionar(Connection connection, int id); 
    public List<T> selecionarTodos(Connection connection);
    public List<T> selecionarPoucoEstoque(Connection connection);
    
}
