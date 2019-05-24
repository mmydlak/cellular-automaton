package cellular_automata.model.space;

import cellular_automata.model.cell.Cell;

public class AbsorbSpace1D<T extends Cell> extends CellSpace1D<T>{

    private T absorber;

    public AbsorbSpace1D(T[] cells, T absorber){
        setCells(cells);
        this.absorber = absorber;
    }

    public void setAbsorber(T absorber) {
        this.absorber = absorber;
    }

    @Override
    public T getCell(int index) {
        return (index>-1)&&(index<cells.length) ? cells[index] : absorber;
    }
}
