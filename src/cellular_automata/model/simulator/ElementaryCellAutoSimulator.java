package cellular_automata.model.simulator;

import cellular_automata.model.space.AbsorbSpace1D;
import cellular_automata.model.space.CellSpace1D;
import cellular_automata.model.space.PeriodicSpace1D;
import cellular_automata.model.utils.Decimal;
import cellular_automata.model.cell.BoolCell;

public class ElementaryCellAutoSimulator implements CellAutoSimulator {

    private CellSpace1D<BoolCell> space;
    private BoolCell[] cells;
    private BoolCell[] result;
    private String rule;

    public ElementaryCellAutoSimulator(BoolCell[] cells, int decimalRule, boolean periodic) {
        try {
            if(decimalRule > 255 || decimalRule < 0) {
                throw new Exception("Rule have to be an integer in range 0-255.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cells = cells;
        if(periodic){
            this.space = new PeriodicSpace1D<>(cells);
        }
        else {
            this.space = new AbsorbSpace1D<>(cells, new BoolCell(false));
        }
        this.result = new BoolCell[cells.length];
        for(int i=0; i<cells.length; i++){
            result[i] = new BoolCell(false);
        }
        this.rule = Decimal.toBinary(decimalRule, 8);
    }

    private void updateState(){
        for(int i=0; i<cells.length; i++){
            cells[i].state = result[i].state;
        }
    }

    @Override
    public void simulationStep() {
        for(int i=0; i<cells.length; i++){
            if(space.getCell(i-1).state==true && space.getCell(i).state==true && space.getCell(i+1).state==true){
                result[i].state = rule.charAt(0)=='1';
            }
            else if(space.getCell(i-1).state==true && space.getCell(i).state==true && space.getCell(i+1).state==false){
                result[i].state = rule.charAt(1)=='1';
            }
            else if(space.getCell(i-1).state==true && space.getCell(i).state==false && space.getCell(i+1).state==true){
                result[i].state = rule.charAt(2)=='1';
            }
            else if(space.getCell(i-1).state==true && space.getCell(i).state==false && space.getCell(i+1).state==false){
                result[i].state = rule.charAt(3)=='1';
            }
            else if(space.getCell(i-1).state==false && space.getCell(i).state==true && space.getCell(i+1).state==true){
                result[i].state = rule.charAt(4)=='1';
            }
            else if(space.getCell(i-1).state==false && space.getCell(i).state==true && space.getCell(i+1).state==false){
                result[i].state = rule.charAt(5)=='1';
            }
            else if(space.getCell(i-1).state==false && space.getCell(i).state==false && space.getCell(i+1).state==true){
                result[i].state = rule.charAt(6)=='1';
            }
            else if(space.getCell(i-1).state==false && space.getCell(i).state==false && space.getCell(i+1).state==false){
                result[i].state = rule.charAt(7)=='1';
            }
        }
        updateState();
    }
}
