package ma.enset.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ServerGui extends Application {

    private ObservableList<String> data= FXCollections.observableArrayList();

    public static void main(String[] args) throws ControllerException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stratContainer();
        BorderPane root=new BorderPane();
        ListView<String> listView=new ListView<>(data);
        root.setCenter(listView);
        Scene scene=new Scene(root,400,300);
        stage.setScene(scene);
        stage.show();
    }

    public void stratContainer() throws StaleProxyException {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=runtime.createAgentContainer(profile);
        AgentController agentController=agentContainer.createNewAgent("Server","ma.enset.agents.Server",new Object[]{this});
        agentController.start();
    }

    public void showMessage(String message){
        data.add(message);
    }
}
