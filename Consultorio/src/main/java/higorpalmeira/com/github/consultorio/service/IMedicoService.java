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
    
    boolean criarMedico();
    
    boolean atualizarMedico();
    
    boolean deletarMedico();
    
    List<Medico> listarTodosMedicos();
    
    Medico buscarMedicoPorId();
    
}
