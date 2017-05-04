package net.gentledot.scott.web;

import net.gentledot.scott.service.EmpService;
import net.gentledot.vo.EmpVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sang on 2017-05-02.
 */
@Controller
public class EmpController {
    private static final int PAGE_SIZE = 5;
    @Resource(name = "empService")
    EmpService empService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String loginProcess() {
        return "thymeleaf/index";
    }

    @RequestMapping("/empList.do")
    public String selectEmpList(@RequestParam HashMap<String, String> req, ModelMap model) {
        String empNo = req.get("empno") == null ? "" : req.get("empno");
        String pageNo = req.get("pageNo") == null ? "1" : req.get("pageNo");
        int pageNum = Integer.parseInt(pageNo);

        EmpVO vo = new EmpVO();
        vo.setEmpNo(empNo);
        vo.setPageNo(pageNum);
        vo.setPageSize(PAGE_SIZE);

        List<EmpVO> resultList = empService.selectEmpList(vo);

        model.addAttribute("resultList", resultList);
        model.addAttribute("empno", empNo);

        return "empList";
    }
}
