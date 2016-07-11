package com.blu.imdg.streamer;

import java.io.*;

import com.blu.imdg.CacheConfig;
import com.blu.imdg.ExamplesUtils;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.affinity.AffinityUuid;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.mxbean.ThreadPoolMXBean;

import javax.cache.configuration.FactoryBuilder;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;


public class StreamWords {
    private static final String FILE_FOR_STREAM ="/alice-in-wonderland.txt";
    private static final int ENTRY_COUNT = 500000;
    /**
     * Starts words streaming.
     *
     * @param args Command line arguments (none required).
     * @throws Exception If failed.
     */
    public static void main(String[] args) throws Exception {

        // Mark this cluster member as client.
        Ignition.setClientMode(true);

        try (Ignite ignite = Ignition.start("example-ignite.xml")) {
            if (!ExamplesUtils.hasServerNodes(ignite))
                return;
            //long start = System.currentTimeMillis();
            CacheConfiguration<AffinityUuid, String> cfg = CacheConfig.wordCache();

            // The cache is configured with sliding window holding 1 second of the streaming data.
            IgniteCache<AffinityUuid, String> stmCache = ignite.getOrCreateCache(cfg);

            int cnt =0;
            try (IgniteDataStreamer<AffinityUuid, String> stmr = ignite.dataStreamer(stmCache.getName())) {
                // Stream words from "alice-in-wonderland" book.
                stmr.allowOverwrite(true);

                try(BufferedReader br = new BufferedReader(new InputStreamReader(StreamWords.class.getClass().getResourceAsStream(FILE_FOR_STREAM))) ){
                    String line;
                    while ((line = br.readLine())!= null ){
                        for(String word : line.split(" ")){
                            cnt++;
                            // Stream words into Ignite.
                            // By using AffinityUuid we ensure that identical
                            // words are processed on the same cluster node.
                            stmr.addData(new AffinityUuid(word), word);
                        }
                    }
                }

            stmr.flush();
            }
            System.out.println("Word count: "+ cnt);
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}