package com.yyt.cdyy.resp;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yyt.cdyy.entity.Pay;

public interface PayResp extends JpaRepository<Pay, Integer> {

  @Query("select max(dateId) from Pay")
  Integer findDateId();

  @Query(value = "select * from( select '随心花' as zbmc,sum(sxh_khs) as wcsl,sum(sxh_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '手机银行' as zbmc,sum(sjyh_khs) as wcsl,sum(sjyh_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '电子账户' as zbmc,sum(dzzh_khs) as wcsl,sum(dzzh_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '信用卡' as zbmc,sum(xyk_khs) as wcsl,sum(xyk_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '大额分期卡' as zbmc,sum(defqk_khs) as wcsl,sum(defqk_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '大额分期清户卡' as zbmc,sum(defq_qh_khs) as wcsl,sum(defq_qh_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '电子社保卡' as zbmc,sum(dzsbk_khs) as wcsl,sum(dzsbk_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '电子医保凭证' as zbmc,sum(dzybpz_khs) as wcsl,sum(dzybpz_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '市民卡激活' as zbmc,sum(smk_jh_khs) as wcsl,sum(smk_jh_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select 'ETC' as zbmc,sum(etc_khs) as wcsl,sum(etc_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '市民卡新增' as zbmc,sum(smk_xz_khs) as wcsl,sum(smk_xz_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select 'POS智能付' as zbmc,sum(znf_khs) as wcsl,sum(znf_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '丰收一码通' as zbmc,sum(fsymt_khs) as wcsl,sum(fsymt_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '便民自助终端' as zbmc,sum(bmzzzd_khs) as wcsl,sum(bmzzzd_payment) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '企业上银新开' as zbmc,sum(qywy_khs) as wcsl,sum(qywy_jc) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '新增外汇账户' as zbmc,sum(whzh_xz_khs) as wcsl,sum(whzh_xz_jc) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '存量外汇账户' as zbmc,sum(whzh_cl_khs) as wcsl,sum(whzh_cl_jc) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '外币结算量' as zbmc,sum(wbjsl_khs) as wcsl,sum(wbjsl_jc) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '关贸E贷福费廷' as zbmc,sum(fft_khs) as wcsl,sum(fft_jc) as jjje from ydw.r_cdyy_payment where date_id=?1 union all select '他行养老金转户' as zbmc,sum(yljzh_khs) as wcsl,sum(yljzh_jc) as jjje from ydw.r_cdyy_payment where date_id=?1) order by jjje desc", nativeQuery = true)
  List<Object> findAll(Integer dateId);

  @Query(value = "select branch_name,sale_name,payment_all from ydw.R_CDYY_PAYMENT where date_id = ?1 and branch_code not in ('808000','808003') and post_name not in ('系统','备用','行领导','部室总经理','部室副经理','部室经理助理','支行行长','支行副行长','支行行长助理','退休','退养','部室员工','返聘人员','外包人员') and left(branch_code,4) <> '8088' and left(sale_code,4) <> '9808'", nativeQuery = true)
  List<Object> findRank(Integer dateId, Pageable pageable);

  @Query("select sum(paymentAll) from Pay where dateId=?1")
  Integer getTotal(Integer dateId);

  @Query(value = "select * from ydw.r_cdyy_payment where date_id = ?1 and branch_code not in ('808000','808003') and post_name not in ('系统','备用','行领导','部室总经理','部室副经理','部室经理助理','支行行长','支行副行长','支行行长助理','退休','退养','部室员工','返聘人员','外包人员') and left(branch_code,4) <> '8088' and left(sale_code,4) <> '9808'", nativeQuery = true)
  List<Pay> findData(Integer dateId);

}
