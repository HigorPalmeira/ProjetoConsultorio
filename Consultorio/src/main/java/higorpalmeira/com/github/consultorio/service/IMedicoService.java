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
    
    boolean loginMedico(String usuario, String senha);
    
    boolean criarMedico(String nome, String crm, int idEspecialidade, String telefone, String email, int idEndereco);
    
    boolean atualizarMedico(int id, String nome, String crm, int idEspecialidade, String status, String telefone, String email, int idEndereco);
    
    boolean deletarMedico(int id);
    
    List<Medico> listarTodosMedicos();
    
    Medico buscarMedicoPorId(int id);
    
    Medico buscarMedicoPorCrm(String crm);
    
    List<Medico> buscarMedicoPorEspecialidade(int idEspecialidade);
    
    List<Medico> buscarMedicoPorStatus(String status);
    
    List<Medico> buscarMedicoPorTelefone(String telefone);
    
}
