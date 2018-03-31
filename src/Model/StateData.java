package Model;

import java.util.ArrayList;

//Data class
public class StateData {
	
	//Boolean array of states
	public boolean[][] state;
	
	public StateData(int x) {
		state = new boolean[x][x];
	}

}
