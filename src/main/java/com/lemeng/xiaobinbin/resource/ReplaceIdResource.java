package com.lemeng.xiaobinbin.resource;

import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemeng.xiaobinbin.MyDebug;
import com.lemeng.xiaobinbin.SqlHelper;
import com.lemeng.xiaobinbin.bean.AddMoneyBean;
import com.lemeng.xiaobinbin.bean.BigNumber;
import com.lemeng.xiaobinbin.bean.BuilderStatusBean;
import com.lemeng.xiaobinbin.bean.RepondBase;
import com.lemeng.xiaobinbin.bean.RepondLocalBean;
import com.lemeng.xiaobinbin.bean.RequitAddMoneyBean;
import com.lemeng.xiaobinbin.bean.RequiteReplaceId;
import com.lemeng.xiaobinbin.bean.SqlHaveMapInfo;
import com.lemeng.xiaobinbin.configuration.HelloWorldConfiguration;

/**
 * Created by rmiao on 3/14/2017.
 */
@Path("/replace")
@Produces(MediaType.APPLICATION_JSON)
public class ReplaceIdResource {
    private final AtomicLong counter;
    private final HelloWorldConfiguration configuration;
    
    public ReplaceIdResource(HelloWorldConfiguration configuration) {
        this.configuration = configuration;
        this.counter = new AtomicLong();   
    }

    @POST
    public String sayHello(RequiteReplaceId cost) {
    	MyDebug.log("addmoney");
    	RepondBase bean =new  RepondBase();

    	if(cost != null) {
        	ObjectMapper mapper = new ObjectMapper();
        	try {
        		String json = mapper.writeValueAsString(cost);
        		MyDebug.log2("addmoney json="+json);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	SqlHelper.getIntance().repalaceId(cost.old, cost.newId);
        	bean.status = 0;

    	}else {
    		bean.status = -1;
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
}
