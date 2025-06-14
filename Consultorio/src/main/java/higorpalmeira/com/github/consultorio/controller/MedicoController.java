/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.controller;

import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Medico;
import main.java.higorpalmeira.com.github.consultorio.service.IMedicoService;

/**
 *
 * @author higor
 */
public class MedicoController {
    
    private final IMedicoService medicoService;
    
    public MedicoController(IMedicoService medicoService) {
        this.medicoService = medicoService;
    }
    
    public void criarMedico(String nome, String crm, int idEspecialidade, String telefone, String email) {
        
        if (this.medicoService.criarMedico(nome, crm, idEspecialidade, telefone, email)) {
            System.out.println("Médico criado com sucesso!");
        
        } else {
            System.out.println("Médico não pode ser criado!");
        }
        
    }
    
    public void atualizarMedico(int id, String nome, String crm, int idEspecialidade, String status, String telefone, String email) {
        
        if (this.medicoService.atualizarMedico(id, nome, crm, idEspecialidade, status, telefone, email)) {
            System.out.println("Médico atualizado com sucesso!");
            
        } else {
            System.out.println("Médico não pode ser atualizado!");
            
        }
        
    }
    
    public void deletarMedico(int id) {
        
        if (this.medicoService.deletarMedico(id)) {
            System.out.println("Médico deletado com sucesso!");
            
        } else {
            System.out.println("Médico não pode ser deletado!");
            
        }
        
    }
    
    public void listarTodosMedicos() {
        
        List<Medico> listaMedicos = this.medicoService.listarTodosMedicos();
        
        if (listaMedicos.isEmpty()) {
            System.out.println("Não há médicos cadastrados no sistema!");
            
        } else {
            for (Medico medico : listaMedicos) {
                System.out.println(medico.toString());
            }
        }
        
    }
    
    public void buscarMedicoPorId(int id) {
        
        Medico medico = this.medicoService.buscarMedicoPorId(id);
        
        if (medico == null || (medico.getId() == 0 && medico.getNome() == null)) {
            System.out.printf("O médico com o ID '%d' não existe no sistema!\n", id);
            
        } else {
            System.out.println(medico.toString());
            
        }
        
    }
    
}
