/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;

import controller.UsuarioController;
import model.dao.UsuarioDAOJDBC;
import service.UsuarioServiceImpl;

/**
 *
 * @author higor
 */
public class Runner {

    private static UsuarioController usuarioController;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        usuarioController = new UsuarioController(new UsuarioServiceImpl( new UsuarioDAOJDBC() ));
        
        String login, senha, nivelAcesso;
        
        login = "kalleb";
        senha = "bellak";
        nivelAcesso = "BAIXO";
        
        //usuarioController.inserirUsuario(login, senha, nivelAcesso);
        
        usuarioController.listarTodosUsuarios();
        
    }
    
}
