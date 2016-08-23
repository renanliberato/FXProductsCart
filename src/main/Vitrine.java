package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renan on 22/08/16.
 */
public class Vitrine {

    private static List<Produto> produtos = new ArrayList<Produto>();

    public void addProdutos(Produto... ps) {
        for (Produto p : ps) {
            produtos.add(p);
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
