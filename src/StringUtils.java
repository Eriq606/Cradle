public class StringUtils {
    public static String minStart(String s){
        return s.replaceFirst(String.valueOf(s.charAt(0)), String.valueOf(s.charAt(0)).toLowerCase());
    }
}
