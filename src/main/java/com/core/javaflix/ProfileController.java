package com.core.javaflix;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// generating values
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;

public class ProfileController {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label passwordLabel;

    @FXML // contains email address of the user
    private TextField userNameInput;

    @FXML // contains the first name of the user
    private TextField firstNameInput;

    @FXML // contains the last name of the user
    private TextField lastNameInput;

    @FXML // contains email address info given by the user
    private TextField emailInput;

    @FXML // contains email address info given by the user
    private TextField passwordInput;

    @FXML //button to update the user's information
    private Button updateButton;

    @FXML //button to update the user's information
    private Button cancelButton;


    /**
     * Builder that assigns the text fields with the correct
     * information of the user.
     */
    ProfileController() {
        int userID = BaseApplication.storage.userID;

        try {
            var c = DataStreamManager.conn;
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(  "SELECT p320_05.\"User\".\"Username\", " +
                                                        "p320_05.\"User\".\"Firstname\", " +
                                                        "p320_05.\"User\".\"Lastname\",  " +
                                                        "p320_05.\"User\".\"Email\", " +
                                                        "p320_05.\"User\".\"Password\" " +
                                                        "FROM p320_05.\"User\" " +
                                                        "WHERE \"UserID\" = '" + userID + "'");
            rs.next();
            userNameInput.setText(rs.getString("UserName"));
            firstNameInput.setText(rs.getString("FirstName"));
            lastNameInput.setText(rs.getString("LastName"));
            emailInput.setText(rs.getString("Email"));
            passwordInput.setText(rs.getString("Password"));
        }
        catch (SQLException e){
            System.out.println("Failed to get profile information");
        }
    }

    /**
     * Listener for when the user clicks on the "update" button
     * redirect user to login page
     * @param actionEvent contains data regarding the nature of the interaction
     */
    @FXML
    public void profileUpdate(ActionEvent actionEvent) {
        try {
            var c = DataStreamManager.conn;
            Statement statement = c.createStatement();
            statement.execute("Update");
            System.out.println("Account Updated");
            BaseApplication.base.load();
        }
        catch (SQLException e) {
            System.out.println("Could not update profile");
        }
    }

    /**
     * Listener for when the user clicks on the "back" button
     * redirect user to login page
     * @param actionEvent contains data regarding the nature of the interaction
     */
    @FXML
    public void profileCancel(ActionEvent actionEvent) {
        //TODO go back to dashboard without changing profile data
    }

}
