/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.higorpalmeira.com.github.consultorio.model.dao.DAOFactory;
import main.java.higorpalmeira.com.github.consultorio.model.dao.EspecialidadeDAO;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;

/**
 *
 * @author higor
 */
public class EspecialidadeService {
    
    static EspecialidadeDAO especialidadeDAO = DAOFactory.criarEspecialidadeDAO();
    
    public static boolean insert(String descricao) {
        
        if (descricao.trim().isBlank()) return false;
        
        Especialidade novaEspecialidade = new Especialidade();
        novaEspecialidade.setDescricao(descricao.trim());
        
        return especialidadeDAO.insert(novaEspecialidade) != 0;
        
    }
    
    public static boolean update(int id, String descricao) {
        
        if (id < 0 || descricao.trim().isBlank()) return false;
        
        Especialidade especialidade = new Especialidade();
        especialidade.setId(id);
        especialidade.setDescricao(descricao.trim());
        
        return especialidadeDAO.update(especialidade) != 0;
        
    }
    
    public static boolean delete(int id) {
        
        if (id < 0) return false;
        
        boolean isValid = false;
        
        try {
            isValid = especialidadeDAO.delete(id) != 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EspecialidadeService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isValid;
        
    }
    
    public static List<Especialidade> selectAll() {
        
        return especialidadeDAO.selectAll();
        
    }
    
    public static Especialidade selectId(int id) {
        
        if (id < 0) return null;
        
        Especialidade especialidade = especialidadeDAO.selectId(id);
        
        return especialidade;
    }
    
}
