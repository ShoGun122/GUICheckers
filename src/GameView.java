import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameView extends Application {

  // metoda uruchamiająca nasz program
  public static void main(String[] args) {
    launch(args);
  }

  // widok musi przechowywać kontroler w celu wywołania jego metod jako odpowiedzi na działania użytkownika
  private GameController controller;
  
  // w celu aktualizacji widoku konieczne będzie również przechoywanie stanu planszy
  private Circle[][] board;

  @Override
  public void start(Stage primaryStage) {
    
    // inicjalizujemy nasz kontroler, przekazując adres obecnego obiektu klasy GameView jako widok oraz nowy obiekt modelu
    // tworzenie tych trzech elementów powinno odbywać się zazwyczaj w oddzielnej klasie, aczkolwiek obecne rozwiązanie jest wygodne z punktu widzenia biblioteki JavaFX
    controller = new GameController(this, new GameModel());
    
    // metoda getBoardSide pozwala nam wykorzytać stałą zdefiniowaną w modelu
    // nie chcemy posiadać w naszym programie magicznych stałych, pojawiających się w wielu miejscach w kodzie (jaką przy grze w kółko i krzyżyk byłaby liczba 3)
    // co więcej przy obecnym rozwiązaniu możemy śmiało zmienić rozmiar planszy na większy, a wymagana zmiana musi zostać przeprowadzona tylko w jednym miejscu w kodzie
    board = new Circle[controller.getBoardSide()][controller.getBoardSide()];
    
    // tworzymy scenę i wypełniamy ją planszą do gry
    Scene scene = new Scene(prepareBoard(), 500, 500);
    scene.getStylesheets().add("checkersStyles.css");

    controller.start();
    
    // na koniec ustawiamy parametry obiektu klasy Stage
    primaryStage.setTitle("TicTacToe!");
    primaryStage.setScene(scene);
    primaryStage.setMinWidth(250);
    primaryStage.setMinHeight(250);
    primaryStage.show();
  }

  public GridPane prepareBoard() {
    // przygotowujemy naszą planszę
    GridPane root = new GridPane();
    for (int row = 0; row < controller.getBoardSide(); row++) {
      for (int col = 0; col < controller.getBoardSide(); col++) {
        // dla każdego wiersza i kolumny tworzymy nowy obiekt klasy StackPane
        root.add(getBoardSquare(row, col), col, row);
      }
    }
    // zapewniamy, że wszystkie wiersze skalują się wraz z rozmiarem sceny
    for (int col = 0; col < controller.getBoardSide(); col++) {
      ColumnConstraints constrains = new ColumnConstraints();
      constrains.setPercentWidth(100.0 / controller.getBoardSide());
      root.getColumnConstraints().add(constrains);
    }
    // zapewniamy, że wszystkie kolumny skalują się wraz z rozmiarem sceny
    for (int row = 0; row < controller.getBoardSide(); row++) {
      RowConstraints constrains = new RowConstraints();
      constrains.setPercentHeight(100.0 / controller.getBoardSide());
      root.getRowConstraints().add(constrains);
    }
    return root;
  }

  public StackPane getBoardSquare(int row, int col) {
    
    // tworzymy nowy obiekt klasy StackPane
    StackPane pane = new StackPane();
    
    // na dnie obiektu umieszczamy pojedynczy obiekt klasy BoardSquare, nadający kolor naszej planszy
    BoardSquare square = new BoardSquare((row + col) % 2 == 0 ? Color.LIGHTGREY: Color.DARKRED);
    pane.getChildren().add(square);
    
    // nad obiektem klasy BoardSquare umieszczamy etykietę, pozwalającą wyświetlić kółko lub krzyżyk
    board[row][col] = new Circle();
    board[row][col].setRadius(0);
    pane.getChildren().add(board[row][col]);
    
    // po kliknięciu na dowolny obiekt klasy StackPane, podejmijmy próbę wykonania ruchu
    pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent arg0) {
        controller.attemptMove(row, col);
      }
    });

    // Podświetlanie

      square.setOnMouseEntered(e -> square.getStyleClass().add("hoverBoardSquare"));
      square.setOnMouseExited(e -> square.getStyleClass().remove("hoverBoardSquare"));

    // na koniec zwróćmy obiekt klasy StackPane, pozwalając na dodanie go do komponentu GridPane
    return pane;
  }

  public void updateBoard(BoardState state) {
    // metoda wywoływana jest przez kontroler i pozwala zaktualizować obecny stan planszy
    for (int row = 0; row < controller.getBoardSide(); row++) {
      for (int col = 0; col < controller.getBoardSide(); col++) {
        if (state.getBoardSquare(row, col) == Symbol.BLACK) {
            board[row][col].setFill(Color.BLACK);
            board[row][col].setRadius(20);
        }
        else if (state.getBoardSquare(row, col) == Symbol.WHITE) {
          board[row][col].setRadius(20);
          board[row][col].setFill(Color.WHITE);
        }
        else
          board[row][col].setRadius(0);
      }
    }
  }

  public void showWinner(Symbol symbol) {
    // metoda wywoływana jest przez kontroler i pozwala pokazać podsumowanie wygranej gry oraz wymusić jej zakończenie 
    showDialogAndExit("Game won by " + symbol + "!");
  }

  public void showDraw() {
    // metoda wywoływana jest przez kontroler i pozwala pokazać podsumowanie zremisowanej gry oraz wymusić jej zakończenie
    showDialogAndExit("Game is drawn!");
  }

  public void showDialogAndExit(String message) {
    // metoda pokazuje okienko dialogowe z wiadomością otrzymaną jako argument
    Alert dialog = new Alert(AlertType.NONE, message, ButtonType.OK);
    dialog.setTitle("Game summary");
    Optional<ButtonType> result = dialog.showAndWait();
    // po wyświetleniu wiadomości gra jest zamykana
    if (result.isPresent() && result.get() == ButtonType.OK) {
      Platform.exit();
    }
  }
  
}