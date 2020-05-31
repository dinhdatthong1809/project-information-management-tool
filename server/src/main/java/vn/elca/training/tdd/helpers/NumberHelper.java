package vn.elca.training.tdd.helpers;

public class NumberHelper {
    
    public static boolean isInteger(String string) {
        if (string == null) {
            return false;
        }
        
        try {
            Integer.parseInt(string);
        }
        catch (NumberFormatException ex) {
            return false;
        }
        
        return true;
    }
    
}
