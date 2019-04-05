import java.io.*;

public class HitAndBlow {

    // クラス定数
    private int length; // 答えとなる整数の桁数
    private int[] answer; // 答えとなる整数
    private int count; // 試行回数
    private String inputStr; // 入力された文字列
    private int hit; // ヒットした回数
    private int blow; // ブロウした回数

    // コンストラクタ
    HitAndBlow(int length) {
        this.length = length;
        answer = createAnswer(this.length);
        count = 0;
    }

    // インプット処理のメソッド
    public boolean input() {
        // 初回のみゲーム開始のメッセージを出力
        if(count == 0) {
            System.out.println("「Hit and Blow」を開始します");
            count ++;
        }
        // 入力
        System.out.println(count + "回目: " + length + "桁の整数を入力して下さい");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // 入力された内容をチェックする
        try {
            inputStr = reader.readLine();
        }
        catch(IOException e) {
            System.out.println("エラーが発生しました: " + e);
            return false;
        }
        // 指定された桁数の整数であるかチェック
        if(isNumber(inputStr)) return true;
        else return false;
    }

    // 計算処理を行うメソッド
    public boolean calc() {
        hit = 0;
        blow = 0;
        // 入力された文字列を数値の配列に変換
        int[] inputArr = new int[length];
        for(int i = 0; i < length; i ++) {
            inputArr[i] = Integer.parseInt(inputStr.substring(i, i+1));
        }
        // 答えと入力を比較
        for(int j = 0; j < length; j ++) {
            for(int k = 0; k < length; k ++) {
                if(j == k && answer[j] == inputArr[k])
                    hit ++;
                else if(answer[j] == inputArr[k])
                    blow ++;
            }
        }
        return true;
    }

    // 計算結果を出力するメソッド
    public boolean output() {
        // 計算結果を出力
        System.out.println("Hit: " + hit + " Blow: " + blow);
        // 答えと一致した場合は正解メッセージ出力
        if(hit == length) {
            System.out.println("答えが一致しました");
            System.out.println("おめでとうございます");
            return false; // ゲーム終了
        }
        count ++;
        return true; // ゲーム続行
    }

    // それぞれ値の異なる指定された桁数の乱数を生成して返すメソッド
    private int[] createAnswer(int length) {
        int[] answer = new int[length];
        for(int i = 0; i < answer.length; i++) {
            Loop: while(true) {
                answer[i] = (int)(Math.random() * 10); // 0~9の数値を代入
                // 数字が被っていないかチェック
                for(int j = 0; j < i; j++) {
                    if(answer[j] == answer[i]) continue Loop; // 被りがあった場合数値を入れ直す
                }
                break; // 被りがなかった場合次の桁に進む
            }
        }
        return answer;
    }

    // 与えられた文字列が指定された桁数であるかどうかチェックするメソッド
    private boolean isNumber(String text) {
        int number;
        int digit = 0; // 桁数
        // 数値であるかチェック
        try {
            number = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("数値以外の文字が入力されています");
            return false;
        }
        // 指定された桁数であるかチェック
        while(number != 0) {
            number = number / 10;
            digit ++;
        }
        if(digit == length) return true;
        else return false;
    }
}