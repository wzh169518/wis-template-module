package com.wis.template.infrastructure.persistent.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公用的DAO
 * @author zkn
 *
 */

public abstract class abMapper extends SqlSessionDaoSupport{

    /**
     * Autowired 必须要有
     */
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){

        super.setSqlSessionFactory(sqlSessionFactory);
    }

}