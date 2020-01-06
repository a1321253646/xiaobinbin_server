package com.lemeng.xiaobinbin;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.lemeng.xiaobinbin.bean.BigNumber;
import com.lemeng.xiaobinbin.bean.BuilderStatusBean;
import com.lemeng.xiaobinbin.bean.RepondBuildStatusBaseBean;
import com.lemeng.xiaobinbin.bean.RepondLocalBean;
import com.lemeng.xiaobinbin.bean.RequiteBuilderStatusBean;
import com.lemeng.xiaobinbin.bean.ShopItemBean;
import com.lemeng.xiaobinbin.bean.SqlDituPeizhiBean;
import com.lemeng.xiaobinbin.bean.SqlDituWeizhiBean;
import com.lemeng.xiaobinbin.bean.SqlHaveMapInfo;
import com.lemeng.xiaobinbin.bean.SqlJianZhuShuXingBean;
import com.lemeng.xiaobinbin.bean.ZichangInfoBean;
import com.lemeng.xiaobinbin.configuration.HelloWorldConfiguration;

public class SqlHelper {
	
	String STR_RAMDOM="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	Connection conn = null;
	Statement stmt = null;
	
	private SqlHelper() {
	    try {
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/xiaobinbin?serverTimezone=GMT%2B8&characterEncoding=utf-8","root","zsbin149");
			stmt = conn.createStatement();
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}	
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	
	
	
	
	
	
	
	
	public String getUpdateUserInfoStr(String money,String zuanshi,String zichang,String history,String user) {
		
		String commPath = "update user_data set time="+System.currentTimeMillis()+
				",money='"+money+"',zichang='"+zichang+"',zuanshi='"+zuanshi+"',history='"+history+"' WHERE user_id='"+user+"'";
		return commPath;
	}
	public String getUpdateMapInfoStr(String creat,String cost,long mapid,String zibenbeilv,String moneyBeilv,String timeBeilv,String user) {
		
		String commPath = "update user_map set creat='"+creat+
				"',cost='"+cost+"',zibenbeilv='"+zibenbeilv+"',moneybeilv='"+moneyBeilv+"',timebeilv='"+timeBeilv
				+"' WHERE user='"+user+"' and id="+mapid;
		return commPath;
	}
	public String getUpdateDaoju(long mapId,String user,String daojuList) {
		
		String commPath =  "update user_map set buy='"+daojuList+"'where user='"+user+"' and id="+mapId;
		return commPath;
	}
	
	public String getAddHaveMapStr(long mapId,String user) {
		
		String commPath = "INSERT INTO user_map (user,id,time,creat,cost,unlock1,shopcount,buy,zibenbeilv,moneybeilv,timebeilv)  VALUES ('"+
		user+"' ,"+mapId+", 0,0,0,0,0,'','0','10000','10000')";
		return commPath;
	}
	public String getChangeCurrentMap(long mapId,String user) {
		String commPath = "update user_data set current_map="+mapId+" WHERE user_id='"+user+"'";
		return commPath;	
		
	}
	public String resetMapMoney(long mapId,String user,long unlock) {
		
		String commPath = "update user_map set creat='0',cost='0',buy='',zibenbeilv='0',moneybeilv='10000',timebeilv='10000',unlock1="+unlock
				+",time="+System.currentTimeMillis()+"  WHERE user='"+user+"' and id="+mapId;
		return commPath;
	}
	
	public String cleanAllBuilder(String[] builders,String user) {
		
		String commPath = "delete from user_builder_data where user_id ='"+user+"' and (";
		for(int i = 0 ; i < builders.length ; i++) {
			if(i!= builders.length -1) {
				commPath +=( " buidler_id="+builders[i]+" or ");
			}else {			
				commPath +=( " buidler_id="+builders[i]+" )");
			}
			
		}
		return commPath;
	}
	
	
    public boolean testTransaction(ArrayList<String> list) {
    	MyDebug.log2(">>>>>>>>>>>>>>>>>>>>>>>>>testTransaction");
    	if(list == null || list.size() == 0) {
    		return false;    		
    	}
    	MyDebug.log2(">>>>>>>>>>>>>>>>>>>>>>>>>事务执行:开始执行");
    	Statement statement = null;
         try
         {
        	 
             conn.setAutoCommit(false); //开启事务，禁止自动提交

             statement = conn.createStatement();
             for(String str : list) {           	 
            	 statement.addBatch(str);
             }
            
             statement.executeBatch();
             
             conn.commit(); //执行成功，提交事务
             statement.close();
             statement = null;
             MyDebug.log2("事务执行:执行成功<<<<<<<<<<<<<<<<<<<<<<<");
         }
         catch (Exception e)
         {
        	 MyDebug.log2("事务执行:执行失败<<<<<<<<<<<<<<<<<<<<<<<");
             try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
			//	
				MyDebug.log2(e1.getMessage());
				e1.printStackTrace();
			} //发生异常，事务回滚
            if(statement != null) {
            	try {
					statement.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					MyDebug.log2(e1.getMessage());
				}
            }
            MyDebug.log2(e.getMessage());
             return false;
         }
    	   	
    	return true;
    }
	
	
	
	
	
	
	
	
	
	
	
	public void updateLastTime(String user) {
		
		String commPath = "update user_data set time="+System.currentTimeMillis()+" WHERE user_id='"+user+"'";
		try {
			stmt.execute(commPath);
		} catch (SQLException e) {
			
			MyDebug.log("执行："+commPath+"出错");
			e.printStackTrace();
		}	
	}
	
	public void updateBuilderAllMoney(String money,String user,long id) {
		
		String commPath = "update user_builder_data set allmoney='"+money+"', lastime="+System.currentTimeMillis()+" WHERE user_id='"+user+"' and buidler_id="+id;
		try {
			MyDebug.log2("执行："+commPath);
			stmt.execute(commPath);
		} catch (SQLException e) {
			
			MyDebug.log("执行："+commPath+"出错");
			e.printStackTrace();
		}	
	}
	
	public String updateBuilderStatus(BuilderStatusBean person,String user) {
		
		String commPath = "select allmoney from user_builder_data WHERE  user_id='" +user+"' and buidler_id="+person.id;
		ResultSet  rs = null;
		boolean  isHave = false;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	isHave = true;
            	break;
            }     
            rs.close();

            if(!isHave) {
            	commPath = "INSERT INTO user_builder_data (user_id,buidler_id,allmoney,level,time_pre,menoy_pre,lastime,auto,creatBase,creatTime) VALUES (";
            	commPath += "'"+user+"',";
            	commPath += person.id+",";
            	commPath += "'"+person.allmoney+"',";
            	commPath += person.level+",";
            	commPath += person.time_pre+",";
            	commPath += person.money_pre+",";
            	commPath +=System.currentTimeMillis()+",";
            	commPath += person.isAuto+",";
            	commPath += "'"+person.creatBase+"',";
            	commPath += person.creatTime+")";
                        	
            	return commPath;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	System.out.println("执行 getMacByToken："+commPath +"失败");
            e.printStackTrace();
        } 
		
		
		commPath  = "update user_builder_data set";
		commPath += " allmoney='"+person.allmoney+"'";
		commPath += ",level="+person.level;
		commPath += ",time_pre="+person.time_pre;
		commPath += ",menoy_pre="+person.money_pre;
		commPath += ",lastime="+System.currentTimeMillis();
		commPath += ",auto="+person.isAuto;
		commPath += ",creatBase='"+person.creatBase+"'";
		commPath += ",creatTime="+person.creatTime;
		commPath += " WHERE user_id='"+user+"' and buidler_id="+person.id;
		
		return 	commPath;
	}


	public void updateMapDate(long mapId,String user) {
		
		String commPath = "update user_data set current_map="+mapId+" WHERE user_id='"+user+"'";
		try {
			stmt.execute(commPath);
		} catch (SQLException e) {
			
			MyDebug.log("执行："+commPath+"出错");
			e.printStackTrace();
		}	
	}
	public void addHaveMap(long mapId,String user) {
		String commPath = "INSERT INTO user_map (user,id,time,creat,cost,unlock1,shopcount,buy,zibenbeilv,moneybeilv,timebeilv)  VALUES ('"+
		user+"' ,"+mapId+", 0,0,0,0,0,'','0','10000','10000')";
		MyDebug.log("执行："+commPath);
		try {
			stmt.execute(commPath);
		} catch (SQLException e) {
			
			MyDebug.log("执行："+commPath+"出错");
			e.printStackTrace();
		}	
	}
	
	
	public SqlHaveMapInfo getHaveMap(String user,long map) {
		String commPath = "select * from user_map where user='"+user+"' and id="+map;
		SqlHaveMapInfo bean = new SqlHaveMapInfo();
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){          	
            	bean.id = rs.getLong("id");  
            	bean.time = rs.getLong("time");  
            	bean.user = rs.getString("user");  
            	bean.creat = rs.getString("creat");  
            	bean.cost = rs.getString("cost");  
            	bean.unlock = rs.getLong("unlock1");     
            	bean.shopcount = rs.getLong("shopcount"); 
            	bean.buy = rs.getString("buy"); 
            	bean.zibenbeilv = rs.getString("zibenbeilv"); 
            	bean.moneybeilv = rs.getString("moneybeilv"); 
            	bean.timebeilv = rs.getString("timebeilv"); 
            }     
            rs.close();
            return bean;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	System.out.println("执行 getBuilderStatus："+commPath +"失败");
            e.printStackTrace();
        }
	    return null;
		
	}
	public ArrayList<SqlHaveMapInfo> getHaveMap(String user) {
		 ArrayList<SqlHaveMapInfo>  list = new ArrayList<SqlHaveMapInfo> ();
		String commPath = "select * from user_map where user='"+user+"' ";
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	SqlHaveMapInfo bean = new SqlHaveMapInfo();
            	bean.id = rs.getLong("id");  
            	bean.time = rs.getLong("time");  
            	bean.user = rs.getString("user");  
            	bean.creat = rs.getString("creat");  
            	bean.cost = rs.getString("cost"); 
            	bean.unlock = rs.getLong("unlock1"); 
            	bean.shopcount = rs.getLong("shopcount"); 
            	bean.buy = rs.getString("buy"); 
            	bean.zibenbeilv = rs.getString("zibenbeilv"); 
            	bean.moneybeilv = rs.getString("moneybeilv"); 
            	bean.timebeilv = rs.getString("timebeilv");
            	
            	list.add(bean);
            }     
            rs.close();

        } catch (SQLException e) {
        	list = null;
            // TODO Auto-generated catch block
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	System.out.println("执行 getBuilderStatus："+commPath +"失败");
            e.printStackTrace();
        } 
	    return list;
	}
	
	
	
	public BuilderStatusBean getBuilderStatus(String user,long id) {
		BuilderStatusBean bean = null;
		String commPath = "select allmoney from user_builder_data WHERE  user_id='" +user+"' and buidler_id="+id;
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	if(bean == null) {
            		bean = new BuilderStatusBean();
            		bean.allmoney = rs.getString("allmoney");  
            		break;
            	}                                    
            }     
            rs.close();

        } catch (SQLException e) {
        	bean = null;
            // TODO Auto-generated catch block
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	System.out.println("执行 getBuilderStatus："+commPath +"失败");
            e.printStackTrace();
        } 
		return bean;
	}
	
	public SqlJianZhuShuXingBean getBuilderInfo(long id,long level) {
		SqlJianZhuShuXingBean bean = null; 
		String commPath = "select * from jianzhushuxing WHERE  ID=" +id+" and level="+level;
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	if(bean == null) {
            		bean = new SqlJianZhuShuXingBean();
            		bean.ID = rs.getLong("ID");   
            		bean.level = rs.getLong("level");  
            		bean.level_up_cost =rs.getString("level_up_cost");  
            		bean.creatBase = rs.getString("creat_base"); 
            		bean.resid = rs.getLong("resid");  
            		bean.skill = rs.getLong("skill"); 
            		bean.param = rs.getLong("param"); 
            		break;
            	}                                    
            }     
            rs.close();

        } catch (SQLException e) {
        	bean = null;
            // TODO Auto-generated catch block
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	System.out.println("执行 getBuilderInfo："+commPath +"失败");
            e.printStackTrace();
        } 
		return bean;
	}
	public long getNextHasSkillLevel(long id,long level) {
	
		String commPath = "select level from jianzhushuxing WHERE skill>0 and  ID=" +id+" and level>"+level +" order by level";
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	return rs.getLong("level");
            }     
            rs.close();

        } catch (SQLException e) {
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	System.out.println("执行 getNextHasSkillLevel："+commPath +"失败");
            e.printStackTrace();
        } 
		return 0;
	}	
	
	public ArrayList<RepondBuildStatusBaseBean> getNextHasSkillLevel(long id,long min,long max) {
		ArrayList<RepondBuildStatusBaseBean> list = new ArrayList<RepondBuildStatusBaseBean>();
		String commPath = "select * from jianzhushuxing WHERE ID=" +id+" and level>"+min+" and level<"+max +" order by level";
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	RepondBuildStatusBaseBean bean = new RepondBuildStatusBaseBean();
        		bean.level = rs.getLong("level");  
        		bean.level_up_cost =rs.getString("level_up_cost");  
        		bean.creat_base = rs.getString("creat_base"); 
        		bean.resource = rs.getLong("resid"); 
        		list.add(bean);
            }     
            rs.close();

        } catch (SQLException e) {
        	list = null;
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	System.out.println("执行 getNextHasSkillLevel："+commPath +"失败");
            e.printStackTrace();
        } 
		return list;
	}	
	
	
	
	public void getUserInfo(RepondLocalBean info ,String user) {
		String commPath = "select * from user_data WHERE  user_id='" +user+"'";
		ResultSet  rs = null;
		boolean isHave = false;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	isHave = true;
            	info.money = rs.getString("money");
            	info.zichang = rs.getString("zichang");
            	info.zuanshi = rs.getString("zuanshi");
            	info.current_map = rs.getLong("current_map");
            	info.leave_time = System.currentTimeMillis()-rs.getLong("time");
            	info.history = rs.getString("history");
            }     
            rs.close();

            if(isHave) {
            	return;
            }else {
            	commPath = "INSERT INTO user_data (user_id,money,zuanshi,zichang,current_map,time,history) VALUES"
            			+ " (\""+user+"\",\"0\",\"0\",\"0\",0,"+ System.currentTimeMillis()+",\"0\");";;
            	stmt.execute(commPath);
            }
            
        } catch (SQLException e) {

        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	System.out.println("执行 getUserInfo："+commPath +"失败");
            e.printStackTrace();
        } 
		
	}
	
	public ArrayList<BuilderStatusBean> getBuilderStatus(String user) {
		ArrayList<BuilderStatusBean> list = new ArrayList<BuilderStatusBean>();
		String commPath = "select * from user_builder_data WHERE  user_id='" +user+"'";
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	BuilderStatusBean bean = new BuilderStatusBean();
            		bean.allmoney = rs.getString("allmoney");  
            		bean.id = rs.getLong("buidler_id");  
            		bean.level = rs.getLong("level");  
            		bean.time_pre = rs.getLong("time_pre");  
            		bean.money_pre = rs.getLong("menoy_pre");   
            		bean.lastime = rs.getLong("lastime");  
            		bean.isAuto = rs.getLong("auto");  
            		list.add(bean);
                                        
            }     
            rs.close();

        } catch (SQLException e) {
        	
            // TODO Auto-generated catch block
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	
        	System.out.println("执行 getBuilderStatus："+commPath +"失败");
            e.printStackTrace();
            return null;
        } 
		return list;
	}
	
	public ArrayList<SqlDituPeizhiBean> getMapConfig() {
		ArrayList<SqlDituPeizhiBean> list = new ArrayList<SqlDituPeizhiBean>();
		String commPath = "select * from ditupeizhi order by id";
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	SqlDituPeizhiBean bean = new SqlDituPeizhiBean();
            		bean.id = rs.getLong("id");  
            		bean.name =rs.getLong("name");  
            		bean.cost = rs.getString("cost");  
            		bean.salecd = rs.getLong("salecd");  
            		bean.bg = rs.getString("bg");  
            		bean.icon = rs.getString("icon"); 
            		bean.builde_id =  rs.getString("builde_id"); 
            		bean.open_id =  rs.getLong("open_id"); 
            		list.add(bean);
                                        
            }     
            rs.close();

        } catch (SQLException e) {
        	
            // TODO Auto-generated catch block
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	
        	System.out.println("执行 getMapConfig："+commPath +"失败");
            e.printStackTrace();
            return null;
        } 
		return list;
	}
	
	public ArrayList<ShopItemBean> getShopList(long mapId) {
		ArrayList<ShopItemBean> list = new ArrayList<ShopItemBean>();
		String commPath = "select * from jinbizichangshangpin where map="+mapId+"  order by id";
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	ShopItemBean bean = new ShopItemBean();
            		bean.id = rs.getLong("id");  
            		bean.cost = rs.getString("cost");
            		bean.costtype = rs.getLong("costtype");  
            		bean.dealtype = rs.getLong("dealtype");  
            		bean.desc =rs.getLong("desc"); 
            		bean.icon = rs.getString("icon"); 
            		bean.parame =  rs.getLong("parame"); 
            		list.add(bean);
                                        
            }     
            rs.close();

        } catch (SQLException e) {
        	
            // TODO Auto-generated catch block
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	
        	System.out.println("执行 getMapConfig："+commPath +"失败");
            e.printStackTrace();
            return null;
        }
		return list;
	}
	
	public ArrayList<ZichangInfoBean> getZichangInfo() {
		ArrayList<ZichangInfoBean> list = new ArrayList<ZichangInfoBean>();
		String commPath = "select * from zichang ";
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	ZichangInfoBean bean = new ZichangInfoBean();
            		bean.zichang = BigNumber.getBigNumForString(rs.getString("value")).toString() ;  
            		bean.jingbin =BigNumber.getBigNumForString(rs.getString("beilv")).toString() ;  
            		list.add(bean);
                                        
            }     
            rs.close();

        } catch (SQLException e) {
        	
            // TODO Auto-generated catch block
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	
        	System.out.println("执行 getMapConfig："+commPath +"失败");
            e.printStackTrace();
            return null;
        } 
		return list;
	}
	
	
	public SqlDituWeizhiBean getResource(long resource) {
		SqlDituWeizhiBean bean = null;
		String commPath = "select * from dituweizhi where resourId="+resource;
		ResultSet  rs = null;
	    try {
	    	rs = stmt.executeQuery(commPath);
            while(rs.next()){
            	 	bean = new SqlDituWeizhiBean();
            		bean.id = rs.getLong("resourId");  
            		bean.creattime = rs.getLong("creattime");           		
            		bean.position = rs.getString("position");  
            		bean.name = rs.getLong("name");   
            		bean.size = rs.getString("size"); 
            		bean.icon = rs.getString("Icon"); 
            		
                                        
            }     
            rs.close();

        } catch (SQLException e) {
        	
            // TODO Auto-generated catch block
        	if(rs != null) {
        		try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	
        	System.out.println("执行 getMapConfig："+commPath +"失败");
            e.printStackTrace();
            return null;
        } 
		return bean;
	}
	
	public String getId() {
		String id = "";
		while(true) {
			id = getRandomString(9);
			String commPath = "select * from user_data WHERE  user_id='" +id+"'";
			ResultSet  rs = null;
		    try {
		    	int count = 0;
		    	rs = stmt.executeQuery(commPath);
	            while(rs.next()){
	            	count++;                                     
	            }     
	            rs.close();
	            rs = null;
	            if(count > 0) {
	            	continue;
	            }else{
	            	return id;
	            }
		    }catch(SQLException e) {
		    	System.out.println("执行 getMapConfig："+commPath +"失败");
		    	e.printStackTrace();
		    }
		
		}
	}
	
	public void repalaceId(String old,String newId) {
		String commPath = "update user_data set user_id='"+newId+"' WHERE user_id='"+old+"'";
	    try {
	    	stmt.execute(commPath);
	    	commPath = "update user_map set user='"+newId+"' WHERE user='"+old+"'";
	    	stmt.execute(commPath);
	    	commPath = "update user_builder_data set user_id='"+newId+"' WHERE user_id='"+old+"'";
	    	stmt.execute(commPath);
	    }catch(SQLException e) {
	    	System.out.println("执行 getMapConfig："+commPath +"失败");
	    	e.printStackTrace();
	    }
	
		
	}
	
	
	
	 private String getRandomString(int length){
	     
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(STR_RAMDOM.charAt(number));
	     }
	     return sb.toString();
	 }
	
	
	 
	 
	private static SqlHelper mIntance= new SqlHelper();
	
	public static SqlHelper getIntance() {
		return mIntance;
	}
}
