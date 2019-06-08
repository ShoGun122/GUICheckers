import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static javafx.beans.binding.Bindings.greaterThan;

public class BoardSquare extends Region {
    private Color color;
    private final int ROW_COUNT=10;
    private final int COL_COUNT=10;
    public BoardSquare(Color defaultColor) {
        color = defaultColor;
        setColor(color);
        setPrefSize(200, 200);
    }
    public void highlight() {
        setColor(Color.YELLOW);
    }
    public void blacken() {
        setColor(color);
    }
    public void setColor(Color color) {
        BackgroundFill bgFill = new BackgroundFill(color, CornerRadii.EMPTY, new Insets(0));
        Background bg = new Background(bgFill);
        setBackground(bg);
    }}
    /*
    public GridPane getBoard(){
        GridPane board=new GridPane();
        for(int row=0; row<ROW_COUNT;row++){
            for(int col=0;col<COL_COUNT;col++){
                board.add(getBoardSquare(),row,col);
            }
        }
        for(int row=0;row<ROW_COUNT;row++){
            RowConstraints constrains=new RowConstraints();
            constrains.setPercentHeight(20);;
            board.getRowConstraints().add(constrains);
        }
        for(int col=0;col<COL_COUNT;col++){
            ColumnConstraints constrains=new ColumnConstraints();
            constrains.setPercentWidth(20);
            board.getColumnConstraints().add(constrains);
        }
        return board;
    }
    */
