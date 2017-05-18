package com.mumu.webmagic.impl.b_faloo_com;

import com.mumu.webmagic.impl.AbstractFilePersist;
import org.jsoup.nodes.Document;

/**
 * http://b.faloo.com 这个网站的文件保存器
 *
 * @author peter.wang
 * @version V1.0, 2017/5/18
 * @secret {秘密}
 * @see [com.mumu.webmagic.impl.b_faloo_com#BFalooFilePersist]
 * @since [产品/模块版本]
 */
public class BFalooFilePersist extends AbstractFilePersist{
    public BFalooFilePersist(String rootDir) {
        super(rootDir);
    }

    @Override
    public String getFileName(String requestUrl, Document document) {
        String titleName = document.select("#title h1").text();
        return getFileId(requestUrl) + "-" + titleName + ".txt";
    }

    @Override
    protected String extractContent(Document document) {
        String titleName = document.select("#title h1").text();

        String subTitle = document.select("#title #title_s").text();

        document.select("#content .p_content_bottom").remove();

        String content = document.select("#content").html().replace("<br>", "\n");

        StringBuilder sb = new StringBuilder();
        sb.append(titleName).append("\n")
                .append(subTitle).append("\n\n");

        sb.append(content);

        return sb.toString();
    }
}
