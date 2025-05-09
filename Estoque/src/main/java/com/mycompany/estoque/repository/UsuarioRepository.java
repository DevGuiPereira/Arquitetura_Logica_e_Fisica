/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estoque.repository;

import com.mycompany.estoque.model.Log;
import com.mycompany.estoque.model.Produto;
import com.mycompany.estoque.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author guilh
 */
public class UsuarioRepository implements CrudUsuario<Usuario>{

    @Override
    public boolean inserir(Connection connection, Usuario usuario) {
        PreparedStatement stmt = null;
        try{
            String comando = "INSERT INTO usuarios(nome, usuario, senha, cargo) " +  //criação do comando sql para inserir produtos no banco de dados
                             "VALUES(?, ?, ?, ?)";
            
            stmt = connection.prepareStatement(comando);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getUsuario());          // informações escritas pelo usuário no programa para incrementar o comando sql   
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getCargo());
            
            stmt.executeUpdate();
            stmt.close();
            return true;
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao inserir usuario: " + ex.getMessage(),    //mensagem de erro
                    "Erro ao inserir",
                    JOptionPane.ERROR_MESSAGE
            );
            System.out.println(ex.getMessage());
            return false;
        }    
    }

    @Override
    public boolean atualizar(Connection connection, Usuario usuario) {
        PreparedStatement stmt = null;
        try{
            String comando = "UPDATE usuarios SET " +
                             "nome = ?, usuario = ?, senha = ?, cargo = ? " +  //criação do comando sql para atualizar produtos já existentes no banco de dados
                             "WHERE id = ?";
            
            stmt = connection.prepareStatement(comando);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getUsuario());           
            stmt.setString(3, usuario.getSenha());                  // informações escritas pelo usuário no programa para incrementar o comando sql
            stmt.setInt(4, usuario.getCargo());
            stmt.setInt(5, usuario.getId());
            
            stmt.executeUpdate();
            stmt.close();
            return true;
            
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao atualizar usuario: " + ex.getMessage(),        //mensagem de erro
                    "Erro ao atualizar",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public boolean deletar(Connection connection, int id) {
        PreparedStatement stmt = null;
        try{
            String comando = "DELETE FROM usuarios " +     //criação do comando sql para delletar produtos do banco de dados
                             "WHERE id = ?";
            
            stmt = connection.prepareStatement(comando);
            
            stmt.setInt(1,id);     // id informado pelo usuário
            
            stmt.executeUpdate();
            stmt.close();
            return true;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao excluir usuario: " + ex.getMessage(),   //mensagem de erro
                    "Erro ao excluir",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    @Override
    public Usuario selecionar(Connection connection, int id) {
        
        Usuario usuario = new Usuario(); //criação do comando sql para delletar produtos do banco de dados
        
        try{
            PreparedStatement stmt = null;

            String comando = "SELECT * FROM usuarios WHERE id = ? ";  //criação do comando sql para selecionar o produto no banco de dados
            
            stmt = connection.prepareStatement(comando);  
            
            stmt.setInt(1, id);   // id informado pelo usuário
            
            ResultSet res = stmt.executeQuery();
            if(res.next()){                
                usuario.setId(Integer.parseInt(res.getString("id") ));
                usuario.setNome(res.getString("nome"));
                usuario.setUsuario(res.getString("usuario"));               // transcrevendo as informações do banco para o objeto produto do programa
                usuario.setSenha(res.getString("senha"));
                usuario.setCargo(Integer.parseInt(res.getString("cargo") ));
            }
            
        }catch(Exception ex){ 
            ex.printStackTrace();   //mensagem de erro
        }
        return usuario;
    }

    @Override
    public List<Usuario> selecionarTodos(Connection connection) {
    List<Usuario> usuarios = new ArrayList<>();  //criando a lista de objetos produto
        String comando = "SELECT * FROM usuarios";    //criação do comando sql para selecionar os produtos no banco de dados    

        try {
            PreparedStatement stmt = connection.prepareStatement(comando);
            ResultSet rs = stmt.executeQuery();

            // Preenchendo a lista de produtos com os dados do ResultSet
            while (rs.next()) {
                
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));  
                usuario.setNome(rs.getString("nome"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCargo(rs.getInt("cargo"));
                usuarios.add(usuario);  // Adiciona o produto na lista
            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return usuarios;
    }
    
    public Usuario autenticar(Connection connection, String usuarioInput, String senhaInput) {
    Usuario usuario = null;
    String comando = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";

    try {
        PreparedStatement stmt = connection.prepareStatement(comando);
        stmt.setString(1, usuarioInput);
        stmt.setString(2, senhaInput);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setUsuario(rs.getString("usuario"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setCargo(rs.getInt("cargo"));
        }

        rs.close();
        stmt.close();
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao autenticar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    return usuario;
}

    public List<Log> selecionarTodosLogs(Connection connection) {
        List<Log> logs = new ArrayList<>();
        String comando = "SELECT \n" +
                         "registros.idlogs,\n" +
                         "registros.logscol,\n" +
                         "registros.data,\n" +
                         "usuarios.nome AS nome_usuario\n" +
                         "FROM registros\n" +
                         "INNER JOIN usuarios ON registros.usuario = usuarios.id;";
        try {
            PreparedStatement stmt = connection.prepareStatement(comando);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Log log = new Log();
                log.setId(rs.getInt("idlogs")); // ID do log
                log.setLogscol(rs.getString("logscol")); // Mensagem do log
                log.setData(rs.getObject("data", LocalDateTime.class)); // Data/hora do log
                log.setNomeUsuario(rs.getString("nome_usuario")); // Nome do usuário (String)
                logs.add(log);
                
                System.out.println("Log ID: " + log.getId() + ", Mensagem: " + log.getLogscol() + 
                               ", Data: " + log.getData() + ", Usuário: " + log.getNomeUsuario());

            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return logs;
    }
    
    public boolean inserirLog(Connection connection, Log log) {
        PreparedStatement stmt = null;
        try {
            String comando = "INSERT INTO registros(logscol, usuario) " +
                             "VALUES(?,  ?)";

            stmt = connection.prepareStatement(comando);

            stmt.setString(1, log.getLogscol());                             // Mensagem do log
            stmt.setInt(2, log.getIdusuario());                              // ID do usuário (FK)

            stmt.executeUpdate();
            stmt.close();
            return true;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao inserir log: " + ex.getMessage(),
                    "Erro ao inserir",
                    JOptionPane.ERROR_MESSAGE
            );
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
}


