/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.time.LocalDate;
import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Paciente;

/**
 *
 * @author higor
 */
public interface IPacienteService {
    
    boolean criarPaciente(String nome, String cpf, LocalDate dataNascimento, String sexo, String telefone, String email, int idEndereco);
    
    boolean atualizarPaciente(int id, String nome, String cpf, LocalDate dataNascimento, String sexo, String status, String telefone, String email, int idEndereco);
    
    boolean deletarPaciente(int id);
    
    List<Paciente> listarTodosPaciente();
    
    Paciente buscarPacientePorId(int id);
}
