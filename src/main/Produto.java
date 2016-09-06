package main;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by renan on 22/08/16.
 */
public class Produto {

    private final SimpleStringProperty produto = new SimpleStringProperty("");

    private final SimpleDoubleProperty preco = new SimpleDoubleProperty(0);

    public Produto() {

    }
    public Produto(String produto, double preco) {
        setProduto(produto);
        setPreco(preco);
    }

    public String getProduto() {
        return produto.get();
    }

    public void setProduto(String produto) {
        this.produto.set(produto);
    }

    public double getPreco() {
        return preco.get();
    }

    public void setPreco(double preco) {
        this.preco.set(preco);
    }
}
