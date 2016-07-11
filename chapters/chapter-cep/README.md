What i have done to run the app
> https://apacheignite.readme.io/v1.6/docs/streaming-example fragment code is difference than example - seems here it is in java 8 streams
> add following dependencies
    <dependency>
       <groupId>org.apache.ignite</groupId>
       <artifactId>ignite-indexing</artifactId>
       <version>${ignite.version}</version>
     </dependency>
     <!-- h2 db-->
     <dependency>
       <groupId>com.h2database</groupId>
       <artifactId>h2</artifactId>
       <version>1.3.175</version>
     </dependency>
       <dependency>
           <groupId>org.apache.lucene</groupId>
           <artifactId>lucene-core</artifactId>
           <version>3.5.0</version>
       </dependency>
       <dependency>
           <groupId>commons-codec</groupId>
           <artifactId>commons-codec</artifactId>
           <version>1.6</version>
       </dependency>
       
> run ignite server without any config as follows: ignite.sh
> run StreamWords with example-ignite.xml and the only configuration with cache name, without cache name any entries can't be found in the cache
> run QueryWords without config file, Ignition.start(), thus it can take configuration of setIndexType without spring
When you run Ignite without config - seems it's take the configuration from the IGNITE_HOME - default config