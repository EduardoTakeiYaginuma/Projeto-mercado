package br.insper.ecommerce.cliente;

import br.insper.ecommerce.compra.Item;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    private ArrayList<Item> listaCompras;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, LocalDate dataNascimento, ArrayList<Item> listaCompras) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.listaCompras = listaCompras;
    }

    public ArrayList<Item> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(ArrayList<Item> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
