package ma.enset.helper;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
public class RandomDataLogsGenerator {
    public static void main(String[] args) {
        // Define the possible pages and HTTP response codes
        String[] pages = {"page1", "page2", "page3", "page4", "page5"};
        int[] responseCodes = {200, 404, 500};

        int num_rows = 1024;

        try (FileWriter fileWriter = new FileWriter("access_logs.txt")) {
            Random random = new Random();

            for (int i = 0; i < num_rows; i++) {
                String clientIP = generateRandomIP();
                String dateTime = generateRandomDateTime();
                String page = pages[new Random().nextInt(5)];
                int responseCode = responseCodes[new Random().nextInt(3)];

                String logEntry = clientIP + "--[" + dateTime + "]" + "\"GET /" + page + " HTTP/1.1\"" + responseCode;

                fileWriter.write(logEntry + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static String generateRandomIP() {
        Random random = new Random();
        return "192.168.1."+  random.nextInt(256);
    }

    private static String generateRandomDateTime() {
        Random random = new Random();
        int year = 2015 + random.nextInt(8);
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);

        return year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day) + " " +
                String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second);
    }
}