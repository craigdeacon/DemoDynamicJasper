/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secondReport.container;

/**
 * My attempt at arranging data vertically rather than horizontally
 * 
 * @author craig.deacon
 */
public class ConcatRow
{
    String column1;
    String column2;

    public ConcatRow( String column1, String column2 )
    {
        this.column1 = column1;
        this.column2 = column2;
    }

    public String getColumn1()
    {
        return column1;
    }

    public void setColumn1( String column1 )
    {
        this.column1 = column1;
    }

    public String getColumn2()
    {
        return column2;
    }

    public void setColumn2( String column2 )
    {
        this.column2 = column2;
    }
    
    
}
