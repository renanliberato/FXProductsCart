package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by renan on 23/08/16.
 */
public class ItemApp extends Application {

    private AnchorPane pane;

    private ImageView imgItem;

    private Label lbPreco, lbDescricao;

    private Button btAddCarrinho;

    private static Stage stage;

    private static Produto produto;

    private static int index;

    private static String[] images = {
        "assets/images/angular_sticker.png",
        "assets/images/grunt_sticker.jpg",
        "assets/images/mac_stickeres.jpg",
        "assets/images/mac_up_stickers.jpg",
        "assets/images/node_sticker.png"
    };

    public void start(Stage stage) throws Exception {
        ItemApp.stage = stage;

        initComponents();

        initListeners();
        Scene scene = new Scene(pane);
        stage.setTitle(produto.getProduto());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        initLayout();


    }

    public void initComponents() {

        pane = new AnchorPane();
        pane.setPrefSize(600, 400);
        pane.getStylesheets().add("css/pane.css");
        pane.getStyleClass().add("pane");

        File imgFile = new File(images[index]);
        imgItem      = new ImageView(new Image(imgFile.toURI().toString()));
        imgItem.setFitHeight(200);
        imgItem.setFitWidth(200);

        String descricao = "Descrição: " + produto.getProduto();
        lbDescricao      = new Label(descricao);

        String preco = "Preço: " +  produto.getPreco();
        lbPreco      = new Label(preco);

        btAddCarrinho = new Button("Adicionar ao Carrinho");


        pane.getChildren().addAll(imgItem, lbDescricao, lbPreco, btAddCarrinho);
    }

    public void initLayout() {
        imgItem.setLayoutX(20);
        imgItem.setLayoutY(20);

        lbDescricao.setLayoutX(300);
        lbDescricao.setLayoutY(60);

        lbPreco.setLayoutX(300);
        lbPreco.setLayoutY(100);

        btAddCarrinho.setLayoutX(300);
        btAddCarrinho.setLayoutY(300);
    }

    public void initListeners() {
        btAddCarrinho.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                VitrineApp.getCarrinho().addProdutos(produto);

                try {
                    new CarrinhoApp().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Stage getStage() {
        return stage;
    }

    public static Produto getProduto() {
        return produto;
    }

    public static void setProduto(Produto produto) {
        ItemApp.produto = produto;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        ItemApp.index = index;
    }
}
