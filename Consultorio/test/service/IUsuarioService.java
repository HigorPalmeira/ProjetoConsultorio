/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.entity.Usuario;

/**
 *
 * @author higor
 */
public interface IUsuarioService {
    
    public boolean inserirUsuario(String login, String senha, String nivelAcesso);
    
    public boolean atualizarUsuario(int id, String login, String senha, String nivelAcesso);
    
    public boolean deletarUsuario(int id);
    
    public List<Usuario> listarTodosUsuarios();
    
    public Usuario buscarUsuarioPorId(int id);
    
}
