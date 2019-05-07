package com.prodigy.fondbase.controller;

import com.prodigy.fondbase.AbstractRootTest;
import com.prodigy.fondbase.controller.json.JsonUtil;
import com.prodigy.fondbase.controller.rest.RegionRestController;
import com.prodigy.fondbase.model.Region;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.NoResultException;
import java.util.List;

import static com.prodigy.fondbase.TestUtil.contentJson;
import static com.prodigy.fondbase.TestUtil.readFromJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class RegionRestControllerTest extends AbstractRootTest {

    private static final String REGION_URL = RegionRestController.REGION_URL + '/';

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REGION_URL + 1001))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(regionDao.get(Region.class, 1001)));

    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REGION_URL + 1001))
                .andExpect(status().isNoContent());
        assertThrows(NoResultException.class, () -> regionDao.get(Region.class, 1001));
    }

    @Test
    void save() throws Exception {
        List<Region> regionList = regionDao.getAll(Region.class);

        String name = "Test Region";
        Region created = new Region(1003, name);

        ResultActions actions = mockMvc.perform(post(REGION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Region returned = readFromJson(actions, Region.class);
        created.setId(returned.getId());

        regionList.add(created);

        assertThat(returned).isEqualTo(created);
        assertThat(regionDao.getAll(Region.class)).isEqualTo(regionList);
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REGION_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(regionDao.getAll(Region.class)));
    }
}