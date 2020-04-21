import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class TestJsoup{
    public static void main(String[] args) throws IOException {
        String path = TestJsoup.class.getClassLoader().getResource("users.xml").getPath();
        Document document = Jsoup.parse(new File(path), "utf-8");
        Elements elements = document.getElementsByAttributeValue("number","5");
        System.out.println(elements.get(0).html());
    }
}