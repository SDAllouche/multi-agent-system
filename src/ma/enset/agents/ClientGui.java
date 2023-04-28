package ma.enset.agents;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class ClientGui extends Application {

    Client client;

    ObservableList<String> data= FXCollections.observableArrayList();
    public static void main(String[] args) throws ControllerException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stratContainer();
        BorderPane root=new BorderPane();
        ListView<String> listView=new ListView<>(data);
        Button send=new Button("Send");
        TextField message=new TextField();
        HBox hBox=new HBox(message,send);
        root.setCenter(listView);
        root.setBottom(hBox);
        Scene scene=new Scene(root,400,300);
        stage.setScene(scene);
        stage.show();
        send.setOnAction(actionEvent -> {
            String msg=message.getText();
            data.add(msg);
            GuiEvent guiEvent=new GuiEvent(this,1);
            guiEvent.addParameter(msg);
            client.onGuiEvent(guiEvent);
            message.setText("");
        });
    }

    public void stratContainer() throws StaleProxyException {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        jade.wrapper.AgentContainer agentContainer=runtime.createAgentContainer(profile);
        //Scanner scanner=new Scanner(System.in);
        //String name=scanner.nextLine();
        AgentController agentController=agentContainer.createNewAgent("name","ma.enset.agents.Client",new Object[]{this});
        agentController.start();
    }


    public void setClient(Client client) {
        this.client=client;
    }
}
