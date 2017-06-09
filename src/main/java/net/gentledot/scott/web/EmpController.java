package net.gentledot.scott.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import net.gentledot.idGen.service.IdGenService;
import net.gentledot.scott.service.DeptService;
import net.gentledot.scott.service.EmpService;
import net.gentledot.vo.DeptVO;
import net.gentledot.vo.EmpVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sang on 2017-05-02.
 */
@Controller
public class EmpController {
    private static final int PAGE_SIZE = 5;
    private static final String TABLE_NAME = "EMP";
    @Resource(name = "empService")
    EmpService empService;

    @Resource(name = "idGenService")
    IdGenService idGenService;

    @Resource(name = "deptService")
    DeptService deptService;


    @RequestMapping("/empList.do")
    public String selectEmpList(@RequestParam HashMap<String, String> req, ModelMap model) {
        String empNo = req.get("empno") == null ? "" : req.get("empno");
        String pageNo = req.get("pageNo") == null ? "1" : req.get("pageNo");
        int pageNum = Integer.parseInt(pageNo);

        Map<String, Object> resultMap = empService.selectEmpList(empNo, pageNum, PAGE_SIZE);

        model.addAttribute("resultList", resultMap.get("resultList"));
        model.addAttribute("paging", resultMap.get("paging"));
        model.addAttribute("empno", empNo);

        return "empList";
    }

    @RequestMapping("/empView.do")
    public String selectEmp(@RequestParam HashMap<String, String> req, ModelMap model){
        String empNo = req.get("empNo");
        EmpVO vo = new EmpVO();
        vo.setEmpNo(empNo);

        EmpVO result = empService.selectEmp(vo);
        model.addAttribute("emp", result);

        return "empView";
    }

    @RequestMapping("/empAddView.do")
    public String empAddView(@RequestParam HashMap<String, String> req, ModelMap model){
        String nextId = idGenService.getNextId(TABLE_NAME);
        DeptVO vo = new DeptVO();
        vo.setDeptNo("");
        List<DeptVO> deptList = deptService.selectDeptList(vo);

        model.addAttribute("nextId", nextId);
        model.addAttribute("deptList", deptList);

        return "empAddView";
    }

    @RequestMapping("/empAdd.do")
    public String empAdd(@RequestParam HashMap<String, String> req, ModelMap model){
        String empNo = req.get("empNo");
        String eName = req.get("eName");
        String job = req.get("job");
        String mgr = req.get("mgr");
        String hireDate = req.get("hireDate");
        String sal = req.get("sal");
        String comm = req.get("comm");
        String deptNo = req.get("deptNo");

        EmpVO vo = new EmpVO();
        vo.setEmpNo(empNo);
        vo.seteName(eName);
        vo.setJob(job);
        vo.setMgr(mgr);
        vo.setHireDate(hireDate);
        vo.setSal(sal);
        vo.setComm(comm);
        vo.setDeptNo(deptNo);

        empService.addEmp(vo);

        return "redirect:/empList.do";
    }

    @RequestMapping("/empUpdateView.do")
    public String empUpdateView(@RequestParam HashMap<String, String> req, ModelMap model){
        String empNo = req.get("empNo");

        EmpVO vo = new EmpVO();
        vo.setEmpNo(empNo);

        EmpVO resultEmp = empService.selectEmp(vo);

        DeptVO dVo = new DeptVO();
        dVo.setDeptNo("");
        List<DeptVO> deptList = deptService.selectDeptList(dVo);

        model.addAttribute("result", resultEmp);
        model.addAttribute("deptList", deptList);

        return "empUpdateView";
    }

    @RequestMapping("/empUpdate.do")
    public String empUpdate(@RequestParam HashMap<String, String> req, ModelMap model){
        String empNo = req.get("empNo");
        String eName = req.get("eName");
        String job = req.get("job");
        String mgr = req.get("mgr");
        String hireDate = req.get("hireDate");
        String sal = req.get("sal");
        String comm = req.get("comm");
        String deptNo = req.get("deptNo");

        EmpVO vo = new EmpVO();
        vo.setEmpNo(empNo);
        vo.seteName(eName);
        vo.setJob(job);
        vo.setMgr(mgr);
        vo.setHireDate(hireDate);
        vo.setSal(sal);
        vo.setComm(comm);
        vo.setDeptNo(deptNo);

        empService.updateEmp(vo);

        return "redirect:/empList.do";
    }

    @RequestMapping("/empDel.do")
    public String empDelete(@RequestParam HashMap<String, String> req, ModelMap model){
        String empNo = req.get("empNo");

        EmpVO vo = new EmpVO();
        vo.setEmpNo(empNo);

        empService.deleteEmp(vo);

        return "redirect:/empList.do";
    }
}
