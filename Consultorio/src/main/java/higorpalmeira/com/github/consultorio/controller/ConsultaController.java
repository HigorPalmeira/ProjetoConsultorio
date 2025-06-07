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
    
    public void criarConsulta(int idMedico, int idPaciente, LocalDateTime dataHora, String observacoes, String status) {
        
        if (this.consultaService.criarConsulta(idMedico, idPaciente, dataHora, observacoes, status)) {
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
        
        if (consulta == null || (consulta.getId() == 0 && consulta.getObservacoes() == null)) {
            System.out.printf("A consulta com o ID '%d' não existe no sistema!\n", id);
            
        } else {
            System.out.println(consulta.toString());
            
        }
        
    }
    
}
