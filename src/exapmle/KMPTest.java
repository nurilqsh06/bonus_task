package exapmle;

public class KMPTest {
    public static void main(String[] args) {
        String text1 = "ABABDABACDABABCABAB";
        String pattern1 = "ABABCABAB";
        
        System.out.println("Text: " + text1);
        System.out.println("Pattern: " + pattern1);
        KnuthMorrisPratt.KMPSearch(text1, pattern1);

        String text2 = "AAAAA";
        String pattern2 = "AAA";
        
        System.out.println("\nText: " + text2);
        System.out.println("Pattern: " + pattern2);
        KnuthMorrisPratt.KMPSearch(text2, pattern2);

        String text3 = "AABAACAADAABAABA";
        String pattern3 = "AABA";
        
        System.out.println("\nText: " + text3);
        System.out.println("Pattern: " + pattern3);
        KnuthMorrisPratt.KMPSearch(text3, pattern3);
    }
}
