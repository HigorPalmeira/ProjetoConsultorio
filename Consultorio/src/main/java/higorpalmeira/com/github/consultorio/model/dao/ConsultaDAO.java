/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.model.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Consulta;
import main.java.higorpalmeira.com.github.consultorio.model.enums.ConsultaStatus;
import main.java.higorpalmeira.com.github.consultorio.model.enums.PacienteSexo;
import main.java.higorpalmeira.com.github.consultorio.model.enums.Status;

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
    
    public List<Consulta> selectStatus(ConsultaStatus status);
    
    public List<Consulta> selectDataHota(LocalDateTime dataHora);
    
    public List<Consulta> selectIdMedico(int idMedico);
    
    public List<Consulta> selectCrmMedico(String crm);
    
    public List<Consulta> selectIdEspecialidadeMedico(int idEspecialidadeMedico);
    
    public List<Consulta> selectStatusMedico(Status status);
    
    public List<Consulta> selectStatusPaciente(Status status);
    
    public List<Consulta> selectSexoPaciente(PacienteSexo sexo);
    
    public List<Consulta> selectIdPaciente(int idPaciente);
    
}
