package com.mumu.webmagic.impl;

import com.mumu.demo.webmagic.WebmagicDemo;
import com.mumu.webmagic.IConfig;
import com.mumu.webmagic.IFilePersist;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 默认的文件持久化类
 *
 * @author peter.wang
 * @version V1.0, 2017/5/18
 * @secret {秘密}
 * @see [com.mumu.webmagic.impl#DefaultFilePersist]
 * @since [产品/模块版本]
 */
public abstract class AbstractFilePersist implements IFilePersist{
    private static Logger logger = LoggerFactory.getLogger(AbstractFilePersist.class);
    private String rootDir;

    private String randomDir;

    public AbstractFilePersist(String rootDir) {
        this.rootDir = rootDir;
        randomDir = String.valueOf(System.currentTimeMillis());
    }

    @Override
    public String getRootDir() {
        return rootDir;
    }

    @Override
    public void persist(String requestUrl, IConfig config, Document document) {
        String fileName = getFileName(requestUrl, document);
        String content = extractContent(document);

        File dir = null;

        if(StringUtils.isNotEmpty(config.getHost())){
            dir = new File(new File(getRootDir(), config.getHost()), config.getCategory());
        }else {
            dir = new File(getRootDir(), randomDir);
        }

        if(!dir.exists()){
            dir.mkdirs();
        }

        try(PrintStream ps = new PrintStream(new FileOutputStream(new File(dir, fileName)))){
            ps.println(content);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected abstract String extractContent(Document document);
}
