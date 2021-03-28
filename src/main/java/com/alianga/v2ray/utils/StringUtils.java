/*
 * Created by 郑明亮 on 2021/3/28 14:49.
 */

//

package com.alianga.v2ray.utils;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2021/3/28 14:49
 * @email mpro@vip.qq.com
 */
public class StringUtils {
    public static boolean isEmpty(String str){
        if(str == null || "".equals(str.trim())){
            return true;
        }
        return false;
    }
}
