package com.yyt.cdyy.utils;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CacheData {
  public TimedCache<String, List<Object>> timedCache = CacheUtil.newTimedCache(1000 * 3600 * 5);

  public TimedCache<String, Integer> dateCache = CacheUtil.newTimedCache(1000 * 3600 * 10);
}
