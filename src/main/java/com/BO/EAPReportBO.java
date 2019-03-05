package com.BO;

import com.Component.ME.EAPReport.container.EapGroup;
import com.Component.ME.EAPReport.repository.EAPReportTestRepo;
import com.DAO.EAPReport.EAPReportDAO;
import com.DAO.EAPReport.EAPReportDAOImpl;
import com.DemoDynamicJasper.spring.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.logging.Logger;

public class EAPReportBO
{
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class );
    private static final Logger LOGGER = Logger.getLogger(EAPReportBO.class.getName() );
    EAPReportDAO eapReportDAO = context.getBean(EAPReportTestRepo.class );

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
