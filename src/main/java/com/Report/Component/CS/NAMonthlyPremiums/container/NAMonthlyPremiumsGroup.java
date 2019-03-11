/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.NAMonthlyPremiums.container;

import java.sql.Date;

/**
 *
 * @author craig.deacon
 */
public class NAMonthlyPremiumsGroup
{
    private String policySeries; //group
    private String policyAccount; //Acct
    private String employer; //ER name
    private Date month; //month_id
    private String product; 
    private int benefitId; //does not appear in report
    private Float premium; //current premium
    private Float premiumRate; //Rate
    private String province;
    private int employeeCount; //:lives
    private int volume;    
    private Float pst; //current_tax    
    private Float retroactivePremium; // adjustment premium
    private Float retroactivePst; //adjustment tax
    private Float grossPremium; //gross premium
    private Float administrationAmount; //admin fee
    private Float commissionAmount; // commission
    private Float netPremium; // total net premium
    private Float administrationRate; //admin rate
    private Float commissionRate; //commission rate

    public NAMonthlyPremiumsGroup()
    {
    }

    public String getPolicySeries()
    {
        return policySeries;
    }

    public void setPolicySeries( String policySeries )
    {
        this.policySeries = policySeries;
    }

    public String getPolicyAccount()
    {
        return policyAccount;
    }

    public void setPolicyAccount( String policyAccount )
    {
        this.policyAccount = policyAccount;
    }

    public String getEmployer()
    {
        return employer;
    }

    public void setEmployer( String employer )
    {
        this.employer = employer;
    }

    public Date getMonth()
    {
        return month;
    }

    public void setMonth( Date month )
    {
        this.month = month;
    }

    public String getProduct()
    {
        return product;
    }

    public void setProduct( String product )
    {
        this.product = product;
    }

    public Float getPremiumRate()
    {
        return premiumRate;
    }

    public void setPremiumRate( Float premiumRate )
    {
        this.premiumRate = premiumRate;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince( String province )
    {
        this.province = province;
    }

    public int getEmployeeCount()
    {
        return employeeCount;
    }

    public void setEmployeeCount( int employeeCount )
    {
        this.employeeCount = employeeCount;
    }

    public int getVolume()
    {
        return volume;
    }

    public void setVolume( int volume )
    {
        this.volume = volume;
    }

    public Float getPremium()
    {
        return premium;
    }

    public void setPremium( Float premium )
    {
        this.premium = premium;
    }

    public Float getPst()
    {
        return pst;
    }

    public void setPst( Float pst )
    {
        this.pst = pst;
    }

    public Float getRetroactivePremium()
    {
        return retroactivePremium;
    }

    public void setRetroactivePremium( Float retroactivePremium )
    {
        this.retroactivePremium = retroactivePremium;
    }

    public Float getRetroactivePst()
    {
        return retroactivePst;
    }

    public void setRetroactivePst( Float retroactivePst )
    {
        this.retroactivePst = retroactivePst;
    }

    public Float getGrossPremium()
    {
        return grossPremium;
    }

    public void setGrossPremium( Float grossPremium )
    {
        this.grossPremium = grossPremium;
    }

    public Float getAdministrationAmount()
    {
        return administrationAmount;
    }

    public void setAdministrationAmount( Float administrationAmount )
    {
        this.administrationAmount = administrationAmount;
    }

    public Float getCommissionAmount()
    {
        return commissionAmount;
    }

    public void setCommissionAmount( Float commissionAmount )
    {
        this.commissionAmount = commissionAmount;
    }

    public Float getNetPremium()
    {
        return netPremium;
    }

    public void setNetPremium( Float netPremium )
    {
        this.netPremium = netPremium;
    }

    public Float getAdministrationRate()
    {
        return administrationRate;
    }

    public void setAdministrationRate( Float administrationRate )
    {
        this.administrationRate = administrationRate;
    }

    public Float getCommissionRate()
    {
        return commissionRate;
    }

    public void setCommissionRate( Float commissionRate )
    {
        this.commissionRate = commissionRate;
    }
    
    public int getBenefitId()
    {
        return benefitId;
    }

    public void setBenefitId( int benefitId )
    {
        this.benefitId = benefitId;
    }
    
    
    
    
}
