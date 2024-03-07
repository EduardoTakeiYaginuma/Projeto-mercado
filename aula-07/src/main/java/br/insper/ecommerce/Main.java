package br.insper.ecommerce;

import br.insper.ecommerce.cliente.Cliente;
import br.insper.ecommerce.cliente.ClienteService;
import br.insper.ecommerce.compra.Compra;
import br.insper.ecommerce.compra.CompraService;
import br.insper.ecommerce.compra.Item;
import br.insper.ecommerce.pagamento.*;
import br.insper.ecommerce.produto.Produto;
import br.insper.ecommerce.produto.ProdutoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Cliente> clientes = new ArrayList<>();
        String opcao = "0";

        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        CompraService compraService = new CompraService();

        while (!opcao.equalsIgnoreCase("8")) {

            System.out.println("""
                    1 - Cadastrar Cliente
                    2 - Listar Clientes
                    3 - Excluir Cliente
                    4 - Cadastrar Produto
                    5 - Listar Produtos
                    6 - Excluir Produto
                    7 - Cadastrar Compra
                    8 - Sair
                    """);
            opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("1")) {
                System.out.println("Digite o nome do cliente:");
                String nome = scanner.nextLine();
                System.out.println("Digite o CPF do cliente;");
                String cpf = scanner.nextLine();
                ArrayList<Item> lista = new ArrayList<>();
                clienteService.cadastrarCliente(nome, cpf, null, lista);
            }

            if (opcao.equalsIgnoreCase("2")) {
                clienteService.listarCLientes();
            }


            if (opcao.equalsIgnoreCase("3")) {
                System.out.println("Digite o cpf do cliente para deletar:");
                String cpf = scanner.nextLine();
                clienteService.excluiClientes(cpf);
            }
            if (opcao.equalsIgnoreCase("4")) {
                System.out.println("Digite o nome do produto:");
                String nome = scanner.nextLine();
                System.out.println("Digite o preço do produto;");
                double preco = scanner.nextDouble();
                produtoService.cadastraProduto(nome, preco);
            }

            if (opcao.equalsIgnoreCase("5")) {
                produtoService.listaProdutos();
            }

            if (opcao.equalsIgnoreCase("6")) {
                System.out.println("Digite o nome do produto a deletar:");
                String nomeProduto = scanner.nextLine();
                produtoService.excluiProdutos(nomeProduto);
            }

            if (opcao.equalsIgnoreCase("7")) {
                String opcaoCompra = "0";

                Cliente cliente = null;
                Boolean flag = true;
                while (flag) {
                    System.out.println("Digite o cpf do cliente ou -1 para voltar:");
                    String cpf = scanner.nextLine();
                    if (cpf.equals("-1")){
                        flag = false;
                        opcaoCompra = "4";
                    }
                    cliente = clienteService.encontraCliente(cpf);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado");
                    }
                    else{
                        flag = false;
                    }
                }

                LocalDateTime data = LocalDateTime.now();
                ArrayList<Item> listaItens = new ArrayList<>();

                while (!opcaoCompra.equalsIgnoreCase("4")) {
                    System.out.println(("Olá " + cliente.getNome()));
                    System.out.println("""
                            Escolha uma opcção
                            1 - Finalizar Compra
                            2 - Criar ou adicionar itens ao Carrinho de Compra
                            3 - Listar Compras
                            4 - Sair
                            Obs: Se você sair, sua lista de compras será perdida
                            """);
                    opcaoCompra = scanner.nextLine();
                    if (opcaoCompra.equalsIgnoreCase("1")) {

                        MeioPagamento meioPagamento = new Pix(false, null, null, null);
                        String opcaoPagamento = "0";

                        while (!opcaoPagamento.equalsIgnoreCase("5")) {

                            System.out.println("""
                                    Selecione o meio de pagamento
                                    1 - Boleto
                                    2 - Cartao de Credito
                                    3 - Cartao de Debito
                                    4 - Pix
                                    5 - Sair
                                    """);
                            opcaoPagamento = scanner.nextLine();
                            if (opcaoPagamento.equalsIgnoreCase("1")) {
                                meioPagamento = new Boleto(true, LocalDateTime.now(), "codigo");
                                compraService.cadastraCompra(data, cliente, meioPagamento, listaItens);
                            }
                            if (opcaoPagamento.equalsIgnoreCase("2")) {
                                System.out.println("Numero do cartão:");
                                String numeroCartao = scanner.nextLine();
                                System.out.println("Bandeira do cartão:");
                                String bandeiraCartao = scanner.nextLine();
                                meioPagamento = new CartaoCredito(true, LocalDateTime.now(), numeroCartao, bandeiraCartao);
                                compraService.cadastraCompra(data, cliente, meioPagamento, listaItens);
                            }
                            if (opcaoPagamento.equalsIgnoreCase("3")) {
                                System.out.println("Numero do cartão:");
                                String numeroCartao = scanner.nextLine();
                                System.out.println("Bandeira do cartão:");
                                String bandeiraCartao = scanner.nextLine();
                                meioPagamento = new CartaoDebito(true, LocalDateTime.now(), numeroCartao, bandeiraCartao);
                                compraService.cadastraCompra(data, cliente, meioPagamento, listaItens);
                            }
                            if (opcaoPagamento.equalsIgnoreCase("4")) {
                                System.out.println("Chave origem:");
                                String chaveOrigem = scanner.nextLine();
                                System.out.println("Qrcode:");
                                String Qrcode = scanner.nextLine();
                                meioPagamento = new Pix(true, LocalDateTime.now(), chaveOrigem, Qrcode);
                                compraService.cadastraCompra(data, cliente, meioPagamento, listaItens);
                            }

                        }
                    }
                    if (opcaoCompra.equalsIgnoreCase("2")) {
                        String opcaoCarrinho = "0";

                        while (!opcaoCarrinho.equalsIgnoreCase("2")) {
                            System.out.println("""
                                    Selecione uma opção
                                    1 - Adicionar Produto
                                    2 - Voltar
                                    """);
                            opcaoCarrinho = scanner.nextLine();
                            if (opcaoCarrinho.equalsIgnoreCase("1")) {
                                System.out.println("Nome do produto:");
                                String nomeProduto = scanner.nextLine();
                                Produto produto = produtoService.encontraProduto(nomeProduto);
                                System.out.println("Quantidade:");
                                int quantidade = scanner.nextInt();
                                Item novoItem = new Item();
                                novoItem.setProduto(produto);
                                novoItem.setQuantidade(quantidade);
                                listaItens.add(novoItem);;
                            }
                        }


                    }
                    if (opcaoCompra.equalsIgnoreCase("3")){
                        System.out.println("1");
                        compraService.listarCompras(listaItens);

                    }


                }


            }


        }
    }
}