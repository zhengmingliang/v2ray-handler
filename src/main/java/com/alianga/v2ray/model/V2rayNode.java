/*
 * Created by 郑明亮 on 2021/3/28 15:39.
 */

//

package com.alianga.v2ray.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2021/3/28 15:39
 * @email mpro@vip.qq.com
 */
/**
 * host : docs.microsoft.com
 * path : /v2
 * tls :
 * verify_cert : true
 * add : 43.240.204.142
 * port : 10076
 * aid : 1
 * net : ws
 * type : none
 * v : 2
 * ps : 俄罗斯1 Khabarovsk 杭州联通
 * id : 0eef4d76-964f-3519-ae77-8725d1b36418
 * class : 1
 */
@Data
public class V2rayNode {

    /**
     * 伪装域名
     */
    private String host;
    /**
     * 伪装路径
     */
    private String path;
    private String tls;
    @JSONField(name = "verify_cert")
    private boolean verifyCert;
    /**
     * 是否是合法节点信息
     */
    private boolean illegalNode;
    /**
     * 地址（域名或ip）
     */
    private String add;
    private String port;
    /**
     * alterId 额外ID
     */
    private String aid;
    /**
     * 传输协议类型,默认为tcp类型
     */
    private String net = "tcp";
    /**
     * 加密类型
     */
    private String type;
    /**
     * 版本号
     */
    private String v;
    /**
     * 别名，备注信息
     */
    private String ps;
    /**
     * 用户id
     */
    private String id;

    @JSONField(name = "class")
    private Integer classX;

    public V2rayNode(String ps) {
        this.ps = ps;
    }

    public V2rayNode() {
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
