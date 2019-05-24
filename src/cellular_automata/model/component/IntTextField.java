package cellular_automata.model.component;

import cellular_automata.model.utils.RangeCorrector;
import javafx.scene.control.TextField;

public class IntTextField {
    private TextField textField;
    private int value;
    private int min;
    private int max;


    public IntTextField(TextField textField) {
        this.textField=textField;
        this.value = 0;
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
    }

    public IntTextField(TextField textField, int value) {
        this.textField=textField;
        this.value = value;
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
        this.textField.setText(String.valueOf(this.value));
    }

    public IntTextField(TextField textField, int value, int min, int max) {
        this.textField=textField;
        this.min = min;
        this.max = max;
        this.value = RangeCorrector.valueBeIn(value,min,min,max,max);
        this.textField.setText(String.valueOf(this.value));
    }

    public IntTextField(TextField textField, int min, int max) {
        this.textField=textField;
        this.min = min;
        this.max = max;
        this.value = RangeCorrector.valueBeIn(0,min,min,max,max);
        this.textField.setText(String.valueOf(this.value));
    }

    public void setValue(String text){
        try {
            int value = Integer.parseInt(text);
            this.value = RangeCorrector.valueBeIn(value, min, this.value, max, this.value);
            this.textField.setText(String.valueOf(this.value));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setValue(String text, int min, int max){
        try {
            int value = Integer.parseInt(text);
            this.value = RangeCorrector.valueBeIn(value, min, this.value, max, this.value);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setConstraints(int min, int max){
        this.min = min;
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setValue(int value){
        this.value = RangeCorrector.valueBeIn(value, min, this.value, max, this.value);
        this.textField.setText(String.valueOf(this.value));
    }

    public int getValue(){
        //Needed because text not always equals value
        setValue(textField.getText());
        textField.setText(String.valueOf(value));
        return value;
    }

    public void setDisable(boolean disable){
        textField.setDisable(disable);
    }

    public void setVisible(boolean visible){
        textField.setVisible(visible);
    }
}
