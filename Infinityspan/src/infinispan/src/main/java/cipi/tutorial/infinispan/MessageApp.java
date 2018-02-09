package cipi.tutorial.infinispan;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;

public class MessageApp {
	
	//1
	private DefaultCacheManager cacheManager;
	//2
	private GlobalConfigurationBuilder globalConfiguration;
	//solo con global
	private ConfigurationBuilder defaultConfiguration;
	
	public void initializeLocal ()
	{
		//CACHE MANAGER
		this.cacheManager = new DefaultCacheManager();		
	}
	
	public void initializeGlobal ()
	{
		globalConfiguration= GlobalConfigurationBuilder.defaultClusteredBuilder();
		globalConfiguration.transport().clusterName("messageCluster");
		defaultConfiguration= new ConfigurationBuilder();
		defaultConfiguration.clustering().cacheMode(CacheMode.DIST_SYNC);
		this.cacheManager= new DefaultCacheManager (globalConfiguration.build(), defaultConfiguration.build());
	}
	
	public void stopCacheManager ()
	{
		this.cacheManager.stop();
	}
	
	public DefaultCacheManager getCacheManager ()
	{
		return this.cacheManager;
	}
	
}
