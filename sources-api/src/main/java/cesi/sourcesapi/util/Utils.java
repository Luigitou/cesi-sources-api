package cesi.sourcesapi.util;

public class Utils {

    public static String validateString(String newString,String oldString){
        if (newString == null || newString.isEmpty()){
            return oldString;
        }
        return newString;
    }
}
