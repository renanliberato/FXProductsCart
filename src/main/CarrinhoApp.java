package main;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by renan on 24/08/16.
 */
public class CarrinhoApp extends Application {

    private AnchorPane pane;

    private TableView<ItensProperty> tbCarrinho;

    private TableColumn<ItensProperty, String> columnProduto;

    private TableColumn<ItensProperty, Double> columnPreco;

    private Button btExcluirItem, btVoltarVitrine,btConfirmarCompra;

    private static ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();;

    public void start(Stage stage) throws Exception {
        initComponents();

        initListeners();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Carrinho");
        stage.setResizable(false);
        stage.show();

        initLayout();
    }

    private void initComponents() {
        pane = new AnchorPane();
        pane.setPrefSize(400, 400);
        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, blue 0%, silver 100%);");

        btExcluirItem     = new Button("Excluir");
        btVoltarVitrine   = new Button("Voltar");
        btConfirmarCompra = new Button("Confirmar Compra");

        tbCarrinho = new TableView<>();
        tbCarrinho.setPrefSize(300, 300);

        columnProduto = new TableColumn<>();
        columnProduto.setText("Descrição");
        columnProduto.setPrefWidth(200);
        columnProduto.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("produto"));

        columnPreco = new TableColumn<>();
        columnPreco.setText("Preço");
        columnPreco.setPrefWidth(100);
        columnPreco.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("preco"));

        tbCarrinho.getColumns().addAll(columnProduto, columnPreco);

        initItems();

        pane.getChildren().addAll(btExcluirItem, btVoltarVitrine, btConfirmarCompra, tbCarrinho);
    }

    private void initItems() {
        listItens.clear();
        for (Produto p : VitrineApp.getCarrinho().getProdutos()) {
            listItens.add(new ItensProperty(p.getProduto(), p.getPreco()));
        }

        tbCarrinho.setItems(listItens);
    }

    private void initListeners() {

    }

    private void initLayout() {

    }

    public class ItensProperty {

        private SimpleStringProperty produto;

        private SimpleDoubleProperty preco;

        public ItensProperty(String produto, double preco) {
            this.produto = new SimpleStringProperty(produto);
            this.preco   = new SimpleDoubleProperty(preco);
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
}
