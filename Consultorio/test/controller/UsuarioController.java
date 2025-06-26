/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.entity.Usuario;
import service.IUsuarioService;

/**
 *
 * @author higor
 */
public class UsuarioController {
    
    private final IUsuarioService usuarioService;
    
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    public void inserirUsuario(String login, String senha, String nivelAcesso) {
        
        if (this.usuarioService.inserirUsuario(login, senha, nivelAcesso)) {
            System.out.println("Usuário cadastrado com sucesso!");
            
        } else {
            System.out.println("Usuário não pode ser cadastrado!");
        }
        
    }
    
    
    public void listarTodosUsuarios() {
        
        List<Usuario> listaUsuarios = this.usuarioService.listarTodosUsuarios();
        
        if (listaUsuarios.isEmpty()) {
            System.out.println("Não há usuário cadastrados no sistema!");
            
        } else {
            for (Usuario usuario : listaUsuarios) {
                System.out.println(usuario.toString());
            }
        }
        
    }
}
