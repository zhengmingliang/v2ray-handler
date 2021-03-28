/*
 * Created by 郑明亮 on 2021/3/28 14:45.
 */

//

package com.alianga.v2ray.handler;

import com.alianga.v2ray.model.V2rayNode;
import com.alianga.v2ray.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONValidator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2021/3/28 14:45
 * @email mpro@vip.qq.com
 */
@Slf4j
public class V2rayUtils {

    private static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 解析v2ray订阅信息
     *
     * @param subscription
     * @return
     */
    public static List<String> parseSubscription(String subscription) {
        if (StringUtils.isEmpty(subscription)) {
            return Collections.emptyList();
        }
        byte[] decodeBytes = Base64.getDecoder().decode(subscription.getBytes(DEFAULT_CHARSET));

        String decodeStr = new String(decodeBytes, DEFAULT_CHARSET);
        log.debug("decodeStr:{}", decodeStr);
        String[] split = decodeStr.split("\r\n");
        if (split.length == 1) {
            split = decodeStr.split("\n");
        }
        List<String> vmessList = new ArrayList<>();
        for (String encodeStr : split) {
            String vmess = null;
            try {
                vmess = parseVmessNode(encodeStr);
            } catch (Exception e) {
                log.error("vmess节点信息不合法【{}】",encodeStr,e);
            }
            if (vmess == null) {
                continue;
            }
            vmessList.add(vmess);
        }

        return vmessList;
    }


    public static String parseVmessNode(String vmessNode) {
        if (StringUtils.isEmpty(vmessNode)) {
            return null;
        }
        if (vmessNode.startsWith("vmess://")) {
            vmessNode = vmessNode.substring(8);
        } else {
            return vmessNode;
        }

        byte[] decode = Base64.getDecoder().decode(vmessNode.getBytes(DEFAULT_CHARSET));
        String vmess = new String(decode, DEFAULT_CHARSET);
        return vmess;
    }

    public static List<V2rayNode> getNodesFromSubscription(String subscription) {
        List<String> list = parseSubscription(subscription);
        List<V2rayNode> v2rayNodes = new ArrayList<>();
        for (String vmessStr : list) {
            boolean validate = JSONValidator.from(vmessStr).validate();
            if (validate) {
                V2rayNode v2rayNode = JSON.parseObject(vmessStr, V2rayNode.class);
                v2rayNode.setIllegalNode(true);
                v2rayNodes.add(v2rayNode);
            } else {
                v2rayNodes.add(new V2rayNode(vmessStr));
            }
        }
        return v2rayNodes;
    }

}
