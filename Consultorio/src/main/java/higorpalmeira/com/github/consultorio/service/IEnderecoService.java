/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Endereco;

/**
 *
 * @author higor
 */
public interface IEnderecoService {
    
    public boolean criarEndereco(String rua, String numero, String bairro, String cidade, String estado, String cep);
    
    public boolean atualizarEndereco(int id, String rua, String numero, String bairro, String cidade, String estado, String cep);
    
    public boolean deletarEndereco(int id);
    
    public List<Endereco> listarTodosEnderecos();
    
    public Endereco buscarEnderecoPorId(int id);
    
}
