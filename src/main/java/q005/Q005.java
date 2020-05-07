package q005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Q005 データクラスと様々な集計
 * <p>
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 * <p>
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 * <p>
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 * <p>
 * [出力イメージ]
 * 部長: xx時間xx分
 * 課長: xx時間xx分
 * 一般: xx時間xx分
 * Z-7-31100: xx時間xx分
 * I-7-31100: xx時間xx分
 * T-7-30002: xx時間xx分
 * （省略）
 * 194033: xx時間xx分
 * 195052: xx時間xx分
 * 195066: xx時間xx分
 * （省略）
 */
public class Q005 {

    public static void main(String[] args) {
        List<String> data = readFile();
        List<WorkData> workData = data.stream().map(WorkData::of).collect(Collectors.toList());
        workTimeByPosition(workData);
        workTimeByPCode(workData);
        workTimeByNumber(workData);
    }

    public static List<String> readFile() {
        try (InputStreamReader is = new InputStreamReader(Q005.class.getResourceAsStream("data.txt"));
             BufferedReader reader = new BufferedReader(is)) {
            String line;
            List<String> words = new ArrayList<>();
            boolean firstFlg = true;
            while ((line = reader.readLine()) != null) {
                if (firstFlg) {
                    firstFlg = false;
                } else {
                    words.add(line);
                }
            }

            System.out.println(words);
            return words;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void workTimeByPosition(List<WorkData> workData) {
        Map<String, Integer> map = workData.stream()
                .collect(Collectors.groupingBy(
                        WorkData::getPosition,
                        Collectors.summingInt(WorkData::getWorkTime)));
        map.forEach((k, v) -> System.out.println(k + ":" + v / 60 + "時間" + v % 60 + "分"));
    }

    private static void workTimeByPCode(List<WorkData> workData) {
        Map<String, Integer> map = workData.stream()
                .collect(Collectors.groupingBy(
                        WorkData::getPCode,
                        Collectors.summingInt(WorkData::getWorkTime)));
        map.forEach((k, v) -> System.out.println(k + ":" + v / 60 + "時間" + v % 60 + "分"));
    }

    private static void workTimeByNumber(List<WorkData> workData) {
        Map<String, Integer> map = workData.stream()
                .collect(Collectors.groupingBy(
                        WorkData::getNumber,
                        Collectors.summingInt(WorkData::getWorkTime)));
        map.forEach((k, v) -> System.out.println(k + ":" + v / 60 + "時間" + v % 60 + "分"));
    }
}
// 完成までの時間: 01時間 30分