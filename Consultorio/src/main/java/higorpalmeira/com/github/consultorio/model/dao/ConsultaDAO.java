/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Consulta;

/**
 *
 * @author higor
 */
public interface ConsultaDAO {
    
    public int insert(Consulta consulta);
    
    public int update(Consulta consulta);
    
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException;
    
    public List<Consulta> selectAll();
    
    public Consulta selectId(int id);
    
}
