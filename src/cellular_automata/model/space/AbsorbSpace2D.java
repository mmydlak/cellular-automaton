package cellular_automata.model.space;

import cellular_automata.model.cell.Cell;

public class AbsorbSpace2D<T extends Cell> extends CellSpace2D<T> {
    private T absorber;

    public AbsorbSpace2D(T[][] cells, T absorber){
        setCells(cells);
        this.absorber = absorber;
    }

    public void setAbsorber(T absorber) {
        this.absorber = absorber;
    }

    @Override
    public T getCell(int rIndex, int cIndex) {
        return (rIndex>-1)&&(rIndex<cells.length)&&(cIndex>-1)&&(cIndex<cells[0].length) ? cells[rIndex][cIndex] : absorber;
    }
}
