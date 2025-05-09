/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estoque.repository;
import com.mycompany.estoque.model.Produto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author guilh
 */
public class ProdutoRepository implements CrudProduto<Produto>  { // Hierarquia da interface Crud que recebe o objeto Produto para ser trabalhado

    @Override
    public boolean inserir(Connection connection, Produto produto) { // função para inserir produtos no banco de dados
        PreparedStatement stmt = null;
        try{
            String comando = "INSERT INTO produtos(nome, descricao, preco, quantidade) " +  //criação do comando sql para inserir produtos no banco de dados
                             "VALUES(?, ?, ?, ?)";
            
            stmt = connection.prepareStatement(comando);
            
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());          // informações escritas pelo usuário no programa para incrementar o comando sql   
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            
            stmt.executeUpdate();
            stmt.close();
            return true;
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao inserir produto: " + ex.getMessage(),    //mensagem de erro
                    "Erro ao inserir",
                    JOptionPane.ERROR_MESSAGE
            );
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Connection connection, Produto produto) {  // função para atualzar produtos existentes no banco de dados
        PreparedStatement stmt = null;
        try{
            String comando = "UPDATE produtos SET " +
                             "nome = ?, descricao = ?, preco = ?, quantidade = ? " +  //criação do comando sql para atualizar produtos já existentes no banco de dados
                             "WHERE idprodutos = ?";
            
            stmt = connection.prepareStatement(comando);
            
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());           
            stmt.setDouble(3, produto.getPreco());                  // informações escritas pelo usuário no programa para incrementar o comando sql
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getId());
            
            stmt.executeUpdate();
            stmt.close();
            return true;
            
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao atualizar prouto: " + ex.getMessage(),        //mensagem de erro
                    "Erro ao atualizar",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public boolean deletar(Connection connection, int id) {  // função para deletar produtos do banco de dados
        PreparedStatement stmt = null;
        try{
            String comando = "DELETE FROM produtos " +     //criação do comando sql para delletar produtos do banco de dados
                             "WHERE idprodutos = ?";
            
            stmt = connection.prepareStatement(comando);
            
            stmt.setInt(1,id);     // id informado pelo usuário
            
            stmt.executeUpdate();
            stmt.close();
            return true;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao excluir produto: " + ex.getMessage(),   //mensagem de erro
                    "Erro ao excluir",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }   
    }

    @Override
    public Produto selecionar(Connection connection, int id) {  // função para selecionar um produto em específico do banco de dados
        
        Produto produto = new Produto(); //criação do comando sql para delletar produtos do banco de dados
        
        try{
            PreparedStatement stmt = null;

            String comando = "SELECT * FROM produtos WHERE idprodutos = ? ";  //criação do comando sql para selecionar o produto no banco de dados
            
            stmt = connection.prepareStatement(comando);  
            
            stmt.setInt(1, id);   // id informado pelo usuário
            
            ResultSet res = stmt.executeQuery();
            if(res.next()){                
                produto.setId(Integer.parseInt(res.getString("idprodutos") ));
                produto.setNome(res.getString("nome"));
                produto.setDescricao(res.getString("descricao"));               // transcrevendo as informações do banco para o objeto produto do programa
                produto.setPreco(res.getDouble("preco"));
                produto.setQuantidade(Integer.parseInt(res.getString("quantidade") ));
            }
            
        }catch(Exception ex){ 
            ex.printStackTrace();   //mensagem de erro
        }
        return produto;
    }

    @Override
    public List<Produto> selecionarTodos(Connection connection) {  // função para selecionar todos os produtos do banco de dados
        List<Produto> produtos = new ArrayList<>();  //criando a lista de objetos produto
        String comando = "SELECT * FROM produtos";    //criação do comando sql para selecionar os produtos no banco de dados    

        try {
            PreparedStatement stmt = connection.prepareStatement(comando);
            ResultSet rs = stmt.executeQuery();

            // Preenchendo a lista de produtos com os dados do ResultSet
            while (rs.next()) {
                
                Produto produto = new Produto();
                produto.setId(rs.getInt("idprodutos"));  
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produtos.add(produto);  // Adiciona o produto na lista
            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return produtos;  // Retorna a lista de produtos
    }

    @Override
    public List<Produto> selecionarPoucoEstoque(Connection connection) {// função para selecionar todos os produtos com quantidade < 5 no banco de dados
        
        List<Produto> produtos = new ArrayList<>(); //criando a lista de objetos produto
        String comando = "SELECT * FROM produtos WHERE quantidade < 5"; //criação do comando sql para selecionar os produtos no banco de dados  

        try {
            PreparedStatement stmt = connection.prepareStatement(comando);
            ResultSet rs = stmt.executeQuery();

            // Preenchendo a lista de produtos com os dados do ResultSet
            while (rs.next()) {
                
                Produto produto = new Produto();
                produto.setId(rs.getInt("idprodutos"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produtos.add(produto);  // Adiciona o produto na lista
            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return produtos;  // Retorna a lista de produtos
    }

}
    
