/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.higorpalmeira.com.github.consultorio.model.dao.ConsultaDAO;
import main.java.higorpalmeira.com.github.consultorio.model.dao.DAOFactory;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Consulta;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Medico;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Paciente;
import main.java.higorpalmeira.com.github.consultorio.model.enums.ConsultaStatus;
import main.java.higorpalmeira.com.github.consultorio.model.enums.PacienteSexo;
import main.java.higorpalmeira.com.github.consultorio.model.enums.Status;
import main.java.higorpalmeira.com.github.consultorio.util.validator.Validator;

/**
 *
 * @author higor
 */
public class ConsultaServiceImpl implements IConsultaService {
    
    private final ConsultaDAO consultaDAO;    
    
    public ConsultaServiceImpl(ConsultaDAO consultaDAO) {
        this.consultaDAO = consultaDAO;
    }

    @Override
    public boolean criarConsulta(int idMedico, int idPaciente, LocalDateTime dataHora, String observacoes) {
        
        if (idMedico < 0 || idPaciente < 0) {
            return false;
        }
        
        if ( ! Validator.isDataHoraConsulta(dataHora) ) {
            return false;
        }

        Consulta consulta = new Consulta();
        consulta.setDataHorario(dataHora);
        consulta.setObservacoes(observacoes);
        
        Medico medico = new MedicoServiceImpl(DAOFactory.criarMedicoDAO()).buscarMedicoPorId(idMedico);
        Paciente paciente = new PacienteServiceImpl(DAOFactory.criarPacienteDAO()).buscarPacientePorId(idPaciente);
        
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        
        return consultaDAO.insert(consulta) > 0;
        
    }

    @Override
    public boolean atualizarConsulta(int id, int idMedico, int idPaciente, LocalDateTime dataHora, String observacoes, String status) {
        
        if (id < 0 || idMedico < 0 || idPaciente < 0) {
            return false;
        }
        
        if ( ! Validator.isDataHoraConsulta(dataHora) ) {
            return false;
        }
        
        ConsultaStatus enumStatus = ConsultaStatus.fromDescricao(status.trim().toUpperCase());
        
        Consulta consulta = new Consulta();
        consulta.setId(id);
        consulta.setDataHorario(dataHora);
        consulta.setObservacoes(observacoes);
        consulta.setStatus(enumStatus);
        
        Medico medico = new MedicoServiceImpl(DAOFactory.criarMedicoDAO()).buscarMedicoPorId(idMedico);
        Paciente paciente = new PacienteServiceImpl(DAOFactory.criarPacienteDAO()).buscarPacientePorId(idPaciente);
        
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        
        return consultaDAO.update(consulta) > 0;
        
    }

    @Override
    public boolean deletarConsulta(int id) {
        
        if (id < 0) {
            return false;
        }
        
        boolean deletado = false;
        
        try {
            deletado = consultaDAO.delete(id) > 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return deletado;
        
    }

    @Override
    public List<Consulta> listarTodasConsultas() {
        
        return consultaDAO.selectAll();
        
    }

    @Override
    public Consulta buscarConsultaPorId(int id) {
        
        return consultaDAO.selectId(id);
        
    }

    @Override
    public List<Consulta> buscarConsultaPorStatus(String status) {
        
        return consultaDAO.selectStatus( ConsultaStatus.fromDescricao(status.trim().toUpperCase()) );
        
    }

    @Override
    public List<Consulta> buscarConsultaPorDataHora(LocalDateTime dataHora) {
        
        List<Consulta> listaConsultas = new ArrayList<>();      
        
        if ( ! Validator.isDataHoraConsulta(dataHora) ) {
            return listaConsultas;
        }
        
        listaConsultas = consultaDAO.selectDataHota(dataHora);
        
        return listaConsultas;
        
    }

    @Override
    public List<Consulta> buscarConsultaPorIdMedico(int idMedico) {
        
        return consultaDAO.selectIdMedico( idMedico );
        
    }

    @Override
    public List<Consulta> buscarConsultaPorCrmMedico(String crm) {
        
        List<Consulta> listaConsultas = new ArrayList<>();
        
        if ( ! Validator.isCrm(crm) ) {
            return listaConsultas;
        }
        
        listaConsultas = consultaDAO.selectCrmMedico(crm);
        
        return listaConsultas;
        
    }

    @Override
    public List<Consulta> buscarConsultaPorIdEspecialidadeMedico(int idEspecialidadeMedico) {
        
        return consultaDAO.selectIdEspecialidadeMedico(idEspecialidadeMedico);
        
    }

    @Override
    public List<Consulta> buscarConsultaPorStatusMedico(String status) {
        
        return consultaDAO.selectStatusMedico( Status.fromDescricao(status.trim().toUpperCase()) );
        
    }

    @Override
    public List<Consulta> buscarConsultaPorStatusPaciente(String status) {
        
        return consultaDAO.selectStatusPaciente( Status.fromDescricao(status.trim().toUpperCase()) );
        
    }

    @Override
    public List<Consulta> buscarConsultaPorSexoPaciente(String sexo) {
        
        return consultaDAO.selectSexoPaciente( PacienteSexo.fromDescricao(sexo.trim().toUpperCase()) );
        
    }

    @Override
    public List<Consulta> buscarConsultaPorIdPaciente(int idPaciente) {
        
        return consultaDAO.selectIdPaciente(idPaciente);
        
    }
    
    
}
