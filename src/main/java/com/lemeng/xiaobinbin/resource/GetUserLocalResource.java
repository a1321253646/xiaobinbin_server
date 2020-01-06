package com.lemeng.xiaobinbin.resource;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemeng.xiaobinbin.MyDebug;
import com.lemeng.xiaobinbin.SqlHelper;
import com.lemeng.xiaobinbin.bean.BuilderInfoBean;
import com.lemeng.xiaobinbin.bean.BuilderStatusBean;
import com.lemeng.xiaobinbin.bean.MapInfoBean;
import com.lemeng.xiaobinbin.bean.RepondLocalBean;
import com.lemeng.xiaobinbin.bean.RequiteLocalBean;
import com.lemeng.xiaobinbin.bean.SqlDituWeizhiBean;
import com.lemeng.xiaobinbin.configuration.HelloWorldConfiguration;

/**
 * Created by rmiao on 3/14/2017.
 */
@Path("/getuser")
@Produces(MediaType.APPLICATION_JSON)
public class GetUserLocalResource {
    private final AtomicLong counter;
    private final HelloWorldConfiguration configuration;
    
    public GetUserLocalResource(HelloWorldConfiguration configuration) {
        this.configuration = configuration;
        this.counter = new AtomicLong();   
    }

    @POST
    public String sayHello(RequiteLocalBean person) {
    	MyDebug.log("sayHello");
    	RepondLocalBean bean = new RepondLocalBean();
    	if(person != null) {
        	ObjectMapper mapper = new ObjectMapper();
        	try {
        		String json = mapper.writeValueAsString(person);
        		MyDebug.log2("json="+json);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	try {
        		
        		bean = getUserInfo(person);
        		getMapInfo(bean,person.user);
        		getHaveMap(bean,person.user);
        		getZichangToJingbin(bean);
        		if(bean == null) {
        			return "";
        		}
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
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
    
    private RepondLocalBean getUserInfo(RequiteLocalBean user) {
    	if(user.user.equals("default")) {
    		user.user = SqlHelper.getIntance().getId();
    	}
    	RepondLocalBean bean = new RepondLocalBean();
    	SqlHelper.getIntance().getUserInfo(bean, user.user);
    	bean.builders = SqlHelper.getIntance().getBuilderStatus(user.user);
    	bean.userId = user.user;
    	bean.net_time = System.currentTimeMillis();
    	return bean;
    }
    

    
    public void  getMapInfo(RepondLocalBean bean ,String user) {
    	
    	bean.mapInfo = new MapInfoBean();
    	bean.mapInfo.allMapInfo = SqlHelper.getIntance().getMapConfig();
    	
    	MyDebug.log2("bean.current_map="+bean.current_map);
    	if(bean.current_map == 0) {
    		bean.current_map  = bean.mapInfo.allMapInfo.get(0).id;
    		SqlHelper.getIntance().updateMapDate(bean.current_map,  user);
    		SqlHelper.getIntance().addHaveMap(bean.current_map,  user);
    	}
    	bean.shop = SqlHelper.getIntance().getShopList(bean.current_map);
    	MyDebug.log2("bean.current_map="+bean.current_map);
    	String[] builderIds = null;
    	for(int i = 0 ; i< bean.mapInfo.allMapInfo.size(); i++) {
    		if(bean.mapInfo.allMapInfo.get(i).id == bean.current_map) {
    			builderIds = bean.mapInfo.allMapInfo.get(i).builde_id.split(",");
    			break;
    		}
    		
    	}
    	for(String id : builderIds) {
    		
    		int intID = Integer.parseInt(id);
    		long level = 0;
    		if( bean.builders != null) {
        		for(BuilderStatusBean item2 : bean.builders) {
        			if(item2.id == intID) {
        				level = item2.level;
        				break;			
        			}
        		}
    			
    		}
    		BuilderInfoBean infoBean = GetBuilderInfoResource.getBuilderInfo (intID, level);
    		bean.mapInfo.builderInfoList.add(infoBean);
    			
    	}
    }
    
    public void getHaveMap(RepondLocalBean bean ,String user) {
    	
    	bean.mMapHave = SqlHelper.getIntance().getHaveMap(user);
    }
    
    public void getZichangToJingbin(RepondLocalBean bean) {
    	bean.zichang_to_jingbin = SqlHelper.getIntance().getZichangInfo();
    	
    }

}
