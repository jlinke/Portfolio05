import java.util.*;


public class DominoGame {
    private HashMap<Integer, Player> players;
    private LinkedList<Domino> heapOfDominos;
    private DominoPool dominoPool;
    private ArrayList<Domino> setDominos;

    public DominoGame() {
        dominoPool = new DominoPool();
        players = new HashMap<>();
        heapOfDominos = (LinkedList<Domino>) this.dominoPool.provideShuffledDominoHeap();
        setDominos = new ArrayList<Domino>();
    }

    public HashMap<Integer, Player> getPlayers() {
        return players;
    }

    public LinkedList<Domino> getHeapOfDominos() {
        return heapOfDominos;
    }

    public void dispenseDominos() {
        int playerIndex = 0;
        for (int index = 0; index < (players.size() * 5); index++) {
            if (players.get(playerIndex).getMyDominos().size() < 5) {
                players.get(playerIndex).getMyDominos().put((index % 5), heapOfDominos.pollLast());
            } else if (playerIndex < players.size()) {
                index--;
                playerIndex++;
            }
        }
    }

    public void createPlayer(int numberOfHumanPlayers, int numberOfComputerPlayers, int numberOfHozardousComputerPlayer) {
        int numberOfPlayers = numberOfComputerPlayers + numberOfHumanPlayers + numberOfHozardousComputerPlayer;
        for (int index = 0; index < numberOfPlayers; index++) {
            if (numberOfHumanPlayers > index) {
                HumanPlayer humanPlayer = new HumanPlayer();
                players.put(index, humanPlayer);
            } else if (numberOfComputerPlayers + numberOfHumanPlayers > index) {
                ComputerPlayer computerPlayer = new ComputerPlayer();
                players.put(index, computerPlayer);
            } else {
                HazardousComputerPlayer hazardousComputerPlayer = new HazardousComputerPlayer();
                players.put(index, hazardousComputerPlayer);
            }
        }
    }

    private void roundCounter() {
        for (int index = 0; index <= players.size(); index++) {
            if (index == players.size() - 1) {
                index = 0;
            } else if (players.get(index).getMyDominos().size() == 0) {
                endDominoGame();
            } else {
                String[] possibleSets = getPossibleSets(players.get(index)).toArray(new String[0]);
                showSetDominos();
                int userChoice = players.get(index).moveDomino(possibleSets);

                if (userChoice == getPossibleSets(players.get(index)).size() && !heapOfDominos.isEmpty()) {

                    players.get(index).drawDomino(heapOfDominos.getLast());
                    heapOfDominos.removeLast();
                } else if (heapOfDominos.isEmpty() && userChoice == getPossibleSets(players.get(index)).size()) {
                    endDominoGame();
                } else {
                    Object i;
                    for (Map.Entry<Integer, Domino> entry : getPlayers().get(index).getMyDominos().entrySet()) {
                        if (entry.getValue().showValues().equals(possibleSets[userChoice])){
                        setDominos.add(entry.getValue());
                        players.get(index).getMyDominos().remove(entry);
                        }
                    }
                }
            }
        }
    }

    public void endDominoGame() {

    }

    public void startDominoGame() {
        if (!heapOfDominos.isEmpty()) {
            addDomino(heapOfDominos.getLast());
            heapOfDominos.removeLast();
            getSetDominos().size();
            roundCounter();
        } else {
            System.err.println("Bitte Dominopool erstellen!");
        }

    }

    public List<String> getPossibleSets(Player player) {
        List<String> possibleSets = new ArrayList<>();
        boolean isSetDominosSameSize = true;
        for (Domino setDomino : setDominos) {
            int indexCurrentSetDomino = 0;
            for (int dominoIndex = 0; dominoIndex < player.getMyDominos().size(); dominoIndex++) {
                if (isDominoFree(setDominos.get(indexCurrentSetDomino)) <= 2) {

                    if (isDominoFree(setDominos.get(indexCurrentSetDomino)) == 2) {
                        possibleSets.add(player.getMyDominos().get(dominoIndex).showValues() + "links anlegen");
                        possibleSets.add(player.getMyDominos().get(dominoIndex).showValues() + "rechts anlegen");

                    } else if (isDominoFree(setDominos.get(indexCurrentSetDomino)) == 0 && isDominoSideEqual(setDominos.get(indexCurrentSetDomino).getRight(), player.getMyDominos().get(dominoIndex).getLeft())) {
                        possibleSets.add(player.getMyDominos().get(dominoIndex).showValues());

                    } else if (isDominoFree(setDominos.get(indexCurrentSetDomino)) == 1 && isDominoSideEqual(setDominos.get(indexCurrentSetDomino).getLeft(), player.getMyDominos().get(dominoIndex).getRight())) {
                        possibleSets.add(player.getMyDominos().get(dominoIndex).showValues());
                    }
                }
            }
        }
        possibleSets.add("ziehen");
        return possibleSets;
    }

    private void showSetDominos() {
        if (setDominos.size() == 1) {
            setDominos.get(0).showValues();
        } else {
            for (Domino domino : setDominos) {
                System.out.print("Gelegte Dominos: ");
                System.out.print(domino.showValues());
                System.out.println();
            }
        }

    }

    public ArrayList<Domino> getSetDominos() {
        return setDominos;
    }

    public void addDomino(Domino domino) {
        this.setDominos.add(domino);
    }

    private int isDominoFree(Domino domino) {
        if (domino.getRightDomino() == null) {
            return 0;
        } else if (domino.getLeftDomino() == null) {
            return 1;
        } else if (domino.getLeftDomino() == null && domino.getRightDomino() == null) {
            return 2;
        } else {
            return 3;
        }
    }

    private boolean isDominoSideEqual(int side1, int site2) {
        if (side1 == site2) {
            return true;
        } else {
            return false;
        }
    }


    private void connectDominos(Domino domino1, Domino domino2) {
        if (isDominoFree(domino1) == 0) {
            domino1.setRightDomino(domino2);
        } else if (isDominoFree(domino1) == 1) {
            domino1.setRightDomino(domino2);
        } else if (isDominoFree(domino1) == 2) {
            UserDialog userDialog = new UserDialog();
            int userChoise = userDialog.getUserInput("An welche Seite ?", "rechts ", "links ");
            if (userChoise == 0) {
                domino1.setRightDomino(domino2);
            } else {
                domino1.setLeftDomino(domino2);
            }
        }

    }
}

