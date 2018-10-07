import java.util.Random;

public class TestKram {
    public static void main(String[] args){

        DominoGame dominoGame = new DominoGame();

        dominoGame.createPlayer(1,1,1);

        dominoGame.getHeapOfDominos();

        dominoGame.dispenseDominos();

        dominoGame.startDominoGame();


    }
}
