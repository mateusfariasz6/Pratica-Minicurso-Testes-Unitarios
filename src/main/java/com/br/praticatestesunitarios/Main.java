package com.br.praticatestesunitarios;

import com.br.praticatestesunitarios.modelo.Usuario;
import com.br.praticatestesunitarios.modelo.dto.UsuarioDto;
import com.br.praticatestesunitarios.repositorio.RepositorioUsuario;
import com.br.praticatestesunitarios.servico.ServicoUsuario;
import com.br.praticatestesunitarios.util.AtributoInvalidoExcecao;
import com.br.praticatestesunitarios.util.ExcecaoConflitoDeDados;
import com.br.praticatestesunitarios.util.RecursoNaoEncontradoExcecao;

import java.util.List;
import java.util.Scanner;

public class Main {
    static String linhaDelimitadora = "\n---------------------------------------------------------------------------------------------------------\n";

    public static void main(String[] args) {
        ServicoUsuario servicoUsuario = new ServicoUsuario(new RepositorioUsuario());
        Scanner sc = new Scanner(System.in);
        boolean controle = true;

        while (controle) {

            System.out.print("Escolha uma opção:\n1 - Salvar Usuário\n2 - Buscar Usuário Pelo Id\n3 - Listar Todos Os Usuários\n4 - Atualizar Usuário Pelo Id\n5 - Deletar Usuário Pelo Id\n6 - Encerrar o Programa\n--> ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> salvarUsuario(sc, servicoUsuario);
                case 2 -> buscarUsuarioPeloId(sc, servicoUsuario);
                case 3 -> listarTodosOsUsuarios(servicoUsuario);
                case 4 -> atualizarUsuario(sc, servicoUsuario);
                case 5 -> deletarUsuario(sc, servicoUsuario);
                case 6 -> controle = false;
                default -> {
                    System.out.println(linhaDelimitadora);
                    System.out.println("Nenhuma opção válida foi escolhida!");
                    System.out.println(linhaDelimitadora);

                }
            }

        }

        sc.close();
    }

    private static void deletarUsuario(Scanner sc, ServicoUsuario servicoUsuario) {
        System.out.print(linhaDelimitadora);
        System.out.print("Informe o id do usuário que você quer deletar: ");
        Long id = sc.nextLong();
        try {
            servicoUsuario.deletarUsuarioPeloId(id);
        } catch (Exception e) {
            if (e instanceof RecursoNaoEncontradoExcecao) {
                System.out.print(linhaDelimitadora);
                System.out.print(e.getMessage());
                System.out.print(linhaDelimitadora);
                return;
            } else {
                System.out.print(linhaDelimitadora);
                System.out.print(e.getMessage());
                System.out.print(linhaDelimitadora);
                return;
            }
        }
        System.out.println("Usuário Deletado Com Sucesso!");

        System.out.println(linhaDelimitadora);
    }

    private static void atualizarUsuario(Scanner sc, ServicoUsuario servicoUsuario) {
        System.out.print(linhaDelimitadora);

        sc.nextLine();
        System.out.print("Informe o id do usuário que você quer buscar: ");
        Long id = sc.nextLong();
        sc.nextLine();

        System.out.print("\nDigite o novo nome do usuário: ");
        String nome = sc.nextLine();


        System.out.print("Digite novo o CPF do usuário: ");
        String cpf = sc.nextLine();
        Usuario usuario = new Usuario();
        try {
            usuario = servicoUsuario.atualizarUsuarioPeloId(id, new UsuarioDto(nome, cpf));
        } catch (Exception e) {
            if (e instanceof AtributoInvalidoExcecao) {
                System.out.print(linhaDelimitadora);
                System.out.print(e.getMessage());
                System.out.print(linhaDelimitadora);
                return;
            } else if (e instanceof ExcecaoConflitoDeDados) {
                System.out.print(linhaDelimitadora);
                System.out.print(e.getMessage());
                System.out.print(linhaDelimitadora);
                return;
            } else if (e instanceof RecursoNaoEncontradoExcecao) {
                System.out.print(linhaDelimitadora);
                System.out.print(e.getMessage());
                System.out.print(linhaDelimitadora);
                return;
            } else {
                System.out.print(linhaDelimitadora);
                System.out.print(e.getMessage());
                System.out.print(linhaDelimitadora);
                return;
            }
        }

        System.out.print("\n" + usuario);

        System.out.print(linhaDelimitadora);

    }

    private static void listarTodosOsUsuarios(ServicoUsuario servicoUsuario) {
        System.out.print(linhaDelimitadora);

        List<Usuario> usuarios = servicoUsuario.listarTodos();

        System.out.println("Usuários Cadastrados:");
        if (usuarios.isEmpty()) {
            System.out.println("Nunhum Usuário Cadastrado!");
        }
        for (Usuario usuario : usuarios) {
            System.out.print("\n");
            System.out.print(usuario);
        }
        System.out.print(linhaDelimitadora);

    }

    private static void buscarUsuarioPeloId(Scanner sc, ServicoUsuario servicoUsuario) {
        System.out.print(linhaDelimitadora);

        System.out.print("Informe o id do usuário que você quer buscar: ");
        Long id = sc.nextLong();

        Usuario usuario = servicoUsuario.obterUmUsuarioPeloId(id);
        System.out.println("\n" + usuario);

        System.out.print(linhaDelimitadora);

    }

    private static void salvarUsuario(Scanner sc, ServicoUsuario servicoUsuario) {
        System.out.print(linhaDelimitadora);

        sc.nextLine();
        System.out.print("Digite o nome do usuário: ");
        String nome = sc.nextLine();

        System.out.print("Digite o CPF do usuário: ");
        String cpf = sc.nextLine();
        Usuario usuario = new Usuario();

        try {
            usuario = servicoUsuario.salvar(new UsuarioDto(nome, cpf));
        } catch (Exception e) {
            if (e instanceof AtributoInvalidoExcecao) {
                System.out.print(linhaDelimitadora);
                System.out.print(e.getMessage());
                System.out.print(linhaDelimitadora);
                return;
            } else if (e instanceof ExcecaoConflitoDeDados) {
                System.out.print(linhaDelimitadora);
                System.out.print(e.getMessage());
                System.out.print(linhaDelimitadora);
                return;
            } else {
                System.out.print(linhaDelimitadora);
                System.out.print(e.getMessage());
                System.out.print(linhaDelimitadora);
                return;
            }

        }

        System.out.print("\nUsuário Salvo Com Sucesso!\n" + usuario);

        System.out.print(linhaDelimitadora);

    }
}