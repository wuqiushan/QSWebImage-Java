package QSWebImage;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class testMain {
    public static void main(String[] args) {

        LruCacheMap<String, Object> lru = new LruCacheMap<String, Object>();
        lru.put1("1", "1Value");
        lru.put1("2", "2Value");
        lru.put1("3", "3Value");
        lru.put1("4", "4Value");
        System.out.println(lru.map);
        lru.get1("2");
        System.out.println(lru.map);
        lru.remove1("3");
        System.out.println(lru.map);

        // 利用这个可以在内存易出时，循环删除文件
        Set<Map.Entry<String,Object>> set = lru.map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            System.out.println("key" + entry.getValue());
        }
    }
}
