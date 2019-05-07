package com.prodigy.fondbase;


import com.prodigy.fondbase.dao.*;
import com.prodigy.fondbase.model.House;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.PostConstruct;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-root.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-mvc.xml"
})
@ActiveProfiles({"mysql", "prod"})
//@Sql(scripts = "classpath:db/test-data-hsqldb.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractRootTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    @Autowired
    private Environment env;

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected RegionDao regionDao;

    @Autowired
    protected CityDao cityDao;

    @Autowired
    protected DistrictDao districtDao;

    @Autowired
    protected StreetDao streetDao;

    @Autowired
    protected AddressDao addressDao;

    @Autowired
    protected HouseDao<House> houseDao;

    @Autowired
    protected SubscriberDao subscriberDao;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }

/*
    @Disabled
    @Test
    private void test(){

    }
*/

}
