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
        
        menuEspecialidade();
    }
    
    private static void menuEspecialidade() {
        boolean running = true;
        
        while(running) {
            
            System.out.println("===\tMENU\t===");
            System.out.println("[ 1 ] Criar Especialidade \n[ 2 ] Atualizar Especialidade \n[ 3 ] Excluir Especialidade \n[ 4 ] Listar Especialidades");
            int opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1 -> criarEspecialidade();
                    
                case 2 -> atualizarEspecialidade();
                    
                case 3 -> excluirEspecialidade();
                    
                case 4 -> listarEspecialidade();
                    
                case 9 -> running = false;
                    
                default -> System.out.println("Opcao inva'lida!");
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
