package com.spdx.hms.util;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Component
public class AuthAccessToken {

	private static final Cache<String, String> authTokenCache;

	static {

		authTokenCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(10, TimeUnit.MINUTES).build();
	}

	public String getAuthToken(String tokenName) {
		return authTokenCache.getIfPresent(tokenName);
	}

	public void setAuthToken(String tokenName, String authToken) {
		authTokenCache.put(tokenName, authToken);
	}

	/*public void removeUserToken(String key) {
		// userTokenCacheMap.remove(key);
		ccTokenCache.invalidate(key);
	}*/

}
