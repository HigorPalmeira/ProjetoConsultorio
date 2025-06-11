/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.controller;

import java.time.LocalDate;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Paciente;
import main.java.higorpalmeira.com.github.consultorio.service.IPacienteService;

/**
 *
 * @author higor
 */
public class PacienteController {
    
    private final IPacienteService pacienteService;
    
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    
    public void criarPaciente(String nome, String cpf, LocalDate dataNascimento,
                                    String sexo, String telefone, String email,
                                    int idEndereco) {
        
        if (pacienteService.criarPaciente(nome, cpf, dataNascimento, sexo, telefone, email, idEndereco)) {
            System.out.println("Paciente criado com sucesso!");
            
        } else {
            System.out.println("Paciente não pode ser criado!");
        }
        
    }
    
    public void atualizarPaciente(int id, String nome, String cpf, LocalDate dataNascimento,
                                    String sexo, String status, String telefone, String email,
                                    int idEndereco) {
        
        if (pacienteService.atualizarPaciente(id, nome, cpf, dataNascimento, sexo, status, telefone, email, idEndereco)) {
            System.out.println("Paciente atualizado com sucesso!");
            
        } else {
            System.out.println("O paciente não foi atualizado!");
            
        }
        
    }
    
    public void deletarPaciente(int id) {
        
        if (pacienteService.deletarPaciente(id)) {
            System.out.println("Paciente foi deletado com sucesso!");
            
        } else {
            System.out.println("Paciente não pode ser deletado!");
            
        }
        
    }
    
    public void listarTodosPacientes() {
        
        List<Paciente> listaPacientes = pacienteService.listarTodosPaciente();
        
        if (listaPacientes.isEmpty()) {
            System.out.println("Não há pacientes cadastrados no sistema!");
            
        } else {
            for (Paciente paciente : listaPacientes) {
                System.out.println(paciente.toString());
                
            }
        }
                
    }
    
    public void buscarPacientePorId(int id) {
        
        Paciente paciente = pacienteService.buscarPacientePorId(id);
        
        if (paciente == null || (paciente.getId() == 0 && paciente.getNome() == null)) {
            System.out.printf("O paciente com o ID '%d' não existe no sistema!\n", id);
            
        } else {
            System.out.println(paciente.toString());
        }
    }
}
