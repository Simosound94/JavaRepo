package cipi.tutorial.infinispan;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryVisited;
import org.infinispan.notifications.cachelistener.event.CacheEntryEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryVisitedEvent;


	@Listener
	   @SuppressWarnings("unused")
	   public class MessageListener {

	      @CacheEntryCreated
	      public void printDetailsOnChange(CacheEntryEvent e) {
	    	  if (!e.isPre())
	    	  {
	    		  System.out.printf("Thread %s has added an entry in the cache named %s under key %s!\n",
                          Thread.currentThread().getName(), e.getCache().getName(), e.getKey());
	    		  System.out.printf("\n \n Title: %s \n Message: %s \n \n ---------------------------------------- \n",
                          e.getKey(), e.getValue());
	    	  }
	    		  
	      }
	      

	      @CacheEntryModified
	      public void printDetailsOnModify(CacheEntryEvent e) {
	    	  if (!e.isPre())
	    	  {
	    		  System.out.printf("Thread %s has modified an entry in the cache named %s under key %s!\n", Thread.currentThread().getName(), e.getCache().getName(), e.getKey());
		    	  System.out.printf("\n \n Title: %s \n Message: %s \n \n ---------------------------------------- \n",
	                      e.getKey(), e.getValue());
	    	  }		        
	      }
	      @CacheEntryRemoved
	      public void printDetailsOnRemove(CacheEntryEvent e) {
	    	  if (!e.isPre()){
	    	    System.out.printf("Thread %s has removed an entry in the cache named %s under key %s!\n",
		                           Thread.currentThread().getName(), e.getCache().getName(), e.getKey());
	    	    }
		  }

	      @CacheEntryVisited
	      public void printDetailsOnVisit(CacheEntryVisitedEvent e) {
	         System.out.printf("Thread %s has visited an entry in the cache named %s under key %s!\n",
	                           Thread.currentThread().getName(), e.getCache().getName(), e.getKey());
	      }
	   }
