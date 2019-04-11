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
    private Double premium; //current premium
    private Double premiumRate; //Rate
    private String province;
    private int employeeCount; //:lives
    private int volume;    
    private Double pst; //current_tax    
    private Double retroactivePremium; // adjustment premium
    private Double retroactivePst; //adjustment tax
    private Double grossPremium; //gross premium
    private Double administrationAmount; //admin fee
    private Double commissionAmount; // commission
    private Double netPremium; // total net premium
    private Double gst; //
    private Double administrationRate; //admin rate
    private Double commissionRate; //commission rate

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

    public Double getPremiumRate()
    {
        return premiumRate;
    }

    public void setPremiumRate( Double premiumRate )
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

    public Double getPremium()
    {
        return premium;
    }

    public void setPremium( Double premium )
    {
        this.premium = premium;
    }

    public Double getPst()
    {
        return pst;
    }

    public void setPst( Double pst )
    {
        this.pst = pst;
    }

    public Double getRetroactivePremium()
    {
        return retroactivePremium;
    }

    public void setRetroactivePremium( Double retroactivePremium )
    {
        this.retroactivePremium = retroactivePremium;
    }

    public Double getRetroactivePst()
    {
        return retroactivePst;
    }

    public void setRetroactivePst( Double retroactivePst )
    {
        this.retroactivePst = retroactivePst;
    }

    public Double getGrossPremium()
    {
        return grossPremium;
    }

    public void setGrossPremium( Double grossPremium )
    {
        this.grossPremium = grossPremium;
    }

    public Double getAdministrationAmount()
    {
        return administrationAmount;
    }

    public void setAdministrationAmount( Double administrationAmount )
    {
        this.administrationAmount = administrationAmount;
    }

    public Double getCommissionAmount()
    {
        return commissionAmount;
    }

    public void setCommissionAmount( Double commissionAmount )
    {
        this.commissionAmount = commissionAmount;
    }

    public Double getNetPremium()
    {
        return netPremium;
    }

    public void setNetPremium( Double netPremium )
    {
        this.netPremium = netPremium;
    }

    public Double getAdministrationRate()
    {
        return administrationRate;
    }

    public void setAdministrationRate( Double administrationRate )
    {
        this.administrationRate = administrationRate;
    }

    public Double getCommissionRate()
    {
        return commissionRate;
    }

    public void setCommissionRate( Double commissionRate )
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

    public Double getGst()
    {
        return gst;
    }

    public void setGst( Double gst )
    {
        this.gst = gst;
    }

    public NAMonthlyPremiumsGroup( String policySeries, String policyAccount, String employer, Date month, String product, int benefitId, Double premium, Double premiumRate, String province, int employeeCount, int volume, Double pst, Double retroactivePremium, Double retroactivePst, Double grossPremium, Double administrationAmount, Double commissionAmount, Double netPremium, Double gst, Double administrationRate, Double commissionRate )
    {
        this.policySeries = policySeries;
        this.policyAccount = policyAccount;
        this.employer = employer;
        this.month = month;
        this.product = product;
        this.benefitId = benefitId;
        this.premium = premium;
        this.premiumRate = premiumRate;
        this.province = province;
        this.employeeCount = employeeCount;
        this.volume = volume;
        this.pst = pst;
        this.retroactivePremium = retroactivePremium;
        this.retroactivePst = retroactivePst;
        this.grossPremium = grossPremium;
        this.administrationAmount = administrationAmount;
        this.commissionAmount = commissionAmount;
        this.netPremium = netPremium;
        this.gst = gst;
        this.administrationRate = administrationRate;
        this.commissionRate = commissionRate;
    }
    
    
    
    
}
