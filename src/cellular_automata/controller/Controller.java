package cellular_automata.controller;

import cellular_automata.Main;
import cellular_automata.model.cell.BoolCell;
import cellular_automata.model.component.IntTextField;
import cellular_automata.model.simulator.CellAutoSimulator;
import cellular_automata.model.simulator.ElementaryCellAutoSimulator;
import cellular_automata.model.simulator.GameOfLifeSimulator;
import cellular_automata.model.sower.BoolCellSower;
import cellular_automata.model.sower.BoolStateChanger;
import cellular_automata.model.utils.PositionUtils;
import cellular_automata.model.utils.RangeCorrector;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import cellular_automata.model.*;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class Controller {

    private Main mainApp;
    private double canvasWidth;
    private double canvasHeight;
    private double minCanvasWidth;
    private double maxCanvasWidth;
    private double minCanvasHeight;
    private double maxCanvasHeight;
    private GraphicsContext gc;
    private GraphicsContext gcStroke;
    //private Set<Integer> initialXIndexes;
    private Set<Pair<Integer,Integer>> initialXYIndexes;
    private int simulationStep;
    private int cellSize;
    private int rule;
    private int toSowInRows;
    private int toSowInColumns;
    private int toSow;
    private int radiusOfSowing;
    //private ElementaryCellSpace space;
    private CellAutoSimulator simul;
    private BoolCell[] cells;
    private BoolCell[][] cells2D;
    private TitledPane rulePane;
    private TitledPane sowPane;
    private TitledPane structuresPane;
    private boolean running;
    private final String elementaryCA = "Elementary CA";
    private final String gameOfLife = "Game of Life";
    private final String homogeneousSow = "Homogeneous sow";
    private final String randomSow = "Random sow";
    private final String radiusSow = "Radius sow";
    private final String cellStructure = "Cell";
    private final String beehiveStructure = "Beehive";
    private final String blinkerStructure = "Blinker";
    private final String gliderStructure = "Glider";
    private IntTextField cellField;
    private IntTextField ruleField;
    private IntTextField sowProp1Field;
    private IntTextField sowProp2Field;
    private IntTextField stepsField;
    private BoolStateChanger boolStateChanger;

    //    @FXML
//    private AnchorPane root;
    @FXML
    private Canvas canvas;
    @FXML
    private Canvas strokeCanvas;
    @FXML
    private Slider widthSlider;
    @FXML
    private Slider heightSlider;
    @FXML
    private Slider cellSlider;
    @FXML
    private TextField cellTextField;
    @FXML
    private TextField ruleTextField;
    @FXML
    private CheckBox periodicCheck;
    @FXML
    private Button simulationBtn;
    @FXML
    private ComboBox<String> simulCombo;
    @FXML
    private Accordion optionAccordion;
    @FXML
    private ComboBox<String> sowTypeCombo;
    @FXML
    private Button sowBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Label sowProp1Label;
    @FXML
    private Label sowProp2Label;
    @FXML
    private TextField sowProp1TextField;
    @FXML
    private TextField sowProp2TextField;
    @FXML
    private ComboBox<String> structureTypeCombo;
    @FXML
    private CheckBox animateCheck;
    @FXML
    private TextField stepsTextField;

//    @FXML
//    private ChoiceBox simulChoice;
//    @FXML
//    private Button nextStepBtn;
//    @FXML
//    private Button prevStepBtn;
//    @FXML
//    private Button toLastBtn;
//    @FXML
//    private Button toStartBtn;

    @FXML
    private void initialize(){
        simulCombo.setItems(FXCollections.observableArrayList(elementaryCA, gameOfLife));
        sowTypeCombo.setItems(FXCollections.observableArrayList(homogeneousSow, randomSow, radiusSow));
        structureTypeCombo.setItems(FXCollections.observableArrayList(cellStructure, beehiveStructure, blinkerStructure, gliderStructure));
        running=false;
        simulationStep=0;
        rule=90;
        toSowInColumns=2;
        toSowInRows=2;
        toSow = 4;
        radiusOfSowing=2;
        cellSize = (int)cellSlider.getValue();
        cellField = new IntTextField(cellTextField, cellSize,(int)cellSlider.getMin(),(int)cellSlider.getMax());
        ruleField = new IntTextField(ruleTextField, rule, 0, 255);
        sowProp1Field = new IntTextField(sowProp1TextField, 2, 1, Integer.MAX_VALUE);
        sowProp2Field = new IntTextField(sowProp2TextField, 2, 1, Integer.MAX_VALUE);
        stepsField = new IntTextField(stepsTextField, 100, 1, Integer.MAX_VALUE);
        initialXYIndexes = new HashSet<>();
        boolStateChanger = new BoolStateChanger();
        gc = canvas.getGraphicsContext2D();
        gcStroke = strokeCanvas.getGraphicsContext2D();
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        initializeTitledPanes();
        calculateAllowedCanvasSize();
        refreshCanvas();
        refreshStrokeCanvas();
        dispInitialValues();
        setEventListeners();
    }

    private void initializeTitledPanes(){
        rulePane=optionAccordion.getPanes().get(0);
        sowPane=optionAccordion.getPanes().get(1);
        structuresPane=optionAccordion.getPanes().get(2);
    }

    public void bindToMainApp(Main mainApp){
        this.mainApp = mainApp;
        //setSceneSizeChangeListeners();
    }

    private void refreshCanvas(){
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, canvasWidth, canvasHeight);
    }

    private void refreshStrokeCanvas(){
        gcStroke.clearRect(0,0, canvasWidth, canvasHeight);
        gcStroke.setFill(new Color(0,0,0,0));
        gcStroke.fillRect(0,0, canvasWidth, canvasHeight);
        drawGrid();
        drawFrame();
    }

    private void drawGrid(){
        gcStroke.setStroke(new Color(0,0,0, 0.3));
        gcStroke.setLineWidth(1);
        gcStroke.beginPath();
        for(double i=cellSize; i<=canvasWidth; i+=cellSize) {
            gcStroke.moveTo(i, 0);
            gcStroke.lineTo(i, canvasHeight);
        }
        for(double i=cellSize; i<=canvasHeight; i+=cellSize) {
            gcStroke.moveTo(0, i);
            gcStroke.lineTo(canvasWidth, i);
        }
        gcStroke.stroke();
    }

    private void drawFrame(){
        gcStroke.setStroke(Color.BLACK);
        gcStroke.strokeRect(0,0, canvasWidth, canvasHeight);
    }

    private void dispInitialValues(){
        simulCombo.getSelectionModel().select(1);
        dispGameOfLifeInitialValues();
        cellField.setValue(cellSize);
        periodicCheck.setSelected(true);
    }

    private void dispElementaryCAInitialValues(){
        optionAccordion.getPanes().clear();
        optionAccordion.getPanes().add(rulePane);
        ruleField.setValue(rule);
    }

    private void dispGameOfLifeInitialValues(){
        optionAccordion.getPanes().clear();
        optionAccordion.getPanes().add(sowPane);
        optionAccordion.getPanes().add(structuresPane);
        sowTypeCombo.getSelectionModel().select(homogeneousSow);
        structureTypeCombo.getSelectionModel().select(cellStructure);
        sowProp1Field.setValue(toSowInRows);
        sowProp2Field.setValue(toSowInColumns);
    }

//    private void setSceneSizeChangeListeners(){
//        primaryStage.getScene().widthProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
//                scaleAppWindowWidth(((double) number2));
//            }
//        });
//
//        primaryStage.getScene().heightProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
//                scaleAppWindowHeight(((double) number2));
//            }
//        });
//    }

    private void setCanvasWidth(double canvasWidth){
        this.canvasWidth = RangeCorrector.valueBeIn(canvasWidth,minCanvasWidth,maxCanvasWidth);
        canvas.setWidth(this.canvasWidth);
        strokeCanvas.setWidth(this.canvasWidth);
    }

    private void setCanvasHeight(double canvasHeight){
        this.canvasHeight = RangeCorrector.valueBeIn(canvasHeight,minCanvasHeight,maxCanvasHeight);
        canvas.setHeight(this.canvasHeight);
        strokeCanvas.setHeight(this.canvasHeight);
    }

//    private void scaleAppWindowWidth(double width){
//        setCanvasWidth(width-300-2*25); //minus first column width and padding
//        refreshCanvas();
//        refreshStrokeCanvas();
//    }
//
//    private void scaleAppWindowHeight(double height){
//        setCanvasHeight(height-2*25);
//        refreshCanvas();
//        refreshStrokeCanvas();
//    }

    private void setEventListeners(){
        strokeCanvas.setOnMouseMoved(event -> {
            if(simulCombo.getSelectionModel().isSelected(0)) {
                if (event.getY() < cellSize) {
                    strokeCanvas.setCursor(Cursor.HAND);
                } else {
                    strokeCanvas.setCursor(Cursor.DEFAULT);
                }
            }
            else {
                strokeCanvas.setCursor(Cursor.HAND);
            }
        });

        strokeCanvas.setOnMouseClicked(event -> {
            if(simulCombo.getSelectionModel().isSelected(0)) {
                if (event.getY() >= cellSize) {
                    return;
                }
            }
            int xIndex = PositionUtils.toIndex(event.getX(), canvasWidth, (int)(canvasWidth/cellSize));
            int yIndex = PositionUtils.toIndex(event.getY(), canvasHeight, (int)(canvasHeight/cellSize));
            if(cells2D!=null && cells2D.length==(int)(canvasHeight / cellSize) && cells2D[0].length==(int)(canvasWidth / cellSize)) {
                cells2D[yIndex][xIndex].state = !cells2D[yIndex][xIndex].state;
            }
            Pair<Integer,Integer> pair = new Pair<>(xIndex, yIndex);
            if(initialXYIndexes.contains(pair)){
                initialXYIndexes.remove(pair);
                gc.setFill(Color.WHITE);
            } else {
                initialXYIndexes.add(pair);
                gc.setFill(Color.BLACK);
            }
            gc.fillRect(xIndex*cellSize, yIndex*cellSize, cellSize, cellSize);
        });
//        root.setOnMouseClicked(event -> {
//            handleCellTextField();
//            handleRuleTextField();
//        });
    }

    @FXML
    private void handleWidthSlider(){
        adaptGridWidth();
        initialXYIndexes.clear();
    }

    private void adaptGridWidth(){
        calculateAllowedCanvasWidth();
        setCanvasWidth(maxCanvasWidth);
        refreshCanvas();
        refreshStrokeCanvas();
    }

    @FXML
    private void handleHeightSlider(){
        adaptGridHeight();
        initialXYIndexes.clear();
    }

    private void adaptGridHeight(){
        calculateAllowedCanvasHeight();
        setCanvasHeight(maxCanvasHeight);
        refreshCanvas();
        refreshStrokeCanvas();
    }

    private void adaptGrid(){
        adaptGridWidth();
        adaptGridHeight();
    }

    @FXML
    private void handleCellSlider(){
        cellSize = (int)cellSlider.getValue();
        cellField.setValue(cellSize);
        adaptGrid();
        initialXYIndexes.clear();
    }

    @FXML
    private void handleCellTextField(){
        setCellValueFromTextField();
        adaptGrid();
        initialXYIndexes.clear();
    }

    private void setCellValueFromTextField(){
        //int value = StringConverter.toInt(cellField.getText(), (int) cellSlider.getValue());
        cellSize = cellField.getValue();//int) RangeCorrector.valueBeIn(value, cellSlider.getMin(), cellSlider.getValue(), cellSlider.getMax(), cellSlider.getValue());
//        try {
//            value = (int) Double.parseDouble(cellTextField.getText());
//        }
//        catch(Exception e) {
//            value = (int) cellSlider.getValue();
//        }
//        if(value<cellSlider.getMin()) {
//            value = (int) cellSlider.getMin();
//        }
//        else if(value>cellSlider.getMax()) {
//            value = (int) cellSlider.getMax();
//        }
        cellSlider.setValue(cellSize);
        cellField.setValue(cellSize);
    }

//    private void setProperTextFieldValues(){
//        setProperCellsValue();
//        setProperRuleValue();
//    }

    private void calculateAllowedCanvasSize(){
        calculateAllowedCanvasWidth();
        calculateAllowedCanvasHeight();
    }

    private void calculateAllowedCanvasWidth(){
        minCanvasWidth = 3*cellSize;
        if(widthSlider.getValue()<minCanvasWidth){
            maxCanvasWidth = minCanvasWidth;
        }
        else {
            int tmp = (int) Math.round(widthSlider.getValue()/cellSize)*cellSize;
            maxCanvasWidth = tmp > widthSlider.getMax() ? tmp-cellSize : tmp;
        }
    }

    private void calculateAllowedCanvasHeight(){
        minCanvasHeight = 3*cellSize;
        if(heightSlider.getValue()<minCanvasHeight){
            maxCanvasHeight = minCanvasHeight;
        }
        else {
            int tmp = (int) Math.round(heightSlider.getValue()/cellSize)*cellSize;
            maxCanvasHeight = tmp > heightSlider.getMax() ? tmp-cellSize : tmp;
        }
    }

    @FXML
    private void simulationBtnClicked(){
        if(!running) {
            running=true;
            simulationStep++;
            setComponentsDisable();
            simulationBtn.setText("CLEAR");
            startSimulation();
        }
        else {
            running=false;
            simulationStep=0;
            setComponentsDisable();
            simulationBtn.setText("START");
            refreshCanvas();
            initialXYIndexes.clear();
            cells2D=null;
            //stopSimulation();
        }
    }

    private void startSimulation(){
        if(simulCombo.getSelectionModel().getSelectedItem().equals(elementaryCA)) {
            cells = BoolCellSower.sow((int) (canvasWidth / cellSize), new BoolCell(false));
            new BoolStateChanger().sowByKeys(cells, initialXYIndexes, true);
            simul = new ElementaryCellAutoSimulator(cells, rule, periodicCheck.isSelected());
            simul.simulationStep();
            CAVisualization.drawCells(cells, canvas, true, Color.BLACK, 0, simulationStep * cellSize);
        }
        else if(simulCombo.getSelectionModel().getSelectedItem().equals(gameOfLife)) {
            if(cells2D==null || cells2D.length!=(int)(canvasHeight / cellSize) || cells2D[0].length!=(int)(canvasWidth / cellSize)) {
                cells2D = BoolCellSower.sow((int) (canvasWidth / cellSize), (int) (canvasHeight / cellSize), new BoolCell(false));
            }
            boolStateChanger.sowIndexes(cells2D, initialXYIndexes, true);
            simul = new GameOfLifeSimulator(cells2D, periodicCheck.isSelected());
            simul.simulationStep();
            refreshCanvas();
            CAVisualization.drawCells(cells2D, canvas, true, Color.BLACK, 0, 0);
        }

//        for (int i = 0; i < space.getCells().length; i++) {
//            if (space.getCells()[i].state == '1') {
//                gc.setFill(Color.BLACK);
//                gc.fillRect((i - 1) * canvasWidth / cellSlider.getValue(), simulationStep * canvasWidth / cellSlider.getValue(), canvasWidth / cellSlider.getValue(), canvasWidth / cellSlider.getValue());
//            }
//        }
    }

    private void stopSimulation(){
        simulationBtn.setText("START");
        refreshCanvas();
        initialXYIndexes.clear();
    }

    private void setComponentsDisable(){
        cellSlider.setDisable(running);
        widthSlider.setDisable(running);
        heightSlider.setDisable(running);
        cellField.setDisable(running);
        ruleField.setDisable(running);
        periodicCheck.setDisable(running);
        canvas.setDisable(running);
        //strokeCanvas.setDisable(running);
        optionAccordion.setDisable(running);
        simulCombo.setDisable(running);
//        nextStepBtn.setDisable(simulationStep==0);
//        prevStepBtn.setDisable(simulationStep==0);
//        toLastBtn.setDisable(simulationStep==0);
//        toStartBtn.setDisable(simulationStep==0);
    }

    @FXML
    private void handleRuleTextField(){
        setRuleValueFromTextField();
    }

    private void setRuleValueFromTextField(){
        int value = ruleField.getValue();
        rule = value;
    }

    @FXML
    private void nextStep(){
        if(running) {
            switch (simulCombo.getSelectionModel().getSelectedItem()) {
                case elementaryCA: {
                    simulationStep++;
                    simul.simulationStep();
                    CAVisualization.drawCells(cells, canvas, true, Color.BLACK, 0, simulationStep * cellSize);
                    break;
                }
                case gameOfLife: {
                    simulationStep++;
                    simul.simulationStep();
                    refreshCanvas();
                    CAVisualization.drawCells(cells2D, canvas, true, Color.BLACK, 0, 0);
                    break;
                }
            }
        }
    }

    @FXML
    private void lastStep(){
        if(running) {
            switch (simulCombo.getSelectionModel().getSelectedItem()) {
                case elementaryCA: {
                    while (simulationStep < canvasHeight / cellSize) {
                        simulationStep++;
                        simul.simulationStep();
                        CAVisualization.drawCells(cells, canvas, true, Color.BLACK, 0, simulationStep * cellSize);
                    }
                    break;
                }
                case gameOfLife: {
                    new AnimationTimer() {
                        public void handle(long currentNanoTime) {
                            if (!running || simulationStep > stepsField.getValue()) {
                                stop();
                            }
                            simulationStep++;
                            simul.simulationStep();
                            refreshCanvas();
                            if(animateCheck.isSelected()) {
                                CAVisualization.drawCells(cells2D, canvas, true, Color.BLACK, 0, 0);
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    break;
                }
            }
        }
    }


    @FXML
    private void handleSimulationChoice(){
        //dispInitialValues();
        initialXYIndexes.clear();
        switch(simulCombo.getSelectionModel().getSelectedItem()) {
            case elementaryCA: {
                dispElementaryCAInitialValues();
                stepsField.setDisable(true);
                animateCheck.setDisable(true);
                break;
            }
            case gameOfLife: {
                dispGameOfLifeInitialValues();
                stepsField.setDisable(false);
                animateCheck.setDisable(false);
                break;
            }
        }
        refreshCanvas();
        //refreshStrokeCanvas();
    }

    @FXML
    private void handleSowTypeChanged(){
        switch(sowTypeCombo.getSelectionModel().getSelectedItem()){
            case homogeneousSow: {
                sowProp2Label.setVisible(true);
                sowProp2Field.setVisible(true);
                sowProp1Label.setText("In row:");
                sowProp2Label.setText("In column:");
                sowProp1Field.setValue(toSowInRows);
                sowProp2Field.setValue(toSowInColumns);
                break;
            }
            case randomSow: {
                sowProp2Label.setVisible(false);
                sowProp2Field.setVisible(false);
                sowProp1Label.setText("How many:");
                sowProp1Field.setValue(toSow);
                break;
            }
            case radiusSow: {
                sowProp2Label.setVisible(true);
                sowProp2Field.setVisible(true);
                sowProp1Label.setText("How many:");
                sowProp2Label.setText("Radius:");
                sowProp1Field.setValue(toSow);
                sowProp2Field.setValue(radiusOfSowing);
                break;
            }
        }
    }

    public void handleSowProp1Changed(ActionEvent actionEvent) {
        switch(sowTypeCombo.getSelectionModel().getSelectedItem()){
            case homogeneousSow: {
                toSowInRows = sowProp1Field.getValue();
                break;
            }
            case randomSow: {
                toSow = sowProp1Field.getValue();
                break;
            }
            case radiusSow: {
                toSow = sowProp1Field.getValue();
                break;
            }
        }
    }

    public void handleSowProp2Changed(ActionEvent actionEvent) {
        switch(sowTypeCombo.getSelectionModel().getSelectedItem()){
            case homogeneousSow: {
                toSowInColumns = sowProp2Field.getValue();
                break;
            }
            case randomSow: {
                break;
            }
            case radiusSow: {
                radiusOfSowing = sowProp2Field.getValue();
                break;
            }
        }
    }

    public void handleSowBtn(ActionEvent actionEvent) {
        if(cells2D==null || cells2D.length!=(int)(canvasHeight / cellSize) || cells2D[0].length!=(int)(canvasWidth / cellSize)) {
            cells2D = BoolCellSower.sow((int) (canvasWidth / cellSize), (int) (canvasHeight / cellSize), new BoolCell(false));
        }
        switch(sowTypeCombo.getSelectionModel().getSelectedItem()){
            case homogeneousSow: {
                boolStateChanger.sowHomogeneous(cells2D,toSowInRows,toSowInColumns,true);
                break;
            }
            case randomSow: {
                boolStateChanger.sowRandom(cells2D,toSow,true);
                break;
            }
            case radiusSow: {
                radiusOfSowing = sowProp2Field.getValue();
                boolStateChanger.sowWithRadius(cells2D,toSow,radiusOfSowing,true);
                break;
            }
        }
        boolStateChanger.sowIndexes(cells2D, initialXYIndexes,true);
        CAVisualization.drawCells(cells2D, canvas, true, Color.BLACK, 0,0);
    }

    public void handleClearBtn(ActionEvent actionEvent) {
        refreshCanvas();
        initialXYIndexes.clear();
        boolStateChanger.sowAll(cells2D,false);
    }
}
