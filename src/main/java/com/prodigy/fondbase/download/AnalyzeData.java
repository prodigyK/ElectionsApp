package com.prodigy.fondbase.download;

import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.service.DistrictService;
import com.prodigy.fondbase.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class AnalyzeData {

    private static final Logger log = LoggerFactory.getLogger(AnalyzeData.class);

    @Autowired
    private DownloadFromExcel downloadFromExcel;

    @Autowired
    private DownloadService downloadService;

    public boolean anilyzeExcel(File fileStreets, File fileSubscribers) {


        Map<String, String> streets = downloadFromExcel.parseStreets(fileStreets);
        if(streets == null || streets.size() == 0)
            return false;

        List<Subscriber> subscribers = downloadFromExcel.parseExcel(fileSubscribers);
        if(subscribers == null || subscribers.size() == 0)
            return false;

        return downloadService.analizeData(streets, subscribers);
    }

}
