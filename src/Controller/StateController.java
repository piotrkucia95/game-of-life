package Controller;

import Model.StateData;

public class StateController {
	
	private long count;

	//No constructor required for this class
	public StateController() {}
	
	//This function is controlling the update of boolean array of states
	public void updateState(int x, StateData stateArray) {
		
		boolean[][] extraStateArray = new boolean[x][x];
		
		for(int i=0; i<x; i++) {
			for(int j=0; j<x; j++) {
				extraStateArray[i][j] = false;
			}
		}
		
		for(int i=0; i<x; i++) {
			for(int j=0; j<x; j++) {
				count = 0;
				
				if(i>0) 
					if(stateArray.state[i-1][j] == true) count++;
				
				if((i>0) && (j<x-1)) 
					if(stateArray.state[i-1][j+1] == true) count++;
				
				if(j<x-1) 
					if(stateArray.state[i][j+1] == true) count++;
				
				if((i<x-1) && (j<x-1))
					if(stateArray.state[i+1][j+1] == true) count++;
				
				if(i<x-1)
					if(stateArray.state[i+1][j] == true) count++;
				
				if((i<x-1) && (j>0))
						if(stateArray.state[i+1][j-1] == true) count++;
				
				if((i>0) && (j>0))
					if(stateArray.state[i-1][j-1] == true) count++;
				
				if(j>0)
					if(stateArray.state[i][j-1] == true) count++;
				
				
				//Main rules of the games
				if ((stateArray.state[i][j] == false) && (count == 3)) 
					extraStateArray[i][j] = true;
							
				if ((stateArray.state[i][j] == true) && ((count == 2) || (count == 3))) 
					extraStateArray[i][j] = true;
				
			}
		}
		
		stateArray.state = extraStateArray;
	
	}

	
}
