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
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {


    @FXML
    public Button addPerson;
    @FXML
    public Button removePerson;
    @FXML
    public Button next;
    @FXML
    public Button getPerson;

    @FXML
    public Label dateOfArrival;
    @FXML
    public Label passportNum;
    @FXML
    public Label name;
    @FXML
    public Label priority;

    @FXML
    public void initialize() {
        addPerson.setOnAction(this::addPerson);
        removePerson.setOnAction(this::removePerson);
        getPerson.setOnAction(this::getPerson);
        next.setOnAction(e -> next());
        displayNextPerson();
    }

    /**
     * Will get the next person from the queue and display their data to the UI.
     */
    private void displayNextPerson() {
        Person nextPerson = ImmigrationQueue.getInstance().getNext();
        if (nextPerson == null) return;
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
    }

    /**
     * Pops the next person of the list and updates the UI to show the next.
     */
    private void next() {
        ImmigrationQueue.getInstance().next();
        displayNextPerson();
    }

    private void getPerson(ActionEvent e) {

    }

    private void removePerson(ActionEvent e) {
    }

    private void addPerson(ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Internal/res/addPersonLayout.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Main menu");
            newStage.setScene(new Scene(root, 500, 500));
            newStage.show();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
