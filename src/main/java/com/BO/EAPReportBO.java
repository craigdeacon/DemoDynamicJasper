package com.BO;

import com.Component.ME.EAPReport.container.EapGroup;
import com.DAO.EAPReport.EAPReportDAO;
import com.DAO.EAPReport.EAPReportDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class EAPReportBO
{
    private static final Logger LOGGER = Logger.getLogger(EAPReportBO.class.getName() );
    private EAPReportDAO eapReportDAO;

    @Autowired
    public EAPReportBO(EAPReportDAOImpl eapReportDAO)
    {
        this.eapReportDAO = eapReportDAO;
    }

    public ArrayList<EapGroup> getEAPGroups(int underwriterId)
    {
        String insurer;
        if (underwriterId == 93064)
        {
            insurer = "Arete";
        }
        else
        {
            insurer = "HumanaCare";
        }

        return eapReportDAO.getGroupList(underwriterId, insurer);
    }
}
