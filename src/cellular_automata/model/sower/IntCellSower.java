package cellular_automata.model.sower;


import cellular_automata.model.cell.IntCell;

public class IntCellSower {

    public IntCell[] sow(int cellsNumber, IntCell cell) {
        IntCell[] cells = new IntCell[cellsNumber];
        for(int i=0; i<cellsNumber; i++){
            cells[i] = cell.clone();
        }
        return cells;
    }

//    public IntCell[] sow(IntCell[] cells, int from, int to, IntCell cell) {
//        for(int i=from; i<to; i++){
//            cells[i] = cell.clone();
//        }
//        return cells;
//    }
//
//    public IntCell[] sow(IntCell[] cells, int[] indexes, IntCell cell) {
//        for(int index : indexes){
//            cells[index] = cell.clone();
//        }
//        return cells;
//    }

}
