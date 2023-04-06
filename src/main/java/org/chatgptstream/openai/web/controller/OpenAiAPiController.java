package org.chatgptstream.openai.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chatgptstream.openai.enmus.MessageType;
import org.chatgptstream.openai.service.UserChatService;
import org.chatgptstream.openai.service.dto.Message;
import org.chatgptstream.openai.util.api.OpenAiWebClient;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author chaser
 * @version 1.0
 * @date 2023/4/5 19:29
 **/
@Slf4j
@RestController
@RequestMapping({"/auto/ai"})
@RequiredArgsConstructor
public class OpenAiAPiController {
    private final UserChatService userChatService;
    private final OpenAiWebClient openAiWebClient;


    /**
     * 发信息
     *
     * @param type   gpt预设回答类型，与人设类似
     * @param prompt 提示词
     * @param user   用户
     * @return
     */
    @GetMapping(value = "/api/get")
    public Flux<String> streamCompletions(int type, String prompt, String user) {
        Assert.hasLength(user, "user不能为空");
        Assert.hasLength(prompt, "prompt不能为空");
        return userChatService.send(MessageType.TEXT, type, prompt, user);
    }
}
