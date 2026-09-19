import Functions.Produto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;


public class Estoque {
    private static final Map<Integer, BiConsumer<ArrayList<Produto>, Scanner>> comandosPorNumero = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<Produto> produtos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 3) {

            System.out.println("===== ESTOQUE ======");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Sair");
            System.out.println("4 - Buscar produto");
            System.out.println("5 - Excluir Produto");
            System.out.println("6 - Alterar quantidade");
            System.out.println("7 - Alterar Validade");
            System.out.println("8 - Alterar nome");

            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            comandosPorNumero.put(1, (x,y) -> {
                Produto produto = cadastrarProduto(scanner);
                produtos.add(produto);
            });

            comandosPorNumero.put(2, (x,y) -> {
                if (produtos.isEmpty()) {
                    System.out.println("Nenhum produto cadastrado");
                } else {
                    listarProdutos(produtos);
                }
            });

            comandosPorNumero.put(3, (ignore,ignore2) -> {
                System.out.println("Saindo..");
            });

            comandosPorNumero.put(4, Estoque::buscarProduto);
            comandosPorNumero.put(5, Estoque::excluirProduto);
            comandosPorNumero.put(6, Estoque::alterarQuantidade);
            comandosPorNumero.put(7, Estoque::alterarValidade);
            comandosPorNumero.put(8, Estoque::alterarNomeProduto);

            comandosPorNumero.getOrDefault(opcao, (ignore, ignore2) -> {
                System.out.println("Opção invalida");
            }).accept(produtos, scanner);
        }

    }

    public static Produto cadastrarProduto(Scanner scanner) {
        Produto produto = new Produto();


        System.out.println("Digite o nome do produto: ");
        produto.setNomeProduto(scanner.nextLine());

        System.out.println("Digite o total do produto: ");
        int quantidade = scanner.nextInt();

        while (quantidade < 0) {
            System.out.println("Número Invalido");
            quantidade = scanner.nextInt();
        }

        produto.setQuantidade(quantidade);
        scanner.nextLine();

        System.out.println("Digite a validade do produto: ");
        produto.setValidade(scanner.nextLine());
        System.out.println("====PRODUTO CADASTADO====");

        return produto;
    }

    public static void listarProdutos(ArrayList<Produto> produtos) {

        for (Produto produto : produtos) {
            System.out.println("Produto: " + produto.getNomeProduto());
            System.out.println("Quantidade: " + produto.getQuantidade());
            System.out.println("Validade: " + produto.getValidade());
        }
    }

    public static void buscarProduto(ArrayList<Produto> produtos, Scanner scanner) {
        System.out.println("Digite o produto que deseja buscar: ");
        String buscaProduto = scanner.nextLine();
        boolean encontrado = false;

        for (Produto produto : produtos) {
            if (produto.getNomeProduto().equals(buscaProduto)) {
                encontrado = true;
                System.out.println("Produto encontrado!");
                System.out.println("Produto: " + produto.getNomeProduto());
                System.out.println("Quantidade: " + produto.getQuantidade());
                System.out.println("Validade: " + produto.getValidade());
            }

        }
        if (!encontrado)
            System.out.println("Produto não encontrado");
    }

    public static void excluirProduto(ArrayList<Produto> produtos, Scanner scanner) {
        System.out.println("Digite o produto que deseja excluir: ");
        String buscaProduto = scanner.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < produtos.size(); i++) {

            Produto produto = produtos.get(i);

            if (produto.getNomeProduto().equals(buscaProduto)) {
                encontrado = true;
                produtos.remove(i);
                i--;
            }
        }

        if (encontrado) {
            System.out.println("Produto excluído!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    public static void alterarQuantidade(ArrayList<Produto> produtos, Scanner scanner) {

        System.out.println("Digite o produto que deseja alterar a quantidade: ");
        String buscaProduto = scanner.nextLine();
        boolean encontrado = false;

        for (Produto produto : produtos) {

            if (produto.getNomeProduto().equals(buscaProduto)) {
                encontrado = true;
                System.out.println("A quantidade atual é de: " + produto.getQuantidade());
                System.out.println("Digite a nova quantidade: ");
                int novaQuantidade = scanner.nextInt();

                while (novaQuantidade < 0) {
                    System.out.println("Número Invalido");
                    novaQuantidade = scanner.nextInt();
                }

                produto.setQuantidade(novaQuantidade);
                System.out.println("A nova quantidade é de: " + produto.getQuantidade());
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Produto não encontrado!");
        }

    }

    public static void alterarValidade(ArrayList<Produto> produtos, Scanner scanner) {

        System.out.println("Qual produto deseja alterar a validade? ");
        String buscaProduto = scanner.nextLine();
        boolean encontrado = false;

        for (Produto produto : produtos) {

            if (produto.getNomeProduto().equals(buscaProduto)) {
                encontrado = true;
                System.out.println("A validade atual é: " + produto.getValidade());
                System.out.println("Digite a nova validade: ");
                String novaValidade = scanner.nextLine();
                produto.setValidade(novaValidade);
                System.out.println("A nova validade é: " + produto.getValidade());
            }

        }
        if (!encontrado) {
            System.out.println("Produto não encontrado");
        }
    }

    public static void alterarNomeProduto(ArrayList<Produto> produtos, Scanner scanner) {
        System.out.println("Qual produto deseja alterar o nome? ");
        String buscaProduto = scanner.nextLine();
        boolean encontrado = false;

        for (Produto produto : produtos) {

            if (produto.getNomeProduto().equals(buscaProduto)) {
                encontrado = true;
                System.out.println("O nome atual cadastrado é: " + produto.getNomeProduto());
                System.out.println("Digite o novo nome: ");
                String novoNome = scanner.nextLine();
                produto.setNomeProduto(novoNome);
                System.out.println("O nome foi alterado para: " + produto.getNomeProduto());
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado");
        }

    }

    public static Produto buscarProduto(ArrayList<Produto> produtos, String nome) {


        for (Produto produto : produtos) {

            if (produto.getNomeProduto().equals(nome)) {
                return produto;
            }
        }
            return null;

    }
}


