/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.NAMonthlyPremiums.container;

/**
 *
 * @author craig.deacon
 */
public class ProductTotal
{
    private String header;
    private String productName;
    private Double premiumTotal; //current premium
    private Double pstTotal; //current_tax
    private Double retroactivePremiumTotal; // adjustment premium
    private Double retroactivePstTotal; //adjustment tax
    private Double grossPremiumTotal; //gross premium
    private Double administrationAmountTotal; //admin fee
    private Double commissionAmountTotal; // commission
    private Double netPremiumTotal; // total net premium
    private Double gstTotal; //

    public ProductTotal()
    {
    }

    public String getHeader()
    {
        return header;
    }

    public void setHeader( String header )
    {
        this.header = header;
    }
        

    public ProductTotal( String productName, Double premiumTotal, Double pstTotal, Double retroactivePremiumTotal, Double retroactivePstTotal, Double grossPremiumTotal, Double administrationAmountTotal, Double commissionAmountTotal, Double netPremiumTotal, Double gstTotal )
    {
        this.header = "";
        this.productName = productName;
        this.premiumTotal = premiumTotal;
        this.pstTotal = pstTotal;
        this.retroactivePremiumTotal = retroactivePremiumTotal;
        this.retroactivePstTotal = retroactivePstTotal;
        this.grossPremiumTotal = grossPremiumTotal;
        this.administrationAmountTotal = administrationAmountTotal;
        this.commissionAmountTotal = commissionAmountTotal;
        this.netPremiumTotal = netPremiumTotal;
        this.gstTotal = gstTotal;
    }

    public ProductTotal( String header, String productName )
    {
        this.header = header;
        this.productName = productName;
        this.premiumTotal = 0.;
        this.pstTotal = 0.;
        this.retroactivePremiumTotal = 0.;
        this.retroactivePstTotal = 0.;
        this.grossPremiumTotal = 0.;
        this.administrationAmountTotal = 0.;
        this.commissionAmountTotal = 0.;
        this.netPremiumTotal = 0.;
        this.gstTotal = 0.;
    }

    public ProductTotal( String header, String productName, Double premiumTotal, Double pstTotal, Double retroactivePremiumTotal, Double retroactivePstTotal, Double grossPremiumTotal, Double administrationAmountTotal, Double commissionAmountTotal, Double netPremiumTotal, Double gstTotal )
    {
        this.header = header;
        this.productName = productName;
        this.premiumTotal = premiumTotal;
        this.pstTotal = pstTotal;
        this.retroactivePremiumTotal = retroactivePremiumTotal;
        this.retroactivePstTotal = retroactivePstTotal;
        this.grossPremiumTotal = grossPremiumTotal;
        this.administrationAmountTotal = administrationAmountTotal;
        this.commissionAmountTotal = commissionAmountTotal;
        this.netPremiumTotal = netPremiumTotal;
        this.gstTotal = gstTotal;
    }
    
    
    

    public String getProductName()
    {
        return productName;
    }

    public void setProductName( String productName )
    {
        this.productName = productName;
    }

    public Double getPremiumTotal()
    {
        return premiumTotal;
    }

    public void setPremiumTotal( Double premiumTotal )
    {
        this.premiumTotal = premiumTotal;
    }

    public Double getPstTotal()
    {
        return pstTotal;
    }

    public void setPstTotal( Double pstTotal )
    {
        this.pstTotal = pstTotal;
    }

    public Double getRetroactivePremiumTotal()
    {
        return retroactivePremiumTotal;
    }

    public void setRetroactivePremiumTotal( Double retroactivePremiumTotal )
    {
        this.retroactivePremiumTotal = retroactivePremiumTotal;
    }

    public Double getRetroactivePstTotal()
    {
        return retroactivePstTotal;
    }

    public void setRetroactivePstTotal( Double retroactivePstTotal )
    {
        this.retroactivePstTotal = retroactivePstTotal;
    }

    public Double getGrossPremiumTotal()
    {
        return grossPremiumTotal;
    }

    public void setGrossPremiumTotal( Double grossPremiumTotal )
    {
        this.grossPremiumTotal = grossPremiumTotal;
    }

    public Double getAdministrationAmountTotal()
    {
        return administrationAmountTotal;
    }

    public void setAdministrationAmountTotal( Double administrationAmountTotal )
    {
        this.administrationAmountTotal = administrationAmountTotal;
    }

    public Double getCommissionAmountTotal()
    {
        return commissionAmountTotal;
    }

    public void setCommissionAmountTotal( Double commissionAmountTotal )
    {
        this.commissionAmountTotal = commissionAmountTotal;
    }

    public Double getNetPremiumTotal()
    {
        return netPremiumTotal;
    }

    public void setNetPremiumTotal( Double netPremiumTotal )
    {
        this.netPremiumTotal = netPremiumTotal;
    }

    public Double getGstTotal()
    {
        return gstTotal;
    }

    public void setGstTotal( Double gstTotal )
    {
        this.gstTotal = gstTotal;
    }
    
    
    
}
