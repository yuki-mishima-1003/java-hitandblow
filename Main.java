public class Main {

    public static void main(String[] args) {

        System.out.println("ゲームを開始します");
        // 答えとなる数値の桁数を指定してインスタンスを作成
        HitAndBlow hab = new HitAndBlow(4);
        // ゲームループ
        while(true) {
            // 入力
            if(!hab.input()) continue;
            // 処理
            if(!hab.calc()) continue;
            // 出力
            if(!hab.output()) break;
        }
        System.out.println("ゲームを終了します");
    }
}