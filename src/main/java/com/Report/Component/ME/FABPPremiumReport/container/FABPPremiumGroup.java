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
    private Double grossPremiums;
    private Double commission;
    private Double admin;
    private Double netPremiums;
    private Double onPst;
    private Double qcPst;
    private Double skPst;

    public FABPPremiumGroup()
    {
    }

    public FABPPremiumGroup( String groupName, String underwriter, Double grossPremiums, Double commission, Double admin, Double netPremiums, Double onPst, Double qcPst, Double skPst )
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

    public Double getGrossPremiums()
    {
        return grossPremiums;
    }

    public void setGrossPremiums( Double grossPremiums )
    {
        this.grossPremiums = grossPremiums;
    }

    public Double getCommission()
    {
        return commission;
    }

    public void setCommission( Double commission )
    {
        this.commission = commission;
    }

    public Double getAdmin()
    {
        return admin;
    }

    public void setAdmin( Double admin )
    {
        this.admin = admin;
    }

    public Double getNetPremiums()
    {
        return netPremiums;
    }

    public void setNetPremiums( Double netPremiums )
    {
        this.netPremiums = netPremiums;
    }

    public Double getOnPst()
    {
        return onPst;
    }

    public void setOnPst( Double onPst )
    {
        this.onPst = onPst;
    }

    public Double getQcPst()
    {
        return qcPst;
    }

    public void setQcPst( Double qcPst )
    {
        this.qcPst = qcPst;
    }

    public Double getSkPst()
    {
        return skPst;
    }

    public void setSkPst( Double skPst )
    {
        this.skPst = skPst;
    }
    
    
}
