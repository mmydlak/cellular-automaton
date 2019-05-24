package cellular_automata.model.sower;

import cellular_automata.model.cell.ByteCell;

public class ByteCellSower {

    public ByteCell[] sow(int cellsNumber, ByteCell cell) {
        ByteCell[] cells = new ByteCell[cellsNumber];
        for(int i=0; i<cellsNumber; i++){
            cells[i] = cell.clone();
        }
        return cells;
    }

//    public ByteCell[] sow(ByteCell[] cells, int from, int to, ByteCell cell) {
//        for(int i=from; i<to; i++){
//            cells[i] = cell.clone();
//        }
//        return cells;
//    }
//
//    public ByteCell[] sow(ByteCell[] cells, int[] indexes, ByteCell cell) {
//        for(int index : indexes){
//            cells[index] = cell.clone();
//        }
//        return cells;
//    }
}
