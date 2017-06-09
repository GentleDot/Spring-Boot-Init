package net.gentledot.persistence;

import net.gentledot.vo.EmpVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sang on 2017-05-02.
 */

@Component
@Repository(value = "empDao")
public class EmpDAO {

    private SqlSession sqlSession;

    public EmpDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<EmpVO> selectEmpList(EmpVO vo) {
        return sqlSession.selectList("selectEmpList", vo);
    }

    public EmpVO selectEmp(EmpVO vo){
        return sqlSession.selectOne("selectEmp", vo);
    }

    public int empListCount(EmpVO vo){
        return sqlSession.selectOne("empListCount", vo);
    }

    public int addEmp(EmpVO vo){
        return sqlSession.insert("addEmp", vo);
    }

    public int updateEmp(EmpVO vo){
        return sqlSession.update("updateEmp", vo);
    }

    public int deleteEmp(EmpVO vo){
        return sqlSession.delete("deleteEmp", vo);
    }


}
