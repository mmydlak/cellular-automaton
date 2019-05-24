package cellular_automata.model.sower;

import cellular_automata.model.cell.BoolCell;
import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BoolStateChanger {

    public BoolCell[] sow(BoolCell[] cells, boolean state) {
        for(int i=0; i<cells.length; i++){
            cells[i].state = state;
        }
        return cells;
    }

    public BoolCell[] sow(BoolCell[] cells, int from, int to, boolean state) {
        for(int i=from; i<to; i++){
            cells[i].state = state;
        }
        return cells;
    }

    public BoolCell[] sow(BoolCell[] cells, int[] indexes, boolean state) {
        for(int index : indexes){
            cells[index].state = state;
        }
        return cells;
    }

    public BoolCell[] sow(BoolCell[] cells, Set<Integer> indexes, boolean state) {
        for(int index : indexes){
            cells[index].state = state;
        }
        return cells;
    }

    public BoolCell[] sowByKeys(BoolCell[] cells, Set<Pair<Integer,Integer>> indexes, boolean state) {
        for(Pair<Integer,Integer> pair : indexes){
            cells[pair.getKey()].state = state;
        }
        return cells;
    }

    //===================================================================================================

    public BoolCell[][] sowAll(BoolCell[][] cells, boolean state) {
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells[0].length; j++) {
                cells[i][j].state = state;
            }
        }
        return cells;
    }

    public BoolCell[][] sowIndexes(BoolCell[][] cells, Set<Pair<Integer,Integer>> indexes, boolean state) {
        for(Pair<Integer,Integer> pair : indexes){
            cells[pair.getValue()][pair.getKey()].state = state;
        }
        return cells;
    }

    public BoolCell[][] sowRandom(BoolCell[][] cells, int cellsNumber, boolean state) {
        int r, c;
        Random random = new Random();
        if(cellsNumber<(cells.length*cells[0].length)*2/3) {
            long czasRozpoczecia = System.currentTimeMillis();
            List<BoolCell> choosen = new ArrayList<>();
            for (int i = 0; i < cellsNumber; i++) {
                c = random.nextInt(cells[0].length);
                r = random.nextInt(cells.length);
                if(choosen.contains(cells[r][c])){
                    i--;
                }
                else {
                    cells[r][c].state = state;
                    choosen.add(cells[r][c]);
                }
            }
            long czasZakonczenia = System.currentTimeMillis();

            long czasTrwania = czasZakonczenia - czasRozpoczecia;

            System.out.println("Czas trwania: " + czasTrwania);
            System.out.println("losuje na oslep");
        }
        else {
            long czasRozpoczecia = System.currentTimeMillis();

            List<BoolCell> list = new ArrayList<>();
            BoolCell considered;
            for(int i=0; i<cells.length; i++){
                for(int j=0; j<cells[0].length; j++) {
                    list.add(cells[i][j]);
                }
            }
            while(cellsNumber>0 && list.size()>0){
                considered = list.get(random.nextInt(list.size()));
                considered.state=state;
                list.remove(considered);
                cellsNumber--;
            }
            long czasZakonczenia = System.currentTimeMillis();

            long czasTrwania = czasZakonczenia - czasRozpoczecia;

            System.out.println("Czas trwania: " + czasTrwania);
            System.out.println("losuje ze zmniej sie listy");
        }
        return cells;
    }

    public BoolCell[][] sowHomogeneous(BoolCell[][] cells, int cellsInRow, int cellsInColumn, boolean state) {
        double drow = (double)cells.length/cellsInRow;
        double dcolumn = (double)cells[0].length/cellsInColumn;
        for(int i=(int)Math.floor(drow/2); i<cells.length; i+=Math.ceil(drow)){
            for(int j=(int)Math.floor(dcolumn/2); j<cells[0].length; j+=Math.ceil(dcolumn)) {
                cells[i][j].state=state;
            }
        }
        return cells;
    }

    public BoolCell[][] sowWithRadius(BoolCell[][] cells, int toChange, int radius, boolean state) {
        List<Point> list = new ArrayList<>();
        Point[][] points = new Point[cells.length][cells[0].length];
        for(int i=0; i<cells.length; i++){
            for(int j=0; j<cells[0].length; j++) {
                points[i][j] = new Point(i,j);
                list.add(points[i][j]);
            }
        }

        Point point;
        int r, c;
        Random random = new Random();
        while(list.size()>0 && toChange>0){
            point = list.get(random.nextInt(list.size()));
            r = point.x;
            c = point.y;
            cells[r][c].state = state;
            for(int i=(r-radius); i<=(r+radius); i++) {
                for (int j=(c-radius); j<=(c+radius); j++) {
                    if(i<0 || j<0 || i>=cells.length || j>=cells[0].length){
                        continue;
                    }
                    if(Math.sqrt((r-i)*(r-i)+(c-j)*(c-j))<=radius){
                        list.remove(points[i][j]);
                    }
                }
            }
            list.remove(point);
            toChange--;
        }
        
        return cells;
    }


}
