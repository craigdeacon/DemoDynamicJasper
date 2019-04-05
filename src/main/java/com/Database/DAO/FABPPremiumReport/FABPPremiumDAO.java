/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.DAO.FABPPremiumReport;

import com.Report.Component.ME.FABPPremiumReport.container.FABPPremiumGroup;
import java.util.ArrayList;

/**
 *
 * @author craig.deacon
 */
public interface FABPPremiumDAO
{
    ArrayList<FABPPremiumGroup> getFABPPremiumGroupList();
}
