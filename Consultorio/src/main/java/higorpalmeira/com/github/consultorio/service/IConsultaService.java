/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.time.LocalDateTime;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Consulta;

/**
 *
 * @author higor
 */
public interface IConsultaService {
    
    public boolean criarConsulta(int idMedico, int idPaciente, LocalDateTime dataHora, String observacoes);
    
    public boolean atualizarConsulta(int id, int idMedico, int idPaciente, LocalDateTime dataHora, String observacoes, String status);
    
    public boolean deletarConsulta(int id);
    
    public List<Consulta> listarTodasConsultas();
    
    public Consulta buscarConsultaPorId(int id);
    
    public List<Consulta> buscarConsultaPorStatus(String status);
    
    public List<Consulta> buscarConsultaPorDataHora(LocalDateTime dataHora);
    
    public List<Consulta> buscarConsultaPorIdMedico(int idMedico);
    
    public List<Consulta> buscarConsultaPorCrmMedico(String crm);
    
    public List<Consulta> buscarConsultaPorIdEspecialidadeMedico(int idEspecialidadeMedico);
    
    public List<Consulta> buscarConsultaPorStatusMedico(String status);
    
    public List<Consulta> buscarConsultaPorStatusPaciente(String status);
    
    public List<Consulta> buscarConsultaPorSexoPaciente(String sexo);
    
    public List<Consulta> buscarConsultaPorIdPaciente(int idPaciente);
    
}
