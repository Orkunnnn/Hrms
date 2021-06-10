package kodlamaio.hrms.core.utilities.helpers;

public class StringHelper {
    public static boolean containsAllWords(String containWord, String content) {
        for (char k : containWord.toCharArray())
            if (!content.contains(Character.toString(k))) return false;
        return true;
    }
}
