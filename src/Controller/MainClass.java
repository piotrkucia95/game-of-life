package Controller;

import View.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

//This class contains 'main' and 'launch' functions
public class MainClass extends Application{
	
	@Override
	public void start(Stage mainStage) throws Exception {		
		MainView mainView = new MainView(mainStage);
	}

	public static void main(String args[]) {
		launch(args);
	}
}
