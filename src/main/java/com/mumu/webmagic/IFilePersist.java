package com.mumu.webmagic;

import org.jsoup.nodes.Document;

import javax.print.Doc;

/**
 * 文件持久化
 *
 * @author peter.wang
 * @version V1.0, 2017/5/18
 * @secret {秘密}
 * @see [com.mumu.webmagic#IFilePersist]
 * @since [产品/模块版本]
 */
public interface IFilePersist extends IPersist{

    /**
     * 获取根目录
     * @return
     */
    public String getRootDir();

    default String getFileId(String requestUrl){
        int index = requestUrl.lastIndexOf("/");
        int dotIndex = requestUrl.lastIndexOf(".");
        if(dotIndex > index){
            return requestUrl.substring(index + 1, dotIndex);
        }else {
            return requestUrl.substring(index + 1);
        }
    }

    /**
     * 获取文件名字
     * @param document
     * @return
     */
    String getFileName(String requestUrl, Document document);
}
