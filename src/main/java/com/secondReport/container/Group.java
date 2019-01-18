/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secondReport.container;

/**
 *Dummy information for Employee Report subtitle
 * 
 * @author craig.deacon
 */
public class Group
{
    private String groupName;

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }

    public Group( String groupName )
    {
        this.groupName = groupName;
    }

    @Override
    public String toString()
    {
        return "Group{" + "name=" + groupName + '}';
    }
    
    
}
