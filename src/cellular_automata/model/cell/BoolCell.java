package cellular_automata.model.cell;

public class BoolCell extends Cell {
    public boolean state;

    public BoolCell() {
        this.state=false;
    }

    public BoolCell(boolean state) {
        this.state=state;
    }

    @Override
    public BoolCell clone() {
        return new BoolCell(state);
    }
}
