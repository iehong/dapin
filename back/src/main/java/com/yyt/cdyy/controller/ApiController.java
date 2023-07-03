package com.yyt.cdyy.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yyt.cdyy.utils.CacheData;
import com.yyt.cdyy.resp.PayResp;

import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.date.DateUtil;

import com.yyt.cdyy.entity.Pay;

@RestController
@RequestMapping("api")
public class ApiController {

  @Autowired
  private CacheData cacheData;

  @Autowired
  private PayResp payResp;

  private Integer getDateId() {
    if (cacheData.dateCache.get("date") == null) {
      cacheData.dateCache.put("date", payResp.findDateId());
    }
    return cacheData.dateCache.get("date");
  }

  @GetMapping("all")
  public SaResult all() {
    if (cacheData.timedCache.get("all") == null) {
      cacheData.timedCache.put("all", payResp.findAll(getDateId()));
    }
    return SaResult.data(cacheData.timedCache.get("all"));
  }

  @GetMapping("total")
  public SaResult total() {
    if (cacheData.dateCache.get("total") == null) {
      cacheData.dateCache.put("total", payResp.getTotal(getDateId()));
    }
    return SaResult.data(cacheData.dateCache.get("total"));
  }

  @GetMapping("ranklh")
  public SaResult ranklh() {
    if (cacheData.timedCache.get("ranklh") == null) {
      cacheData.timedCache.put("ranklh",
          payResp.findRank(getDateId(), PageRequest.of(0, 10, Sort.Direction.DESC, "payment_all")));
    }
    return SaResult.data(cacheData.timedCache.get("ranklh"));
  }

  @GetMapping("rankhy")
  public SaResult rankhy() {
    if (cacheData.timedCache.get("rankhy") == null) {
      cacheData.timedCache.put("rankhy",
          payResp.findRank(getDateId(), PageRequest.of(0, 10, Sort.Direction.ASC, "payment_all")));
    }
    return SaResult.data(cacheData.timedCache.get("rankhy"));
  }

  @GetMapping("rankjb")
  public SaResult rankjb() {
    if (cacheData.timedCache.get("rankjb") == null) {
      List<Pay> tData = payResp.findData(getDateId());
      List<Pay> lData = payResp
          .findData(Integer.parseInt(
              DateUtil.offsetDay(DateUtil.beginOfMonth(DateUtil.parse(getDateId().toString(), "yyyyMMdd")), -1)
                  .toString("YYYYMMdd")));
      lData = lData.stream()
          .filter(a -> tData.stream().map(Pay::getSaleCode).anyMatch(code -> a.getSaleCode().equals(code)))
          .collect(Collectors.toList());
      tData.addAll(lData);
      cacheData.timedCache.put("rankjb",
          tData.stream().collect(Collectors.toMap(Pay::getSaleCode, a -> a, (o1, o2) -> {
            o1.setPaymentAll(o1.getPaymentAll() - o2.getPaymentAll());
            return o1;
          })).values().stream().sorted(Comparator.comparingInt(Pay::getPaymentAll).reversed()).limit(10).map(s -> {
            return new Object[] { s.getBranchName(), s.getSaleName(), s.getPaymentAll()
            };
          })
              .collect(Collectors.toList()));
    }
    return SaResult.data(cacheData.timedCache.get("rankjb"));
  }

}
