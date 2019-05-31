import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
        Scene scene = new Scene (getBoard(),600 ,600 ,Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane getBoard()
    {

        GridPane board = new GridPane();
        //  i = row
        //  j = column

        for(int row = 0; row <10 ; row++)
        {
            if(row%2==0)
            {
                for (int col = 0; col < 10; col++)
                {
                    if (col % 2 == 0)
                    {
                        BoardSquare square = new BoardSquare(Color.BLACK);
                        board.add(square,row,col);
                        square.setOnMouseEntered(e -> square.highlight());
                        square.setOnMouseExited(e -> square.blacken());
                    } else
                    {
                        BoardSquare square = new BoardSquare(Color.WHITE);
                        board.add(square,row,col);
                        square.setOnMouseEntered(e -> square.highlight());
                        square.setOnMouseExited(e -> square.blacken());
                    }
                }
            }
            else
            {
                for(int col =0; col<10; col ++)
                {
                    if(col%2!=0)
                    {
                        BoardSquare square=new BoardSquare(Color.BLACK);
                        board.add(square,row,col);
                        square.setOnMouseEntered(e -> square.highlight());
                        square.setOnMouseExited(e -> square.blacken());
                    }
                    else
                    {
                        BoardSquare square=new BoardSquare(Color.WHITE);
                        board.add(square,row,col);
                        square.setOnMouseEntered(e -> square.highlight());
                        square.setOnMouseExited(e -> square.blacken());
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

}
