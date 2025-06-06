/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;

/**
 *
 * @author higor
 */
public interface EspecialidadeDAO {
    
    public int insert(Especialidade especialidade);
    
    public int update(Especialidade especialidade);
    
    public int delete(int codigo) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException;
    
    public List<Especialidade> selectAll();
    
    public Especialidade selectId(int id);
}
