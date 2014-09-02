package org.hamster.weixinmp.cache;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeoutException;

public class MemCachedCacheService implements CacheService {
	private static final Logger logger= LoggerFactory.getLogger(MemCachedCacheService.class);

	MemcachedClient memcachedClient;
	
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

    public <T> boolean add(String key,T t) {
        return add(key,null,t);
    }
    public <T> boolean add(String key, Integer exp, T t) {
        if (exp == null) {
            exp = 0;
        }
        try {
            return memcachedClient.add(key, exp, t);
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
            return false;
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            return false;
        } catch (MemcachedException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

	public <T> void  set(String key, T t) {
		try {
			memcachedClient.set(key, 0, t);
		} catch (TimeoutException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} catch (MemcachedException e) {
			logger.error(e.getMessage());
		}
	}



	public <T> void set(String key, Integer exp, T t) {
		if (exp == null) {
			exp = 0;
		}
		try {
			memcachedClient.set(key, exp, t);
		} catch (TimeoutException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} catch (MemcachedException e) {
			logger.error(e.getMessage());
		}
	}

	public <T> T get(String key) {
		T t = null;
		try {
			t = memcachedClient.get(key);
		} catch (TimeoutException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} catch (MemcachedException e) {
			logger.error(e.getMessage());
		}
		return t;
	}

	public <T> void checkToSet(String key, T t) {
		T temp = null;
		try {
			temp = memcachedClient.get(key);
		} catch (TimeoutException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} catch (MemcachedException e) {
			logger.error(e.getMessage());
		}
		if (temp == null) {
			set(key, t);
		}
	}

	public void delete(String key) {
		try {
			memcachedClient.deleteWithNoReply(key);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} catch (MemcachedException e) {
			logger.error(e.getMessage());
		}
	}

	public void flushAll() {
		try {
			memcachedClient.flushAll();
		} catch (TimeoutException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} catch (MemcachedException e) {
			logger.error(e.getMessage());
		}
	}
}
