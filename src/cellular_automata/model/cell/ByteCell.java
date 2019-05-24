package cellular_automata.model.cell;

public class ByteCell extends Cell {
    public byte state;

    public ByteCell() {
        this.state=0;
    }

    public ByteCell(byte state) {
        this.state=state;
    }

    @Override
    public ByteCell clone() {
        return new ByteCell(state);
    }
}
