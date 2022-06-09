package com.gzxn.config.sms;

import cn.hutool.core.util.ObjectUtil;
import com.gzxn.core.system.api.ISystemCommonApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: com.gzxn.config.sms
 * @ClassName: SmsServer
 * @Author: CodeBird
 * @Date: 2022-03-06 17:22
 * @Description: 消息服务端
 */
@ServerEndpoint("/smsServer/{token}")
@Component
@Slf4j
public class SmsServer {
    /**
     * 存放ws的用户<br>
     * <uid<token,session>>
     */
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, Session>> userMap = new ConcurrentHashMap();

    private static ISystemCommonApi iSystemCommonApi;

    @Autowired
    public void setISystemCommonApi(ISystemCommonApi iSystemCommonApi) {
        SmsServer.iSystemCommonApi = iSystemCommonApi;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        String uid = iSystemCommonApi.getUidByToken(token);
        try {
            // 判断用户id 和 token 是否已经登录
            if (userMap.containsKey(uid) && userMap.get(uid).containsKey(token)) {
                userMap.get(uid).put(token, session);
            } else {
                ConcurrentHashMap<String, Session> tokenMap = userMap.get(uid);
                if (ObjectUtil.isEmpty(tokenMap)) {
                    userMap.put(uid, new ConcurrentHashMap());
                }
                userMap.get(uid).put(token, session);

            }
            log.info("用户:" + uid + ",token:" + token + ",连接成功。");
            // 连接成功后，获取当前用户未查看的信息
        } catch (Exception e) {
            log.error("用户:" + uid + ",token:" + token + ",连接异常。", e);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("token") String token) {
        String uid = iSystemCommonApi.getUidByToken(token);
        if (userMap.containsKey(uid)) {
            userMap.get(uid).remove(token);
            if (ObjectUtil.isEmpty(userMap.get(uid).keySet())) {
                userMap.remove(uid);
            }
        }
        log.info("用户连接:" + uid + ",token:" + token + ",注销成功");
    }

    @OnMessage
    public void onMessage(String message, @PathParam("token") String token) {
        log.info("【websocket消息】收到客户端消息:" + message);
    }

    /**
     *
     * @param session
     * @param error
     * @param token
     */
    @OnError
    public void onError(Session session, Throwable error, @PathParam("token") String token) {
        log.error("token:" + token + "连接错误,原因:" + error.getMessage());
        error.printStackTrace();
    }

    public void printuserMap(String toUid, String message) {
        log.info("【用户id消息】:" + toUid + "," + message);
        if (ObjectUtil.isNotEmpty(userMap)) {
            if (ObjectUtil.isNotEmpty(userMap.get(toUid))) {
                for (String token : userMap.get(toUid).keySet()) {
                    this.sendMessage(userMap.get(toUid).get(token), message);
                }
            }
        }
    }

    /**
     * 发送消息
     *
     * @param session
     * @param message
     */
    public void sendMessage(Session session, String message) {
        session.getAsyncRemote().sendText(message);
    }

    /**
     * 此为广播消息
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        log.info("【广播消息】:" + message);
        if (ObjectUtil.isNotEmpty(userMap)) {
            for (String uid : userMap.keySet()) {
                if (ObjectUtil.isNotEmpty(userMap.get(uid))) {
                    for (String token : userMap.get(uid).keySet()) {
                        this.sendMessage(userMap.get(uid).get(token), message);
                    }
                }
            }
        }
    }

    /**
     *
     * @param toUid
     * @param message
     */
    public void sendMessageByUid(String toUid, String message) {
        log.info("【用户id消息】:" + toUid + "," + message);
        if (ObjectUtil.isNotEmpty(userMap)) {
            if (ObjectUtil.isNotEmpty(userMap.get(toUid))) {
                for (String token : userMap.get(toUid).keySet()) {
                    this.sendMessage(userMap.get(toUid).get(token), message);
                }
            }
        }
    }

}
