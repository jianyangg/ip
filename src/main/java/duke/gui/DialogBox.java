package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
  @FXML
  private Label dialog;
  @FXML
  private ImageView displayPicture;

  private DialogBox(String text, Image img) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("view/DialogBox.fxml"));
      fxmlLoader.setController(this);
      fxmlLoader.setRoot(this);
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    dialog.setText(text);
    dialog.setWrapText(true);
    displayPicture.setImage(img);
    displayPicture.setClip(new javafx.scene.shape.Circle(50, 50, 50));
    dialog.setMinHeight(Region.USE_PREF_SIZE);
    dialog.setMaxWidth(Region.USE_PREF_SIZE);
    dialog.setPrefWidth(Region.USE_COMPUTED_SIZE);
    dialog.setBorder(null);
    dialog.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
    dialog.setAlignment(Pos.CENTER_LEFT);
  }

  /**
   * Flips the dialog box such that the ImageView is on the left and text on the right.
   */
  private void flip() {
    ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
    Collections.reverse(tmp);
    getChildren().setAll(tmp);
    setAlignment(Pos.TOP_LEFT);
  }

  public static DialogBox getUserDialog(String text, Image img) {
    var db = new DialogBox(text, img);
    db.setStyle("-fx-background-color: #F5F5DC; -fx-padding: 10px; -fx-border-radius: 10px;");
    return db;
  }

  public static DialogBox getDukeDialog(String text, Image img) {
    var db = new DialogBox(text, img);
    db.setStyle("-fx-background-color: #e6f7ff; -fx-padding: 10px; -fx-border-radius: 10px;");
    db.flip();
    return db;
  }
}
