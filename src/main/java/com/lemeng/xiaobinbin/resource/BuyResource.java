package com.lemeng.xiaobinbin.resource;

import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemeng.xiaobinbin.MyDebug;
import com.lemeng.xiaobinbin.SqlHelper;
import com.lemeng.xiaobinbin.bean.RepondBase;
import com.lemeng.xiaobinbin.bean.RequitBuyBean;
import com.lemeng.xiaobinbin.configuration.HelloWorldConfiguration;
import java.util.ArrayList;
/**
 * Created by rmiao on 3/14/2017.
 */
@Path("/buy")
@Produces(MediaType.APPLICATION_JSON)
public class BuyResource {
    private final AtomicLong counter;
    private final HelloWorldConfiguration configuration;
    
    public BuyResource(HelloWorldConfiguration configuration) {
        this.configuration = configuration;
        this.counter = new AtomicLong();   
    }

    @POST
    public String sayHello(RequitBuyBean add) {
    	MyDebug.log("addmoney");
    	RepondBase bean =new  RepondBase();

    	if(add != null) {
        	ObjectMapper mapper = new ObjectMapper();
        	try {
        		String json = mapper.writeValueAsString(add);
        		MyDebug.log2("addmoney json="+json);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	bean.status = addDaoju(add);

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
    private int addDaoju(RequitBuyBean add) {
    	ArrayList<String> list = new ArrayList<String>();
    	list.add(SqlHelper.getIntance().getUpdateUserInfoStr(add.allMoney, add.allZichang, add.allZichang, add.histroy, add.user));
    	list.add(SqlHelper.getIntance().getUpdateMapInfoStr(add.mapCreat, add.mapCost, add.mapid,
    			add.zibenbeilv,add.moneybeilv,add.timebeilv, add.user));
    	if(add.builderInfo != null) {
    		list.add(SqlHelper.getIntance().updateBuilderStatus(add.builderInfo,add.user));		
    	}
    	if(add.type == 3 && add.daojuInfo!= null && add.daojuInfo.length() > 0) {
    		list.add(SqlHelper.getIntance().getUpdateDaoju(add.mapid, add.user, add.daojuInfo));
    	}else if(add.type == 4 ) {
    		if(add.buyMapId != 0) {
        		list.add(SqlHelper.getIntance().getAddHaveMapStr(add.buyMapId, add.user));
        		list.add(SqlHelper.getIntance().getChangeCurrentMap(add.buyMapId,add.user));		
    		}else {
    			list.add(SqlHelper.getIntance().getChangeCurrentMap(add.mapid,add.user));
    		}

    	}
    	
    	boolean isSuccess = SqlHelper.getIntance().testTransaction(list);
    	if(isSuccess) {
    		return 0;
    	}else{
    		return -1;
    	}
    	
    }
}
