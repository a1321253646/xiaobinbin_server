package com.lemeng.xiaobinbin.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemeng.xiaobinbin.MyDebug;
import com.lemeng.xiaobinbin.SqlHelper;
import com.lemeng.xiaobinbin.bean.RepondBase;
import com.lemeng.xiaobinbin.bean.RequiteBuilderStatusBean;
import com.lemeng.xiaobinbin.bean.RequiteWxLoginInfo;
import com.lemeng.xiaobinbin.bean.WechatInfoDate1;
import com.lemeng.xiaobinbin.configuration.HelloWorldConfiguration;
import com.lemeng.xiaobinbin.utils.AesCbcUtil;
import com.lemeng.xiaobinbin.utils.HttpRequest;

/**
 * Created by rmiao on 3/14/2017.
 */
@Path("/wx")
@Produces(MediaType.APPLICATION_JSON)
public class WechatActionResource {
    private final AtomicLong counter;
    private final HelloWorldConfiguration configuration;
    
    public WechatActionResource(HelloWorldConfiguration configuration) {
        this.configuration = configuration;
        this.counter = new AtomicLong();   
    }

    @POST
    public String sayHello(RequiteWxLoginInfo status) {
    	MyDebug.log("sayHello");
    	RepondBase bean = new RepondBase();

    	if(status != null) {
        	ObjectMapper mapper = new ObjectMapper();
        	try {
        		String json = mapper.writeValueAsString(status);
        		MyDebug.log2("changebuilder json="+json);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	return decodeUserInfo(status.encryptedData,status.iv,status.code);
    	}else {
    		MyDebug.log("===person == null");
		}
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		String json = mapper.writeValueAsString(bean);
    		MyDebug.log2("back json="+json);
    		return json;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    }
    
    public String decodeUserInfo(String encryptedData, String iv, String code) {
    	 
 
        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wx3a3cefa1089df4d2";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "3a35f4416215d2adb82d2b1ac4c86bed";
        //授权（必填）
        String grant_type = "authorization_code";
 
 
        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        MyDebug.log2("decodeUserInfo = "+sr);


 
        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            ObjectMapper mapper = new ObjectMapper();
            WechatInfoDate1 date1 =  mapper.readValue(sr,WechatInfoDate1.class);
            String result = AesCbcUtil.decrypt(encryptedData, date1.session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
            	MyDebug.log2("result = "+result);
            	return result;
            /*    map.put("status", 1);
                map.put("msg", "解密成功");
 
                JSONObject userInfoJSON = JSONObject.fromObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                return map;*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //map.put("status", 0);
        //map.put("msg", "解密失败");
        return "success";
    }
    
    
    


}
