package com.yyt.cdyy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "r_cdyy_payment", schema = "ydw")
public class Pay {

  private Integer dateId;

  private String branchCode;

  private String branchName;

  @Id
  private String saleCode;

  private String saleName;

  private String postName;

  private Integer sxhKhs;

  private Integer sxhPayment;

  private Integer sjyhKhs;

  private Integer sjyhPayment;

  private Integer dzzhKhs;

  private Integer dzzhPayment;

  private Integer xykKhs;

  private Integer xykPayment;

  private Integer defqkKhs;

  private Integer defqkPayment;

  private Integer defqQhKhs;

  private Integer defqQhPayment;

  private Integer dzsbkKhs;

  private Integer dzsbkPayment;

  private Integer dzybpzKhs;

  private Integer dzybpzPayment;

  private Integer smkJhKhs;

  private Integer smkJhPayment;

  private Integer etcKhs;

  private Integer etcPayment;

  private Integer smkXzKhs;

  private Integer smkXzPayment;

  private Integer znfKhs;

  private Integer znfPayment;

  private Integer fsymtKhs;

  private Integer fsymtPayment;

  private Integer bmzzzdKhs;

  private Integer bmzzzdPayment;

  private Integer paymentAll;

  private Integer qywyKhs;

  private Integer qywyJc;

  private Integer whzhXzKhs;

  private Integer whzhXzJc;

  private Integer whzhClKhs;

  private Integer whzhClJc;

  private Integer wbjslKhs;

  private Integer wbjslJc;

  private Integer fftKhs;

  private Integer fftJc;

  private Integer yljzhKhs;

  private Integer yljzhJc;

}
