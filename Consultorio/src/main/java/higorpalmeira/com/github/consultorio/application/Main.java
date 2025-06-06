/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.application;

import java.util.Scanner;
import main.java.higorpalmeira.com.github.consultorio.controller.EspecialidadeController;

/**
 *
 * @author higor
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        boolean running = true;
        
        while(running) {
            
            System.out.println("===\tMENU\t===");
            System.out.println("[ 1 ] Criar Especialidade \n[ 2 ] Atualizar Especialidade \n[ 3 ] Excluir Especialidade \n[ 4 ] Listar Especialidades");
            int opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1:
                    criarEspecialidade();
                    break;
                    
                case 2:
                    atualizarEspecialidade();
                    break;
                    
                case 3:
                    excluirEspecialidade();
                    break;
                    
                case 4:
                    listarEspecialidade();
                    break;
                    
                case 9:
                    running = false;
                    break;
                    
                default:
                    System.out.println("Opcao inva'lida!");
                    break;
            }
            
        }
        
    }
    
    private static void criarEspecialidade() {
        String descricao;
        
        System.out.println("===\tCriar Especialidade\t===");
        System.out.println("Informe a descricao (max:100): ");
        descricao = scanner.next();
        
        EspecialidadeController.criarEspecialidade(descricao, null);
        
    }
    
    private static void listarEspecialidade() {
        
        System.out.println("Deseja listar (1) TODAS ou (2) ESPECÍFICA? ");
        if (scanner.nextInt() == 1) {
            EspecialidadeController.listarEspecialidades();
        } else {
            System.out.println("Informe o ID da especialidade para ser listada: ");
            int id = scanner.nextInt();
            EspecialidadeController.listarEspecialidade(id, null);
        }
        
        
        /*
        List<Especialidade> listaEspecialidade = EspecialidadeService.selectAll();
        
        if (listaEspecialidade.isEmpty()) {
            System.out.println("Nao ha' especialidades salvas no sistema!");
        } else {
            
            for (Especialidade especialidade : listaEspecialidade) {
                System.out.println(especialidade.toString());
            }
            
        }
        */
    }
    
    private static void excluirEspecialidade() {
        int id;
        System.out.println("===\tExcluir Especialidade\t===");
        System.out.println("Informe o ID da especialidade: ");
        id = scanner.nextInt();
        
        EspecialidadeController.excluirEspecialidade(id, null);
        
    }
    
    private static void atualizarEspecialidade() {
        int id;
        String descricao;
        System.out.println("===\tAtualizar Especialidade\t===");
        System.out.println("Informe o ID da especialidade: ");
        id = scanner.nextInt();
        
        System.out.println("Informe a nova descricao (max: 100): ");
        descricao = scanner.next();
        
        EspecialidadeController.editarEspecialidade(id, null, descricao);
        
    }
    
}
