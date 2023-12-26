import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class OnlineVotingSystem extends Application {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_voting_system";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Online Voting System");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Candidate Name:");
        GridPane.setConstraints(nameLabel, 0, 0);

        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Button voteButton = new Button("Vote");
        GridPane.setConstraints(voteButton, 1, 1);

        voteButton.setOnAction(e -> voteCandidate(nameInput.getText()));

        grid.getChildren().addAll(nameLabel, nameInput, voteButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void voteCandidate(String candidateName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO votes (candidate_name) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, candidateName);
                preparedStatement.executeUpdate();
                showAlert("Vote Successful", "Thank you for voting!");
            }
        } catch (SQLException e) {
            showAlert("Error", "Failed to vote. Please try again.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
