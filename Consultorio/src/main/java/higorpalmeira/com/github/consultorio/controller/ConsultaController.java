/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.controller;

import java.time.LocalDateTime;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Consulta;
import main.java.higorpalmeira.com.github.consultorio.service.IConsultaService;

/**
 *
 * @author higor
 */
public class ConsultaController {
    
    private final IConsultaService consultaService;
    
    public ConsultaController(IConsultaService consultaService) {
        this.consultaService = consultaService;
    }
    
    public void criarConsulta(int idMedico, int idPaciente, LocalDateTime dataHora, String observacoes) {
        
        if (this.consultaService.criarConsulta(idMedico, idPaciente, dataHora, observacoes)) {
            System.out.println("Consulta criada com sucesso!");
            
        } else {
            System.out.println("Consulta não pode ser criada!");
        }
        
    }
    
    public void atualizarConsulta(int id, int idMedico, int idPaciente, LocalDateTime dataHora, String observacoes, String status) {
        
        if (this.consultaService.atualizarConsulta(id, idMedico, idPaciente, dataHora, observacoes, status)) {
            System.out.println("Consulta atualizada com sucesso!");
            
        } else {
            System.out.println("Consulta não pode ser atualizada!");
            
        }
        
    }
    
    public void deletarConsulta(int id) {
        
        if (this.consultaService.deletarConsulta(id)) {
            System.out.println("Consulta excluída com sucesso!");
            
        } else {
            System.out.println("Consulta não pode ser excluída!");
            
        }
        
    }
    
    public void listarTodasConsultas() {
        
        List<Consulta> listaConsultas = this.consultaService.listarTodasConsultas();
        
        if (listaConsultas.isEmpty()) {
            System.out.println("Não há consultas cadastradas no sistema!");
            
        } else {
            for (Consulta consulta : listaConsultas) {
                System.out.println(consulta.toString());
            }
        }
        
    }
    
    public void buscarConsultaPorId(int id) {
        
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        
        if ((consulta.getId() == 0 && consulta.getObservacoes() == null)) {
            System.out.printf("A consulta com o ID '%d' não existe no sistema!\n", id);
            
        } else {
            System.out.println(consulta.toString());
            
        }
        
    }
    
    public void buscarConsultaPorStatus(String status) {
        
        List<Consulta> listaConsultas = consultaService.buscarConsultaPorStatus(status);
        
        if (listaConsultas.isEmpty()) {
            System.out.printf("Não há consultas com o status '%s' no sistema!\n", status.trim().toUpperCase());
            
        } else {
            for (Consulta consulta : listaConsultas) {
                System.out.println(consulta.toString());
            }
        }
        
    }
    
    public void buscarConsultaPorDataHora(LocalDateTime dataHora) {
        
        List<Consulta> listaConsultas = consultaService.buscarConsultaPorDataHora(dataHora);
        
        if (listaConsultas.isEmpty()) {
            System.out.printf("Não há consultas com a data e horário '%s' no sistema!\n", dataHora.toString());
            
        } else {
            for (Consulta consulta : listaConsultas) {
                System.out.println(consulta.toString());
            }
        }
        
    }
    
    public void buscarConsultaPorIdMedico(int idMedico) {
        
        List<Consulta> listaConsultas = consultaService.buscarConsultaPorIdMedico(idMedico);
        
        if (listaConsultas.isEmpty()) {
            System.out.printf("Não há consultas com o ID do médico '%d' no sistema!\n", idMedico);
            
        } else {
            for (Consulta consulta : listaConsultas) {
                System.out.println(consulta.toString());
            }
        }
        
    }
    
    public void buscarConsultaPorCrmMedico(String crm) {
        
        List<Consulta> listaConsultas = consultaService.buscarConsultaPorCrmMedico(crm);
        
        if (listaConsultas.isEmpty()) {
            System.out.printf("Não há consultas com o CRM do médico '%s' no sistema!\n", crm);
            
        } else {
            for (Consulta consulta : listaConsultas) {
                System.out.println(consulta.toString());
            }
        }
        
    }
    
    public void buscarConsultaPorIdEspecialidadeMedico(int idEspecialidadeMedico) {
        
        List<Consulta> listaConsultas = consultaService.buscarConsultaPorIdEspecialidadeMedico(idEspecialidadeMedico);
        
        if (listaConsultas.isEmpty()) {
            System.out.printf("Não há consultas com o ID da especialidade '%d' no sistema!\n", idEspecialidadeMedico);
            
        } else {
            for (Consulta consulta : listaConsultas) {
                System.out.println(consulta.toString());
            }
        }
        
    }
    
    public void buscarConsultaPorStatusMedico(String status) {
        
        List<Consulta> listaConsultas = consultaService.buscarConsultaPorStatusMedico(status);
        
        if (listaConsultas.isEmpty()) {
            System.out.printf("Não há consultas com médicos do status '%s' no sistema!\n", status.trim().toUpperCase());
            
        } else {
            for (Consulta consulta : listaConsultas) {
                System.out.println(consulta.toString());
            }
        }
        
    }
    
    public void buscarConsultaPorStatusPaciente(String status) {
        
        List<Consulta> listaConsultas = consultaService.buscarConsultaPorStatusPaciente(status);
        
        if (listaConsultas.isEmpty()) {
            System.out.printf("Não há consultas com pacientes do status '%s' no sistema!\n", status.trim().toUpperCase());
            
        } else {
            for (Consulta consulta : listaConsultas) {
                System.out.println(consulta.toString());
            }
        }
        
    }
    
    public void buscarConsultaPorSexoPaciente(String sexo) {
        
        List<Consulta> listaConsultas = consultaService.buscarConsultaPorSexoPaciente(sexo);
        
        if (listaConsultas.isEmpty()) {
            System.out.printf("Não há consultas com pacientes do sexo '%s' no sistema!\n", sexo.trim().toUpperCase());
            
        } else {
            for (Consulta consulta : listaConsultas) {
                System.out.println(consulta.toString());
            }
        }
        
    }
    
    public void buscarConsultaPorIdPaciente(int idPaciente) {
        
        List<Consulta> listaConsultas = consultaService.buscarConsultaPorIdPaciente(idPaciente);
        
        if (listaConsultas.isEmpty()) {
            System.out.printf("Não há consultas com ID do paciente '%d' no sistema!\n", idPaciente);
            
        } else {
            for (Consulta consulta : listaConsultas) {
                System.out.println(consulta.toString());
            }
        }
        
    }
    
}
