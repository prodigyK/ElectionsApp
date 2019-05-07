package com.prodigy.fondbase.controller;


import com.prodigy.fondbase.download.DownloadFromExcel;
import com.prodigy.fondbase.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/download")
public class DownloadController {

    private static final Logger log = LoggerFactory.getLogger(DownloadController.class);

    @Autowired
    private DownloadService service;

    @Autowired
    private DownloadFromExcel downloadFromExcel;

    @GetMapping
    public String download(Model model) {
        return "download/utility_download";
    }

    @GetMapping(value = {"/xlsx"})
    public String download2(Model model) {
        return "download/download";
    }
/*
    @RequestMapping(value = "/downloadStreets")
    public String downloadStreets(Model model) {
        return "download/downloadStreets";
    }
*/

    @PostMapping
    public String downloadFromFile(@RequestParam("file") MultipartFile file, Model model) {

        File excel;
        try {
            excel = new File(file.getOriginalFilename());
            file.transferTo(excel);

//            downloadFromExcel.parseExcel(excel);
            downloadFromExcel.parseExcelFromHDB(excel);
//            downloadFromExcel.parseStreets(excel);
//            downloadFromExcel.parseCSVFile(excel);

        } catch (IOException ioe) {
            log.error(ioe.getMessage());
        }


        return "download/download";
    }

}
