package martian_gui;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;

public class Main extends Application {
	
	TextField txfNameOne;
	TextField txfNameTwo;
	TextField txfNameThree;
	Button createAlienButton;
	TextArea txaMessage;
	private boolean amIGroot;
	protected ToggleGroup toggleStyle;
	private ArrayList<Martian> martians = new ArrayList<>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = buildPane();
			
			Scene scene = new Scene(root,500,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Aliens, Aliens Everywhere");
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
//	CREATE MAIN OUTER PANE
	public Pane buildPane() {
		VBox root = new VBox();
		root.setPadding(new Insets(25, 25, 25, 25));
		root.setSpacing(10);
		
		Label message = new Label("Enter an Integer for Id. If it is a Green Martian, also supply an Integer for Volume.");
		root.getChildren().add(message);
		root.getChildren().add(getFirstPane());
		root.getChildren().add(getSecondPane());
		
		Label buttonMessageOne = new Label("Press Alien Communication to listen in on the aliens communications.");
		Label buttonMessageTwo = new Label("Press Alien Teleportation to see the aliens teleport.\t  Destination");
		root.getChildren().add(buttonMessageOne);
		root.getChildren().add(buttonMessageTwo);
		
		root.getChildren().add(getThirdPane());
		
		txaMessage = new TextArea();		
		root.getChildren().add(txaMessage);
		root.getChildren().add(getFinalPane());

		return root;
	}
//	CREATE FIRST PANE
	private HBox getFirstPane() {
	//	CREATE FIRST PANE
		HBox paneOne = new HBox();
		paneOne.setSpacing(25);
			
		paneOne.getChildren().add(firstPaneElementOne());
		paneOne.getChildren().add(firstPaneElementTwo());
		
		return paneOne;
	}
//	HELPER METHOD FOR getFirstPane()
	private VBox firstPaneElementOne() {
		VBox elementOne = new VBox();
		elementOne.setSpacing(5);
		
		Label labelOne = new Label("Id");
		txfNameOne = new TextField("");
		
		elementOne.getChildren().add(labelOne);
		elementOne.getChildren().add(txfNameOne);
		
		Label labelThree = new Label("Volume (If Green Martian)");
		txfNameTwo = new TextField("");
		
		elementOne.getChildren().add(labelThree);
		elementOne.getChildren().add(txfNameTwo);
		
		return elementOne;
	}
//	HELPER METHOD FOR getFirstPane()
	private VBox firstPaneElementTwo() {
		VBox elementTwo = new VBox();
		elementTwo.setSpacing(5);
		
		Label labelTwo = new Label("Martian Type");
		elementTwo.getChildren().add(labelTwo);
		
		toggleStyle = new ToggleGroup();
		
		RadioButton greenMartianButton = greenMartianButton();
		elementTwo.getChildren().add(greenMartianButton);
		
		RadioButton redMartianButton = redMartianButton();
		elementTwo.getChildren().add(redMartianButton);
		
		return elementTwo;
	}
//	HELPER METHOD FOR firstPaneElementTwo()
	private RadioButton greenMartianButton() {
		RadioButton greenMartianButton = new RadioButton("Green Martian");
		greenMartianButton.setToggleGroup(toggleStyle);
		greenMartianButton.setSelected(true);
		
		return greenMartianButton;
	}
//	HELPER METHOD FOR firstPaneElementTwo()
	private RadioButton redMartianButton() {
		RadioButton redMartianButton = new RadioButton("Red Martian");
		redMartianButton.setToggleGroup(toggleStyle);
		
		return redMartianButton;
	}
//	CREATE SECOND PANE
	private HBox getSecondPane() {
		HBox buttonControlOne = new HBox();
		buttonControlOne.setSpacing(25);
		
		Button createAlienButton = new Button("Create Alien");
		createAlienButton.setOnAction(new CreateAlienButtonEventHandler());
		buttonControlOne.getChildren().add(createAlienButton);
		
		return buttonControlOne;
	}
//	CREATE THIRD PANE
	private HBox getThirdPane() {
		HBox buttonControlTwo = new HBox();
		buttonControlTwo.setSpacing(25);
		
		Button alienSpeak = getAlienCommunication();
		buttonControlTwo.getChildren().add(alienSpeak);
		
		Button alienTeleport = getAlienTeleportation();
		buttonControlTwo.getChildren().add(alienTeleport);
		
		txfNameThree = new TextField("Roswell");
		buttonControlTwo.getChildren().add(txfNameThree);
		
		return buttonControlTwo;
	}
//	HELPER METHOD FOR getThirdPane()
	private Button getAlienCommunication() {
		Button alienSpeak = new Button("Alien Communication");
		alienSpeak.setOnAction(new AlienCommunicationButtonEventHandler());
		
		return alienSpeak;
	}
//	HELPER METHOD FOR getThirdPane()
	private Button getAlienTeleportation() {
		Button alienTeleport = new Button("Alien Teleportation");
		alienTeleport.setOnAction(new AlienTeleportationButtonHandler());
		
		return alienTeleport;
	}
//	CREATE FINAL PANE
	private HBox getFinalPane() {
		HBox elementThree = new HBox();
		elementThree.setSpacing(5);
		
		Button grootButton = getGroot();
		elementThree.getChildren().add(grootButton);
		
		Label finalRemark = new Label("Beware, I Am Groot");
		elementThree.getChildren().add(finalRemark);
		
		return elementThree;
	}
	
	private Button getGroot() {
		Button grootButton = new Button("I AM GROOT");
		grootButton.setOnAction(new IAmGrootButtonEventHandler());
		
		return grootButton;
	}
//	ALIEN CREATION EVENT HANDLER
	private class CreateAlienButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			String id = txfNameOne.getText();
			String volume = txfNameTwo.getText();
			
			if (amIGroot == true) {
				txaMessage.clear();
				amIGroot = false;
			}
			String style = ((RadioButton)toggleStyle.getSelectedToggle()).getText();
			if (style.equals("Green Martian")) {
				addGreenMartian(id, volume);
				txaMessage.appendText("Green Martian Added\n");
			}
			else if (style.equals("Red Martian")) {
				addRedMartian(id, volume);
				txaMessage.appendText("Red Martian Added\n");
			}
			txfNameOne.clear();
			txfNameTwo.clear();
		}
	}
//	HELPER METHOD FOR CreateAlienButtonEventHandler()
	private void addGreenMartian(String id, String volume) {
		if (volume.isEmpty()) {
			GreenMartian gm = new GreenMartian(Integer.parseInt(id));
			if (!martians.contains(gm))
				martians.add(gm);
		}
		else {
			GreenMartian gm = new GreenMartian(Integer.parseInt(id), Integer.parseInt(volume));
			if (!martians.contains(gm))
				martians.add(gm);
		}
	}
//	HELPER METHOD FOR CreateAlienButtonEventHandler()
	private void addRedMartian(String id, String volume) {
		if (volume.isEmpty()) {
			RedMartian rm = new RedMartian(Integer.parseInt(id));
			if (!martians.contains(rm))
				martians.add(rm);
		}
		else {
			System.out.print("Error: Red Martians Do Not Have Volume, Martian not added");
		}
	}
//	ALIEN COMMUNICATION EVENT HANDLER
	private class AlienCommunicationButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			StringBuilder communication = new StringBuilder();
			if (martians.isEmpty()) {
				communication.append("There are no aliens communicating.");
			}
			else {
				communication.append("Martians communicating:\n");
				for (Martian m : martians) {
					if (m instanceof GreenMartian) {
						communication.append(m.speak() + "\n");
					}
					else if (m instanceof RedMartian) {
						communication.append(m.speak() + "\n");
					}
				}
			}
			txaMessage.setText(communication.toString());
		}
	}
//	ALIEN TELEPORTATION EVENT HANDLER
	private class AlienTeleportationButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			String destination = txfNameThree.getText();
			StringBuilder teleportation = new StringBuilder();
			if (martians.isEmpty()) {
				teleportation.append("There are no aliens teleporting.");
			}
			else {
				teleportation.append("Green Martians Teleporting:\n");
				for (Martian m : martians) {
					if (m instanceof GreenMartian) {
						teleportation.append(((GreenMartian) m).teleport(destination) + "\n");
					}
				}
			}
			txaMessage.setText(teleportation.toString());
		}
	}
// I AM GROOT
	private class IAmGrootButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			IAmGroot groot = new IAmGroot();
			martians.clear();
			txaMessage.setText(groot.toString());
			amIGroot = true;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}