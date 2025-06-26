/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.Usuario;
import model.enums.NivelAcesso;
import model.dao.UsuarioDAO;

/**
 *
 * @author higor
 */
public class UsuarioServiceImpl implements IUsuarioService {
    
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public boolean inserirUsuario(String login, String senha, String nivelAcesso) {
        
        if (login == null || login.isBlank() || login.length() > 20) {
            return false;
        }
        
        if (senha == null || senha.isBlank() || senha.length() > 255) {
            return false;
        }
        
        NivelAcesso enumNivelAcesso = NivelAcesso.fromDescricao(nivelAcesso);
        
        boolean sucesso = false;
        try {
            String senhaCriptografada = this.criptografar(senha);
            
            Usuario usuario = new Usuario(login, senhaCriptografada, enumNivelAcesso);
            
            sucesso = this.usuarioDAO.insert(usuario) > 0;
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sucesso;
        
    }

    @Override
    public boolean atualizarUsuario(int id, String login, String senha, String nivelAcesso) {
        
        if (login == null || login.isBlank() || login.length() > 20) {
            return false;
        }
        
        if (senha == null || senha.isBlank() || senha.length() > 255) {
            return false;
        }
        
        NivelAcesso enumNivelAcesso = NivelAcesso.fromDescricao(nivelAcesso);
        
        
        boolean sucesso = false;
        try {
            String senhaCriptografada = this.criptografar(senha);
            
            Usuario usuario = new Usuario(id, login, senhaCriptografada, enumNivelAcesso);
            
            sucesso = this.usuarioDAO.insert(usuario) > 0;
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sucesso;
        
    }

    @Override
    public boolean deletarUsuario(int id) {
        
        boolean resultado = false;
        
        try {
            resultado = this.usuarioDAO.delete(id) > 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        
        return this.usuarioDAO.selectAll();
        
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        
        return this.usuarioDAO.selectById(id);
        
    }
    
    
    private String criptografar(String mensagem) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = algorithm.digest( mensagem.getBytes("UTF-8") );
        
        return new String(messageDigest);
        
    }
    
}
