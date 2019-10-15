package com.flightApp.demo.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class SimpleCache<K, T> {

    private long timeToLive;
    private HashMap<K, CacheObject> cacheMap;

    protected class CacheObject {
        public long lastAccessed = System.currentTimeMillis();
        public T value;

        protected CacheObject(T value) {
            this.value = value;
        }
    }

    public SimpleCache(long timeToLive, int max) {
        this.timeToLive = timeToLive * 2000;

        cacheMap = new HashMap<>(max);

        if (timeToLive > 0) {

            Thread t = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(timeToLive);
                        cleanup();
                    } catch (InterruptedException ex) {
                    }

                }
            });

            t.setDaemon(true);
            t.start();
        }
    }

    public void put(K key, T value) {
        synchronized (cacheMap) {
            CacheObject o = new CacheObject(value);
            cacheMap.put(key, o);
        }
    }

    @SuppressWarnings("unchecked")
    public T get(K key) {
        synchronized (cacheMap) {
            CacheObject c = cacheMap.get(key);

            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return (T) c.value;
            }
        }
    }

    public void remove(String key) {
        synchronized (cacheMap) {
            cacheMap.remove(key);
        }
    }


    public int size() {
        synchronized (cacheMap) {
            return cacheMap.size();
        }
    }

    /**
     * still not working
     * */

    public void cleanup() {

        long now = System.currentTimeMillis();
        ArrayList<String> deleteKey = null;

        synchronized (cacheMap) {
            Iterator<?> itr = cacheMap.entrySet().iterator();

            deleteKey = new ArrayList<String>((cacheMap.size() / 2) + 1);
            CacheObject c = null;

            while (itr.hasNext()) {
                String key = (String) itr.next();
                c = (CacheObject) ((Entry<?, ?>) itr).getValue();
                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }

        for (String key : deleteKey) {
            synchronized (cacheMap) {
                cacheMap.remove(key);
            }

            Thread.yield();
        }
    }
}