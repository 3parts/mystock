package com.cxstock.pojo;



/**
 * Tbcommodity entity. @author MyEclipse Persistence Tools
 */

public class Tbcommodity  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String vcNo;
     private String vcName;
     private Integer typeId;
     private String vcDw;
     private String vcFactoryNo;
     private String vcFactoryName;
     private String vcColor;
     private String vcGg;
     private Double dbSuggMoney;
     private Double dbLowMoney;
     private Double dbAverageMoney;
     private Double dbLastMoney;
     private String vcRemark;
     private Integer companyId;


    // Constructors

    /** default constructor */
    public Tbcommodity() {
    }

    
    /** full constructor */
    public Tbcommodity(String vcNo, String vcName, Integer typeId, String vcDw, String vcFactoryNo, String vcFactoryName, String vcColor, String vcGg, Double dbSuggMoney, Double dbLowMoney, Double dbAverageMoney, Double dbLastMoney, String vcRemark, Integer companyId) {
        this.vcNo = vcNo;
        this.vcName = vcName;
        this.typeId = typeId;
        this.vcDw = vcDw;
        this.vcFactoryNo = vcFactoryNo;
        this.vcFactoryName = vcFactoryName;
        this.vcColor = vcColor;
        this.vcGg = vcGg;
        this.dbSuggMoney = dbSuggMoney;
        this.dbLowMoney = dbLowMoney;
        this.dbAverageMoney = dbAverageMoney;
        this.dbLastMoney = dbLastMoney;
        this.vcRemark = vcRemark;
        this.companyId = companyId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getVcNo() {
        return this.vcNo;
    }
    
    public void setVcNo(String vcNo) {
        this.vcNo = vcNo;
    }

    public String getVcName() {
        return this.vcName;
    }
    
    public void setVcName(String vcName) {
        this.vcName = vcName;
    }

    public Integer getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getVcDw() {
        return this.vcDw;
    }
    
    public void setVcDw(String vcDw) {
        this.vcDw = vcDw;
    }

    public String getVcFactoryNo() {
        return this.vcFactoryNo;
    }
    
    public void setVcFactoryNo(String vcFactoryNo) {
        this.vcFactoryNo = vcFactoryNo;
    }

    public String getVcFactoryName() {
        return this.vcFactoryName;
    }
    
    public void setVcFactoryName(String vcFactoryName) {
        this.vcFactoryName = vcFactoryName;
    }

    public String getVcColor() {
        return this.vcColor;
    }
    
    public void setVcColor(String vcColor) {
        this.vcColor = vcColor;
    }

    public String getVcGg() {
        return this.vcGg;
    }
    
    public void setVcGg(String vcGg) {
        this.vcGg = vcGg;
    }

    public Double getDbSuggMoney() {
        return this.dbSuggMoney;
    }
    
    public void setDbSuggMoney(Double dbSuggMoney) {
        this.dbSuggMoney = dbSuggMoney;
    }

    public Double getDbLowMoney() {
        return this.dbLowMoney;
    }
    
    public void setDbLowMoney(Double dbLowMoney) {
        this.dbLowMoney = dbLowMoney;
    }

    public Double getDbAverageMoney() {
        return this.dbAverageMoney;
    }
    
    public void setDbAverageMoney(Double dbAverageMoney) {
        this.dbAverageMoney = dbAverageMoney;
    }

    public Double getDbLastMoney() {
        return this.dbLastMoney;
    }
    
    public void setDbLastMoney(Double dbLastMoney) {
        this.dbLastMoney = dbLastMoney;
    }

    public String getVcRemark() {
        return this.vcRemark;
    }
    
    public void setVcRemark(String vcRemark) {
        this.vcRemark = vcRemark;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }
    
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
   








}