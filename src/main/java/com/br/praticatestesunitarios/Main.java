package com.br.praticatestesunitarios;

import com.br.praticatestesunitarios.modelo.Usuario;
import com.br.praticatestesunitarios.modelo.dto.UsuarioDto;
import com.br.praticatestesunitarios.repositorio.RepositorioUsuario;
import com.br.praticatestesunitarios.servico.ServicoUsuario;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ServicoUsuario servicoUsuario = new ServicoUsuario(new RepositorioUsuario());
        //TODO: Adicionar as dependencias para testes, e ajeitar o buffer.
        Scanner sc = new Scanner(System.in);


        boolean controle = true;

        while (controle){
            System.out.print("Escolha uma opção:\n1 - Salvar Usuário\n2 - Buscar Usuário Pelo Id\n3 - Listar Todos Os Usuários\n4 - Atualizar Usuário Pelo Id\n5 - Deletar Usuário Pelo Id\n6 - Encerrar o Programa\n--> ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> salvarUsuario(servicoUsuario);
                case 2 -> buscarUsuarioPeloId(servicoUsuario);
                case 3 -> listarTodosOsUsuarios(servicoUsuario);
                case 4 -> atualizarUsuario(servicoUsuario);
                case 5 -> deletarUsuario(servicoUsuario);
                case 6 -> controle = false;
                default -> {
                    System.out.println("Nenhuma opção válida foi escolhida!");
                }
            }

        }

        sc.close();
    }

    private static void deletarUsuario(ServicoUsuario servicoUsuario) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nInforme o id do usuário que você quer deletar: ");
        Long id = sc.nextLong();

        servicoUsuario.deletarUsuarioPeloId(id);
        System.out.println("\nUsuário Deletado Com Sucesso!");

        sc.close();
    }

    private static void atualizarUsuario(ServicoUsuario servicoUsuario) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nInforme o id do usuário que você quer buscar: ");
        Long id = sc.nextLong();

        sc.nextLine();

        System.out.print("\nDigite o novo nome do usuário: ");
        String nome = sc.nextLine();


        System.out.print("Digite novo o CPF do usuário: ");
        String cpf = sc.nextLine();

        Usuario usuario = servicoUsuario.atualizarUsuarioPeloId(id, new UsuarioDto(nome, cpf));
        System.out.println("\n" + usuario);

        sc.close();
    }

    private static void listarTodosOsUsuarios(ServicoUsuario servicoUsuario) {
        List<Usuario> usuarios = servicoUsuario.listarTodos();

        System.out.println("\nUsuários Cadastrados:");
        for (Usuario usuario : usuarios){
            System.out.println(usuario);
        }
    }

    private static void buscarUsuarioPeloId(ServicoUsuario servicoUsuario) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nInforme o id do usuário que você quer buscar: ");
        Long id = sc.nextLong();

        Usuario usuario = servicoUsuario.obterUmUsuarioPeloId(id);
        System.out.println("\n"+usuario);
        sc.close();
    }

    private static void salvarUsuario(ServicoUsuario servicoUsuario) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite o nome do usuário: ");
        String nome = sc.nextLine();

        System.out.print("Digite o CPF do usuário: ");
        String cpf = sc.nextLine();

        Usuario usuario = servicoUsuario.salvar(new UsuarioDto(nome, cpf));

        System.out.println("Usuário Salvo Com Sucesso!\n"+usuario);
        sc.close();
    }
}