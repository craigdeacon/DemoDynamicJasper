/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO.ProvincialSalesTax;

import com.Component.ME.ProvincialSalesTaxReport.container.ProvincialGroup;
import java.util.ArrayList;

/**
 *
 * @author craig.deacon
 */
public interface ProvincialSalesTaxDAO
{
    ArrayList<ProvincialGroup> getGroupsProvincialSalesTax();
}
