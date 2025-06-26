/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.dao.DAOGenerico;
import model.entity.Usuario;
import model.enums.NivelAcesso;

/**
 *
 * @author higor
 */
public class UsuarioDAOJDBC implements UsuarioDAO {

    @Override
    public int insert(Usuario usuario) {
        
        String insert = "CALL InserirNovoUsuario(?, ?, ?, ?)";
        
        int line = 0;
        
        try {
            
            line = DAOGenerico.executarStoredProcedure(insert, usuario.getLogin(),
                    usuario.getSenha(),
                    usuario.getNivelAcesso().getDescricao());
            
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return line;
    }

    @Override
    public int update(Usuario usuario) {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE usuario SET ")
                .append("login = ?, ")
                .append("senha = ?, ")
                .append("nivel_acesso = ? ")
                .append("WHERE id = ?");
        String update = sqlBuilder.toString();
        
        int line = 0;
        
        try {
            
            line = DAOGenerico.executarComando(update, usuario.getLogin(),
                                                usuario.getSenha(),
                                                usuario.getNivelAcesso().getDescricao(),
                                                usuario.getId());
            
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return line;
        
    }

    @Override
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("DELETE FROM usuarios ")
                .append("WHERE id = ?");
        String delete = sqlBuilder.toString();
        int line = 0;
        line = DAOGenerico.executarComando(delete, id);
        return line;
        
    }

    @Override
    public List<Usuario> selectAll() {
        
        ResultSet rset;
        String select = "SELECT * FROM usuario_simples ORDER BY data_criacao_usuario DESC";
        List<Usuario> listaUsuario = new ArrayList<>();
        
        try {
            
            rset = DAOGenerico.executarConsulta(select);
            while(rset.next()) {
                Usuario usuario = new Usuario(
                                        rset.getInt("id_usuario"),
                                        rset.getString("login_usuario"),
                                        rset.getString("senha_usuario"),
                                        rset.getTimestamp("data_criacao_usuario").toLocalDateTime(),
                                        NivelAcesso.fromDescricao(rset.getString("nivel_acesso_usuario"))
                                        
                );
                
                listaUsuario.add(usuario);
            }
            
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return listaUsuario;
        
    }

    @Override
    public List<Usuario> selectByNivelAcesso(NivelAcesso nivelAcesso) {
        
        ResultSet rset;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("SELECT * FROM usuario_simples ")
                .append("WHERE nivel_acesso_usuario = ?");
        String select = sqlBuilder.toString();
        List<Usuario> listaUsuario = new ArrayList<>();
        
        try {
            
            rset = DAOGenerico.executarConsulta(select, nivelAcesso.getDescricao());
            
            while(rset.next()) {
                Usuario usuario = new Usuario(
                                        rset.getInt("id_usuario"),
                                        rset.getString("login_usuario"),
                                        rset.getString("senha_usuario"),
                                        rset.getTimestamp("data_criacao_usuario").toLocalDateTime(),
                                        NivelAcesso.fromDescricao(rset.getString("nivel_acesso_usuario"))
                                        
                );
                
                listaUsuario.add(usuario);
            }
            
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return listaUsuario;
        
    }

    @Override
    public List<Usuario> selectByDataCriacao(LocalDateTime[] intervalo) {
        
        ResultSet rset;
        
        String select = "CALL ListarUsuariosPorDataCriacao(?, ?)";
        
        List<Usuario> listaUsuario = new ArrayList<>();
        
        try {
            
            rset = DAOGenerico.executarConsulta(select, intervalo[0], intervalo[1]);
            
            while(rset.next()) {
                Usuario usuario = new Usuario(
                                        rset.getInt("id_usuario"),
                                        rset.getString("login_usuario"),
                                        rset.getString("senha_usuario"),
                                        rset.getTimestamp("data_criacao_usuario").toLocalDateTime(),
                                        NivelAcesso.fromDescricao(rset.getString("nivel_acesso_usuario"))
                                        
                );
                
                listaUsuario.add(usuario);
            }
            
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return listaUsuario;
        
    }

    @Override
    public Usuario selectById(int id) {
        
        ResultSet rset;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("SELECT * FROM usuario_simples ")
                .append("WHERE id = ? ")
                .append("ORDER BY data_criacao DESC");
        
        String select = sqlBuilder.toString();
        
        Usuario usuario = null;
        
        try {
            
            rset = DAOGenerico.executarConsulta(select, id);
            
            while(rset.next()) {
                usuario = new Usuario(
                                        rset.getInt("id_usuario"),
                                        rset.getString("login_usuario"),
                                        rset.getString("senha_usuario"),
                                        rset.getTimestamp("data_criacao_usuario").toLocalDateTime(),
                                        NivelAcesso.fromDescricao(rset.getString("nivel_acesso_usuario"))
                                        
                );
            }
            
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return usuario;
        
    }

    @Override
    public Usuario selectByLogin(String login) {
        
        ResultSet rset;
        
        String select = "CALL BuscarUsuarioPorLogin(?)";
        
        Usuario usuario = null;
        
        try {
            
            rset = DAOGenerico.executarConsulta(select, login);
            
            while(rset.next()) {
                usuario = new Usuario(
                                        rset.getInt("id_usuario"),
                                        rset.getString("login_usuario"),
                                        rset.getString("senha_usuario"),
                                        rset.getTimestamp("data_criacao_usuario").toLocalDateTime(),
                                        NivelAcesso.fromDescricao(rset.getString("nivel_acesso_usuario"))
                                        
                );
            }
            
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return usuario;
        
    }
    
}
