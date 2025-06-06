/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.service;

import java.util.List;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;

/**
 *
 * @author higor
 */
public interface IEspecialidadeService {
    
    public boolean criarEspecialidade(String descricao);
    
    public boolean atualizarEspecialidade(int id, String descricao);
    
    public boolean deletarEspecialidade(int id);
    
    public List<Especialidade> listarTodasEspecialidades();
    
    public Especialidade buscarEspecialidadePorId(int id);
    
}
