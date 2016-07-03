package com.blu.imdg;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: bsha
 * Date: 03.07.2016
 * Time: 17:58
 */
public class PutElementstoOffHeap {
    public static void main(String[] args) throws Exception{
        System.out.println("Put elements to Off Heap Memory!");
        // Start Ignite cluster
        Ignite ignite = Ignition.start("spring-offheap-tierd.xml");
        // get or create cache
        IgniteCache<Integer, String> cache =  ignite.getOrCreateCache("offheap-cache");
        for(int i = 1; i < 1000; i++){
            cache.put(i, Integer.toString(i));
        }
        for(int i =1; i<1000;i++){
            System.out.println("Cache get:"+ cache.get(i));
        }
        Thread.sleep(Integer.MAX_VALUE); // sleep for 20 seconds
        // statistics
        System.out.println("Cache Hits:"+ cache.metrics(ignite.cluster()).getCacheHits());
        ignite.close();
    }
}
