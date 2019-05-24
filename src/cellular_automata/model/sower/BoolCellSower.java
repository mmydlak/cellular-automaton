package cellular_automata.model.sower;

import cellular_automata.model.cell.BoolCell;

public class BoolCellSower {

    static public BoolCell[] sow(int cellsNumber, BoolCell cell) {
        BoolCell[] cells = new BoolCell[cellsNumber];
        for(int i=0; i<cellsNumber; i++){
            cells[i] = cell.clone();
        }
        return cells;
    }

    static public BoolCell[][] sow(int cellsInWidth, int cellsInHeight, BoolCell cell) {
        BoolCell[][] cells = new BoolCell[cellsInHeight][cellsInWidth];
        for(int i=0; i<cellsInHeight; i++){
            for(int j=0; j<cellsInWidth; j++) {
                cells[i][j] = cell.clone();
            }
        }
        return cells;
    }

//    public BoolCell[] sow(BoolCell[] cells, int from, int to, BoolCell cell) {
//        for(int i=from; i<to; i++){
//            cells[i] = cell.clone();
//        }
//        return cells;
//    }
//
//    public BoolCell[] sow(BoolCell[] cells, int[] indexes, BoolCell cell) {
//        for(int index : indexes){
//            cells[index] = cell.clone();
//        }
//        return cells;
//    }
//
//    public BoolCell[][] sow(int cellsInRows, int cellsInColumns, BoolCell cell) {
//        BoolCell[][] cells = new BoolCell[cellsInRows][cellsInColumns];
//        for(int i=0; i<cellsInRows; i++){
//            for(int j=0; j<cellsInColumns; j++) {
//                cells[i][j] = cell.clone();
//            }
//        }
//        return cells;
//    }
//
//    public BoolCell[][] sow(BoolCell[][] cells, int from, int to, BoolCell cell) {
//        for(int i=from; i<to; i++){
//            cells[i] = cell.clone();
//        }
//        return cells;
//    }
//
//    public BoolCell[] sow(BoolCell[] cells, int[] indexes, BoolCell cell) {
//        for(int index : indexes){
//            cells[index] = cell.clone();
//        }
//        return cells;
//    }
}
