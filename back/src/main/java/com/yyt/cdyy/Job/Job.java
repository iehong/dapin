package com.yyt.cdyy.Job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.yyt.cdyy.utils.CacheData;

@Component
@EnableScheduling
public class Job {

  @Autowired
  private CacheData cacheData;

  @Scheduled(cron = "0 0 0 * * ?")
  public void run() {
    cacheData.timedCache.clear();
    cacheData.dateCache.clear();
  }
}
