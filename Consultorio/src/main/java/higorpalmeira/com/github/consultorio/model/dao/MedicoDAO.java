/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Medico;
import main.java.higorpalmeira.com.github.consultorio.model.enums.Status;

/**
 *
 * @author higor
 */
public interface MedicoDAO {
    
    public int insert(Medico medico);
    
    public int update(Medico medico);
    
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException;
    
    public List<Medico> selectAll();
    
    public Medico selectId(int id);
    
    public Medico selectCrm(String crm);
    
    public List<Medico> selectEspecialidade(int idEspecialidade);
    
    public List<Medico> selectStatus(Status status);
    
    public List<Medico> selectTelefone(String telefone);
    
}
