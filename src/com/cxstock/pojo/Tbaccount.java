package com.cxstock.pojo;



/**
 * Tbaccount entity. @author MyEclipse Persistence Tools
 */

public class Tbaccount  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String vcNo;
     private String vcName;
     private Integer itype;
     private String vcRemark;
     private Integer companyId;


    // Constructors

    /** default constructor */
    public Tbaccount() {
    }

    
    /** full constructor */
    public Tbaccount(String vcNo, String vcName, Integer itype, String vcRemark, Integer companyId) {
        this.vcNo = vcNo;
        this.vcName = vcName;
        this.itype = itype;
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

    public Integer getItype() {
        return this.itype;
    }
    
    public void setItype(Integer itype) {
        this.itype = itype;
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