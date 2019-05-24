package cellular_automata.model.sower;

import cellular_automata.model.cell.ByteCell;

import java.util.HashSet;

public class ByteStateChanger {

    public ByteCell[] sow(ByteCell[] cells, byte state) {
        for(int i=0; i<cells.length; i++){
            cells[i].state = state;
        }
        return cells;
    }

    public ByteCell[] sow(ByteCell[] cells, int from, int to, byte state) {
        for(int i=from; i<to; i++){
            cells[i].state = state;
        }
        return cells;
    }

    public ByteCell[] sow(ByteCell[] cells, HashSet<Integer> indexes, byte state) {
        for(int index : indexes){
            cells[index].state = state;
        }
        return cells;
    }

    public ByteCell[] sow(ByteCell[] cells, int[] indexes, byte state) {
        for(int index : indexes){
            cells[index].state = state;
        }
        return cells;
    }
}
