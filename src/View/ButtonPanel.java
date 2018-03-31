package View;

import Model.SizeConstants;
import Model.StateData;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

//Cells are placed in Flow Layout Panel
public class ButtonPanel extends FlowPane{
	
	//StateHolder holds the state of a cell
	private StateHolder[][] buttons;
	private boolean selectable = true;
	
	//ButtonPanel constructor
	public ButtonPanel(int sizeA, int sizeB) {
		
		setPrefSize(sizeA+2, sizeB+2);
		setMaxSize(sizeA+2, sizeB+2);
		setStyle("-fx-background-color: #65cca9; -fx-border-color: #000000");
		
	}
	
	//This function is used to create board of cells, argument gives number of cells in a row
	public void createBoard(int rows) {		

		int buttonWidth 	= SizeConstants.boardPanelWidth/rows;
		int buttonHeigth 	= SizeConstants.boardPanelHeigth/rows;
		buttons = new StateHolder[rows][rows];
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<rows; j++) {
				buttons[i][j] = new StateHolder(buttonWidth, buttonHeigth);
				getChildren().add(buttons[i][j]);
			}
		}
	}
	
	//This function switches off option of cell's selection
	public void setSelectable(boolean x) {
		if (x) selectable = true;
		else selectable = false;
	}
	
	//This function is used to pass live cells coordinates to boolean array of states
	public void checkMarkedButtons(int rows, StateData stateArray) {
		for(int i=0; i<rows; i++) {
			for(int j=0; j<rows; j++) {
				if (buttons[i][j].marked == true) {
					stateArray.state[i][j] = true;
				}
			}
		}
	}
	
	//This function updates boolean array of states
	public void updateBoard(int rows, StateData stateArray) {
		for(int i=0; i<rows; i++) {
			for(int j=0; j<rows; j++) {
				if(stateArray.state[i][j] == true) {
					buttons[i][j].marked = true;
					buttons[i][j].setStyle("-fx-background-color: #000000");
				} 
				
				if(stateArray.state[i][j] == false) {
					buttons[i][j].marked = false;
					buttons[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #87CEFA");
				} 
			}
		}
	}
	
	//This function allows to remove board of cells
	public void removeButtons(int rows) {
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<rows; j++){
				getChildren().remove(buttons[i][j]);
			}
		}
	}
	
	//This function allows to mark cells continuously
	public void continuousMarkingOn(int rows) {
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<rows; j++){
				buttons[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED, buttons[i][j].changeState);
			}
		}
	}
	
	//This function switches off continuous marking
	public void continuousMarkingOff(int rows) {
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<rows; j++){
				buttons[i][j].removeEventHandler(MouseEvent.MOUSE_ENTERED, buttons[i][j].changeState);
			}
		}
	}
	
	
	//private StateHolder class
	private class StateHolder extends Pane{
		
		boolean marked = false;
		
		//StateHolder constructor
		StateHolder(int width, int heigth) {
			
			setPrefSize(width, heigth);
			setStyle("-fx-border-color: #000000; -fx-background-color: #87CEFA");	
			addEventHandler(MouseEvent.MOUSE_PRESSED, changeState);
		}
		
		//EventHandler allowing to change cells' state
		EventHandler<MouseEvent> changeState = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (selectable) {
					setStyle("-fx-background-color: #000000");
					marked = true;
				}
			}	
		};		
	}
}
