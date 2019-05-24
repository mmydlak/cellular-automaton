package cellular_automata.model.space;

import cellular_automata.model.cell.Cell;

public abstract class CellSpace1D <T extends Cell> {
    protected T[] cells;

    public void setCells(T[] cells){
        this.cells = cells;
    }

    public abstract T getCell(int index);
}
