/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO.EP3;

import com.Component.CS.EP3.container.EP3ProcessEntry;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author craig.deacon
 */
public interface EP3ProcessDAO
{

    public ArrayList<EP3ProcessEntry> getGroupList( Date invoiceDate );

    public boolean getGroupHasActiveAffiliation( String policyNumber, int groupId );

}
