package com.br.praticatestesunitarios.util;

public class RecursoNaoEncontradoExcecao extends RuntimeException{
    public RecursoNaoEncontradoExcecao(String mensagem){
        super(mensagem);
    }
}
