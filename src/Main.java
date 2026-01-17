import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UsuarioService service = new UsuarioService();

        boolean executando = true;

        while (executando) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Remover usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {

                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        if (service.cadastrarUsuario(nome, email)) {
                            System.out.println("Usuário cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro: nome ou email inválidos.");
                        }
                        break;

                    case 2:
                        List<Usuario> usuarios = service.listarUsuarios();

                        if (usuarios.isEmpty()) {
                            System.out.println("Nenhum usuário cadastrado.");
                        } else {
                            for (Usuario u : usuarios) {
                                System.out.println(
                                        "ID: " + u.getId() +
                                                " | Nome: " + u.getNome() +
                                                " | Email: " + u.getEmail()
                                );
                            }
                        }
                        break;

                    case 3:
                        System.out.print("ID do usuário: ");
                        int idAtualizar = Integer.parseInt(scanner.nextLine());

                        Usuario usuario = service.buscarPorId(idAtualizar);
                        if (usuario == null) {
                            System.out.println("Usuário não encontrado.");
                            break;
                        }

                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();

                        System.out.print("Novo email: ");
                        String novoEmail = scanner.nextLine();

                        if (service.atualizarUsuario(idAtualizar, novoNome, novoEmail)) {
                            System.out.println("Usuário atualizado com sucesso!");
                        } else {
                            System.out.println("Erro: dados inválidos.");
                        }
                        break;

                    case 4:
                        System.out.print("ID do usuário: ");
                        int idRemover = Integer.parseInt(scanner.nextLine());

                        if (service.removerUsuario(idRemover)) {
                            System.out.println("Usuário removido com sucesso!");
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        break;

                    case 0:
                        executando = false;
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: digite apenas números.");
            } catch (Exception e) {
                System.out.println("Erro inesperado. Tente novamente.");
            }
        }

        scanner.close();
    }
}
