package com.mumu.webmagic.main;

import com.mumu.webmagic.IConfig;
import com.mumu.webmagic.IFilePersist;
import com.mumu.webmagic.PersistManager;
import com.mumu.webmagic.Spider;
import com.mumu.webmagic.impl.DefaultConfig;
import com.mumu.webmagic.impl.b_faloo_com.BFalooFilePersist;

/**
 * ${todo}〈一句话功能简述〉
 * ${todo}〈功能详细描述〉
 *
 * @author peter.wang
 * @version V1.0, 2017/5/18
 * @secret {秘密}
 * @see [com.mumu.webmagic.main#WebMagicMain]
 * @since [产品/模块版本]
 */
public class WebMagicMain {

    public static void main(String[] args) {
        String host = "b.faloo.com";
        String category = "434970";
        String urlPattern = "http://" + host + "/p/" + category + "/\\d+.html";
        String startUrl = "http://" + host + "/f/" + category + ".html";
        String baseDir = "d:/temp";

        IConfig config = new DefaultConfig(startUrl, urlPattern);
        IFilePersist filePersist = new BFalooFilePersist(baseDir);
        PersistManager.getInstance().setHostPersist(host, filePersist);

        Spider spider = new Spider(config);
        spider.scratch();
    }
}
