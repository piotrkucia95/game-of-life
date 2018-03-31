package View;

import java.util.Timer;
import java.util.TimerTask;

import Controller.MainController;
import Controller.StateController;
import Model.SizeConstants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainView {
	
	private BorderPane rootNode;
	private Scene scene;
	private OptionsPanel optionsPanel;
	private ButtonPanel buttonPanel;
	private MainController mainController;
	private StateController stateController;
	private Timer timer;
	private TimerTask timerTask;
	
	//MainView constructor
	public MainView(Stage myStage) {
		
		rootNode 			= new BorderPane();
		scene 				= new Scene(rootNode, SizeConstants.windowWidth, SizeConstants.windowHeigth);
		optionsPanel		= new OptionsPanel();
		buttonPanel			= new ButtonPanel(SizeConstants.boardPanelWidth, SizeConstants.boardPanelHeigth);
		mainController		= new MainController();
		stateController		= new StateController();
		timer 				= new Timer();
		timerTask			= new TimerTask() { //default TimerTask object
			@Override
			public void run(){}
		};
		
		myStage.setTitle("Game of Life");
		myStage.setScene(scene);
		rootNode.setLeft(buttonPanel);
		rootNode.setCenter(optionsPanel);
		BorderPane.setMargin(optionsPanel, new Insets(20)); //20px margin for optionPanel
		myStage.show();
		
	}
	
	//private panel containing options
	private class OptionsPanel extends FlowPane {
		
		Label sizeChooserInfo;
		ChoiceBox<String> sizeChooser;
		Button boardCreator;
		Label markButtonsInfo;
		CheckBox continuousMarking;
		Button buttonsMarked;
		Label speedChooserInfo;
		ChoiceBox<String> speedChooser;
		Button startButton;
		Button startAgainButton;
		
		//OptionsPanel constructor
		OptionsPanel() {
			
			sizeChooserInfo		= new Label("Choose board size:");
			sizeChooser			= new ChoiceBox<String>();
			boardCreator		= new Button("OK");
			markButtonsInfo		= new Label("Choose live cells on the board");
			continuousMarking	= new CheckBox("Continuous Marking");
			buttonsMarked		= new Button("OK");
			speedChooserInfo	= new Label("Choose refreshing speed:");
			speedChooser 		= new ChoiceBox<String>();
			startButton			= new Button("START");
			startAgainButton	= new Button("Start Again");
			
			sizeChooser.setItems(FXCollections.observableArrayList("10x10", "20x20", "50x50", "100x100"));
			speedChooser.setItems(FXCollections.observableArrayList("1/s", "2/s", "5/s", "10/s"));
			markButtonsInfo.setDisable(true);
			buttonsMarked.setDisable(true);
			continuousMarking.setDisable(true);
			speedChooserInfo.setDisable(true);
			speedChooser.setDisable(true);		
			startButton.setDisable(true);
			
			boardCreator.addEventHandler(MouseEvent.MOUSE_PRESSED, createBoard);
			buttonsMarked.addEventHandler(MouseEvent.MOUSE_PRESSED, confirmButtonsMarked);
			startButton.addEventHandler(MouseEvent.MOUSE_PRESSED, start);
			startAgainButton.addEventHandler(MouseEvent.MOUSE_PRESSED, startAgain);
			continuousMarking.addEventHandler(ActionEvent.ACTION, continousMark);
	
			setStyle("-fx-background-color: #ffffff");			
			setOrientation(Orientation.VERTICAL);			
			setVgap(20);
			
			//organization of OptionsPanel layout
			FlowPane emptyPanel1 = new FlowPane();
			emptyPanel1.setPrefSize(20, 40);
			FlowPane sizePanel = new FlowPane();
			sizePanel.getChildren().addAll(sizeChooser, boardCreator);
			sizePanel.setHgap(67);
			FlowPane markPanel = new FlowPane();
			markPanel.getChildren().addAll(continuousMarking, buttonsMarked);
			markPanel.setHgap(20);
			FlowPane speedPanel = new FlowPane();
			speedPanel.getChildren().addAll(speedChooser, startButton);
			speedPanel.setHgap(75);
			startAgainButton.setPrefSize(205, 50);
			
			getChildren().addAll(emptyPanel1, sizeChooserInfo, sizePanel,
					markButtonsInfo, markPanel, speedChooserInfo,
						speedPanel, startAgainButton);
						
		}
		
	}
	
	//EventHandler allowing to create cells' board containing states
	EventHandler<MouseEvent> createBoard = new EventHandler<MouseEvent>() {
		
		@Override
		public void handle(MouseEvent arg0) {
			
			if (optionsPanel.sizeChooser.getSelectionModel().getSelectedItem() != null) {
				
				mainController.setBoardSize(optionsPanel.sizeChooser.getSelectionModel().getSelectedItem());
				mainController.createStateArray();
				
				buttonPanel.createBoard(mainController.getBoardSize());
			
				optionsPanel.sizeChooser.setDisable(true);
				optionsPanel.boardCreator.setDisable(true);
				optionsPanel.sizeChooserInfo.setDisable(true);
				optionsPanel.markButtonsInfo.setDisable(false);
				optionsPanel.buttonsMarked.setDisable(false);
				optionsPanel.continuousMarking.setDisable(false);
			}
		}	
	};
	
	//EventHandler allowing to confirm chosen live cells
	EventHandler<MouseEvent> confirmButtonsMarked = new EventHandler<MouseEvent>() {
		
		@Override
		public void handle(MouseEvent arg0) {
			
			optionsPanel.markButtonsInfo.setDisable(true);
			optionsPanel.buttonsMarked.setDisable(true);
			optionsPanel.continuousMarking.setDisable(true);
			optionsPanel.speedChooserInfo.setDisable(false);
			optionsPanel.speedChooser.setDisable(false);		
			optionsPanel.startButton.setDisable(false);
			buttonPanel.setSelectable(false);
			
			buttonPanel.checkMarkedButtons(mainController.getBoardSize(), mainController.getStateData());
		}	
	};
	
	//EventHandler allowing to switch on continuous marking on board
	EventHandler<ActionEvent> continousMark = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
			if (optionsPanel.continuousMarking.isSelected()){
				buttonPanel.continuousMarkingOn(mainController.getBoardSize());
			} else {
				buttonPanel.continuousMarkingOff(mainController.getBoardSize());
			}
		}
	};
	
	//EventHandler starting actual game
	EventHandler<MouseEvent> start = new EventHandler<MouseEvent>() {
		
		@Override
		public void handle(MouseEvent arg0) {
			
			timerTask = new TimerTask() {
				@Override
				public void run() {
					stateController.updateState(mainController.getBoardSize(), mainController.getStateData());
					buttonPanel.updateBoard(mainController.getBoardSize(), mainController.getStateData());
				}	
			};
			
			if(optionsPanel.speedChooser.getSelectionModel().getSelectedItem() == "1/s") {
				timer.schedule(timerTask, 0, 1000);
			} else if(optionsPanel.speedChooser.getSelectionModel().getSelectedItem() == "2/s") {
				timer.schedule(timerTask, 0, 500);
			} else if(optionsPanel.speedChooser.getSelectionModel().getSelectedItem() == "5/s") {
				timer.schedule(timerTask, 0, 200);
			} else if(optionsPanel.speedChooser.getSelectionModel().getSelectedItem() == "10/s") {
				timer.schedule(timerTask, 0, 100);
			}
			
			if (optionsPanel.speedChooser.getSelectionModel().getSelectedItem() != null) {
				optionsPanel.speedChooserInfo.setDisable(true);
				optionsPanel.speedChooser.setDisable(true);
				optionsPanel.startButton.setDisable(true);
			}
		}	
	};
	
	//'start again' EventHandler
	EventHandler<MouseEvent> startAgain = new EventHandler<MouseEvent>() {
		
		@Override
		public void handle(MouseEvent arg0) {
				
			timerTask.cancel();
			buttonPanel.removeButtons(mainController.getBoardSize());
			
			optionsPanel.sizeChooser.setDisable(false);
			optionsPanel.boardCreator.setDisable(false);
			optionsPanel.sizeChooserInfo.setDisable(false);
			optionsPanel.markButtonsInfo.setDisable(true);
			optionsPanel.buttonsMarked.setDisable(true);
			optionsPanel.continuousMarking.setDisable(true);
			optionsPanel.speedChooserInfo.setDisable(true);
			optionsPanel.speedChooser.setDisable(true);
			optionsPanel.startButton.setDisable(true);
			buttonPanel.setSelectable(true);
		}	
	};
	
}
