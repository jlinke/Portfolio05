public class Domino {

    private int left;
    private int right;
    private Domino leftDomino;
    private Domino rightDomino;

    public Domino(int left, int right) {
        this.left  = left;
        this.right = right;
    }

    public String showValues(){
        String dominoValues = "[" + left + "|" + right + "]";
        return dominoValues;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public Domino getLeftDomino() {
        return leftDomino;
    }

    public Domino getRightDomino() {
        return rightDomino;
    }

    public void setRightDomino(Domino rightDomino) {
        this.rightDomino = rightDomino;
    }

    public void setLeftDomino(Domino leftDomino) {
        this.leftDomino = leftDomino;
    }
}
