/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.higorpalmeira.com.github.consultorio.util.validator;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;

/**
 *
 * @author higor
 */
public class Validator {
    
    private static final String CONFIG_FILE = "/main/resources/config/config.properties";
    
    private static final String EMAIL_REGEX = 
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_REGEX);
    
    private static final LocalDate LIMITE_INFERIOR_DATA_NASCIMENTO = LocalDate.of(1900, 1, 1);
    private static final long ANO_MINIMO = 5;
    
    private static final List<String> UFS_VALIDAS = Arrays.asList(
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", 
            "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", 
            "RR", "SC", "SP", "SE", "TO"
    );
    private static final String REGEX_CRM = "^CRM/([A-Z]{2})\\s(\\d{6})$";
    private static final Pattern PATTERN_CRM = Pattern.compile(REGEX_CRM);

    public static boolean isCpf(final String cpf) {
        if (cpf.trim().isBlank() || (cpf.trim().length() != 14 && cpf.trim().length() != 11)) return false;
        
        String cpfAlter = cpf;
        if (cpf.contains(".") || cpf.contains("-")) {
            cpfAlter = cpf.replace(".", "").replace("-", "");
        }
        
        
        char[] arrCpf = cpfAlter.toCharArray();
        int primeiroDigito = 0;
        int j = 10;
        
        for (int i=0; i<9; i++) {
            primeiroDigito += Character.getNumericValue(arrCpf[i]) * j--;
        }
        primeiroDigito = (primeiroDigito * 10) % 11;
        
        if (primeiroDigito != Character.getNumericValue(arrCpf[9])) return false;
        
        int segundoDigito = 0;
        j = 11;
        for (int i=0; i<10; i++) {
            segundoDigito += Character.getNumericValue(arrCpf[i]) * j--;
        }
        segundoDigito = (segundoDigito * 10) % 11;
        
        if (segundoDigito != Character.getNumericValue(arrCpf[10])) return false;
        
        char charInicial = arrCpf[0];
        for (int i=1; i<arrCpf.length; i++) {
            if (charInicial != arrCpf[i]) return true;
        }
        
        return false;
    }

    public static boolean isEmail(final String email) {
        
        if (email == null || email.length() > 255) return false;
        
        Matcher matcher = PATTERN_EMAIL.matcher(email);
        return matcher.matches();
        
    }
    
    public static boolean isDataNascimento(LocalDate dataNascimento) {
        
        if (dataNascimento == null) return false;
        
        LocalDate hoje = LocalDate.now();
        
        LocalDate limiteSuperior = hoje.minusYears(ANO_MINIMO);
        
        return dataNascimento.isAfter(LIMITE_INFERIOR_DATA_NASCIMENTO) && dataNascimento.isBefore(limiteSuperior);
        
    }
    
    public static boolean isCrm(String crm) {
        
        if (crm == null || crm.trim().isEmpty()) {
            return false;
        }

        Matcher matcher = PATTERN_CRM.matcher(crm.trim());

        if (!matcher.matches()) {
            return false;
        }
        
        String uf = matcher.group(1);
        String numero = matcher.group(2);
        
        try {
            Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            return false;
        }
        
        if (!UFS_VALIDAS.contains(uf)) {
            return false;
        }
        
        return true;
        
    }
    
    public static boolean isDataHoraConsulta(LocalDateTime dataHora) {
        
        int horarioInicio, horarioFim;
        Properties props = new Properties();
        
        try(InputStream input = Validator.class.getResourceAsStream(CONFIG_FILE)) {
            
            if (input == null) {
                System.err.println("Erro: Arquivo de configuração " + CONFIG_FILE + " não encontrado.");
                return false;
            }
            
            props.load(input);
            
            horarioInicio = Integer.parseInt( props.getProperty("config.horario-inicio") );
            horarioFim = Integer.parseInt( props.getProperty("config.horario-final") );
            
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de configuração: " + e.getMessage());
            return false;
        }
        
        return !(dataHora.getHour() < horarioInicio || dataHora.getHour() > horarioFim);
    }
    
}
