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

    public static int getCharactersCount(String str) {
        //字符数统计数量
        int count = 0;
        char[] ch = str.toCharArray();
        for(int i = 0; i < ch.length; i++) {
            if(ch[i] >= 0 && ch[i] <= 127) {
                count++;
            }else continue;
        }
        return count;
    }

    public static int getWordsCount(String str) {
        //单词数的统计量
        int count = 0;
        String[] strs = str.split(BREAK_RE);
        for(int i = 0; i < strs.length; i++) {
            if(strs[i].matches(WORDS_RE)) {
                String temp = strs[i].toLowerCase();
                if(wordsMap.containsKey(temp)) {
                    int num = wordsMap.get(temp);
                    wordsMap.put(temp, 1 + num);
                }
                else {
                    wordsMap.put(temp, 1);
                }
                count++;
            }
        }
        return count;
    }

    public static int getLineCount(String str) {
        //行数统计数量
        int count = 0;
        Matcher matcher = Pattern.compile(LINE_RE).matcher(str);
        while(matcher.find()) {
            count++;
        }
        return count;
    }

    public static List<Map.Entry<String, Integer>> sortHashmap() {
        List<Map.Entry<String, Integer>> list;
        list = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Entry<String, Integer> m1, Entry<String, Integer> m2) {
                if(m1.getValue().equals(m2.getValue())) {
                    return m1.getKey().compareTo(m2.getKey());
                }else return m2.getValue()-m1.getValue();
            }
        });
        return list;
    }

    public static void writeToFile(int characters, int words, int lines, String filePath) {
        String str = "characters: " + characters + "\nwords: " + words + "\nlines: " + lines +"\n";
        List<Map.Entry<String, Integer>> list = sortHashmap();
        int i = 0;
        for(Map.Entry<String, Integer> map : list) {
            if(i < 10) {
                str += map.getKey() + ": " + map.getValue() + "\n";
                i++;
            }else break;
        }

        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;

        try {
            fos = new FileOutputStream(filePath);
            writer = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(writer);
            bw.write(str);
            bw.flush();
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
                writer.close();
                bw.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}