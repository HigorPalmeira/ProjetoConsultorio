/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Medico;

/**
 *
 * @author higor
 */
public interface IMedicoService {
    
    boolean criarMedico(String nome, String crm, int idEspecialidade, String telefone, String email);
    
    boolean atualizarMedico(int id, String nome, String crm, int idEspecialidade, String telefone, String email);
    
    boolean deletarMedico(int id);
    
    List<Medico> listarTodosMedicos();
    
    Medico buscarMedicoPorId(int id);
    
}
