package com.br.praticatestesunitarios.servico;

import com.br.praticatestesunitarios.modelo.Usuario;
import com.br.praticatestesunitarios.modelo.dto.UsuarioDto;
import com.br.praticatestesunitarios.repositorio.RepositorioUsuario;
import com.br.praticatestesunitarios.util.AtributoInvalidoExcecao;
import com.br.praticatestesunitarios.util.ExcecaoConflitoDeDados;
import com.br.praticatestesunitarios.util.RecursoNaoEncontradoExcecao;

import java.util.List;
import java.util.Optional;

public class ServicoUsuario {
    private final RepositorioUsuario repositorioUsuario;

    public ServicoUsuario(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }
    public Usuario salvar(UsuarioDto usuarioDto){
        if (usuarioDto.nome() == null || usuarioDto.nome().isBlank()){
            throw new AtributoInvalidoExcecao("O atributo nome não pode ser nulo ou vazio!");
        }

        if (repositorioUsuario.existeUsuarioComCpf(usuarioDto.cpf())){
            throw new ExcecaoConflitoDeDados("Já existe um usuário com esse cpf!");
        }

        Usuario usuarioParaSalvar = new Usuario();
        usuarioParaSalvar.setNome(usuarioDto.nome());
        usuarioParaSalvar.setCpf(usuarioDto.cpf());

        return repositorioUsuario.salvar(usuarioParaSalvar);
    }

    public List<Usuario> listarTodos(){
        return repositorioUsuario.listarTodos();
    }

    public Usuario obterUmUsuarioPeloId(Long id){
        Optional<Usuario> usuario = repositorioUsuario.obterUsuarioPeloId(id);
        return usuario.get();
    }

    public Usuario atualizarUsuarioPeloId(Long id, UsuarioDto usuarioDto){
        if (id == null){
            throw new AtributoInvalidoExcecao("O atributo (id) não pode ser nulo!");
        }

        if (usuarioDto.nome() == null || usuarioDto.nome().isBlank() || usuarioDto.cpf() == null || usuarioDto.cpf().isBlank()){
            throw new AtributoInvalidoExcecao("Os atributos {id} e {nome} não podem ser nulos ou vazios!");
        }

        if (repositorioUsuario.existeUsuarioComCpfComIdDiferente(id, usuarioDto.cpf())){
            throw new ExcecaoConflitoDeDados("Já existe um usuário com esse cpf!");
        }

        Optional<Usuario> usuario = repositorioUsuario.obterUsuarioPeloId(id);

        if (usuario.isEmpty()){
            throw new RecursoNaoEncontradoExcecao("Nenhum usuário encontrado com esse id!");
        }
        usuario.get().setNome(usuarioDto.nome());
        usuario.get().setCpf(usuarioDto.cpf());
        return repositorioUsuario.atualizar(usuario.get());
    }

    public void deletarUsuarioPeloId(Long id){

        if (repositorioUsuario.obterUsuarioPeloId(id).isEmpty()){
            throw new RecursoNaoEncontradoExcecao("Nenhum usuário encontrado com esse id!");
        }

        repositorioUsuario.deletarUsuarioPeloId(id);
    }
}
