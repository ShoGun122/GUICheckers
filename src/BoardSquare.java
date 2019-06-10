import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

// klasa BoardSquare zapewnia w prosty sposób możliwość ustawienia tła naszej planszy
public class BoardSquare extends Region {
  
  private Color color;
  
  public BoardSquare(Color defaultColor) {
    color = defaultColor;
    setColor(color);
    setPrefSize(200, 200);
  }
  
  public void highlight() {
    setColor(Color.YELLOW);
  }
  
  public void blacken() {

  }
  
  public void setColor(Color color) {
    BackgroundFill bgFill = new BackgroundFill(color, CornerRadii.EMPTY, new Insets(0));
    Background bg = new Background(bgFill);
    setBackground(bg);
  }
  
}