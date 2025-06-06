/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.controller;

import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;
import main.java.higorpalmeira.com.github.consultorio.service.IEspecialidadeService;

/**
 *
 * @author higor
 */
public class EspecialidadeController {
    
    private final IEspecialidadeService especialidadeService;
    
    public EspecialidadeController(IEspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }
    
    public void criarEspecialidade(String descricao) {
        
        if (this.especialidadeService.criarEspecialidade(descricao)) {
            System.out.println("Especialidade criada com sucesso!");
            
        } else {
            System.out.println("Especialidade não pode ser criada!");
        }
        
    }
    
    public void atualizarEspecialidade(int id, String descricao) {
        
        if (this.especialidadeService.atualizarEspecialidade(id, descricao)) {
            System.out.println("Especialidade atualizada com sucesso!");
            
        } else {
            System.out.println("Especialidade não pode ser atualizada!");
        }
        
    }
    
    public void deletarEspecialidade(int id) {
        
        if (this.especialidadeService.deletarEspecialidade(id)) {
            System.out.println("Especialidade deletada com sucesso!");
            
        } else {
            System.out.println("Especialidade não pode ser deletada!");
        }
        
    }
    
    public void listarTodasEspecialidades() {
        
        List<Especialidade> listaEspecialidades = this.especialidadeService.listarTodasEspecialidades();
        
        if (listaEspecialidades.isEmpty()) {
            System.out.println("Não há especialidades cadastradas no sistema!");
            
        } else {
            for (Especialidade especialidade : listaEspecialidades) {
                System.out.println(especialidade.toString());
            }
            
        }
        
    }
    
    public void buscarEspecialidadePorId(int id) {
        
        Especialidade especialidade = this.especialidadeService.buscarEspecialidadePorId(id);
        
        if (especialidade == null || (especialidade.getId() == 0 && especialidade.getDescricao() == null) ) {
            System.out.println("Especialidade não encontrada no sistema!");
            
        } else {
            System.out.println(especialidade.toString());
            
        }
    }
    
}
