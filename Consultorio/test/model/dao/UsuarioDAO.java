/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import model.entity.Usuario;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import model.enums.NivelAcesso;

/**
 *
 * @author higor
 */
public interface UsuarioDAO {
    
    public int insert(Usuario usuario);
    
    public int update(Usuario usuario);
    
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException;
    
    public List<Usuario> selectAll();
    
    public List<Usuario> selectByNivelAcesso(NivelAcesso nivelAcesso);
    
    public List<Usuario> selectByDataCriacao(LocalDateTime[] intervalo);
    
    public Usuario selectById(int id);
    
    public Usuario selectByLogin(String login);
}
