// klasa służąca do przekazania obecnego stanu planszy z modelu do widoku
// zastosowanie podanej klasy nie pozwala na modyfikację wewnętrznej tablicy, dzięki czemu widok w żaden sposób nie może zmodyfikować modelu 
public class BoardState {

  private Symbol[][] board;
  
  public BoardState(Symbol[][] board) {
    this.board = board;
  }
  
  public Symbol getBoardSquare(int row, int col) {
    return board[row][col];
  }
  
}
