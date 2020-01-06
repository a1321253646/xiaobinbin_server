package com.lemeng.xiaobinbin.resource;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemeng.xiaobinbin.MyDebug;
import com.lemeng.xiaobinbin.SqlHelper;
import com.lemeng.xiaobinbin.bean.BuilderInfoBean;
import com.lemeng.xiaobinbin.bean.RequiteBuilderInfo;
import com.lemeng.xiaobinbin.bean.SqlDituWeizhiBean;
import com.lemeng.xiaobinbin.configuration.HelloWorldConfiguration;

/**
 * Created by rmiao on 3/14/2017.
 */
@Path("/getbuilderinfo")
@Produces(MediaType.APPLICATION_JSON)
public class GetBuilderInfoResource {
    private final AtomicLong counter;
    private final HelloWorldConfiguration configuration;
    
    public GetBuilderInfoResource(HelloWorldConfiguration configuration) {
        this.configuration = configuration;
        this.counter = new AtomicLong();   
    }

    @POST
    public String sayHello(RequiteBuilderInfo person) {
    	MyDebug.log("sayHello");
    	BuilderInfoBean bean = new BuilderInfoBean();
    	if(person != null) {
        	ObjectMapper mapper = new ObjectMapper();
        	try {
        		String json = mapper.writeValueAsString(person);
        		MyDebug.log2("json="+json);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	try {
        		bean = getBuilderInfo(person.id,person.level);

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
    
    public static BuilderInfoBean getBuilderInfo(long id,long level) {
    	MyDebug.log2("getBuilderInfo id="+id+" level="+level);
    	BuilderInfoBean info = new BuilderInfoBean();
    	info.resource = new ArrayList<SqlDituWeizhiBean>();
    	info.current = SqlHelper.getIntance().getBuilderInfo(id, level);
    	MyDebug.log2("getBuilderInfo info.current.resid="+info.current.resid);
    	long nextLevel = SqlHelper.getIntance().getNextHasSkillLevel(id, level);
    	if(nextLevel != 0) {
    		info.next = SqlHelper.getIntance().getBuilderInfo(id, nextLevel);
    		if(nextLevel > level+1) {
    			info.list = SqlHelper.getIntance().getNextHasSkillLevel(id, level, nextLevel);
    			if(info.list != null && info.list.size() >0) {
    				if(info.list.get(0).resource != info.current.resid) {
    					info.resource.add(SqlHelper.getIntance().getResource(info.list.get(0).resource));
    				}
    			}
    		}
    	}
    	
    	info.resource.add(SqlHelper.getIntance().getResource(info.current.resid));
    	if(info.next != null) { 		
    		info.resource.add(SqlHelper.getIntance().getResource(info.next.resid));
    	}
    	
    	
    	return info;
    }
    


}
