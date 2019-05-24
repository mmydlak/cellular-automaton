package cellular_automata.model.utils;

public class RangeCorrector {

    static public int valueBeIn(int value, int from, int to) {
        if (value < from) {
            return from;
        } else if (value > to) {
            return to;
        }
        return value;
    }

    static public int valueBeIn(int value, int from, int whenLess, int to, int whenMore) {
        if (value < from) {
            return whenLess;
        } else if (value > to) {
            return whenMore;
        }
        return value;
    }

    static public double valueBeIn(double value, double from, double to) {
        if (value < from) {
            return from;
        } else if (value > to) {
            return to;
        }
        return value;
    }

    static public double valueBeIn(double value, double from, double whenLess, double to, double whenMore) {
        if (value < from) {
            return whenLess;
        } else if (value > to) {
            return whenMore;
        }
        return value;
    }
}
