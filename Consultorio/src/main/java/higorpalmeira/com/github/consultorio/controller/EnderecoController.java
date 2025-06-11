/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.controller;

import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Endereco;
import main.java.higorpalmeira.com.github.consultorio.service.IEnderecoService;

/**
 *
 * @author higor
 */
public class EnderecoController {
    
    public final IEnderecoService enderecoService;
    
    public EnderecoController(IEnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }
    
    public void criarEndereco(String rua, String numero, String bairro, String cidade, String estado, String cep) {
        
        if (this.enderecoService.criarEndereco(rua, numero, bairro, cidade, estado, cep)) {
            System.out.println("Endereço criado com sucesso!");
            
        } else {
            System.out.println("Endereço não pode ser criado!");
            
        }
        
    }
    
    public void atualizarEndereco(int id, String rua, String numero, String bairro, String cidade, String estado, String cep) {
        
        if (this.enderecoService.atualizarEndereco(id, rua, numero, bairro, cidade, estado, cep)) {
            System.out.println("Endereço atualizado com sucesso!");
            
        } else {
            System.out.println("Endereço não pode ser atualizado!");
            
        }
    }
    
    public void deletarEndereco(int id) {
        
        if (this.enderecoService.deletarEndereco(id)) {
            System.out.println("Endereço deletado com sucesso!");
            
        } else {
            System.out.println("Endereço não pode ser deletado!");
            
        }
        
    }
    
    public void listarTodosEnderecos() {
        
        List<Endereco> listaEnderecos = this.enderecoService.listarTodosEnderecos();
        
        if (listaEnderecos.isEmpty()) {
            System.out.println("Não há endereços cadastrados no sistema!");
            
        } else {
            for (Endereco endereco : listaEnderecos) {
                System.out.println(endereco.toString());
            }
        }
        
    }
    
    public void buscarEnderecoPorId(int id) {
        
        Endereco endereco = this.enderecoService.buscarEnderecoPorId(id);
        
        if (endereco == null || (endereco.getId() == 0 && endereco.getCep() == null)) {
            System.out.println("Endereço não pode ser encontrado no sistema!");
        }
        
    }
    
}
