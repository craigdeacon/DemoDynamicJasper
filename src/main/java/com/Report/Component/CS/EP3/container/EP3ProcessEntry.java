/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.EP3.container;

import java.sql.Date;

/**
 *
 * @author craig.deacon
 */
public class EP3ProcessEntry
{

    public EP3ProcessEntry( String greenShieldDivision, String billingDivisionName, Date renewalMonth, Date initialEffective, String poolingThreshold, String poolingBasis, String exclusion, Date terminationDate, Date dateEntered )
    {
        this.greenShieldDivision = greenShieldDivision;
        this.billingDivisionName = billingDivisionName;
        this.renewalMonth = renewalMonth;
        this.initialEffective = initialEffective;
        this.poolingThreshold = poolingThreshold;
        this.poolingBasis = poolingBasis;
        this.exclusion = exclusion;
        this.terminationDate = terminationDate;
        this.dateEntered = dateEntered;
    }

    public EP3ProcessEntry()
    {
    }

    private String greenShieldDivision;
    private String billingDivisionName;
    private Date renewalMonth;
    private Date initialEffective;
    private String poolingThreshold;
    private String poolingBasis;
    private String exclusion;
    private Date terminationDate;
    private Date dateEntered;
    private Integer groupId;

    public Integer getGroupId()
    {
        return groupId;
    }

    public void setGroupId( Integer groupId )
    {
        this.groupId = groupId;
    }

    public String getGreenShieldDivision()
    {
        return greenShieldDivision;
    }

    public void setGreenShieldDivision( String greenShieldDivision )
    {
        this.greenShieldDivision = greenShieldDivision;
    }

    public String getBillingDivisionName()
    {
        return billingDivisionName;
    }

    public void setBillingDivisionName( String billingDivisionName )
    {
        this.billingDivisionName = billingDivisionName;
    }

    public String getRenewalMonth()
    {
        return renewalMonth.toString();
    }

    public void setRenewalMonth( Date renewalMonth )
    {
        this.renewalMonth = renewalMonth;
    }

    public String getInitialEffective()
    {
        return initialEffective.toString();
    }

    public void setInitialEffective( Date initialEffective )
    {
        this.initialEffective = initialEffective;
    }

    public String getPoolingThreshold()
    {
        return poolingThreshold;
    }

    public void setPoolingThreshold( String poolingThreshold )
    {
        this.poolingThreshold = poolingThreshold;
    }

    public String getPoolingBasis()
    {
        return poolingBasis;
    }

    public void setPoolingBasis( String poolingBasis )
    {
        this.poolingBasis = poolingBasis;
    }

    public String getExclusion()
    {
        return exclusion;
    }

    public void setExclusion( String exclusion )
    {
        this.exclusion = exclusion;
    }

    public String getTerminationDate()
    {
        return terminationDate.toString();
    }

    public void setTerminationDate( Date terminationDate )
    {
        this.terminationDate = terminationDate;
    }

    public String getDateEntered()
    {
        return dateEntered.toString();
    }

    public void setDateEntered( Date dateEntered )
    {
        this.dateEntered = dateEntered;
    }

    /**
     * @return the values of it's parameters.
     */
    @Override
    public String toString()
    {
        return greenShieldDivision + " "
                + billingDivisionName + " "
                + renewalMonth + " "
                + initialEffective + " "
                + poolingThreshold + " "
                + poolingBasis + " "
                + exclusion + " "
                + terminationDate + " "
                + dateEntered;
    }
}
