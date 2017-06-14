package net.gentledot.scott.web;/**
 * Created by Sang on 2017-06-11.
 */

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
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

    @Test
    public void selectDeptDetailTest() throws Exception {
        vo.setDeptNo("10");

        when(service.selectDept((DeptVO) anyObject())).thenReturn(vo);

        mockMvc.perform(get("/dept/deptView.do")
                            .param("deptNo", "10"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("resultVO"));
    }

    @Test
    public void updateDeptViewTest() throws Exception {
        vo.setDeptNo("10");

        when(service.selectDept((DeptVO) anyObject())).thenReturn(vo);

        mockMvc.perform(get("/dept/deptEdit.do")
                .param("deptNo", "10"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("resultVO"));

    }

    @Test
    public void updateDeptTest() throws Exception {
        vo.setDeptNo("124");
        vo.setdName("testAndTest");
        vo.setLoc("testLocation");

        when(service.updateDept(vo)).thenReturn(1);

        mockMvc.perform(get("/dept/deptUpdate.do"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/dept/deptList.do"));
    }

    @Test
    public void deleteDeptTest() throws Exception {
        vo.setDeptNo("140");

        when(service.deleteDept(vo)).thenReturn(1);

        mockMvc.perform(get("/dept/deptDel.do")
                .param("deptNo", "140"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/dept/deptList.do"));
    }

}
