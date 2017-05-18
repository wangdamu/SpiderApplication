package com.mumu.webmagic;

/**
 * 配置
 *
 * @author peter.wang
 * @version V1.0, 2017/5/18
 * @secret {秘密}
 * @see [com.mumu.webmagic#Config]
 * @since [产品/模块版本]
 */
public interface IConfig {
    /**
     * 爬虫起点
     * @return
     */
    public String getStartUrl();

    /**
     * 目标url模式
     * @return
     */
    public String getTargetUrlPattern();
    /**
     * 获取主机地址
     * @return
     */
    public String getHost();

    /**
     * 获取内容分类
     * @return
     */
    public String getCategory();
}
