package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.EscolasWell;
import model.services.EscolaWellService;

public class EscolasWellListController implements Initializable, DataChangeListener {
	
	private EscolaWellService service ;
	
	@FXML
	private TableView<EscolasWell> tableViewEscolasWell;
	
	@FXML
	private TableColumn<EscolasWell, Integer> tableColumnId;
	
	@FXML
	private TableColumn<EscolasWell, String> tableColumnNome;
	
	@FXML
	private Button btnCriaEscolas;
	
	
	private ObservableList<EscolasWell> obsList;
	
	@FXML
	public void onbtnCriar(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		EscolasWell obj = new EscolasWell();
		createDialogForm(obj,"/gui/EscolasWellInsert.fxml", parentStage);
	}
	
	public void setEscolaWellService(EscolaWellService service) {
		this.service = service;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeNode();
		
	}
	

	private void initializeNode() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewEscolasWell.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Serviço está vazio");
		}
		
		List<EscolasWell> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewEscolasWell.setItems(obsList);
	}
	
	private void createDialogForm(EscolasWell obj,  String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			  EscolasWellFormController controller = loader.getController();
			  controller.setEscolasWell(obj);
			  controller.updateFormData();
			  controller.setEscolasWEllService(new EscolaWellService());
			  controller.subscribeDataChangeLiestener(this);;
			 

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Adicionar Escolas");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void ondataChanged() {
		updateTableView();

	}


}
