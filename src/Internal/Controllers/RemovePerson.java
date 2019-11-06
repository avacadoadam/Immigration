package Internal.Controllers;

import DataStructure.ImmigrationQueue;
import DataTypes.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RemovePerson {

    @FXML
    public TextField IDField;
    @FXML
    public Label name;
    @FXML
    public Label passportNum;
    @FXML
    public Label dateOfArrival;
    @FXML
    public Label priority;
    @FXML
    public Label ID;
    @FXML
    public Button confirm;
    @FXML
    public Label error_msg;
    @FXML
    public Button search;
    @FXML
    public Button back;
    private int currentSearchPersonID = -1;

    //TODO not removing or not sending back to menu not sure which one
    @FXML
    public void initialize() {
        search.setOnAction(e -> displayNextPerson());
        confirm.setOnAction(e -> {
            if (currentSearchPersonID != -1) {
                if (!ImmigrationQueue.getInstance().removePerson(currentSearchPersonID)) {
                    error_msg.setText("Error deleting that Person from list");
                } else {
                    backToMainMenu(e);
                }
            }
        });
        back.setOnAction(this::backToMainMenu);

    }

    /**
     * Will get the next person from the queue and display their data to the UI.
     */
    private void displayNextPerson() {
        Person nextPerson = ImmigrationQueue.getInstance().getNext();
        if (nextPerson == null) {
            error_msg.setText("Couldn't find Person");
            confirm.setDisable(true);
            return;
        }
        String priorityText;
        switch (nextPerson.getPriorityLevel().value()) {
            case 1:
                priorityText = "medium";
                break;
            case 2:
                priorityText = "High";
                break;
            default:
                priorityText = "low";
        }
        name.setText(nextPerson.getFname() + nextPerson.getLname());
        passportNum.setText(nextPerson.getPassportNum());
        priority.setText(priorityText);
        dateOfArrival.setText(nextPerson.getDateOfArrival().toString());
        ID.setText((String.valueOf(nextPerson.getID())));
        confirm.setDisable(false);
    }

    private void backToMainMenu(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Internal/res/MainMenuLayout.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Main menu");
            newStage.setScene(new Scene(root, 500, 500));
            newStage.show();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
