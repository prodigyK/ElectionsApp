package com.prodigy.fondbase.service;

import com.prodigy.fondbase.AbstractRootTest;
import com.prodigy.fondbase.dao.SubscriberDao;
import com.prodigy.fondbase.download.AnalyzeData;
import com.prodigy.fondbase.download.DownloadFromExcel;
import com.prodigy.fondbase.model.Subscriber;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Transactional
//@ActiveProfiles({"mysql", "prod"})
class DownloadServiceTest extends AbstractRootTest {

    private static final Logger log = LoggerFactory.getLogger(DownloadServiceTest.class);

    @Autowired
    private DownloadFromExcel downloadFromExcel;

    @Autowired
    private AnalyzeData analyzeData;


    @Test
    void save() {
        File file = null;
        try {
//            URL resource = this.getClass().getResource("/files/junit-test.xlsx");
            URL resource = this.getClass().getResource("/files/1-999.xlsx");
            file = new File(resource.toURI());

        } catch (URISyntaxException e){
            assertTrue(false);
        }

//        assertTrue(downloadFromExcel.parseExcel(file));
        assertTrue(downloadFromExcel.parseExcelFromHDB(file));
    }

    @Test
    void analizeData(){

        File fileStreets = null;
        File fileSubscribers = null;
        try {
            URL resource = this.getClass().getResource("/files/streets.xlsx");
            fileStreets = new File(resource.toURI());
//            URL resource2 = this.getClass().getResource("/files/data-for-analyze.xlsx");
            URL resource2 = this.getClass().getResource("/files/data-primorsky.xlsx");
            fileSubscribers = new File(resource2.toURI());

        } catch (URISyntaxException e){
            assertTrue(false);
        }

        boolean result = analyzeData.anilyzeExcel(fileStreets, fileSubscribers);

        assertTrue(result);

    }

    @Test
    void getNames(){

        File fileNames = null;
        try {
            URL resource = this.getClass().getResource("/files/data-primorsky.xlsx");
            fileNames = new File(resource.toURI());

        } catch (URISyntaxException e){
            assertTrue(false);
        }

        assertTrue(downloadFromExcel.getNamesFromExcel(fileNames));

    }

    @Test
    void getNamesFromDB(){

        List<String> subscriberList = subscriberDao.getFirstnames();
        Set<String> subscriberSet = new TreeSet<String>(subscriberList.stream().map(x -> x).collect(Collectors.toSet()));

        subscriberSet.forEach(lastname -> log.info("{}", lastname));
/*
        for(String lastname : subscriberSet){

            log.info("{};{};{}", lastname.getLastname(), lastname.getFirstname(), lastname.getMiddlename());
        }
*/
    }

    @Test
    void getStreets(){
        File fileStreets = null;
        try {
            URL resource = this.getClass().getResource("/files/data-suvorovsky.xlsx");
            fileStreets = new File(resource.toURI());

        } catch (URISyntaxException e){
            assertTrue(false);
        }

        Set<String> streets = downloadFromExcel.getStreets(fileStreets);

        streets.forEach(street -> log.info("{}", street));
    }

    @Test
    void downloadFromHDB(){
        File fileStreets = null;
        try {
//            URL resource = this.getClass().getResource("/files/HDB/kievskiy_main.xlsx");
            URL resource = this.getClass().getResource("/files/HDB/kievskiy-test.xlsx");
            fileStreets = new File(resource.toURI());

        } catch (URISyntaxException e){
            assertTrue(false);
        }

        boolean isOk = downloadFromExcel.parseExcelFromHDB(fileStreets);

        assertTrue(isOk);
    }
}