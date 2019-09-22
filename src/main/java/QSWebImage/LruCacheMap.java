package QSWebImage;

import java.util.LinkedHashMap;

public class LruCacheMap<K, V> extends LinkedHashMap {

    /**
     * 1.获取系统分配给此应用最大内存
     * 2.框架内存占此应用内存的 1/8
     * 3.框架磁盘 默认为 500M
     * 4.已使用内存大小
     * 5.已使用磁盘大小
     */
    private int maxSize = (int) Runtime.getRuntime().maxMemory();
    private int cacheSize = maxSize / 8;
    private int diskSize = 500 * 1024 * 1024;
    private int useCacheSize = 0;
    private int useDiskSize  = 0;
    private LinkedHashMap<K, V> map;


    /**
     * 1.构造方法
     */
    public LruCacheMap() {
        this.map = new LinkedHashMap<K, V>(0, 0.75f, true);
    }

    public LruCacheMap(int cacheSize, int diskSize) {

        if (cacheSize <= 0) {
            throw new IllegalArgumentException("cacheSize <= 0");
        }
        if (diskSize <= 0) {
            throw new IllegalArgumentException("diskSize <= 0");
        }

        this.cacheSize = cacheSize;
        this.diskSize = diskSize;
        this.map = new LinkedHashMap<K, V>(0, 0.75f, true);
    }

    /**
     * 增加元素
     */


}
