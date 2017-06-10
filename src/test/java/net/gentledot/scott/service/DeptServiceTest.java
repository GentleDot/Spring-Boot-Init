package net.gentledot.scott.service;/**
 * Created by Sang on 2017-06-10.
 */

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import net.gentledot.persistence.DeptDAO;
import net.gentledot.scott.service.impl.DeptServiceImpl;
import net.gentledot.utils.Paging;
import net.gentledot.vo.DeptVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class DeptServiceTest {
    private static final int SUCCESS = 1;
    private static final int FAIL = 0;

    private int pageSize= 10;
    private int pageNo = 1;
    private int pageScope = 10;

    private DeptVO vo;

    @InjectMocks
    private DeptService deptService = new DeptServiceImpl();

    @Mock
    private DeptDAO deptDAO;

    @Before
    public void setUp(){
        vo = new DeptVO();
    }

    @Test
    public void selectDeptListTest(){
        // setVO
        vo.setDeptNo("");
        vo.setPageSize(pageSize);
        vo.setPageNo(pageNo);

        // resultList
        List<DeptVO> returnList = new ArrayList<>();

        for (int i = 0; i < pageSize; i++){ // pageSize 수 만큼 리스트 결과값 반환
            DeptVO tempVO = new DeptVO();
            tempVO.setDeptNo(String.valueOf(i + 1 * 10));
            returnList.add(tempVO);
        }

        when(deptDAO.selectDeptList(vo)).thenReturn(returnList);

        List<DeptVO> resultList = deptService.selectDeptList(vo);

        assertThat(resultList.size() <= pageSize, is(true));
    }

    @Test
    public void selectDeptListWithSetPagingTest(){
        // setVO
        vo.setDeptNo("");
        vo.setPageSize(pageSize);
        vo.setPageNo(pageNo);

        // returnList
        List<DeptVO> returnList = new ArrayList<>();

        for(int i = 0; i < pageSize; i++){
            DeptVO tempVO = new DeptVO();
            tempVO.setDeptNo(String.valueOf(i + 1 * 10));
            returnList.add(tempVO);
        }

        when(deptDAO.selectDeptList(vo)).thenReturn(returnList);

        when(deptDAO.deptListCount(vo)).thenReturn(returnList.size());

        Map<String, Object> returnMap = new HashMap<>();

        List<DeptVO> resultList = deptDAO.selectDeptList(vo);
        int totalCount = deptDAO.deptListCount(vo);

        Paging paging = new Paging(pageSize, pageNo, pageScope, totalCount);

        returnMap.put("resultList", resultList);
        returnMap.put("paging", paging);

        assertThat(returnMap.containsKey("resultList"), is(true) );
        assertThat(returnMap.containsKey("paging"), is(true));
    }

    @Test
    public void selectDeptVOTest(){
        vo.setDeptNo("10");

        DeptVO returnVO = new DeptVO();
        returnVO.setDeptNo("10");
        returnVO.setdName("test");
        returnVO.setLoc("Korea");

        when(deptDAO.selectDept(vo)).thenReturn(returnVO);

        DeptVO resultVO = deptDAO.selectDept(vo);

        assertThat(resultVO.getDeptNo(), is("10"));
    }

    @Test
    public void addDeptTest(){
        vo.setDeptNo("81");
        vo.setdName("test");
        vo.setLoc("testLoc");

        when(deptDAO.addDept(vo)).thenReturn(SUCCESS);

        int resultStatus = deptDAO.addDept(vo);

        assertThat(resultStatus, is(SUCCESS));
    }

    @Test
    public void updateDeptTest(){
        vo.setDeptNo("81");
        vo.setdName("updateTest");
        vo.setLoc("testLoc_update");

        when(deptDAO.updateDept(vo)).thenReturn(SUCCESS);

        int resultStatus = deptDAO.updateDept(vo);

        assertThat(resultStatus, is(SUCCESS));
    }

    @Test
    public void deleteDeptTest(){
        vo.setDeptNo("81");

        when(deptDAO.deleteDept(vo)).thenReturn(SUCCESS);

        int resultStatus = deptDAO.deleteDept(vo);

        assertThat(resultStatus, is(SUCCESS));
    }
}