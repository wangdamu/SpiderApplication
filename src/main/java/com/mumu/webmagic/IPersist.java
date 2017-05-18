package com.mumu.webmagic;

import org.jsoup.nodes.Document;

/**
 * 持久化接口
 *
 * @author peter.wang
 * @version V1.0, 2017/5/18
 * @secret {秘密}
 * @see [com.mumu.webmagic#IPersist]
 * @since [产品/模块版本]
 */
public interface IPersist {
    /**
     * 持久化document
     * @param document
     */
    public void persist(String requestUrl, IConfig config, Document document);

}
