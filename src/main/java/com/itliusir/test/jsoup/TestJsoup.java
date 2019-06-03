package com.itliusir.test.jsoup;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * TODO
 *
 * @author liugang
 * @since 2019/4/28
 */

@Slf4j
public class TestJsoup {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://v.ifeng.com/special/2019sdzhjt/").get();
        String title = doc.title();
        Element element = doc.selectFirst("div#p2_lecont").child(0).child(0);
        String childTitle = element.text();
        String name = element.attributes().get("name");
        System.out.println(title);
        System.out.println(childTitle);
        System.out.println(name);

    }
}
