package q003;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Q003 集計と並べ替え
 * <p>
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 * <p>
 * 出力形式:単語=数
 * <p>
 * [出力イメージ]
 * （省略）
 * highest=1
 * I=3
 * if=2
 * ignorance=1
 * （省略）
 * <p>
 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {

    private static final String A = ",";
    private static final String B = ";";
    private static final String C = ".";
    private static final String I = "I";
    private static final String EMPTY = "";

    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    public void sortWord() {
        try (InputStreamReader is = new InputStreamReader(openDataFile());
             BufferedReader reader = new BufferedReader(is)) {
            String line;
            List<String> words = new ArrayList<>();
            StringBuilder character = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                character.append(line);
                String[] array = character.toString().split(" ");
                words.addAll(Arrays.asList(array));
            }
            for (int i=0; i<words.size(); i++) {
                if (words.get(i).contains(C)) {
                    if (words.get(i).substring(words.get(i).indexOf(C)).length() > 1) {
                        String[] stls = words.get(i).split(C);
                        words.addAll(Arrays.asList(stls));
                        words.remove(i);
                    }
                }
            }

            List<String> strings = new ArrayList<>();
            for (int i = 0; i < words.size(); i++) {
                String str = words.get(i);
                String s = str.contains(A)
                        ? str.replace(A, EMPTY)
                        : str.contains(B)
                        ? str.replace(B, EMPTY)
                        : str.contains(C)
                        ? str.replace(C, EMPTY)
                        : str.equals(I)
                        ? str
                        : str.toLowerCase();
                strings.add(s);
            }
            Set<String> set = new TreeSet<>(strings);
            set.stream().forEach(s -> System.out.println(s + "=" + Collections.frequency(strings, s)));
//            Map<String, Long> map =
//                    new TreeMap<>(strings.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting())));
//            map.forEach((k, v) -> System.out.println(k + "=" + v));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// 完成までの時間: 02時間 00分