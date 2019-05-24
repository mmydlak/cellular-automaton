//package sample.model;
//
//public class ElementaryCARulset implements Ruleset {
//
//    private String rule = "";
//
//    public ElementaryCARulset(int decimalRule) {
//        if(decimalRule > 255 || decimalRule < 0) {
//            try {
//                throw new Exception("Rule have to be an integer in range 0-255.");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        ruleToBinary(decimalRule);
//    }
//
//    private void ruleToBinary(int decimalRule) {
//        int tmp;
//        while(decimalRule > 0)
//        {
//            tmp = decimalRule % 2;
//            rule += tmp;
//            decimalRule = decimalRule / 2;
//        }
//        while(rule.length() < 8){
//            rule = "0" + rule;
//        }
//    }
//
//
//    @Override
//    public void applyOn(CellSpace cellSpace) {
//        if
//
//    }
//}
