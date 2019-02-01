/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.ME.ProvincialSalesTaxReport.container;

/**
 *
 * @author craig.deacon
 */
public class ProvincialGroup
{
    String groupName;
    String policyNum;
    Float taxPremiumOn;
    Float salesTaxOn;
    Float taxPremiumMa;
    Float salesTaxMa;
    Float taxPremiumQc;
    Float salesTaxQc;
    Float taxPremiumSa;
    Float salesTaxSa;

    public ProvincialGroup()
    {
        
    }
    
    public ProvincialGroup( String groupName, String policyNum, Float taxPremiumOn, Float salesTaxOn, Float taxPremiumMa, Float salesTaxMa, Float taxPremiumQc, Float salesTaxQc, Float taxPremiumSa, Float salesTaxSa )
    {
        this.groupName = groupName;
        this.policyNum = policyNum;
        this.taxPremiumOn = taxPremiumOn;
        this.salesTaxOn = salesTaxOn;
        this.taxPremiumMa = taxPremiumMa;
        this.salesTaxMa = salesTaxMa;
        this.taxPremiumQc = taxPremiumQc;
        this.salesTaxQc = salesTaxQc;
        this.taxPremiumSa = taxPremiumSa;
        this.salesTaxSa = salesTaxSa;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }

    public String getPolicyNum()
    {
        return policyNum;
    }

    public void setPolicyNum( String policyNum )
    {
        this.policyNum = policyNum;
    }

    public Float getTaxPremiumOn()
    {
        return taxPremiumOn;
    }

    public void setTaxPremiumOn( Float taxPremiumOn )
    {
        this.taxPremiumOn = taxPremiumOn;
    }

    public Float getSalesTaxOn()
    {
        return salesTaxOn;
    }

    public void setSalesTaxOn( Float salesTaxOn )
    {
        this.salesTaxOn = salesTaxOn;
    }

    public Float getTaxPremiumMa()
    {
        return taxPremiumMa;
    }

    public void setTaxPremiumMa( Float taxPremiumMa )
    {
        this.taxPremiumMa = taxPremiumMa;
    }

    public Float getSalesTaxMa()
    {
        return salesTaxMa;
    }

    public void setSalesTaxMa( Float salesTaxMa )
    {
        this.salesTaxMa = salesTaxMa;
    }

    public Float getTaxPremiumQc()
    {
        return taxPremiumQc;
    }

    public void setTaxPremiumQc( Float taxPremiumQc )
    {
        this.taxPremiumQc = taxPremiumQc;
    }

    public Float getSalesTaxQc()
    {
        return salesTaxQc;
    }

    public void setSalesTaxQc( Float salesTaxQc )
    {
        this.salesTaxQc = salesTaxQc;
    }

    public Float getTaxPremiumSa()
    {
        return taxPremiumSa;
    }

    public void setTaxPremiumSa( Float taxPremiumSa )
    {
        this.taxPremiumSa = taxPremiumSa;
    }

    public Float getSalesTaxSa()
    {
        return salesTaxSa;
    }

    public void setSalesTaxSa( Float salesTaxSa )
    {
        this.salesTaxSa = salesTaxSa;
    }
    
    
    }

//AbstractColumn groupName = CreateColumnString("groupName", "Group Name", 60 );
//           AbstractColumn policyNum = CreateColumnString("policyNum", "Policy Number", 40 );
//           AbstractColumn taxPremiumOn = CreateColumnFloat("taxPremiumOn", "Taxable Premium", 40);
//           AbstractColumn salesTaxOn = CreateColumnFloat("salesTaxOn", "Sales Tax", 40);
//           AbstractColumn taxPremiumMa = CreateColumnFloat("taxPremiumMa", "Taxable Premium", 40);
//           AbstractColumn salesTaxMa = CreateColumnFloat("salesTaxMa", "Sales Tax", 40);
//           AbstractColumn taxPremiumQc = CreateColumnFloat("taxPremiumQc", "Taxable Premium", 40);
//           AbstractColumn salesTaxQc = CreateColumnFloat("salesTaxQc", "Sales Tax", 40);
//           AbstractColumn taxPremiumSa = CreateColumnFloat("taxPremiumSa", "Taxable Premium", 40);
//           AbstractColumn salesTaxSa = CreateColumnFloat("salesTaxSa", "Sales Tax", 40);
