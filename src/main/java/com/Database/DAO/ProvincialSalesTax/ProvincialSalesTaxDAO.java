/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.DAO.ProvincialSalesTax;

import com.Report.Component.ME.ProvincialSalesTaxReport.container.ProvincialGroup;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author craig.deacon
 */
public interface ProvincialSalesTaxDAO
{

    /**
     *
     * @return
     */
    ArrayList<ProvincialGroup> getGroupsProvincialSalesTax();

    /**
     *
     * @return
     */
    HashMap<String, Integer> getUnderWriters();
}
