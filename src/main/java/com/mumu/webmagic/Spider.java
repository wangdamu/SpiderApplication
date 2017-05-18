package com.mumu.webmagic;

import com.mumu.demo.webmagic.WebmagicDemo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.SimplePageProcessor;

/**
 * ${todo}〈一句话功能简述〉
 * ${todo}〈功能详细描述〉
 *
 * @author peter.wang
 * @version V1.0, 2017/5/18
 * @secret {秘密}
 * @see [com.mumu.webmagic#Spider]
 * @since [产品/模块版本]
 */
public class Spider {
    private static Logger logger = LoggerFactory.getLogger(Spider.class);
    private IConfig config;

    public Spider(IConfig config) {
        this.config = config;
    }

    public void scratch(){
        us.codecraft.webmagic.Spider.create(new SimplePageProcessor(config.getStartUrl(), config.getTargetUrlPattern()))
                .addPipeline(new Pipeline() {
                    @Override
                    public void process(ResultItems resultItems, Task task) {
                        Document doc = Jsoup.parse(resultItems.get("html"));
                        IPersist persist = PersistManager.getInstance().getPersist(config.getHost(), config.getCategory());
                        if(persist == null){
                            logger.warn("persistNotExists: host={}, category={}", config.getHost(), config.getCategory());
                        }else{
                            persist.persist(resultItems.getRequest().getUrl(), config, doc);
                        }
                    }
                })
                .thread(5).run();
    }
}
