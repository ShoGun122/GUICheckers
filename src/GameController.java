public class GameController {

  // kontroler musi posiadać dostęp zarówno do modelu, jak i do widoku
  private GameView view;
  private GameModel model;

  // konstruktor pozwala na inicjalizację obu pól klasy
  public GameController(GameView view, GameModel model) {
    this.view = view;
    this.model = model;
  }
  
  // czasami niektóre parametry gry wpływają na widok
  // jednocześnie nie chcielibyśmy, aby widok posiadał bezpośredni dostęp do modelu
  // w takich sytuacjach często tworzy się w kontrolerze metody, których jedynym zadaniem jest wywołanie metody modelu
  public int getBoardSide() {
    return model.getBoardSide();
  }

  public void start() {
    model.initBoard();
    view.updateBoard(model.getBoardState());
  }

  // cała struktura naszej gry zawarta jest w metodzie attemptMove, gdzie widok sygnalizuje chęć wykonania ruchu przez gracza
  // sensem podziału na model, widok i kontroler jest uczynienie samej logiki tak czytelnej, jak tylko jest to możliwe
  // w rezultacie kod podanej metody w zasadzie nie wymaga żadnego komentarza
  public void attemptMove(int row, int col) {
    if (model.isMoveAllowed(row, col)) {
      model.makeMove(row, col);
      view.updateBoard(model.getBoardState());
//      if (model.isGameDrawn()) {
//        view.showDraw();
//      }
//      else if (model.isGameWon()) {
//        view.showWinner(model.getWinner());
//      }
    }
  }

}
