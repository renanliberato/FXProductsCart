package main;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
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

    private TableColumn<Produto, Produto> columnEdit;

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
        pane.setPrefSize(650, 600);
        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, blue 0%, silver 100%);");

        btExcluirItem     = new Button("Excluir Item");
        btVoltarVitrine   = new Button("Voltar à Vitrine");
        btConfirmarCompra = new Button("Confirmar Compra");

        tbCarrinho = new TableView<>();
        tbCarrinho.setPrefSize(600, 400);

        columnProduto = new TableColumn<>();
        columnProduto.setText("Descrição");
        columnProduto.setPrefWidth(400);
        columnProduto.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("produto"));

        columnPreco = new TableColumn<>();
        columnPreco.setText("Preço");
        columnPreco.setPrefWidth(200);
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
        btExcluirItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tbCarrinho.getSelectionModel().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Clique sobre um produto primeiro",
                            "Ops",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                VitrineApp.getCarrinho().removeProduto(new Produto(
                        tbCarrinho.getSelectionModel().getSelectedItem().getProduto(),
                        tbCarrinho.getSelectionModel().getSelectedItem().getPreco()));

                tbCarrinho.getItems().remove(
                        tbCarrinho.getSelectionModel().getSelectedItem()
                );
            }
        });
    }

    private void initLayout() {
        tbCarrinho.setLayoutX((pane.getWidth() - tbCarrinho.getWidth()) / 2);
        tbCarrinho.setLayoutY(10);

        btVoltarVitrine.setLayoutX(25);
        btVoltarVitrine.setLayoutY(420);

        btExcluirItem.setLayoutX(pane.getWidth() - btExcluirItem.getWidth() - 25);
        btExcluirItem.setLayoutY(420 + btConfirmarCompra.getHeight() + 10);

        btConfirmarCompra.setLayoutX(pane.getWidth() - btConfirmarCompra.getWidth() - 25);
        btConfirmarCompra.setLayoutY(420);

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
