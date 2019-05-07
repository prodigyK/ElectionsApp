package com.prodigy.fondbase.download;

import com.prodigy.fondbase.model.*;
import com.prodigy.fondbase.service.DownloadService;
import com.prodigy.fondbase.service.SubscriberService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class DownloadFromExcel {

    private static final Logger log = LoggerFactory.getLogger(DownloadFromExcel.class);

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private DownloadService downloadService;

    public Map<String, String> parseStreets(File fileName) {

//        Set<String> streets = new TreeSet<>();
        Map<String, String> streets = new TreeMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {

            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workBook.getSheetAt(0);
            Iterator<Row> itRow = sheet.iterator();

            Row row = itRow.next();
            itRow.next();

            // row 1 - start parsing
//            int rows = sheet.getPhysicalNumberOfRows();


            int count = 0;

            while (itRow.hasNext()) {

                row = itRow.next();

                Cell cell;
                cell = row.getCell(1);
                if (cell == null)
                    continue;
                cell.setCellType(CellType.STRING);
                String xlsStreetRus = cell.getStringCellValue();

                cell = row.getCell(2);
                if (cell == null)
                    continue;
                cell.setCellType(CellType.STRING);
                String xlsStreetUkr = cell.getStringCellValue();

                streets.putIfAbsent(xlsStreetRus, xlsStreetUkr);


//                System.out.println(String.valueOf(++count));


            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;

        }


        return streets;
    }

    public Set<String> getStreets(File fileName) {
        Set<String> streets = new TreeSet<>();

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {

            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workBook.getSheetAt(0);
            Iterator<Row> itRow = sheet.iterator();

            Row row = itRow.next();
            itRow.next();

            int count = 0;

            while (itRow.hasNext()) {

                row = itRow.next();

                Cell cell;
                cell = row.getCell(7);
                if (cell == null)
                    continue;
                cell.setCellType(CellType.STRING);
                String xlsStreetRus = cell.getStringCellValue();

/*
                cell = row.getCell(1);
                if (cell == null)
                    continue;
                cell.setCellType(CellType.STRING);
                String xlsStreetUkr = cell.getStringCellValue();
*/

                streets.add(xlsStreetRus);


//                System.out.println(String.valueOf(++count));


            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;

        }


        return streets;
    }

    public boolean parseCSVFile(File fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {


            String line = reader.readLine();
            String xlsDistrict = line.split(";")[0];
            reader.readLine();

            int count = 0;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(";");
                String xlsLastName = parts[0];
                String xlsFirstName = parts[1];
                String xlsMiddleName = parts[2];
                String xlsBirthday = parts[3];
                String xlsFullAddress = parts[4];
                String[] addressParts = xlsFullAddress.split(",");
                String xlsMailIndex = addressParts[0].trim();
                String xlsCountry = addressParts[1].trim();
                String xlsRegion = addressParts[2].trim().split(" ")[0];
                String xlsCity = "Одеса"; //addressParts[3].split(".")[1];
                String xlsStreet = addressParts[4].trim();
                if (xlsStreet.startsWith("бульв.")) {
                    xlsStreet = xlsStreet.substring(6, xlsStreet.length());
                    xlsStreet = xlsStreet + " " + "бульвар";
                } else if (xlsStreet.startsWith("просп.")) {
                    xlsStreet = xlsStreet.substring(6, xlsStreet.length());
                    xlsStreet = xlsStreet + " " + "проспект";
                } else if (xlsStreet.startsWith("вул.")) {
                    xlsStreet = xlsStreet.substring(4, xlsStreet.length());
//                    xlsStreet = xlsStreet + " " + "вулиця";
                } else if (xlsStreet.startsWith("пл.")) {
                    xlsStreet = xlsStreet.substring(3, xlsStreet.length());
                    xlsStreet = xlsStreet + " " + "площа";
                } else if (xlsStreet.startsWith("пров.")) {
                    xlsStreet = xlsStreet.substring(5, xlsStreet.length());
                    xlsStreet = xlsStreet + " " + "провулок";
                }
//                xlsStreet = addressParts[4].substring(5, addressParts[4].length());
                String xlsHouse = addressParts[5].trim();
                String xlsCorps = "";
                String xlsWord = "";
                String xlsFlat = "";

                if (Pattern.matches("(([0-9])* [^0-9])|(([0-9])*[^0-9])", xlsHouse)) {
                    String newHouse = xlsHouse.replaceAll("[^0-9]", "").trim();
                    xlsWord = xlsHouse.replaceAll("\\p{Digit}", "").trim();
                    xlsHouse = newHouse;
                } else if (Pattern.matches("/", xlsHouse)) {
                    String newHouse = xlsHouse.split("/")[0];
                    xlsCorps = xlsHouse.split("/")[1];
                    xlsHouse = newHouse;
                }

                if (addressParts.length > 6) {
                    String corp = addressParts[6];
                    if (corp.startsWith(" корп.")) {
                        String corpPart = corp.split("\\.")[1];
                        if (Pattern.matches("([^0-9])", corpPart)) {
                            xlsWord = corpPart;
                        } else {
                            xlsCorps = corpPart;
                        }
                    }
                    if (addressParts[6].startsWith(" кв.")) {
                        xlsFlat = addressParts[6].split("\\.")[1];
                    }
                }
                if (addressParts.length > 7) {
                    if (addressParts[7].startsWith(" кв.")) {
                        xlsFlat = addressParts[7].split("\\.")[1];
                    }
                }

                Region region = new Region(null, xlsRegion);
                City city = new City(null, xlsCity);
                District district = new District(null, xlsDistrict);
                Street street = new Street(null, xlsStreet);
                House house = new House(street, xlsHouse, xlsCorps, xlsWord);
//                Postman postman = new Postman(null, xlsPostman);
//                Area area = new Area(null, xlsAreaNumber);

                Address address = new Address();
//                address.setArea(area);
                address.setMailIndex(xlsMailIndex);
                address.setRegion(region);
                address.setCity(city);
                address.setDistrict(district);
                address.setHouse(house);
                address.setFlat(xlsFlat);
                address.setArea(new Area(null, "empty"));

                Subscriber subscriber = new Subscriber();
                subscriber.setLastname(xlsLastName);
                subscriber.setFirstname(xlsFirstName);
                subscriber.setMiddlename(xlsMiddleName);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate birthday = LocalDate.parse(xlsBirthday, formatter);

                subscriber.setBirthday(birthday);
//                subscriber.setPhone(new Phone(xlsPhone));
//                subscriber.setAddresses(address);

                subscriber = downloadService.save(subscriber);

                if (count % 5000 == 0) {
                    log.info("{}", count);
                }
                count++;

            }


        } catch (IOException e) {
            log.error(e.getMessage());
            return false;

        }

        return true;
    }

    public boolean parseExcelFromHDB(File fileName) {

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {

            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workBook.getSheetAt(0);
            Iterator<Row> itRow = sheet.iterator();

            Row row = itRow.next();
            Cell cell;
            cell = row.getCell(0);
            cell.setCellType(CellType.STRING);
            String xlsDistrict = cell.getStringCellValue();

            // row 1 - start parsing
//            int rows = sheet.getPhysicalNumberOfRows();
            int count = 0;
            while (itRow.hasNext()) {

                row = itRow.next();

                cell = row.getCell(0);
                if (cell == null)
                    continue;
                cell.setCellType(CellType.STRING);
                String xlsLastName = cell.getStringCellValue();

                if ("".equals(xlsLastName) || "Фамилия".equals(xlsLastName)) {
                    continue;
                }

                cell = row.getCell(1);
                cell.setCellType(CellType.STRING);
                String xlsFirstName = cell.getStringCellValue();

                cell = row.getCell(2);
                String xlsMiddleName = "";
                if(cell != null){
                    cell.setCellType(CellType.STRING);
                    xlsMiddleName = cell.getStringCellValue();
                }

                cell = row.getCell(3);
//                cell.setCellType(CellType.STRING);
                Date xlsBirthday = cell.getDateCellValue();
//                String xlsBirthday = cell.getStringCellValue();

                cell = row.getCell(4);
                cell.setCellType(CellType.STRING);
                String xlsFullAddress = cell.getStringCellValue();

                String[] addressParts = xlsFullAddress.split(",");
                String xlsMailIndex = addressParts[0].trim();
                String xlsCountry = addressParts[1].trim();
                String xlsRegion = addressParts[2].trim().split(" ")[0];
                String xlsCity = "Одеса"; //addressParts[3].split(".")[1];
                String xlsStreet = addressParts[4].trim();
                if (xlsStreet.startsWith("бульв.")) {
                    xlsStreet = xlsStreet.substring(6, xlsStreet.length());
                    xlsStreet = xlsStreet + " " + "бульвар";
                } else if (xlsStreet.startsWith("просп.")) {
                    xlsStreet = xlsStreet.substring(6, xlsStreet.length());
                    xlsStreet = xlsStreet + " " + "проспект";
                } else if (xlsStreet.startsWith("вул.")) {
                    xlsStreet = xlsStreet.substring(4, xlsStreet.length());
//                    xlsStreet = xlsStreet + " " + "вулиця";
                } else if (xlsStreet.startsWith("пл.")) {
                    xlsStreet = xlsStreet.substring(3, xlsStreet.length());
                    xlsStreet = xlsStreet + " " + "площа";
                } else if (xlsStreet.startsWith("пров.")) {
                    xlsStreet = xlsStreet.substring(5, xlsStreet.length());
                    xlsStreet = xlsStreet + " " + "провулок";
                }
//                xlsStreet = addressParts[4].substring(5, addressParts[4].length());
                String xlsHouse = "";
                if(addressParts.length > 5){
                    xlsHouse = addressParts[5].trim();
                }
                String xlsWord = "";
                if (Pattern.matches("(([0-9])* [^0-9])|(([0-9])*[^0-9])", xlsHouse)) {
                    String newHouse = xlsHouse.replaceAll("[^0-9]", "").trim();
                    xlsWord = xlsHouse.replaceAll("\\p{Digit}", "").trim();
                    xlsHouse = newHouse;
                }

                String xlsCorps = "";
                String xlsFlat = "";
                if (addressParts.length > 6) {
                    if (addressParts[6].startsWith(" корп.")) {
                        xlsCorps = addressParts[6].split("\\.")[1];
                    }
                    if (addressParts[6].startsWith(" кв.")) {
                        xlsFlat = addressParts[6].split("\\.")[1];
                    }
                }
                if (addressParts.length > 7) {
                    if (addressParts[7].startsWith(" кв.")) {
                        xlsFlat = addressParts[7].split("\\.")[1];
                    }
                }

                Region region = new Region(null, xlsRegion);
                City city = new City(null, xlsCity);
                District district = new District(null, xlsDistrict);
                Street street = new Street(null, xlsStreet);
                House house = new House(street, xlsHouse, xlsCorps, xlsWord);
//                Postman postman = new Postman(null, xlsPostman);
//                Area area = new Area(null, xlsAreaNumber);

                Address address = new Address();
//                address.setArea(area);
                address.setMailIndex(xlsMailIndex);
                address.setRegion(region);
                address.setCity(city);
                address.setDistrict(district);
                address.setHouse(house);
                address.setFlat(xlsFlat);
                address.setArea(new Area(null, "empty"));

                Subscriber subscriber = new Subscriber();
                subscriber.setLastname(xlsLastName);
                subscriber.setFirstname(xlsFirstName);
                subscriber.setMiddlename(xlsMiddleName);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//                LocalDate birthday = LocalDate.parse(xlsBirthday, formatter);
                LocalDate birthday = xlsBirthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                birthday = birthday.plusDays(1);

                subscriber.setBirthday(birthday);
                subscriber.setPhone(null);
                List<Address> addresses = new ArrayList<Address>();
                addresses.add(address);
                subscriber.setAddresses(addresses);

                subscriber = downloadService.save(subscriber);
/*
                log.info("{}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}({}), {}, {}",
                        xlsLastName, xlsFirstName, xlsMiddleName, xlsBirthday, xlsMailIndex,
                        xlsCountry, xlsRegion, xlsCity, xlsDistrict, xlsStreet, xlsHouse,
                        xlsWord, xlsCorps, xlsFlat);
*/


                if (count % 5000 == 0) {
                    log.info("{}", count);
                }
                count++;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;

        }

        return true;
    }

    public List<Subscriber> parseExcel(File fileName) {

        List<Subscriber> subscribers = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {

            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workBook.getSheetAt(0);
            Iterator<Row> itRow = sheet.iterator();
            Row row;

            // row 1 skipping
            itRow.next();

            // row 2 skipping
            itRow.next();

            // row 3 - start parsing
//            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; itRow.hasNext(); i++) {

                if (i == 7871) {
                    int q = 1;
                }

                row = itRow.next();

                Cell cell;
                //column
                cell = row.getCell(1);
                String xlsAreaNumber = "";
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    xlsAreaNumber = cell.getStringCellValue();
                }
                //column
/*
                cell = row.getCell(2);
                cell.setCellType(CellType.STRING);
                String xlsMailIndex = cell.getStringCellValue();
*/
                //column
/*
                cell = row.getCell(4);
                cell.setCellType(CellType.STRING);
                String xlsRegion = cell.getStringCellValue();
*/
                //column
/*
                cell = row.getCell(5);
                cell.setCellType(CellType.STRING);
                String xlsCity = cell.getStringCellValue();
*/
                //column
                //column
/*
                cell = row.getCell(7);
                cell.setCellType(CellType.STRING);
                String xlsDistrict = cell.getStringCellValue();
*/
                //column
/*
                cell = row.getCell(5);
                cell.setCellType(CellType.STRING);
                String xlsPostman = cell.getStringCellValue();
*/
                //column
                cell = row.getCell(7);
                String xlsStreet = "";
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    xlsStreet = cell.getStringCellValue();
                }
                //column
                //column
                cell = row.getCell(9);
                String xlsHouse = "";
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    xlsHouse = cell.getStringCellValue();
                }
                //column
                cell = row.getCell(10);
                String xlsCorps = "";
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    xlsCorps = cell.getStringCellValue();
                }
                //column
                cell = row.getCell(11);
                String xlsLetter = "";
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    xlsLetter = cell.getStringCellValue().toUpperCase();
                }
                //column
                cell = row.getCell(12);
                String xlsFlat = "";
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    xlsFlat = cell.getStringCellValue();
                }
                //column
                cell = row.getCell(14);
                String xlsLastname = "";
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    xlsLastname = cell.getStringCellValue();
                }
                //column 1
                cell = row.getCell(15);
                String xlsFirstname = "";
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    xlsFirstname = cell.getStringCellValue();
                }
                //column
                cell = row.getCell(16);
                String xlsMiddlename = "";
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    xlsMiddlename = cell.getStringCellValue();
                }
                //column
                cell = row.getCell(18);
                String xlsPhone = "";
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    xlsPhone = cell.getStringCellValue().replaceAll(";", ",");
                }
                //column 19
                //column 20
/*
                cell = row.getCell(18);
                cell.setCellType(CellType.STRING);
                String xlsSubscribeDay = cell.getStringCellValue();
*/
                //column 21
/*
                cell = row.getCell(19);
                cell.setCellType(CellType.STRING);
                String xlsComment = cell.getStringCellValue();
*/


//                Region region = new Region(null, xlsRegion);
//                City city = new City(null, xlsCity);
//                District district = new District(null, xlsDistrict);
                Street street = new Street(null, xlsStreet);
                House house = new House(street, xlsHouse, xlsCorps, xlsLetter);
//                Postman postman = new Postman(null, xlsPostman);
                Area area = new Area(null, xlsAreaNumber);

                Address address = new Address();
                address.setArea(area);
//                address.setMailIndex(xlsMailIndex);
//                address.setRegion(region);
//                address.setCity(city);
//                address.setDistrict(district);
                address.setHouse(house);
                address.setFlat(xlsFlat);

                Subscriber subscriber = new Subscriber();
                subscriber.setLastname(xlsLastname);
                subscriber.setFirstname(xlsFirstname);
                subscriber.setMiddlename(xlsMiddlename);
                subscriber.setPhone(new Phone(xlsPhone));
//                subscriber.setAddresses(address);


/*
                int length = xlsSubscribeDay.length();
                String subscribe = xlsSubscribeDay.substring(length-8, length-1);
                LocalDate localDate;
                subscriber.setSubscribeDay();
*/

//                subscriber = downloadService.save(subscriber);
                subscribers.add(subscriber);


            }


        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        return subscribers;

    }


    public boolean getNamesFromExcel(File fileNames) {

        Set<String> lastnameSet = new TreeSet<>();
        Set<String> firstnameSet = new TreeSet<>();
        Set<String> middlenameSet = new TreeSet<>();

        int count = 0;

        try (FileInputStream fileInputStream = new FileInputStream(fileNames)) {


            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workBook.getSheetAt(0);
            Iterator<Row> itRow = sheet.iterator();
            Row row;

            // row 1 skipping
            itRow.next();

            // row 2 skipping
            itRow.next();

            // row 3 - start parsing
            for (int i = 1; itRow.hasNext(); i++) {

                row = itRow.next();

                Cell cell;
                //column
//                cell = row.getCell(14);
                cell = row.getCell(11);
                if (cell == null) continue;
                cell.setCellType(CellType.STRING);
                String xlsLastname = cell.getStringCellValue();
                //column 1
//                cell = row.getCell(15);
                cell = row.getCell(12);
                if (cell == null) continue;
                cell.setCellType(CellType.STRING);
                String xlsFirstname = cell.getStringCellValue();
                //column
//                cell = row.getCell(16);
                cell = row.getCell(13);
                if (cell == null) continue;
                cell.setCellType(CellType.STRING);
                String xlsMiddlename = cell.getStringCellValue();

                lastnameSet.add(xlsLastname);
                firstnameSet.add(xlsFirstname);
                middlenameSet.add(xlsMiddlename);

                count++;
            }

            log.info("==========================================");
            lastnameSet.forEach(s -> log.info("{}", s));
            log.info("==========================================");
            firstnameSet.forEach(s -> log.info("{}", s));
            log.info("==========================================");
            middlenameSet.forEach(s -> log.info("{}", s));
            log.info("==========================================");

        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("Current row is {}", count);
            return false;
        }

        return true;
    }
}