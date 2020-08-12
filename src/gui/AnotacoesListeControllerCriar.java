package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.entities.AnotacoesC;

public class AnotacoesListeControllerCriar implements Initializable {
	
	@FXML
	private TableView<AnotacoesC> tableViewAnotacoes;
	
	@FXML
	private TableColumn<AnotacoesC, String> tableColumnId;
	
	@FXML
	private TableColumn<AnotacoesC, String> tableColumnEscola;
	
	@FXML
	private TableColumn<AnotacoesC, String> tableColumnSituacao;
	
	@FXML
	private TableColumn<AnotacoesC, String> tableColumnNChamado;
	
	@FXML
	private TableColumn<AnotacoesC, String> tableColumnDate;
	
	@FXML
	private Button btnCriar;
	
	@FXML
	public void onbtnCriar() {
		System.out.println("Apro play dj");
	}
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
			
	}

}
