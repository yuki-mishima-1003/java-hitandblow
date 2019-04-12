import java.io.*;

public class ConsoleInput implements Input {
    @Override
    public String readText() {
        String message = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            message = reader.readLine();
        }
        catch(IOException e) {
            System.out.println("エラーが発生しました: " + e);
        }
        return message;
    }
}