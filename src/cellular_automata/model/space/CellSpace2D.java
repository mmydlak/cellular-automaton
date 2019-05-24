package cellular_automata.model.space;

import cellular_automata.model.cell.Cell;

public abstract class CellSpace2D<T extends Cell> {
    protected T[][] cells;

    public void setCells(T[][] cells){
        this.cells = cells;
    }

    public abstract T getCell(int rIndex, int cIndex);
}
