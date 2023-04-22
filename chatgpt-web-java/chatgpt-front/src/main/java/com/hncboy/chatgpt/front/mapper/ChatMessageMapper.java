package com.hncboy.chatgpt.front.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hncboy.chatgpt.base.domain.entity.ChatMessageDO;
import org.springframework.stereotype.Repository;

/**
 * @author hncboy
 * @date 2023/3/25 16:28
 * 聊天记录数据访问层
 */
@Repository("FrontChatMessageMapper")
public interface ChatMessageMapper extends BaseMapper<ChatMessageDO> {
}
