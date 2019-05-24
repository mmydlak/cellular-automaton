package cellular_automata.model.space;

import cellular_automata.model.cell.Cell;

public class PeriodicSpace2D<T extends Cell> extends CellSpace2D<T> {
    public PeriodicSpace2D(T[][] cells) {
        setCells(cells);
    }

    @Override
    public T getCell(int rIndex, int cIndex) {
        return cells[(cells.length+rIndex)%cells.length][(cells[0].length+cIndex)%cells[0].length];
    }
}
