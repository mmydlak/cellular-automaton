package cellular_automata.model.space;

import cellular_automata.model.cell.Cell;

public class PeriodicSpace1D<T extends Cell> extends CellSpace1D<T> {

    public PeriodicSpace1D(T[] cells) {
        setCells(cells);
    }

    @Override
    public T getCell(int index) {
        return cells[(cells.length+index)%cells.length];
    }
}
