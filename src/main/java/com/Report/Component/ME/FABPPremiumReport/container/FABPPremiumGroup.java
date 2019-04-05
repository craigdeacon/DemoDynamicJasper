/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.ME.FABPPremiumReport.container;

/**
 *
 * @author craig.deacon
 */
public class FABPPremiumGroup
{
    private String groupName;
    private String underwriter;
    private double grossPremiums;
    private double commission;
    private double admin;
    private double netPremiums;
    private double onPst;
    private double qcPst;
    private double skPst;

    public FABPPremiumGroup()
    {
    }

    public FABPPremiumGroup( String groupName, String underwriter, double grossPremiums, double commission, double admin, double netPremiums, double onPst, double qcPst, double skPst )
    {
        this.groupName = groupName;
        this.underwriter = underwriter;
        this.grossPremiums = grossPremiums;
        this.commission = commission;
        this.admin = admin;
        this.netPremiums = netPremiums;
        this.onPst = onPst;
        this.qcPst = qcPst;
        this.skPst = skPst;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }

    public String getUnderwriter()
    {
        return underwriter;
    }

    public void setUnderwriter( String underwriter )
    {
        this.underwriter = underwriter;
    }

    public double getGrossPremiums()
    {
        return grossPremiums;
    }

    public void setGrossPremiums( double grossPremiums )
    {
        this.grossPremiums = grossPremiums;
    }

    public double getCommission()
    {
        return commission;
    }

    public void setCommission( double commission )
    {
        this.commission = commission;
    }

    public double getAdmin()
    {
        return admin;
    }

    public void setAdmin( double admin )
    {
        this.admin = admin;
    }

    public double getNetPremiums()
    {
        return netPremiums;
    }

    public void setNetPremiums( double netPremiums )
    {
        this.netPremiums = netPremiums;
    }

    public double getOnPst()
    {
        return onPst;
    }

    public void setOnPst( double onPst )
    {
        this.onPst = onPst;
    }

    public double getQcPst()
    {
        return qcPst;
    }

    public void setQcPst( double qcPst )
    {
        this.qcPst = qcPst;
    }

    public double getSkPst()
    {
        return skPst;
    }

    public void setSkPst( double skPst )
    {
        this.skPst = skPst;
    }
    
    
}
