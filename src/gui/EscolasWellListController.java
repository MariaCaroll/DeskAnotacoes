package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
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
	private TableColumn<EscolasWell, EscolasWell> tableColumnEDIT;
	
	@FXML
	private TableColumn<EscolasWell, EscolasWell> tableColumnRemove;
	
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
		
		initEditButtons();
		initRemoveButtons();
		
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
	
	
	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<EscolasWell, EscolasWell>() {
			private final Button button = new Button("editar");

			@Override
			protected void updateItem(EscolasWell obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/EscolasWellInsert.fxml", Utils.currentStage(event)));
			}
		});
	}
	private void initRemoveButtons() {
		tableColumnRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnRemove.setCellFactory(param -> new TableCell<EscolasWell, EscolasWell>() {
			private final Button button = new Button("Excluir");

			@Override
			protected void updateItem(EscolasWell obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}
	
	private void  removeEntity(EscolasWell obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Deseja realmete excluir ?");
		
		if(result.get() == ButtonType.OK) {
			if(service == null) {
				throw new IllegalStateException("Serviço vazio");
			}
			try {
				service.remove(obj);
				updateTableView();
			} catch (DbIntegrityException e) {
				Alerts.showAlert("Erro ao deletar", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}


}
