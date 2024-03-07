package br.insper.ecommerce.produto;

import br.insper.ecommerce.cliente.Cliente;

import java.util.ArrayList;

public class ProdutoService {
    ArrayList<Produto> produtos = new ArrayList<>();

    public void cadastraProduto(String nome, Double preco) {
        if (nome.equals("") || preco.equals("")) {
            System.out.println("Dados do produto invalido.");
        } else {
            Produto produto = new Produto(nome, preco);
            produtos.add(produto);
            System.out.println("Produto cadastrado com sucesso.");
        }
    }

    public void listaProdutos() {
        System.out.println("Lista de produtos:");
        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: " + produto.getPreco());
        }
    }

    public void excluiProdutos(String nomeProduto) {
        Produto produtoRemover = null;
        for (Produto produto : produtos) {
            if (nomeProduto.equalsIgnoreCase(produto.getNome())) {
                produtoRemover = produto;
            }
        }
        if (produtoRemover != null) {
            System.out.println("Produto removido com sucesso");
            produtos.remove(produtoRemover);
        } else {

            System.out.println("Produto não encontrado");

        }
    }

    public Produto encontraProduto(String nomeProduto) {
        Produto produtoEncontrado = null;
        for (Produto produto : produtos) {
            if (nomeProduto.equalsIgnoreCase(produto.getNome())) {
                produtoEncontrado = produto;
            }
        }
        return produtoEncontrado;
    }
}