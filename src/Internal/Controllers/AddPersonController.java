package Internal.Controllers;

import DataStructure.ImmigrationQueue;
import DataTypes.Person;
import DataTypes.Priority;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPersonController {


    @FXML
    TextField FnameField;
    @FXML
    TextField LnameField;
    @FXML
    TextField passportNumField;
    @FXML
    DatePicker DateOfArrivalField;
    @FXML
    ChoiceBox priorityField;
    @FXML
    Button addButton;
    @FXML
    Label error_msg;

    @FXML
    public void initialize() {
        addButton.setOnAction(e -> {
            if (addPerson()) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("res/MainMenu.fxml"));
                    Stage newStage = new Stage();
                    newStage.setTitle("Main menu");
                    newStage.setScene(new Scene(root, 500, 500));
                    newStage.show();
                    ((Node) (e.getSource())).getScene().getWindow().hide();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                error_msg.setText("There was a error adding person");
            }
        });
    }


    private boolean addPerson() {
        FnameField.getText();
        LnameField.getText();

        if (FnameField.getText().isEmpty() || LnameField.getText().isEmpty()) return false;
        Priority priority;
        switch ((String) priorityField.getValue()) {
            case "Low":
                priority = Priority.LOW;
                break;
            case "Medium":
                priority = Priority.MEDIUM;
                break;
            case "High":
                priority = Priority.HIGH;
                break;
            default:
                return false;
        }
        Person p = new Person(FnameField.getText(), LnameField.getText(), DateOfArrivalField.getValue(), passportNumField.getText(), priority);
        ImmigrationQueue.getInstance().addPerson(p);
        return true;
    }


}
