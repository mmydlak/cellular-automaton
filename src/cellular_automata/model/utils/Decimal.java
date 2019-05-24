package cellular_automata.model.utils;

public class Decimal {
    static public String toBinary(int decimal, int positions){
        String result = "";
        int tmp;
        while(decimal > 0)
        {
            tmp = decimal % 2;
            result = tmp+result;
            decimal = decimal / 2;
        }
        while(result.length() < positions){
            result = "0" + result;
        }
        return result;
    }
}
