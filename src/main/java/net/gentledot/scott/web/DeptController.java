package net.gentledot.scott.web;

import net.gentledot.idGen.service.IdGenService;
import net.gentledot.scott.service.DeptService;
import net.gentledot.utils.Paging;
import net.gentledot.utils.Tools;
import net.gentledot.vo.DeptVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.tools.Tool;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sang on 2017-06-12.
 */
@Controller
public class DeptController {
    private static final Logger logger = LogManager.getLogger(DeptController.class);

    private static final int PAGE_SIZE = 10;
    private static final int PAGE_RANGE = 3;

    @Resource(name = "deptService")
    DeptService service;

    @Resource(name = "idGenService")
    IdGenService idGen;

    @RequestMapping(value = "/dept/deptList.do")
    public String selectDeptList(@RequestParam HashMap<String, String> req, ModelMap model){
        String deptNo = Tools.toEmptyBlank(req.get("itemDeptNo"));
        int pageNo = Integer.parseInt(Tools.customToEmptyBlank(req.get("pageNo"), "1"));


        logger.debug("===============================");
        logger.debug("받아온 pageNo : " + pageNo);
        logger.debug("===============================");

        Map<String, Object> resultMap = service.selectDeptListWithPaging(deptNo, PAGE_SIZE, pageNo, PAGE_RANGE);

        List<DeptVO> resultLIst = (List<DeptVO>)resultMap.get("resultList");
        Paging setPaging = (Paging) resultMap.get("paging");

        logger.debug("===============================");
        logger.debug("호출된 paging : " + setPaging.getFirstPageNo() + " // " + setPaging.getPrevPageNo() + " // " + setPaging.getPageNo() + " // " + setPaging.getNextPageNo() + " // " + setPaging.getFinalPageNo());
        logger.debug("===============================");

        model.addAttribute("resultList", resultLIst);
        model.addAttribute("paging", setPaging);
        model.addAttribute("deptNo",deptNo);
        model.addAttribute("pageNo",pageNo);

        return "thymeleaf/dept/deptList";
    }

    @RequestMapping("/dept/deptView.do")
    public String selectDeptDataView(@RequestParam HashMap<String, String> req, ModelMap model){
        String deptNo = Tools.toEmptyBlank(req.get("deptNo"));

        DeptVO vo = new DeptVO();
        vo.setDeptNo(deptNo);

        DeptVO resultVO = service.selectDept(vo);

        model.addAttribute("resultVO", resultVO);

        return "thymeleaf/dept/deptView";
    }

    @RequestMapping("/dept/deptEdit.do")
    public String updateDeptView(@RequestParam HashMap<String, String> req, ModelMap model){
        String deptNo = Tools.toEmptyBlank(req.get("deptNo"));

        DeptVO vo = new DeptVO();
        vo.setDeptNo(deptNo);

        DeptVO resultVO = service.selectDept(vo);

        model.addAttribute("resultVO", resultVO);

        return "thymeleaf/dept/deptUpdateView";
    }

    @RequestMapping("/dept/deptUpdate.do")
    public String updateDept(@RequestParam HashMap<String, String> req, ModelMap model){
        String deptNo = Tools.toEmptyBlank(req.get("item_deptNo"));
        String dName = Tools.toEmptyBlank(req.get("item_dName"));
        String loc = Tools.toEmptyBlank(req.get("item_loc"));

        DeptVO vo = new DeptVO();
        vo.setDeptNo(deptNo);
        vo.setdName(dName);
        vo.setLoc(loc);

        int resultStatus = service.updateDept(vo);

        return "redirect:/dept/deptList.do";
    }

    @RequestMapping("/dept/deptDel.do")
    public String deleteDept(@RequestParam HashMap<String, String> req, ModelMap model){
        String deptNo = Tools.toEmptyBlank(req.get("deptNo"));

        DeptVO vo = new DeptVO();
        vo.setDeptNo(deptNo);

        int resultStatus = service.deleteDept(vo);

        return "redirect:/dept/deptList.do";
    }

}
