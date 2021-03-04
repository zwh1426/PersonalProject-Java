import java.io.IOException;

public class WordCount {
    String inputFile = "";
    String outputFile = "";
    int characters = 0;
    int lines = 0;
    int words = 0;

    //构造函数
    public WordCount(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    //变量的初始化
    public void init() {
        this.characters = Lib.getCharactersCount(Lib.readFromFile(this.inputFile));
        this.lines = Lib.getLineCount(Lib.readFromFile(this.inputFile));
        this.words = Lib.getWordsCount(Lib.readFromFile(this.inputFile));
    }

    public void run() {
        Lib.writeToFile(this.characters, this.words, this.lines, this.outputFile);
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("参数不足,请重新运行！");
        }
        else {
            long starttime = System.currentTimeMillis();
            WordCount wordCount = new WordCount(args[0], args[1]);
            wordCount.init();
            wordCount.run();
            long endtime = System.currentTimeMillis();
            System.out.println("time costs: " + (endtime - starttime) + "ms");
        }
    }
}
