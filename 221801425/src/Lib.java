import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib{
    Lib lib=new Lib();
    //单词正则表达式
    private static String WORDS_RE = "[a-zA-Z]{4,}[a-zA-Z0-9]*";
    //分隔符正则表达式
    private static String BREAK_RE = "[^a-zA-Z0-9]";
    //非空行正则表达式
    private static String LINE_RE = "(^|\n)\\s*\\S+";
    //获取当前工程的路径
    public static String DIR = System.getProperty("user.dir");
    //Map表用于存放单词以及相对应的个数
    private static Map<String, Integer> wordsMap = new HashMap<String, Integer>();



    public static String readFromFile(String filePath) {
        int temp;
        BufferedReader br = null;
        StringBuilder sbuilder = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            sbuilder = new StringBuilder();
            while((temp = br.read()) != -1) {
                sbuilder.append((char)temp);
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        return sbuilder.toString();
    }
}