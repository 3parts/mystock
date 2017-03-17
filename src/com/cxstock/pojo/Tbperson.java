package com.cxstock.pojo;



/**
 * Tbperson entity. @author MyEclipse Persistence Tools
 */

public class Tbperson  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String vcNo;
     private String vcName;
     private Integer igender;
     private String vcNation;
     private String vcIdCard;
     private String vcAddress;
     private Integer positionId;
     private Integer icomminSsion;
     private String vcTel;
     private Integer istate;
     private String dtEntry;
     private String dtQuit;
     private String vcQuitReason;
     private String vcRemark;
     private Integer companyId;


    // Constructors

    /** default constructor */
    public Tbperson() {
    }

    
    /** full constructor */
    public Tbperson(String vcNo, String vcName, Integer igender, String vcNation, String vcIdCard, String vcAddress, Integer positionId, Integer icomminSsion, String vcTel, Integer istate, String dtEntry, String dtQuit, String vcQuitReason, String vcRemark, Integer companyId) {
        this.vcNo = vcNo;
        this.vcName = vcName;
        this.igender = igender;
        this.vcNation = vcNation;
        this.vcIdCard = vcIdCard;
        this.vcAddress = vcAddress;
        this.positionId = positionId;
        this.icomminSsion = icomminSsion;
        this.vcTel = vcTel;
        this.istate = istate;
        this.dtEntry = dtEntry;
        this.dtQuit = dtQuit;
        this.vcQuitReason = vcQuitReason;
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

    public Integer getIgender() {
        return this.igender;
    }
    
    public void setIgender(Integer igender) {
        this.igender = igender;
    }

    public String getVcNation() {
        return this.vcNation;
    }
    
    public void setVcNation(String vcNation) {
        this.vcNation = vcNation;
    }

    public String getVcIdCard() {
        return this.vcIdCard;
    }
    
    public void setVcIdCard(String vcIdCard) {
        this.vcIdCard = vcIdCard;
    }

    public String getVcAddress() {
        return this.vcAddress;
    }
    
    public void setVcAddress(String vcAddress) {
        this.vcAddress = vcAddress;
    }

    public Integer getPositionId() {
        return this.positionId;
    }
    
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getIcomminSsion() {
        return this.icomminSsion;
    }
    
    public void setIcomminSsion(Integer icomminSsion) {
        this.icomminSsion = icomminSsion;
    }

    public String getVcTel() {
        return this.vcTel;
    }
    
    public void setVcTel(String vcTel) {
        this.vcTel = vcTel;
    }

    public Integer getIstate() {
        return this.istate;
    }
    
    public void setIstate(Integer istate) {
        this.istate = istate;
    }

    public String getDtEntry() {
        return this.dtEntry;
    }
    
    public void setDtEntry(String dtEntry) {
        this.dtEntry = dtEntry;
    }

    public String getDtQuit() {
        return this.dtQuit;
    }
    
    public void setDtQuit(String dtQuit) {
        this.dtQuit = dtQuit;
    }

    public String getVcQuitReason() {
        return this.vcQuitReason;
    }
    
    public void setVcQuitReason(String vcQuitReason) {
        this.vcQuitReason = vcQuitReason;
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