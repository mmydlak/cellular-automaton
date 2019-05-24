package cellular_automata.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import cellular_automata.model.cell.BoolCell;

public class CAVisualization {

    static public void drawCells(BoolCell[] space, Canvas canvas, boolean state, Color color, double startX, double startY){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int numCells = space.length;
        for (int i = 0; i < numCells; i++) {
            if (space[i].state == state) {
                gc.setFill(color);
                gc.fillRect(startX+((i)*canvas.getWidth()/numCells),
                            startY,
                            canvas.getWidth() / numCells,
                            canvas.getWidth() / numCells);
            }
        }
    }

    static public void drawCells(BoolCell[][] space, Canvas canvas, boolean state, Color color, double startX, double startY){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int cellsInHeight = space.length;
        int cellsInWidth = space[0].length;
        gc.setFill(color);
        double xStart, yStart, wSize, hSize;
        for (int i = 0; i < cellsInHeight; i++) {
            for (int j = 0; j < cellsInWidth; j++) {
                if (space[i][j].state == state) {
                    xStart = startX + ((j) * (canvas.getWidth() / cellsInWidth));
                    yStart = startY + ((i) * (canvas.getHeight() / cellsInHeight));
                    wSize = canvas.getWidth()/cellsInWidth;
                    hSize = canvas.getHeight()/cellsInHeight;
                    gc.fillRect(xStart,yStart,wSize,hSize);
                }
            }
        }
    }

}
