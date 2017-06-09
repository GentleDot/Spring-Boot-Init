package net.gentledot.persistence;/**
 * Created by Sang on 2017-06-09.
 */

import net.gentledot.vo.DeptVO;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
public class DeptDAOTest {
    DeptVO vo;
    int pageSize = 0;

    @Resource(name = "deptDao")
    DeptDAO deptDAO;

    @Before
    public void setUp(){
        vo = new DeptVO();
        pageSize = 10;
    }

    @Test
    public void selectDeptListTest(){
        vo.setDeptNo("10");
        vo.setPageNo(1);
        vo.setPageSize(pageSize);
        List<DeptVO> resultList = deptDAO.selectDeptList(vo);

        assertThat(resultList.size(), is(1));
    }

    @Test
    public void selectDeptTest(){
        vo.setDeptNo("10");
        DeptVO resultVo = deptDAO.selectDept(vo);

        assertThat(resultVo.getDeptNo(), is("10"));
    }

    @Test
    public void addDeptTest(){
        vo.setDeptNo("99");
        deptDAO.addDept(vo);

        DeptVO selectedVo = deptDAO.selectDept(vo);

        assertEquals("addDept should added a new dept row", selectedVo.getDeptNo() , "99");
    }

    @Test
    public void updateDeptTest() {
        String resultMessage = "";

        vo.setDeptNo("80");
        int resultStatus = deptDAO.updateDept(vo);

        if (resultStatus == 1) {
            resultMessage = "successfully updated.";
        } else if (resultStatus == 0) {
            resultMessage = "update failed.";
        }

        assertThat(resultMessage, is("successfully updated."));
//        assertEquals("updateDeptTest should update selected deptNo", resultStatus, 1);
    }

    @Test
    public void deleteDeptTest(){
        String resultMessage = "";
        vo.setDeptNo("99");
        int resultStatus = deptDAO.deleteDept(vo);
//        resultMessage = resultStatus == 1 ? "success" : "fail";

//        assertThat(resultStatus, is(1));
//        assertEquals("deleteDept() should delete dept data", resultMessage, "success");

        assertThat(resultStatus, responseDeleteDept());
    }

    public Matcher<Integer> responseDeleteDept(){
        return new BaseMatcher<Integer>() {
            @Override
            public boolean matches(Object item) {
                if(!(item instanceof Integer)){
                    return false;
                }

                boolean result = ((Integer) item).equals(1);

                return result;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("delete failed when resultStatus had 0. (success = 1)");
            }
        };
    }

}