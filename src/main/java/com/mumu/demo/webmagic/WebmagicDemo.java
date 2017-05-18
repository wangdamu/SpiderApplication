package com.mumu.demo.webmagic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.SimplePageProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * webmagic测试demo
 *
 * @author peter.wang
 * @version V1.0, 2017/4/19
 * @secret {秘密}
 * @see [com.mumu.demo.webmagic#WebmagicDemo]
 * @since [产品/模块版本]
 */
public class WebmagicDemo {
    private static Logger logger = LoggerFactory.getLogger(WebmagicDemo.class);

    public static void main(String[] args) {
//        String reg = "(http://b\.faloo\.com/p/426277/\.+)";
//        Pattern pattern = Pattern.compile(reg);
//        Matcher matcher = pattern.matcher("http://b.faloo.com/p/426277/1.html");
//        System.out.println(matcher.find());
//        System.out.println("http://b.faloo.com/p/426277/1.html".matches(reg));

        Pattern pattern = Pattern.compile("http(s)?://([^/]+)/(.+/)*(.+)/(\\d+).html");

        String urlPattern = "http://b.faloo.com/p/426277/\\d+.html";

        String baseDir = "d:/temp";

        String randomDir = String.valueOf(System.currentTimeMillis());

        Spider.create(new SimplePageProcessor("http://b.faloo.com/f/426277.html", urlPattern))
                .addPipeline(new Pipeline() {
                    @Override
                    public void process(ResultItems resultItems, Task task) {
                        Document doc = Jsoup.parse(resultItems.get("html"));
                        String titleName = doc.select("#title h1").text();

                        String subTitle = doc.select("#title #title_s").text();

                        doc.select("#content .p_content_bottom").remove();

                        String content = doc.select("#content").html().replace("<br>", "\n");


                        Matcher matcher = pattern.matcher(resultItems.getRequest().getUrl());

                        String host = "";
                        String id = "";
                        String number = "";

                        File dir = null;

                        if(matcher.find()){
                            host = matcher.group(2);
                            id = matcher.group(4);
                            number = matcher.group(5);
                            dir = new File(new File(baseDir, host), id);
                        }else {
                            dir = new File(baseDir, randomDir);
                        }

                        if(!dir.exists()){
                            dir.mkdirs();
                        }

                        String fileName = number + "-" + titleName + ".txt";

                        try(PrintStream ps = new PrintStream(new FileOutputStream(new File(dir, fileName)))){
                            ps.println(titleName);
                            ps.println(subTitle);
                            ps.println("\n\n");
                            ps.println(content);
                        } catch (FileNotFoundException e) {
                            logger.error(e.getMessage(), e);
                        }

//                        logger.info("===============" + resultItems.get("content"));
                    }
                })
                .thread(5).run();
    }
}
