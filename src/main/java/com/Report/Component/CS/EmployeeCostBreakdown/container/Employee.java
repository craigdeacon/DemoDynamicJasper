/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.EmployeeCostBreakdown.container;

/**
 *
 * @author craig.deacon
 */
public class Employee
{
    private String name;
    private Double life;
    private Double add;
    private Double dLife;
    private Double std;
    private Double ltd;
    private Double ci;
    private Double depCi;
    private Double ehb;
    private Double dental;
    private Double hcsa;
    private Double psa;
    private Double eap;
    private Double dsai;
    private Double total; 

    /**
     *
     * @return
     */
    public Double getLtd()
    {
        return ltd;
    }

    /**
     *
     * @param ltd
     */
    public void setLtd( Double ltd )
    {
        this.ltd = ltd;
    }

    /**
     *
     * @param name
     * @param life
     * @param add
     * @param dLife
     * @param std
     * @param ltd
     * @param ci
     * @param depCi
     * @param ehb
     * @param dental
     * @param hcsa
     * @param psa
     * @param eap
     * @param dsai
     */
    public Employee( String name, Double life, Double add, Double dLife, Double std, Double ltd, Double ci, Double depCi, Double ehb, Double dental, Double hcsa, Double psa, Double eap, Double dsai )
    {
        this.name = name;
        this.life = life;
        this.add = add;
        this.dLife = dLife;
        this.std = std;
        this.ltd = ltd;
        this.ci = ci;
        this.depCi = depCi;
        this.ehb = ehb;
        this.dental = dental;
        this.hcsa = hcsa;
        this.psa = psa;
        this.eap = eap;
        this.dsai = dsai;
        this.total = (Double)0.;

        if (life!=null)
            total+=life;
        if (add!=null)
            total+=add;
        if (dLife!=null)
            total+=dLife;
        if (std!=null)
            total+=std;
        if (ltd!=null)
            total+=ltd;
        if (ci!=null)
            total+=ci;
        if (depCi!=null)
            total+=depCi;
        if (ehb!=null)
            total+=ehb;
        if (dental!=null)
            total+=dental;
        if (hcsa!=null)
            total+=hcsa;
        if (psa!=null)
            total+=psa;
        if (eap!=null)
            total+=eap;
        if (dsai!=null)
            total+=dsai;
        
    }

    /**
     *
     */
    public Employee()
    {
        this.total = Double.MIN_VALUE;
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Double getLife()
    {
        return life;
    }

    /**
     *
     * @param life
     */
    public void setLife( Double life )
    {
        this.life = life;
    }

    /**
     *
     * @return
     */
    public Double getAdd()
    {
        return add;
    }

    /**
     *
     * @param add
     */
    public void setAdd( Double add )
    {
        this.add = add;
    }

    /**
     *
     * @return
     */
    public Double getdLife()
    {
        return dLife;
    }

    /**
     *
     * @param dLife
     */
    public void setdLife( Double dLife )
    {
        this.dLife = dLife;
    }

    /**
     *
     * @return
     */
    public Double getStd()
    {
        return std;
    }

    /**
     *
     * @param std
     */
    public void setStd( Double std )
    {
        this.std = std;
    }

    /**
     *
     * @return
     */
    public Double getCi()
    {
        return ci;
    }

    /**
     *
     * @param ci
     */
    public void setCi( Double ci )
    {
        this.ci = ci;
    }

    /**
     *
     * @return
     */
    public Double getDepCi()
    {
        return depCi;
    }

    /**
     *
     * @param depCi
     */
    public void setDepCi( Double depCi )
    {
        this.depCi = depCi;
    }

    /**
     *
     * @return
     */
    public Double getEhb()
    {
        return ehb;
    }

    /**
     *
     * @param ehb
     */
    public void setEhb( Double ehb )
    {
        this.ehb = ehb;
    }

    /**
     *
     * @return
     */
    public Double getDental()
    {
        return dental;
    }

    /**
     *
     * @param dental
     */
    public void setDental( Double dental )
    {
        this.dental = dental;
    }

    /**
     *
     * @return
     */
    public Double getHcsa()
    {
        return hcsa;
    }

    /**
     *
     * @param hcsa
     */
    public void setHcsa( Double hcsa )
    {
        this.hcsa = hcsa;
    }

    /**
     *
     * @return
     */
    public Double getPsa()
    {
        return psa;
    }

    /**
     *
     * @param psa
     */
    public void setPsa( Double psa )
    {
        this.psa = psa;
    }

    /**
     *
     * @return
     */
    public Double getEap()
    {
        return eap;
    }

    /**
     *
     * @param eap
     */
    public void setEap( Double eap )
    {
        this.eap = eap;
    }

    /**
     *
     * @return
     */
    public Double getDsai()
    {
        return dsai;
    }

    /**
     *
     * @param dsai
     */
    public void setDsai( Double dsai )
    {
        this.dsai = dsai;
    }

    /**
     *
     * @return
     */
    public Double getTotal()
    {
        total = (Double)0.;
        if (life!=null)      
            total+=life;
        if (add!=null)
            total+=add;
        if (dLife!=null)
            total+=dLife;
        if (std!=null)
            total+=std;
        if (ltd!=null)
            total+=ltd;
        if (ci!=null)
            total+=ci;            
        if (depCi!=null)
            total+=depCi;
        if (ehb!=null)
            total+=ehb;
        if (dental!=null)
            total+=dental;
        if (hcsa!=null)
            total+=hcsa;
        if (psa!=null)
            total+=psa;
        if (eap!=null)
            total+=eap;
        if (dsai!=null)
            total+=dsai;
        return total;
    }

    /**
     *
     * @param total
     */
    public void setTotal( Double total )
    {
        this.total = total;
    }

    @Override
    public String toString()
    {
        return "Employee{" + "name=" + name + ", life=" + life + ", add=" + add + ", dLife=" + dLife + ", std=" + std + ", ci=" + ci + ", depCi=" + depCi + ", ehb=" + ehb + ", dental=" + dental + ", hcsa=" + hcsa + ", psa=" + psa + ", eap=" + eap + ", dsai=" + dsai + ", total=" + total + '}';
    }
    
    
}
