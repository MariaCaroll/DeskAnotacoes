package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class MainViewController implements Initializable {
	
	///Criar
	@FXML
	private MenuItem menuItemCAnotacoes;
	
	@FXML
	private MenuItem menuItemCEscolasWell;
	
	@FXML
	private MenuItem menuItemCColaborador;
	
	
	//Editar
	@FXML
	private MenuItem menuItemEAnotacoes;
	
	@FXML
	private MenuItem menuItemEEscolasWell;
	
	@FXML
	private MenuItem menuItemEColaborador;
	
	@FXML
	private MenuItem menuItemAbout;
	

	@FXML
	public void onMenuItemCAnotacoesAction() {
		System.out.println("Anotacoes");
	}
	
	@FXML
	public void onMenuItemCEscolasWellAction() {
		System.out.println("Escolas");
	}
	
	@FXML
	public void onMenuItemCColaboradorAction() {
		System.out.println("Colaboradores");
	}
	
	@FXML
	public void onMenuItemEAnotacoesAction() {
		System.out.println("editar anotacoes");
	}
	
	@FXML
	public void onMenuItemEEscolasWellAction() {
		System.out.println("editar escolas");
	}
	
	@FXML
	public void onMenuItemEColaboradorAction() {
		System.out.println("editat colar");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}

	private synchronized void loadView(String absoluteName) {
		
		try {
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a View", e.getMessage(), AlertType.ERROR);
		}
	}
}
