/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.higorpalmeira.com.github.consultorio.model.dao.EnderecoDAO;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Endereco;

/**
 *
 * @author higor
 */
public class EnderecoServiceImpl implements IEnderecoService {
    
    private final EnderecoDAO enderecoDAO;
    
    public EnderecoServiceImpl(EnderecoDAO enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    @Override
    public boolean criarEndereco(String rua, String numero, String bairro, String cidade, String estado, String cep) {
        
        if (rua.isBlank() || bairro.isBlank() || cidade.isBlank() || estado.isBlank() || cep.isBlank()) {
            return false;
        }
        
        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        
        return enderecoDAO.insert(endereco) > 0;
        
    }

    @Override
    public boolean atualizarEndereco(int id, String rua, String numero, String bairro, String cidade, String estado, String cep) {
        
        if (id < 0 || rua.isBlank() || bairro.isBlank() || cidade.isBlank() || estado.isBlank() || cep.isBlank()) {
            return false;
        }
        
        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        endereco.setId(id);
        
        return enderecoDAO.update(endereco) > 0;
        
    }

    @Override
    public boolean deletarEndereco(int id) {
        
        if (id < 0) return false;
        
        boolean deletado = false;
        
        try {
            deletado = enderecoDAO.delete(id) > 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return deletado;
        
    }

    @Override
    public List<Endereco> listarTodosEnderecos() {
        
        List<Endereco> listaEnderecos = enderecoDAO.selectAll();
        
        return listaEnderecos;
        
    }

    @Override
    public Endereco buscarEnderecoPorId(int id) {
        
        if (id < 0) return null;
        
        return enderecoDAO.selectById(id);
        
    }
    
}
