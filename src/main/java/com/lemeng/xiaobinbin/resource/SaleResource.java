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
import com.lemeng.xiaobinbin.bean.RepondBase;
import com.lemeng.xiaobinbin.bean.RequitBuyBean;
import com.lemeng.xiaobinbin.bean.SqlDituPeizhiBean;
import com.lemeng.xiaobinbin.configuration.HelloWorldConfiguration;

/**
 * Created by rmiao on 3/14/2017.
 */
@Path("/sale")
@Produces(MediaType.APPLICATION_JSON)
public class SaleResource {
    private final AtomicLong counter;
    private final HelloWorldConfiguration configuration;
    
    public SaleResource(HelloWorldConfiguration configuration) {
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
        	bean.status = addMoney(add);
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
    

    private int addMoney(RequitBuyBean add) {
    	ArrayList<String> list = new ArrayList<String>();
    	list.add(SqlHelper.getIntance().getUpdateUserInfoStr(add.allMoney, add.allZichang, add.allZichang, add.histroy, add.user));
    	list.add(SqlHelper.getIntance().getUpdateMapInfoStr(add.mapCreat, add.mapCost, add.mapid,
    			add.zibenbeilv,add.moneybeilv,add.timebeilv, add.user));

    	boolean isSuccess = SqlHelper.getIntance().testTransaction(list);
    	if(!isSuccess) {
    		return -1;
    	}
    	
		
		ArrayList<SqlDituPeizhiBean> maps =SqlHelper.getIntance().getMapConfig();
		String[] builders = null;
		for(SqlDituPeizhiBean map : maps) {
			if(map.id == add.mapid) {		
				builders = map.builde_id.split(",");
			}
			
		}
		list.clear();
		list.add(SqlHelper.getIntance().cleanAllBuilder(builders, add.user));
		list.add(SqlHelper.getIntance().resetMapMoney(add.mapid, add.user,add.unlock));
    	isSuccess = SqlHelper.getIntance().testTransaction(list);
    	if(!isSuccess) {
    		return -1;
    	}else {
    		return 0;
    	}
    	
    }
    


}
