package main;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionListener;

/**
 * Created by renan on 22/08/16.
 */
public class VitrineApp extends Application {

    private AnchorPane pane;

    private TextField txPesquisa;

    private TableView<ItensProperty> tbVitrine;

    private TableColumn<ItensProperty, String> columnProduto;

    private TableColumn<ItensProperty, Double> columnPreco;

    private static ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();

    private static Carrinho carrinho;

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        initComponents();

        initListeners();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Pesquisa");
        stage.show();

        initLayout();
    }


    private void initComponents() {
        pane = new AnchorPane();
        pane.setPrefSize(800, 600);
        pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, blue 0%, silver 100%);");

        txPesquisa = new TextField();
        txPesquisa.setPromptText("Digite o item para pesquisa");

        tbVitrine = new TableView<ItensProperty>();
        tbVitrine.setPrefSize(780, 550);

        columnProduto = new TableColumn<ItensProperty, String>();
        columnProduto.setText("Produto");
        columnProduto.setPrefWidth(600);
        columnProduto.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("produto"));

        columnPreco   = new TableColumn<ItensProperty, Double>();
        columnPreco.setText("Preço");
        columnPreco.setPrefWidth(180);
        columnPreco.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("preco"));

        tbVitrine.getColumns().addAll(columnProduto, columnPreco);

        initItens();

        pane.getChildren().addAll(txPesquisa, tbVitrine);

        carrinho = new Carrinho();
    }

    private void initItens() {
        Vitrine v = new Vitrine();
        v.addProdutos(
                new Produto("Adesivo AngularJS",5.00),
                new Produto("Adesivo Grunt", 4.00),
                new Produto("Macbook Customizado", 10500.00),
                new Produto("Macbook com adesivos 2010", 9000.00),
                new Produto("Adesivo NodeJS", 3.00)
        );

        for (Produto p : v.getProdutos()) {
            listItens.add(new ItensProperty(p.getProduto(), p.getPreco()));
        }

        tbVitrine.setItems(listItens);
    }

    private ObservableList<ItensProperty> findItems() {
        ObservableList<ItensProperty> itensEncontrados = FXCollections.observableArrayList();

        for (ItensProperty itens : listItens) {
            if (itens.getProduto().contains(txPesquisa.getText())) {
                itensEncontrados.add(itens);
            }
        }

        return itensEncontrados;
    }

    private void initListeners() {

        txPesquisa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!txPesquisa.getText().equals("")) {
                    tbVitrine.setItems(findItems());
                } else {
                    tbVitrine.setItems(listItens);
                }
            }
        });

        tbVitrine.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<ItensProperty>() {
                    @Override
                    public void changed(ObservableValue<? extends ItensProperty> observable, ItensProperty oldItem, ItensProperty newItem) {

                        ItemApp.setProduto(new Produto(newItem.getProduto(), newItem.getPreco()));
                        ItemApp.setIndex(tbVitrine.getSelectionModel().getSelectedIndex());

                        try {
                            new ItemApp().start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initLayout() {
        txPesquisa.setLayoutX(pane.getWidth() - txPesquisa.getWidth() - 10);
        txPesquisa.setLayoutY(10);
        tbVitrine.setLayoutX((pane.getWidth() - tbVitrine.getWidth()) / 2);
        tbVitrine.setLayoutY(40);
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
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
