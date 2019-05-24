package cellular_automata.model.sower;

import cellular_automata.model.cell.IntCell;

import java.util.HashSet;

public class IntStateChanger {

    public IntCell[] sow(IntCell[] cells, int state) {
        for(int i=0; i<cells.length; i++){
            cells[i].state = state;
        }
        return cells;
    }

    public IntCell[] sow(IntCell[] cells, int from, int to, int state) {
        for(int i=from; i<to; i++){
            cells[i].state = state;
        }
        return cells;
    }

    public IntCell[] sow(IntCell[] cells, HashSet<Integer> indexes, int state) {
        for(int index : indexes){
            cells[index].state = state;
        }
        return cells;
    }

    public IntCell[] sow(IntCell[] cells, int[] indexes, int state) {
        for(int index : indexes){
            cells[index].state = state;
        }
        return cells;
    }

}
