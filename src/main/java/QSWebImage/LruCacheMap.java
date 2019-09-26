package QSWebImage;

import java.util.LinkedHashMap;

public class LruCacheMap<K, V> {

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
    public LinkedHashMap<K, V> map;


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

//    private int sizeOf(String key, BitMap value) {
//        return value.size();
//    }

    private int safeSizeOf(K key, V value) {
//        int result = sizeOf((String) key, (BitMap) value);
//        if (result < 0) {
//            throw new IllegalArgumentException("safeSizeOf: " + key + "=" + value);
//        }
//        return result;
        return 1;
    }

    /**
     * 增加元素
     */
    public final V put1(K key, V value) {

        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }

        V previous;
        synchronized (this) {
            useCacheSize = safeSizeOf(key, value);
            previous = map.put(key, value);
            if (previous != null) {
                useCacheSize -= safeSizeOf(key, previous);
            }
        }

        if (previous != null) {
            // 移除
        }
        return previous;
    }

    public final V remove1(K key) {

        if (key == null) {
            throw new NullPointerException("key == null");
        }

        V previous;
        synchronized (this) {
            previous = map.remove(key);
            if (previous != null) {
                useCacheSize -= safeSizeOf(key, previous);
            }
        }

        if (previous != null) {
            // 移除
        }

        return previous;
    }

    /**
     * 获取值，
     * @param key
     * @return
     */
    public final V get1(K key) {

        if (key == null) {
            throw new NullPointerException("key == null");
        }

        V mapValue;
        synchronized (this) {
            mapValue = map.get(key);
            if (mapValue != null) {
                return mapValue;
            }
        }
        return mapValue;
    }

}
