package org.code.server.dao;

import org.code.server.model.Channel;

public interface IChannelDao {
    int deleteByPrimaryKey(Integer cid);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}