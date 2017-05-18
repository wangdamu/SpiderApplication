package com.mumu.webmagic;

import java.util.HashMap;
import java.util.Map;

/**
 * 持久化管理器
 *
 * @author peter.wang
 * @version V1.0, 2017/5/18
 * @secret {秘密}
 * @see [com.mumu.webmagic#PersistManager]
 * @since [产品/模块版本]
 */
public class PersistManager {
    /**
     * 持久化Map
     */
    private Map<String, PersistAssemble> persistAssembleMap = new HashMap<>();

    private PersistManager(){}

    /**
     * 设置主机对应的持久化类
     * @param hostPersist
     */
    public void setHostPersist(String host, IPersist hostPersist){
        PersistAssemble pa = getPersistAssemble(host);
        if(pa == null){
            pa = new PersistAssemble();
            persistAssembleMap.put(host, pa);
        }
        pa.setHostPersist(hostPersist);
    }

    /**
     * 新增主机+分类的持久化类
     * @param host
     * @param category
     * @param persist
     */
    public void addHostCategoryPersist(String host, String category, IPersist persist){
        PersistAssemble pa = getPersistAssemble(host);
        pa.getCategoryPersist().put(category, persist);
    }

    /**
     * 获取持久化接口实现
     * @param host
     * @param category
     * @return
     */
    public IPersist getPersist(String host, String category){
        PersistAssemble pa = persistAssembleMap.get(host);
        if(pa == null){
            return null;
        }

        IPersist ret = pa.getCategoryPersist().get(category);
        if(ret == null){
            ret = pa.getHostPersist();
        }
        return ret;
    }

    private PersistAssemble getPersistAssemble(String host){
        PersistAssemble pa = persistAssembleMap.get(host);
        if(pa == null){
            pa = new PersistAssemble();
            persistAssembleMap.put(host, pa);
        }
        return pa;
    }

    private class PersistAssemble{
        private IPersist hostPersist;

        private Map<String, IPersist> categoryPersist = new HashMap<>();

        public IPersist getHostPersist() {
            return hostPersist;
        }

        public void setHostPersist(IPersist hostPersist) {
            this.hostPersist = hostPersist;
        }

        public Map<String, IPersist> getCategoryPersist() {
            return categoryPersist;
        }

        public void setCategoryPersist(Map<String, IPersist> categoryPersist) {
            this.categoryPersist = categoryPersist;
        }
    }

    public static PersistManager getInstance(){
        return Inner.instance;
    }

    private static class Inner{
        public static final PersistManager instance = new PersistManager();
    }
}
