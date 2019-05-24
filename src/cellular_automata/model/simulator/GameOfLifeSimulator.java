package cellular_automata.model.simulator;

import cellular_automata.model.cell.BoolCell;
import cellular_automata.model.space.AbsorbSpace2D;
import cellular_automata.model.space.CellSpace2D;
import cellular_automata.model.space.PeriodicSpace2D;

public class GameOfLifeSimulator implements CellAutoSimulator {
    private CellSpace2D<BoolCell> space;
    private BoolCell[][] cells;
    private BoolCell[][] result;
    private String rule;

    public GameOfLifeSimulator(BoolCell[][] cells, boolean periodic) {
        this.cells = cells;
        if(periodic){
            this.space = new PeriodicSpace2D<>(cells);
        }
        else {
            this.space = new AbsorbSpace2D<>(cells, new BoolCell(false));
        }
        this.result = new BoolCell[cells.length][cells[0].length];
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells[0].length; j++){
                result[i][j] = new BoolCell(false);
            }
        }
    }

    private void updateState(){
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells[0].length; j++){
                cells[i][j].state = result[i][j].state;
            }
        }
    }

    @Override
    public void simulationStep() {
        int neighbours=0;
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells[0].length; j++){
                for(int k=i-1; k<=i+1; k++){
                    for(int l=j-1; l<=j+1; l++){
                        if(space.getCell(k,l).state==true){
                            if(k!=i || l!=j) {
                                neighbours++;
                            }
                        }
                    }
                }
                if(cells[i][j].state==false){
                    if(neighbours==3){
                        result[i][j].state=true;
                    }
                    else {
                        result[i][j].state=false;
                    }
                }
                else {
                    if(neighbours>3 || neighbours<2){
                        result[i][j].state=false;
                    }
                    else {
                        result[i][j].state=true;
                    }
                }
                neighbours=0;
            }
        }
        updateState();
    }
}
