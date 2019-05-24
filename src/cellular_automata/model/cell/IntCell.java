package cellular_automata.model.cell;

public class IntCell extends Cell {
    public int state;

    public IntCell() {
        this.state=0;
    }

    public IntCell(int state) {
        this.state=state;
    }

    @Override
    public IntCell clone() {
        return new IntCell(state);
    }
}
