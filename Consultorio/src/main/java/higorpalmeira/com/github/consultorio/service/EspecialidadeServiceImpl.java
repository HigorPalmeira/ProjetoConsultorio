/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.higorpalmeira.com.github.consultorio.model.dao.EspecialidadeDAO;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;

/**
 *
 * @author higor
 */
public class EspecialidadeServiceImpl implements IEspecialidadeService {

    private final EspecialidadeDAO especialidadeDAO;
    
    public EspecialidadeServiceImpl(EspecialidadeDAO especialidadeDAO) {
        this.especialidadeDAO = especialidadeDAO;
    }
    
    @Override
    public boolean criarEspecialidade(String descricao) {
        
        if (descricao == null || descricao.trim().isBlank() || descricao.trim().length() > 100) return false;
        
        Especialidade especialidade = new Especialidade();
        especialidade.setDescricao(descricao.trim());
        
        return especialidadeDAO.insert(especialidade) > 0;
    }

    @Override
    public boolean atualizarEspecialidade(int id, String descricao) {
        
        if (id < 0) return false;
        
        if (descricao == null || descricao.trim().isBlank() || descricao.trim().length() > 100) return false;
        
        Especialidade especialidade = new Especialidade();
        especialidade.setId(id);
        especialidade.setDescricao(descricao.trim());
        
        return especialidadeDAO.update(especialidade) > 0;
    }

    @Override
    public boolean deletarEspecialidade(int id) {
        
        if (id < 0) return false;
        
        boolean deletado = false;
        
        try {
            deletado = especialidadeDAO.delete(id) > 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EspecialidadeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return deletado;
    }

    @Override
    public List<Especialidade> listarTodasEspecialidades() {
        
        List<Especialidade> listaEspecialidades = especialidadeDAO.selectAll();
        
        if (listaEspecialidades.isEmpty()) return null;
        
        return listaEspecialidades;
    }

    @Override
    public Especialidade buscarEspecialidadePorId(int id) {
        
        if (id < 0) return null;
        
        return especialidadeDAO.selectId(id);
    }
    
}
