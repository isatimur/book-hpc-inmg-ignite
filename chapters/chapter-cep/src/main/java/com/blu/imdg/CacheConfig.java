package com.blu.imdg;

import javax.cache.configuration.FactoryBuilder;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import org.apache.ignite.cache.affinity.AffinityUuid;
import org.apache.ignite.configuration.CacheConfiguration;

import static java.util.concurrent.TimeUnit.SECONDS;



/**
 * Created by shamim on 11/07/16.
 */
public class CacheConfig {

    public static CacheConfiguration<AffinityUuid, String> wordCache() {
        CacheConfiguration<AffinityUuid, String> cfg = new CacheConfiguration<AffinityUuid, String>("words");

        // Index the words and their counts,
        // so we can use them for fast SQL querying.
        cfg.setIndexedTypes(AffinityUuid.class, String.class);

        // cfg.set
        // Sliding window of 5 seconds.
        cfg.setExpiryPolicyFactory(FactoryBuilder.factoryOf(
                new CreatedExpiryPolicy(new Duration(SECONDS, 5))));

        return cfg;
    }
}
