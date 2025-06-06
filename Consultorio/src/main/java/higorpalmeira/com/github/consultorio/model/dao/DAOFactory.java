/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

/**
 *
 * @author higor
 */
public class DAOFactory {
    
    public static EspecialidadeDAO criarEspecialidadeDAO() {
        
        return new EspecialidadeDAOJDBC();
        
    }
    
}
