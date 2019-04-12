public class ConsoleOutput implements Output {
    @Override
    public void normalOutput(String message) {
        System.out.println(message);
    }
}