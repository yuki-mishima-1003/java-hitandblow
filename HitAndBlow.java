import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HitAndBlow {

    // クラス定数
    private int length; // 答えとなる整数の桁数
    private List<Integer> ansList; // 答えとなる整数
    private int count; // 試行回数
    private String inputStr; // 入力された文字列
    private int hit; // ヒットした回数
    private int blow; // ブロウした回数
    private Output output;
    private Input input;

    // コンストラクタ
    HitAndBlow(int length, Input input, Output output) {
        this.length = length;
        this.input = input;
        this.output = output;
        ansList = createAnswer(this.length);
        count = 1;
    }

    // インプット処理のメソッド
    public void input() {
        // 入力
        output.normalOutput(count + "回目: " + length + "桁の整数を入力して下さい");
        // 入力された内容を格納する
        inputStr = input.readText();
    }

    // 計算処理を行うメソッド
    public boolean calc() {
        hit = 0;
        blow = 0;
        // 指定された桁数の整数であるかチェック
        if(!validate(inputStr)) return false;
        // 入力された文字列を数値の配列に変換
        List<Integer> inputArr = new ArrayList<Integer>();
        for(int i = 0; i < length; i ++) {
            inputArr.add(Integer.parseInt(inputStr.substring(i, i+1)));
        }
        // 答えと入力を比較
        for(int i = 0; i < length; i ++) {
            for(int j = 0; j < length; j ++) {
                if(i == j && ansList.get(i) == inputArr.get(j))
                    hit ++;
                else if(ansList.get(i) == inputArr.get(j))
                    blow ++;
            }
        }
        return true;
    }

    // 計算結果を出力するメソッド
    public boolean output() {
        // 計算結果を出力
        output.normalOutput("Hit: " + hit + " Blow: " + blow);
        // 答えと一致した場合は正解メッセージ出力
        if(hit == length) {
            output.normalOutput("答えが一致しました");
            output.normalOutput("おめでとうございます");
            return false; // ゲーム終了
        }
        count ++;
        return true; // ゲーム続行
    }

    // それぞれ値の異なる指定された桁数の乱数を生成して返すメソッド
    private List<Integer> createAnswer(int length) {
        List<Integer> numList = new ArrayList<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        // リストに0~9の数値を格納
        for(int i = 0; i < 10; i++) {
            numList.add(i);
        }
        // リストの値をシャッフル
        Collections.shuffle(numList);
        // 指定された桁数分数値を取り出す
        for(int i = 0; i < length; i++) {
            list.add(numList.get(i));
        }
        return list;
    }

    // 与えられた文字列が指定された桁数であるかどうかチェックするメソッド
    private boolean validate(String text) {
        List<Integer> numList = new ArrayList<Integer>();
        int number; 
        int digit = 0; // 桁数
        // 1文字ずつ取り出して数値であるかチェック
        for(int i = 0; i < text.length(); i++) {
            try {
                number = Integer.parseInt(text.substring(i, i+1));
            } catch(NumberFormatException e) {
                output.normalOutput("数値以外の文字が入力されています");
                return false;
            }
            numList.add(number);
        }
        // 入力された数値に被りがないかチェック
        for(int i = 0; i < numList.size(); i++) {
            for(int j = i+1; j < numList.size(); j++) {
                if(numList.get(i) == numList.get(j)) {
                    output.normalOutput("入力された数値に被りがあります");
                    return false;
                }
            }
        }
        // 指定された桁数であるかチェック
        if(numList.size() == length) return true;
        else {
            output.normalOutput("指定された桁数の数値を入力してください");
            return false;
        }
    }
}