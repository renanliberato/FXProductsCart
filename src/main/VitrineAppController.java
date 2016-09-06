package main;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class VitrineAppController {

    @FXML TextField searchField;

    @FXML TableView tableView;

    @FXML ObservableList<Produto> listItems = FXCollections.observableArrayList();

    @FXML protected void searchProduct() {
        ObservableList<Produto> itensEncontrados = FXCollections.observableArrayList();
//        ObservableList<Produto> listItems = FXCollections.observableArrayList();
        listItems.addAll((Produto) tableView.getItems());
        for (Produto produto : listItems) {
            if (produto.getProduto().contains(searchField.getText())) {
                itensEncontrados.add(produto);
            }
        }
        tableView.setItems(itensEncontrados);
    }

    @FXML protected void openItem(MouseEvent click) {
        System.out.println("Clicked");
    }
}
