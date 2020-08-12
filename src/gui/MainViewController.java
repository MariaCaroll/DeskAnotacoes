package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.AnotacoesService;
import model.services.EscolaWellService;

public class MainViewController implements Initializable {
	
	///Criar
	@FXML
	private MenuItem menuItemCAnotacoes;
	
	@FXML
	private MenuItem menuItemCEscolasWell;
	
	
	

	@FXML
	public void onMenuItemCAnotacoesAction() {
		loadView("/gui/AnotacoesForm.fxml", (AnotacoesListeControllerCriar controller) -> {
			//controller.( new AnotacoesService());
			//controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemCEscolasWellAction() {
		loadView("/gui/EscolasWellForm.fxml", (EscolasWellListController controller) -> {
			controller.setEscolaWellService(new EscolaWellService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", X ->{});
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		
		try {
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		
			T controller = loader.getController();
			initializingAction.accept(controller);
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a View", e.getMessage(), AlertType.ERROR);
		}
	}
	
	
}
