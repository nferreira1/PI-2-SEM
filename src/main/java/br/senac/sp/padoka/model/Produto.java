/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.sp.padoka.model;

/**
 *
 * @author Nathan Ferreira
 */
public class Produto {

    private int id;
    private String nome;
    private int categoria_id;
    private String nomeCategoria;
    private String unidade_de_medida;
    private int estoque;
    private double valor;

    public Produto(String nome, int categoria_id, String nomeCategoria, String unidade_de_medida, int estoque, double valor) {
        this.nome = nome;
        this.categoria_id = categoria_id;
        this.nomeCategoria = nomeCategoria;
        this.unidade_de_medida = unidade_de_medida;
        this.estoque = estoque;
        this.valor = valor;
    }

    public Produto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getCategoria() {
        return categoria_id;
    }

    public void setCategoria(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getUnidade_de_medida() {
        return unidade_de_medida;
    }

    public void setUnidade_de_medida(String unidade_de_medida) {
        this.unidade_de_medida = unidade_de_medida;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
