package br.insper.ecommerce.compra;

import br.insper.ecommerce.cliente.Cliente;
import br.insper.ecommerce.pagamento.MeioPagamento;
import br.insper.ecommerce.produto.Produto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class CompraService {
    ArrayList<Compra> compras = new ArrayList<>();


    public void cadastraCompra(LocalDateTime data, Cliente cliente, MeioPagamento meioPagamento, ArrayList<Item> listaItens){
        if (data.equals("")  || cliente.equals("") || meioPagamento.equals(("")) || listaItens.isEmpty()) {
            System.out.println("Dados da compra invalido.");
        } else {
            Compra compra = new Compra(data, listaItens, cliente, meioPagamento);
            compra.calculaPrecoTotal();
            double valor = compra.getPrecoTotal();
            compras.add(compra);
            System.out.println("Compra de " +valor+ " reais cadastrada e finalizada com sucesso.");
        }
    }

    public void listarCompras(ArrayList<Item> listaItens) {

        System.out.println("Lista de compras:");


        Map<String, Integer> produtos = new HashMap<>();

        for (Item item : listaItens) {
            String nomeProduto = item.getProduto().getNome();
            int quantidade = item.getQuantidade();

            // Se o produto já estiver no mapa, atualize a quantidade
            if (produtos.containsKey(nomeProduto)) {
                produtos.put(nomeProduto, produtos.get(nomeProduto) + quantidade);
            } else {
                // Se o produto não estiver no mapa, adicione-o
                produtos.put(nomeProduto, quantidade);
            }
        }

// Imprima os produtos e suas quantidades
        for (Map.Entry<String, Integer> entry : produtos.entrySet()) {
            System.out.println("produto: " + entry.getKey());
            System.out.println("quantidade: " + entry.getValue());
        }

        if (listaItens.isEmpty()){
            System.out.println("O cliente não realizou nenhuma compra.");
        }
    }


}



