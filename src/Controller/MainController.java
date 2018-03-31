package Controller;

import Model.StateData;

//MainController is used to create proper-sized cells' board and boolean array of states
public class MainController {
	
	private int boardSize = 0;
	private StateData stateData;
	
	//No constructor required
	public MainController() {}
	
	//This function sets accurate size of cells' board
	public void setBoardSize(String size) {
		if (size=="10x10") {
			boardSize=10;
		} else if (size=="20x20") {
			boardSize=20;
		} else if (size=="50x50") {
			boardSize=50;
		} else if (size=="100x100") {
			boardSize=100;
		}	
	}
	
	//boardSize getter
	public int getBoardSize() {
		return boardSize;
	}
	
	//This function creates boolean array of states
	public void createStateArray() {
		stateData = new StateData(boardSize);
		for (int i=0; i<boardSize; i++) {
			for(int j=0; j<boardSize; j++) {
				stateData.state[i][j] = false;
			}
		}
	}
	
	//stateData getter
	public StateData getStateData() {
		return stateData;
	}
	
	
	
	
}
