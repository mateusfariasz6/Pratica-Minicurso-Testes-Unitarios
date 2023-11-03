package com.br.praticatestesunitarios.util;

public class ExcecaoConflitoDeDados extends RuntimeException{

    public ExcecaoConflitoDeDados(String mensagem){
        super(mensagem);
    }
}
