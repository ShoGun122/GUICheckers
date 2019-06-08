import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.*;
import javafx.scene.paint.Color;
import java.awt.*;

import java.awt.Color.*;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        StackPane stPane = new StackPane();
        GridPane gpPane = new GridPane();
        Scene scene = new Scene (getBoard(),600 ,600 ,Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane getBoard()
    {

        GridPane board = new GridPane();

        for(int row = 0; row <10 ; row++)
        {
            if(row%2==0)
            {
                for (int col = 0; col < 10; col++)
                {
                    if (col % 2 == 0)
                    {
                        createRedField(board,row,col);
                    } else
                    {
                        createGreyField(board,row,col);
                    }
                }
            }
            else
            {
                for(int col =0; col<10; col ++)
                {
                    if(col%2!=0)
                    {
                        createRedField(board,row,col);
                    }
                    else
                    {
                        createGreyField(board,row,col);
                    }
                }
            }
        }
        for(int row = 0; row<10; row++)
        {
            RowConstraints contstraint = new RowConstraints();
            contstraint.setPercentHeight(20);
            board.getRowConstraints().add(contstraint);
        }
        for(int col = 0; col<10; col++)
        {
            ColumnConstraints colConstraint = new ColumnConstraints();
            colConstraint.setPercentWidth(20);
            board.getColumnConstraints().add(colConstraint);
        }
        return board;
    }
    // == CREATING RED BOARD SQUARE
    public void createRedField ( GridPane board, int row, int col)
    {
        BoardSquare square=new BoardSquare(Color.DARKRED);
        StackPane stPane = getBoardSquare(Color.DARKRED);
        board.add(stPane,row,col);
        square.setOnMouseEntered(e -> square.highlight());
        square.setOnMouseExited(e -> square.blacken());
    }
    // == CREATING GREY BOARD SQUARE
    public  void createGreyField (GridPane board, int row, int col)
    {
        BoardSquare square = new BoardSquare(Color.LIGHTGREY);
        StackPane stPane = getBoardSquare(Color.LIGHTGREY);
        board.add(stPane,row,col);
        square.setOnMouseEntered(e -> square.highlight());
        square.setOnMouseExited(e -> square.blacken());
    }
    public StackPane getBoardSquare(Color color){
        BoardSquare square=new BoardSquare(color);
        StackPane stackPane = new StackPane(square);
        stackPane.setOnMouseEntered(e -> square.highlight());
        stackPane.setOnMouseExited(e -> square.blacken());

        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            Circle circle = new Circle();
            {
                NumberBinding radiusProperty = Bindings.when(square.widthProperty().greaterThan(square.heightProperty())).then(square.heightProperty().subtract(12).divide(2)).otherwise(square.widthProperty().subtract(12).divide(2));
                circle.radiusProperty().bind(radiusProperty);
                circle.setFill(Color.BEIGE);
            }
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(stackPane.getChildren().contains(circle)){
                    stackPane.getChildren().remove(circle);
                }
                else{
                    stackPane.getChildren().add(circle);
                }


            }
        });
        return stackPane;
    }
}




