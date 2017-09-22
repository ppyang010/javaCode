package org.code.server.dao.impl;




import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao extends SqlSessionDaoSupport {
	@Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate)
    {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
}
