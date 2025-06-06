/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.controller;

import java.awt.Frame;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.java.higorpalmeira.com.github.consultorio.model.entity.Especialidade;
import main.java.higorpalmeira.com.github.consultorio.service.EspecialidadeService;

/**
 *
 * @author higor
 */
public class EspecialidadeController {
    
    public static void criarEspecialidade(String descricao, JFrame frmOrigem) {
        
        if (frmOrigem != null) {
            if (EspecialidadeService.insert(descricao)) {
                JOptionPane.showMessageDialog(null, "Especialidade criada com sucesso!", "Sucesso", 1);

                if (frmOrigem.getName().equals("frmCadEspecialidade")) {
                    frmOrigem.dispose();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Especialidade não pode ser criada!", "Falha na criação", 2);
            }
            
        } else {
            
            if (EspecialidadeService.insert(descricao)) {
                System.out.println("Especialidade criada com sucesso!");
            } else {
                System.out.println("Especialidade não pode ser criada!");
            }
            
        }
        
        
    }
    
    public static void editarEspecialidade(int id, String currentDescricao, String novaDescricao) {
        
        Frame[] frames = JFrame.getFrames();
        
        if (frames.length > 0) {
            if ( currentDescricao.equals(novaDescricao) ) {
                JOptionPane.showMessageDialog(null, "Para editar a especialidade '" + novaDescricao + "' coloque um nome diferente da atual!", "Edição Inválida!", 2);
                return;
            }

            if (EspecialidadeService.update(id, novaDescricao)) {
                JOptionPane.showMessageDialog(null, "Especialidade editada com sucesso!", "Edição Concluída!", 1);

            }
        } else {
            if ( EspecialidadeService.selectId(id)
                                        .getDescricao().equals(novaDescricao) ) {
                
                System.out.println("\n===\tEdição Inválida!\t===");
                System.out.println("Para editar a especialidade '" + novaDescricao + "' coloque um nome diferente da atual!\n");
                return;
                
            }

            if (EspecialidadeService.update(id, novaDescricao)) {
                System.out.println("\n===\tEdição Concluída!\t===");
                System.out.println("Especialidade editada com sucesso!\n");

            }
        }
        
        
    }
    
    public static void excluirEspecialidade(int id, String descricao) {
        
        Frame[] frames = JFrame.getFrames();
        
        if (frames.length > 0) {
            if ( JOptionPane.showConfirmDialog(null, 
                                            "Tem certeza de que deseja excluir a especialidade '" 
                                                    + descricao + "'?", 
                                            "Tem certeza?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                if (EspecialidadeService.delete(id)) {
                    JOptionPane.showMessageDialog(null, "A especialidade foi excluída com sucesso!", "Exclusão Concluída!", 1);
                }

            }
            
        } else {
            Scanner s = new Scanner(System.in);
            System.out.println("Tem certeza de que deseja excluir a especialidade '" + EspecialidadeService.selectId(id).getDescricao() + "'? [Y/N] " );
            
            if (s.next().trim().toUpperCase().equals("Y")) {
                
                if (EspecialidadeService.delete(id)) {
                    System.out.println("\n===\tExclusão Concluída!\t===");
                    System.out.println("A especialidade foi excluída com sucesso!\n");
                }
                
            } else {
                System.out.println("Exclusão cancelada!\n");
            }
        }
        
    }
    
    public static void listarEspecialidade(int id, JTable tabelaVisualizar) {
        
        Especialidade especialidade = EspecialidadeService.selectId(id);
        
        if (tabelaVisualizar == null) {
            
            if (especialidade.getId() == 0 && especialidade.getDescricao() == null) {
                System.out.printf("A especialidade com ID '%d' não foi encontrada no sistema!\n", id);

            } else {
                System.out.println("===\tEspecialidade\t===");
                System.out.println(especialidade.toString());
            }
            
        } else {
            
            if (especialidade == null) {
                JOptionPane.showMessageDialog(null, 
                        "A especialidade com o ID '" + id + "' não foi encontrada no sistema!", "Não encontrada!", 1);
                
            } else {
                DefaultTableModel dtmEspecialidade = (DefaultTableModel) tabelaVisualizar.getModel();
                dtmEspecialidade.setRowCount(0);
                Object[] especialidadeObj = { especialidade.getId(), especialidade.getDescricao() };
                dtmEspecialidade.addRow(especialidadeObj);
            }
        }
        
        
    }
    
    public static void listarEspecialidades(JTable tabelaVisualizar) {
        
        DefaultTableModel dtmEspecialidades = (DefaultTableModel) tabelaVisualizar.getModel();
        List<Especialidade> listaEspecialidades = EspecialidadeService.selectAll();

        dtmEspecialidades.setRowCount(0);

        for (Especialidade especialidade : listaEspecialidades) {
            Object[] especialidades = { especialidade.getId(), especialidade.getDescricao() };
            dtmEspecialidades.addRow(especialidades);
        }
        
    }
    
    public static void listarEspecialidades() {
        
        List<Especialidade> listaEspecialidades = EspecialidadeService.selectAll();
        
        if (listaEspecialidades.isEmpty()) {
            System.out.println("Não há especialidades cadastradas no sistema!");
            
        } else {
            System.out.println("===\tLista de Especialidades\t===");
            
            for (Especialidade especialidade : listaEspecialidades) {
                System.out.println(especialidade.toString());
            }
            
            System.out.println("===\tFim da Lista\t===");
        }
        
    }
    
}
