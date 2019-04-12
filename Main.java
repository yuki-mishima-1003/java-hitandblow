public class Main {

    public static void main(String[] args) {

        System.out.println("Hit and Blowを開始します");
        ConsoleInput input = new ConsoleInput();
        ConsoleOutput output = new ConsoleOutput();
        // 答えとなる数値の桁数を指定してインスタンスを作成
        HitAndBlow hab = new HitAndBlow(4, input, output);
        // ゲームループ
        while(true) {
            // 入力
            hab.input();
            // 処理
            if(!hab.calc()) continue;
            // 出力
            if(!hab.output()) break;
        }
        System.out.println("Hit and Blowを終了します");
    }
}