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
public class ProvinceTotal
{
    private String header;
    private String province;
    private Double pstTotal;
    private Double retroactivePstTotal;

    public ProvinceTotal()
    {
    }

    public ProvinceTotal( String header, String province, Double pstTotal, Double retroactivePstTotal )
    {
        this.header = header;
        this.province = province;
        this.pstTotal = pstTotal;
        this.retroactivePstTotal = retroactivePstTotal;
    }
    
    
    

    public ProvinceTotal( String Province, Double pstTotal, Double retroactivePstTotal )
    {
        this.header = "";
        this.province = Province;
        this.pstTotal = pstTotal;
        this.retroactivePstTotal = retroactivePstTotal;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince( String province )
    {
        this.province = province;
    }


    public Double getPstTotal()
    {
        return pstTotal;
    }

    public void setPstTotal( Double pstTotal )
    {
        this.pstTotal = pstTotal;
    }

    public Double getRetroactivePstTotal()
    {
        return retroactivePstTotal;
    }

    public void setRetroactivePstTotal( Double retroactivePstTotal )
    {
        this.retroactivePstTotal = retroactivePstTotal;
    }

    public String getHeader()
    {
        return header;
    }

    public void setHeader( String header )
    {
        this.header = header;
    }
     
    
    
}
