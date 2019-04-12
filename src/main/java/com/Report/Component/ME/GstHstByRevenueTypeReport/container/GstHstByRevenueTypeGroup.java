/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.ME.GstHstByRevenueTypeReport.container;

/**
 *
 * @author craig.deacon
 */
public class GstHstByRevenueTypeGroup
{
    String groupName;
    String province;
    Double hsaPremium;
    Double hsaGst;
    Double asoPremium;
    Double asoGst;
    Double otherPremium;
    Double otherGst;
    Double totalGst;

    public GstHstByRevenueTypeGroup()
    {
    }

    public GstHstByRevenueTypeGroup( String groupName, String province, Double hsaPremium, Double hsaGst, Double asoPremium, Double asoGst, Double otherPremium, Double otherGst, Double totalGst )
    {
        this.groupName = groupName;
        this.province = province;
        this.hsaPremium = hsaPremium;
        this.hsaGst = hsaGst;
        this.asoPremium = asoPremium;
        this.asoGst = asoGst;
        this.otherPremium = otherPremium;
        this.otherGst = otherGst;
        this.totalGst = totalGst;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince( String province )
    {
        this.province = province;
    }

    public Double getHsaPremium()
    {
        return hsaPremium;
    }

    public void setHsaPremium( Double hsaPremium )
    {
        this.hsaPremium = hsaPremium;
    }

    public Double getHsaGst()
    {
        return hsaGst;
    }

    public void setHsaGst( Double hsaGst )
    {
        this.hsaGst = hsaGst;
    }

    public Double getAsoPremium()
    {
        return asoPremium;
    }

    public void setAsoPremium( Double asoPremium )
    {
        this.asoPremium = asoPremium;
    }

    public Double getAsoGst()
    {
        return asoGst;
    }

    public void setAsoGst( Double asoGst )
    {
        this.asoGst = asoGst;
    }

    public Double getOtherPremium()
    {
        return otherPremium;
    }

    public void setOtherPremium( Double otherPremium )
    {
        this.otherPremium = otherPremium;
    }

    public Double getOtherGst()
    {
        return otherGst;
    }

    public void setOtherGst( Double otherGst )
    {
        this.otherGst = otherGst;
    }

    public Double getTotalGst()
    {
        return totalGst;
    }

    public void setTotalGst( Double totalGst )
    {
        this.totalGst = totalGst;
    }
    
    
}
