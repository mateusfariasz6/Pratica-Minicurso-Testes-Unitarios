package com.br.praticatestesunitarios.repositorio;

import com.br.praticatestesunitarios.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioUsuario {
    private List<Usuario> usuarios = new ArrayList<>();
    private Long geradorDeIds = 0L ;
    public Usuario salvar(Usuario usuario){
        usuario.setId(++geradorDeIds);
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listarTodos() {
        return usuarios;
    }

    public Optional<Usuario> obterUsuarioPeloId(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public boolean existeUsuarioComCpf(String cpf){
        Optional<Usuario> usuario = usuarios.stream().filter(u -> u.getCpf().equals(cpf)).findFirst();
        return usuario.isPresent();
    }

    public boolean existeUsuarioComCpfComIdDiferente(Long id, String cpf){
        return usuarios.stream().anyMatch(u -> u.getCpf().equals(cpf) && !u.getId().equals(id));
    }

    public Usuario atualizar(Usuario usuario) {
        usuarios.remove(obterUsuarioPeloId(usuario.getId()).get());
        usuarios.add(usuario);
        return usuario;
    }

    public void deletarUsuarioPeloId(Long id) {
        usuarios.remove(usuarios.stream().filter(u -> u.getId().equals(id)).findFirst().get());
    }
}
