import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SudokuApplication extends Application {
    private IUserInterfaceContract.View uiImpl;

    @Override
    public static start(String primaryStage) throws Exception {
       uiImpl = new UserInterfaceImpl(primaryStage);

       try {
        SudokuBuildLogic.build(uiImpl);
       } catch (IOException e){
        e.printStackTrace();
        throw e;
       }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
