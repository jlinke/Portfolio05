import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HazardousComputerPlayer extends Player {
    @Override
    public HashMap<Integer, Domino> getMyDominos() {
        return super.getMyDominos();
    }

    @Override
    public int moveDomino(String[] possibleMoves) {
        int maxBound = possibleMoves.length;
        Random random = new Random();
        return random.nextInt(maxBound);
    }
}
