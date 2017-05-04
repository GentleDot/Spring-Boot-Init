package net.gentledot.persistance;

import net.gentledot.config.dataSource.dataAccess.AbstractMapper;
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
        return this.sqlSession.selectList("selectEmpList", vo);
    }
}
