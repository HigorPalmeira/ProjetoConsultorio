/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Paciente;
import main.java.higorpalmeira.com.github.consultorio.model.enums.PacienteSexo;
import main.java.higorpalmeira.com.github.consultorio.model.enums.Status;

/**
 *
 * @author higor
 */
public interface PacienteDAO {
    
    public int insert(Paciente paciente);
    
    public int update(Paciente paciente);
    
    public int delete(int id) throws ClassNotFoundException, SQLException, SQLIntegrityConstraintViolationException;
    
    public List<Paciente> selectAll();
    
    public Paciente selectId(int id);
    
    public Paciente selectCpf(String cpf);
    
    public List<Paciente> selectTelefone(String telefone);
    
    public List<Paciente> selectSexo(PacienteSexo sexo);
    
    public List<Paciente> selectStatus(Status status);
    
}
