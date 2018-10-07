import java.util.HashMap;

public class Player {
    private HashMap<Integer, Domino> myDominos = new HashMap<>();
    private String name;



    public void drawDomino(Domino domino) {
        myDominos.put(myDominos.size(),domino);
    }

    public int moveDomino(String[] possibleMoves) {
        UserDialog userDialog = new UserDialog();
        showMyDominos();
        return userDialog.getUserInput("Auswahlmöglichkeiten", possibleMoves);
    }

    public HashMap<Integer, Domino> getMyDominos() {
        return myDominos;
    }

    private void showMyDominos() {
        System.out.println("Ihre Steine:");
        for (int index = 0; index < myDominos.size();index++){
            System.out.print(myDominos.get(index).showValues());
        }
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}