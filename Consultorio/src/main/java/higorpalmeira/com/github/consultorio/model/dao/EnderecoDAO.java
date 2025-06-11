/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Endereco;

/**
 *
 * @author higor
 */
public interface EnderecoDAO {
    
    public int insert(Endereco endereco);
    
    public int update(Endereco endereco);
    
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException;
    
    public List<Endereco> selectAll();
    
    public Endereco selectById(int id);
    
}
