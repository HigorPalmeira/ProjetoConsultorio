/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.controller;

import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.dao.DAOFactory;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Medico;
import main.java.higorpalmeira.com.github.consultorio.service.EspecialidadeServiceImpl;
import main.java.higorpalmeira.com.github.consultorio.service.IMedicoService;

/**
 *
 * @author higor
 */
public class MedicoController {
    
    private final IMedicoService medicoService;
    
    private boolean acesso;
    
    public MedicoController(IMedicoService medicoService) {
        this.medicoService = medicoService;
        this.acesso = false;
    }
    
    public boolean getAcesso() {
        return this.acesso;
    }
    
    public void setAcesso(boolean acesso) {
        this.acesso = acesso;
    }
    
    public void loginMedico( String usuario, String senha ) {
        
        if (this.medicoService.loginMedico(usuario, senha)) {
            this.acesso = true;
            System.out.println("Acesso garantido!");
            
        } else {
            this.acesso = false;
            System.out.println("Acesso negado!");
        }
        
    }
    
    public void criarMedico(String nome, String crm, int idEspecialidade, String telefone, String email, int idEndereco) {
        
        if (this.medicoService.criarMedico(nome, crm, idEspecialidade, telefone, email, idEndereco)) {
            System.out.println("Médico criado com sucesso!");
        
        } else {
            System.out.println("Médico não pode ser criado!");
        }
        
    }
    
    public void atualizarMedico(int id, String nome, String crm, int idEspecialidade, String status, String telefone, String email, int idEndereco) {
        
        if (this.medicoService.atualizarMedico(id, nome, crm, idEspecialidade, status, telefone, email, idEndereco)) {
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
    
    public void buscarMedicoPorCrm(String crm) {
        
        Medico medico = this.medicoService.buscarMedicoPorCrm(crm);
        
        if (medico == null || ( medico.getId() == 0 && medico.getCrm() == null )) {
            System.out.printf("O médico com o CRM '%s' não existe no sistema!\n", crm);
            
        } else {
            System.out.println(medico.toString());
            
        }
        
    }
    
    public void buscarMedicoPorStatus(String status) {
        
        List<Medico> listaMedicos = this.medicoService.buscarMedicoPorStatus(status);
        
        if (listaMedicos.isEmpty()) {
            System.out.printf("Não há médicos cadastrados no sistema com o status '%s'!\n", status);
            
        } else {
            for (Medico medico : listaMedicos) {
                System.out.println(medico.toString());
            }
        }
        
    }
    
    public void buscarMedicoPorTelefone(String telefone) {
        
        List<Medico> listaMedicos = this.medicoService.buscarMedicoPorTelefone(telefone);
        
        if (listaMedicos.isEmpty()) {
            System.out.printf("Não há médicos cadastrados no sistema com o telefone '%s'!\n", telefone);
            
        } else {
            for (Medico medico : listaMedicos) {
                System.out.println(medico.toString());
            }
        }
        
    }
    
    public void buscarMedicoPorEspecialidade(int idEspecialidade) {
        
        List<Medico> listaMedicos = this.medicoService.buscarMedicoPorEspecialidade(idEspecialidade);
        
        if (listaMedicos.isEmpty()) {
            System.out.printf("Não há médicos cadastrados no sistema com a especialidade '%s'!\n", new EspecialidadeServiceImpl( DAOFactory.criarEspecialidadeDAO() ).buscarEspecialidadePorId(idEspecialidade).getDescricao());
            
        } else {
            for (Medico medico : listaMedicos) {
                System.out.println(medico.toString());
            }
        }
        
    }
    
}
