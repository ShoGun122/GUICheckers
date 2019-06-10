public class GameModel {
  
  // stałe, opisujące parametry naszej gry (dzięki czemu łatwo można edytować rozmiar planszy i liczbę symboli wymaganych do wygranej)
  private final int BOARD_SIDE = 10;
  private final int SYMBOL_COUNT_TO_WIN = 3;

  // pole opisujące symbol dla którego wykonywany jest aktualny ruch
  private Symbol symbol;
  
  // tablica opisująca aktualny stan planszy
  private Symbol[][] board;

  private boolean selection = false;
  private int startRow, startCol;

  public GameModel() {
    // pierwszym symbolem w naszej grze jest zawsze kółko
    symbol = Symbol.WHITE;
    // utwórzmy również nową tablicę, przechowującą stan planszy
    board = new Symbol[BOARD_SIDE][BOARD_SIDE];
    // wywołajmy metodę, oznaczającą wszystkie elementy planszy jako puste
    clearBoard();
  }
  
  // metoda pozwalająca uzyskać dostęp do liczby wierszy i kolumn
  public int getBoardSide() {
    return BOARD_SIDE;
  }
  
  // metoda, oznaczająca wszystkie elementy planszy jako puste
  public void clearBoard() {
    for (int row = 0; row < BOARD_SIDE; row++) {
      for (int col = 0; col < BOARD_SIDE; col++) {
        board[row][col] = Symbol.EMPTY;
      }
    }
  }

  public void initBoard() {
    clearBoard();
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < BOARD_SIDE; col++) {
        if ((row + col) % 2 != 0)
        board[row][col] = Symbol.BLACK;
      }
    }
    for (int row = BOARD_SIDE - 4; row < BOARD_SIDE; row++) {
      for (int col = 0; col < BOARD_SIDE; col++) {
        if ((row + col) % 2 != 0)
          board[row][col] = Symbol.WHITE;
      }
    }
  }

  // metoda, pozwalająca sprawdzić czy dany ruch jest dopuszczalny
  public boolean isMoveAllowed(int row, int col) {
    if (selection) {
      // if ()
      //
      return true;
    }
    else {
      selection = board[row][col] == symbol;
      startRow = row;
      startCol = col;
      return false;
    }
  }

  // metoda wykonująca żądany ruch i następnie zmieniająca symbol na przeciwny
  public void makeMove(int row, int col) {
    selection = false;
    board[startRow][startCol] = Symbol.EMPTY;
    board[row][col] = symbol;
    switchSymbol();
  }

  // metoda zmieniająca obecny symbol na przeciwny
  public void switchSymbol() {
    if (symbol == Symbol.WHITE)
      symbol = Symbol.BLACK;
    else
      symbol = Symbol.WHITE;
  }

  // metoda zwracająca obecny stan planszy
  public BoardState getBoardState() {
    return new BoardState(board);
  }
  
  // metoda sprawdzająca czy istnieje możliwość wykonania jeszcze jakiegoś ruchu
  public boolean isGameDrawn() {
    for (int row = 0; row < BOARD_SIDE; row++) {
      for (int col = 0; col < BOARD_SIDE; col++) {
        if (board[row][col] == Symbol.EMPTY)
          return false;
      }
    }
    return true;
  }

  // metoda sprawdzająca czy któryś z graczy wygrał
  public boolean isGameWon() {
    return isGameWon(Symbol.WHITE) || isGameWon(Symbol.BLACK);
  }

  // metoda zwracająca zwycięskiego gracza
  public Symbol getWinner() {
    if (isGameWon(Symbol.WHITE))
      return Symbol.WHITE;
    else if (isGameWon(Symbol.BLACK))
      return Symbol.BLACK;
    else
      return Symbol.EMPTY;
  }

  // metoda sprawdzająca dla danego symbolu czy gracz wygrał
  public boolean isGameWon(Symbol symbol) {
    // sprawdzenie czy w którymś wierszu jest odpowiednia liczba symboli
    for (int row = 0; row < BOARD_SIDE; row++) {
      int counter = 0;
      for (int col = 0; col < BOARD_SIDE; col++) {
        if (board[row][col] == symbol) {
          counter++;
          if (counter == SYMBOL_COUNT_TO_WIN) {
            return true;
          }
        }
        else {
          counter = 0;
        }
      }
    }
    // sprawdzenie czy w którejś kolumnie jest odpowiednia liczba symboli
    for (int col = 0; col < BOARD_SIDE; col++) {
      int counter = 0;
      for (int row = 0; row < BOARD_SIDE; row++) {
        if (board[row][col] == symbol) {
          counter++;
          if (counter == SYMBOL_COUNT_TO_WIN) {
            return true;
          }
        }
        else {
          counter = 0;
        }
      }
    }
    // sprawdzenie czy na pierwszej przekątnej jest odpowiednia liczba symboli
    int counter = 0;
    for (int idx = 0; idx < BOARD_SIDE; idx++) {
      if (board[idx][idx] == symbol) {
        counter++;
        if (counter == SYMBOL_COUNT_TO_WIN) {
          return true;
        }
      }
      else {
        counter = 0;
      }      
    }
    // sprawdzenie czy na drugiej przekątnej jest odpowiednia liczba symboli
    counter = 0;
    for (int idx = 0; idx < BOARD_SIDE; idx++) {
      if (board[idx][BOARD_SIDE - (idx + 1)] == symbol) {
        counter++;
        if (counter == SYMBOL_COUNT_TO_WIN) {
          return true;
        }
      }
      else {
        counter = 0;
      }      
    }
    // w przeciwnym przypadku w żadnym kierunku nie ma odpwoiedniej liczby symboli (czyli nikt jeszcze nie wygrał)
    return false;
  }

}
