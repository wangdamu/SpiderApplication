package com.mumu.webmagic.impl;

import com.mumu.webmagic.IConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 默认的Config
 *
 * @author peter.wang
 * @version V1.0, 2017/5/18
 * @secret {秘密}
 * @see [com.mumu.webmagic.impl#DefaultConfig]
 * @since [产品/模块版本]
 */
public class DefaultConfig implements IConfig{
    private static Pattern pattern = Pattern.compile("http(s)?://([^/]+)/(.+/)*([^/]+).html");

    private String startUrl;
    private String targetUrlPattern;

    private String host;
    private String category;

    public DefaultConfig(String startUrl, String targetUrlPattern) {
        this.startUrl = startUrl;
        this.targetUrlPattern = targetUrlPattern;
        init();
    }

    private void init(){
        Matcher matcher = pattern.matcher(startUrl);
        if(matcher.find()){
            host = matcher.group(2);
            category = matcher.group(4);
        }
    }

    @Override
    public String getStartUrl() {
        return startUrl;
    }

    @Override
    public String getTargetUrlPattern() {
        return targetUrlPattern;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getCategory() {
        return category;
    }
}
