package net.gentledot.scott.web;/**
 * Created by Sang on 2017-06-11.
 */

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


import net.gentledot.idGen.service.IdGenService;
import net.gentledot.scott.service.DeptService;
import net.gentledot.utils.Paging;
import net.gentledot.vo.DeptVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Import(MyTestsConfiguration.class)
// or @ComponentScan
@Transactional
//@WebAppConfiguration
public class DeptControllerTest {
    private DeptVO vo;

    /*@Autowired
    private WebApplicationContext context;*/

    private MockMvc mockMvc;

    @InjectMocks
    DeptController controller;

    @Mock
    DeptService service;

    @Mock
    IdGenService idGen;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        /*mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true).build();*/

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        vo = new DeptVO();
    }

    @Test
    public void selectDeptListTest() throws Exception{
        int pageSize = 10;
        int pageNo = 1;
        int range = 2;
        int totalCnt = 10;

        Map<String, Object> returnMap = new HashMap<>();

        List<DeptVO> returnList = new ArrayList<>();
        Paging tempPaging = new Paging(pageSize, pageNo, range, totalCnt);

        returnMap.put("paging", tempPaging);
        returnMap.put("resultList", returnList);

        when(service.selectDeptListWithPaging(anyString(), anyInt(), anyInt(), anyInt())).thenReturn(returnMap);

        mockMvc.perform(get("/dept/deptList.do"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("deptNo"))
                .andExpect(model().attributeExists("pageNo"))
                .andExpect(model().attributeExists("resultList"))
                .andExpect(model().attributeExists("paging"));
    }

}
    /*
    @Test
    public void selectBikeRentalListTest() throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        Paging tempPaging = new Paging();
        tempPaging.setPageNo(1);
        tempPaging.setPageSize(10);

        resultMap.put("paging", tempPaging);
        resultMap.put("resultList", new ArrayList<BikeRentalVO>());

        when(service.selectListService(anyString(), anyInt(), anyInt()))
                .thenReturn(resultMap);

		*//*when(service.selectBikeRentalList(vo))
			.thenReturn(new ArrayList<GeoInfoBikeRentalVO>());
		when(service.selectBikeRentalListCount(vo))
			.thenReturn(100);*//*

        mockMvc.perform(get("/bikeRental/list.do"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("station"))
                .andExpect(model().attributeExists("resultList"))
                .andExpect(model().attributeExists("paging"));

        mockMvc.perform(get("/bikeRental/list.do")
                .param("pageNo", "1")
                .param("station", "성균관"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("station"))
                .andExpect(model().attributeExists("resultList"))
                .andExpect(model().attributeExists("paging"));
    }

    @Test
    public void bikeRentalViewTest() throws Exception{

        when(service.selectBikeRental((BikeRentalVO) anyObject()))
                .thenReturn(new BikeRentalVO());

        mockMvc.perform(get("/bikeRental/detail.do"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("resultVO"));


        mockMvc.perform(get("/bikeRental/detail.do")
                .param("mainKey", "BE_IW09-0001"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("resultVO"));
    }

    @Test
    public void bikeRentalAddViewTest() throws Exception{
        String tableName = "BIKE_RENTAL";

        when(idService.getNextId(tableName))
                .thenReturn("149");

        mockMvc.perform(get("/bikeRental/addView.do"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("nextNo"));
    }

    @Test
    public void bikeRentalAddTest() throws Exception{
        when(service.addBikeRental(vo))
                .thenReturn(1);

        mockMvc.perform(get("/bikeRental/add.do")
                .param("mainKey", "BE_IW09-9999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bikeRental/list.do"));
    }

    @Test
    public void faiiBikeRentalAddTest() throws Exception{
        when(service.addBikeRental(vo))
                .thenReturn(0);

        mockMvc.perform(get("/bikeRental/add.do")
                .param("mainKey", "BE_IW09-9999")
                .param("len", "ㄱㄴㄷ"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("addView.do"));
    }

    @Test
    public void bikeRentalUpdateViewTest() throws Exception{
        when(service.selectBikeRental((BikeRentalVO) anyObject()))
                .thenReturn(new BikeRentalVO());

        mockMvc.perform(get("/bikeRental/modifyView.do"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("resultVO"));

        mockMvc.perform(get("/bikeRental/modifyView.do")
                .param("mainKey", "BE_IW09-0001"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("resultVO"));

    }

    @Test
    public void bikeRentalUpdateTest() throws Exception{
        when(service.modifyBikeRental(vo))
                .thenReturn(1);

        mockMvc.perform(get("/bikeRental/modify.do")
                .param("mainKey", "BE_IW09-0001"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bikeRental/list.do"));
    }

    @Test
    public void failBikeRentalUpdateTest() throws Exception{
        when(service.modifyBikeRental(vo))
                .thenReturn(0);

        mockMvc.perform(get("/bikeRental/modify.do")
                .param("mainKey", "BE_IW09-0001")
                .param("len", "abc"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("modifyView.do?mainKey=1"));
    }

    @Test
    public void bikeRentalDeleteTest() throws Exception{
        when(service.deleteBikeRental(vo))
                .thenReturn(1);

        mockMvc.perform(get("/bikeRental/del.do")
                .param("mainKey", "BE_IW09-0001"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bikeRental/list.do"));
    }*/
