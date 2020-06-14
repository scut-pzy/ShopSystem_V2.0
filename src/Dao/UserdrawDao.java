package Dao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.mysql.cj.xdevapi.Statement;

import utils.*;
import Bean.MiddleBean;
import Bean.ProductBean;
import Bean.UserBean;
import Bean.UserdrawBean;
public class UserdrawDao {
	public static Double round(Double d, int newScale) {

        Double retValue = null;
        if (d != null) {
            BigDecimal bd = new BigDecimal(d);
            retValue = bd.setScale(newScale,BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return retValue;
    }
	public static int getMaxIndex(double[] arr) {
        if(arr==null||arr.length==0){
            return -1;//如果数组为空 或者是长度为0 就返回null
        }
        int sum=0;
        for(int x=0;x<arr.length;x++) {sum+=arr[x];}
        if(sum==0) {
        	return -1;
        }
        int maxIndex=0;//假设第一个元素为最大值 那么下标设为0
        for(int i =0;i<arr.length-1;i++){
            if(arr[maxIndex]<arr[i+1]){       	
                maxIndex=i+1;
            }
        }
        return maxIndex;
    }
	public static String getCatellog(String catelog) {
		if(catelog.equals("1")) {catelog="手机数码";}
		else if(catelog.equals("2")) {catelog="电子产品";}
		else if(catelog.equals("3")) {catelog="衣服服饰";}
		else if(catelog.equals("4")) {catelog="穿戴设备";}
		else if(catelog.equals("5")) {catelog="生活用品";}
		else if(catelog.equals("6")) {catelog="医疗药物";}
		else if(catelog.equals("7")) {catelog="交通出行";}
		return catelog;
    }
	public int getcount(Connection conn,String tablename) throws SQLException {
		int count=0;
		String sql="select count(*) from "+tablename+";";
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null;
            try {    	 
	    	 prestmt=conn.prepareStatement(sql);
	    	 rs=prestmt.executeQuery();
	    	 if(rs.next()) {
	    	 count=rs.getInt(1);
	    	 }
	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	     }
	     return count;
	    }
	public int setNumberIntoManagerDrawTable(int buytablenum,int historytablenum,int carttablenum)  throws SQLException{
		 String sql="insert into shop.manageruserdraw values(?,?,?,?,?)";
		 Connection conn = null;
	     PreparedStatement prestmt= null;	
	     int bool=0;
	     int id=0;
	     int sum=buytablenum+historytablenum+carttablenum;
	     String table="manageruserdraw";
	     try {
	    	 conn=DBDao.GetConnection();
	    	 id=getcount(conn,table);
	    	 id++;
	    	 prestmt=conn.prepareStatement(sql);
	    	 prestmt.setInt(1,id);
	    	 prestmt.setInt(2,buytablenum);
	    	 prestmt.setInt(3, historytablenum);
	    	 prestmt.setInt(4, carttablenum);
	    	 prestmt.setInt(5, sum);
	    	 bool=prestmt.executeUpdate();
	    	 prestmt.close();
	    	 conn.close();
	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	     }
	     finally {
	    	 DBDao.CloseConnection(conn);
	     }
	     return bool;
	    }
	public List<MiddleBean> getDataFromcartTable(Connection conn,int index) throws SQLException {
		 List<MiddleBean> list=new ArrayList<MiddleBean>();		
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     String indexString=index+"";
      try {
      	String sql="select *  from shop.shopcart where id>"+indexString+";";
     	    prestmt=conn.prepareStatement(sql);
  	    rs=prestmt.executeQuery();
  	   while(rs.next()) {
  		   MiddleBean m=new MiddleBean();
  		   m.setUsername(rs.getString("user_name"));
  		   m.setProduct(rs.getString("panme"));
  		   m.setLable(rs.getString("pactelog"));
  		   m.setAction("cart");
  		   m.setDate(rs.getString("date"));
  		   m.setLasttime("0");
  		   list.add(m);
      		}
	    	 prestmt.close();
      }
      catch (Exception e) {
      	e.printStackTrace();
      }
      System.out.println(list.size());
		return list;
	    }
	public List<MiddleBean> getDataFromBuyTable(Connection conn,int index) throws SQLException {
		 List<MiddleBean> list=new ArrayList<MiddleBean>();		
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     String indexString=index+"";
       try {
       	String sql="select *  from shop.buy where id>"+indexString+";";
      	    prestmt=conn.prepareStatement(sql);
   	    rs=prestmt.executeQuery();
   	   while(rs.next()) {
   		   MiddleBean m=new MiddleBean();
   		   m.setUsername(rs.getString("uname"));
   		   m.setProduct(rs.getString("pname"));
   		   m.setLable(rs.getString("pcatelog"));
   		   m.setAction("buy");
   		   m.setDate(rs.getString("date"));
   		   m.setLasttime("0");
   		   list.add(m);
       		}
	    	 prestmt.close();
       }
       catch (Exception e) {
       	e.printStackTrace();
       }
       System.out.println(list.size());
		return list;
	    }
	public List<MiddleBean> getDataFromHistoryTable(Connection conn,int index) throws SQLException {
		 List<MiddleBean> list=new ArrayList<MiddleBean>();		
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     String indexString=index+"";
        try {
        	String sql="select *  from shop.history2 where id>"+indexString+";";
       	    prestmt=conn.prepareStatement(sql);
    	    rs=prestmt.executeQuery();
    	   while(rs.next()) {
    		   MiddleBean m=new MiddleBean();
    		   m.setUsername(rs.getString("username"));
    		   m.setProduct(rs.getString("pname"));
    		   m.setLable(rs.getString("pcatelog"));
    		   m.setAction("look");
    		   m.setDate(rs.getString("date"));
    		   m.setLasttime(rs.getString("lasttime"));
    		   list.add(m);
        		}
	    	 prestmt.close();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println(list.size());
		return list;
	    }
	
	public List<Integer> getDataFromManageruserdraw(Connection conn) throws SQLException {
		 List<Integer> list=new ArrayList<Integer>();		
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
       try {
       	String sql="SELECT * FROM shop.manageruserdraw order by id desc limit 1;";
      	    prestmt=conn.prepareStatement(sql);
   	    rs=prestmt.executeQuery();
   	   if(rs.next()) {
   		   list.add(rs.getInt("buytable"));
   		  list.add(rs.getInt("historytable"));
   		  list.add(rs.getInt("carttable"));
       		}
	    	 prestmt.close();
       }
       catch (Exception e) {
       	e.printStackTrace();
       }
       System.out.println(list.size());
		return list;
	    }
	public int  getSumFromManageruserdraw(Connection conn) throws SQLException {
		 int sum=0;
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
      try {
      	String sql="SELECT sum FROM shop.manageruserdraw order by id desc;";
     	    prestmt=conn.prepareStatement(sql);
  	    rs=prestmt.executeQuery();
  	    int i=0;
  	   while(rs.next()&&i<2) {
  		   sum=rs.getInt(1);
  		   i++;
      		}
	    	 prestmt.close();
      }
      catch (Exception e) {
      	e.printStackTrace();
      }
		return sum;
	    }
	public int InsertintoMiddleTable()  throws SQLException{
		 List<Integer> IntList=new ArrayList<Integer>();	
		 List<MiddleBean> listHistory=new ArrayList<MiddleBean>();	
		 List<MiddleBean> listBuy=new ArrayList<MiddleBean>();	
		 List<MiddleBean> listCart=new ArrayList<MiddleBean>();	
		 Connection conn = null;
	     PreparedStatement prestmt= null;	
	     String table="shop.middlelog";
	     int bool=0;
	    	 try {
	    	 conn=DBDao.GetConnection();
	    	
	    	 List<Double> list=new ArrayList<Double>();	
	    	 //list=getIDF(conn);
	    	 int id=getcount(conn,table);
	    	 id++;
	    	 IntList=getDataFromManageruserdraw(conn);
	    	 
	    	 listBuy=getDataFromBuyTable(conn,IntList.get(0).intValue());        	
        	 listHistory=getDataFromHistoryTable(conn,IntList.get(1).intValue());
        	 listCart=getDataFromcartTable(conn,IntList.get(2).intValue());
        	 int sizelistBuy=listBuy.size();
        	 int sizelistHistory=listHistory.size();
        	 int sizelistCart=listCart.size();
        	 if(sizelistBuy+sizelistHistory+sizelistCart>1) {
        		 setNumberIntoManagerDrawTable(sizelistBuy+IntList.get(0).intValue(),
        				 sizelistHistory+IntList.get(1).intValue(),sizelistCart+IntList.get(2).intValue());
        	 }
        	 for(int i=0;i<listHistory.size();i++) {
        		String sql="insert into shop.middlelog values(?,?,?,?,?,?,?) ";
        		prestmt=conn.prepareStatement(sql );
        		prestmt.setInt(1,id);
        		prestmt.setString(2, listHistory.get(i).getUsername());
	    	 	prestmt.setString(3, listHistory.get(i).getProduct());
	    	 	prestmt.setString(4, listHistory.get(i).getLable());
	    	 	prestmt.setString(5, listHistory.get(i).getAction());
	    	 	prestmt.setString(6, listHistory.get(i).getDate().substring(0, 10));
	    	 	prestmt.setString(7, listHistory.get(i).getLasttime());
	    	 	bool=prestmt.executeUpdate();
	    	 	id++;
        	 }
        	
        	 for(int i=0;i<listCart.size();i++) {
        		String sql="insert into shop.middlelog values(?,?,?,?,?,?,?) ";
        		prestmt=conn.prepareStatement(sql );
        		prestmt.setInt(1,id);
        		prestmt.setString(2, listCart.get(i).getUsername());
	    	 	prestmt.setString(3, listCart.get(i).getProduct());
	    	 	prestmt.setString(4, listCart.get(i).getLable());
	    	 	prestmt.setString(5, listCart.get(i).getAction());
	    	 	prestmt.setString(6, listCart.get(i).getDate().substring(0, 10));
	    	 	prestmt.setString(7, listCart.get(i).getLasttime());
	    	 	bool=prestmt.executeUpdate();
	    	 	id++;
        	 }
        	
        	 for(int i=0;i<listBuy.size();i++) {
        		String sql="insert into shop.middlelog values(?,?,?,?,?,?,?) ";
        		prestmt=conn.prepareStatement(sql );
        		prestmt.setInt(1,id);
        		prestmt.setString(2, listBuy.get(i).getUsername());
	    	 	prestmt.setString(3, listBuy.get(i).getProduct());
	    	 	prestmt.setString(4, listBuy.get(i).getLable());
	    	 	prestmt.setString(5, listBuy.get(i).getAction());
	    	 	prestmt.setString(6, listBuy.get(i).getDate().substring(0, 10));
	    	 	prestmt.setString(7, listBuy.get(i).getLasttime());
	    	 	bool=prestmt.executeUpdate();
	    	 	id++;
		    	 prestmt.close();
        	 }

	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	     }
	     finally {
	    	 /***
	    	  * 这里将中间表数据整合并且加工到用户画像表中去
	    	 ** */  	  
	    	 insertUserDrawTable(conn);
	    	 DBDao.CloseConnection(conn);
	     }
	     return bool;
	    }
	public List<MiddleBean> getDataFromMiddleTable(Connection conn,int index) throws SQLException {
		 List<MiddleBean> list=new ArrayList<MiddleBean>();		
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     String indexString=index+"";
	     //String indexString="0";
      try {
      	String sql="select *  from shop.middlelog where id>"+indexString+";";
     	    prestmt=conn.prepareStatement(sql);
  	    rs=prestmt.executeQuery();
  	   while(rs.next()) {
  		   MiddleBean m=new MiddleBean();
  		   m.setUsername(rs.getString("username"));
  		   m.setProduct(rs.getString("pname"));
  		   m.setLable(rs.getString("catelog"));
  		   m.setAction(rs.getString("action"));
  		   m.setDate(rs.getString("date"));
  		   m.setLasttime(rs.getString("lasttime"));
  		   list.add(m);
      		}
	    	 prestmt.close();
      }
      catch (Exception e) {
      	e.printStackTrace();
      }
      System.out.println(list.size());
		return list;
	    }
	//利用中间表 生成用户画像表
	public void insertUserDrawTable(Connection conn) throws SQLException {
		List<MiddleBean> list=new ArrayList<MiddleBean>();		
		int index=getSumFromManageruserdraw(conn);
	    PreparedStatement prestmt= null;
	    int bool=0;
	    try {
	    	list=getDataFromMiddleTable(conn, index);
	    	for (int i=0;i<list.size();i++) {
	    		String sql="insert into shop.userdraw values(?,?,?,?,?,?,?,?) "
				 		+ "ON DUPLICATE KEY UPDATE  actiontime=actiontime+1;";
	    		prestmt=conn.prepareStatement(sql);
	 	   	    prestmt.setString(1, list.get(i).getUsername());
	 		 	prestmt.setString(2, list.get(i).getProduct());
	 		 	prestmt.setString(3, list.get(i).getAction());
	 		 	prestmt.setInt(4, 1);
	 		 	prestmt.setString(5, list.get(i).getDate());
	 		 	String action=list.get(i).getAction();
	 		 	if(action.equals("buy")) {
	 		 		prestmt.setDouble(6, 5.0);
	 		 	}
	 		 	else if (action.equals("cart")){
	 		 		prestmt.setDouble(6, 2.0);
	 		 	}
	 		 	else if (action.equals("look")){
	 		 		int time=Integer.parseInt(list.get(i).getLasttime());
	 		 		if(time<10) {
	 		 			prestmt.setDouble(6, 0.4);
	 		 		}
	 		 		else if(time<70&&time>10) {
	 		 			int t=time%10;
	 		 			prestmt.setDouble(6, 0.4+0.1*t);
	 		 		}
	 		 		else
	 		 		prestmt.setDouble(6, 1.0);
	 		 	}
	 	 	 	int a=IpadrUtils.subTime(list.get(i).getDate());
	 	 	 	if(a<7) {
	 	 	 		prestmt.setDouble(7, 1.0);
	 	 	 	}
	 	 	 	else if(a>7&&a<30) {
	 	 	 		prestmt.setDouble(7, 0.8);
	 	 	 	}
	 	 	 	else if(a>30&&a<90) {
	 	 	 		prestmt.setDouble(7, 0.6);
	 	 	 	}
	 	 	 	else if(a>90&&a<360) {
	 	 	 		prestmt.setDouble(7, 0.4);
	 	 	 	}
	 	 	 	else if(a>360) {
	 	 	 		prestmt.setDouble(7, 0.2);
	 	 	 	}
	 		 	prestmt.setString(8, list.get(i).getLable());
	 		 	bool=prestmt.executeUpdate();
	    	}
		 	prestmt.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		return ;
	}
	public List<Double> getTFbyUsername(Connection conn,String username) throws SQLException {
		 List<Integer> list=new ArrayList<Integer>();	
		 List<Double> listDouble=new ArrayList<Double>();	
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     String sql2=null;
	     String sql1=null;
     try {
    	 for(int i =1;i<8;i++) {
    		 String catelog=i+"";
    		 sql2="select count(*)  from shop.middlelog where username=\""+username+"\" and catelog=\""+catelog+"\";" ;
    		 prestmt=conn.prepareStatement(sql2);
    		 rs=prestmt.executeQuery(sql2);    	
 	         if(rs.next()) {
             int s=rs.getInt(1);
             list.add(s);
     		}
	    	 prestmt.close();
    	 }
     }
     catch (Exception e) {
     	e.printStackTrace();
     }
     int Max=0;
     try {
    		 sql1="select count(*)  from shop.middlelog where  username=\""+username+"\" group by catelog order by count(*) desc limit 1 ; ";
    		 prestmt=conn.prepareStatement(sql1);
    		 rs=prestmt.executeQuery(sql1);    	
 	         if(rs.next()) {
 	        	Max=rs.getInt(1);
     		}
	    	 prestmt.close();
     }
     catch (Exception e2) {
     	e2.printStackTrace();
     }
     for(int i=0;i<list.size();i++) {
      	if(Max==0) {Max=1;}
    	 double ss=round(1.0*list.get(i)/Max,4);
    	 listDouble.add(ss);
     }
		return listDouble;
	    }
	public List<Double> getIDF(Connection conn) throws SQLException {
		 List<String> list=new ArrayList<String>();	
		 int[] list_n= {1,1,1,1,1,1,1};
		 List<Double> listDouble=new ArrayList<Double>();	
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     String sql2=null;
	     String sql1=null;
	     UserBean user=new UserBean();
	     String name=null;
    try {
   		 sql2="select * from shop.user; " ;
   		 prestmt=conn.prepareStatement(sql2);
   		 rs=prestmt.executeQuery(sql2);    	
	     while(rs.next()) {
            user.setUsername(rs.getString("name"));
            name=user.getUsername();
            list.add(name);
    		}
	    	 prestmt.close();
    }
    catch (Exception e) {
    	e.printStackTrace();
    }
    int N=list.size();
    try {
    	for(int i=0;i<7;i++) {
    		for(int j=0;i<N;i++) {
    			sql1="select count(*)  from shop.middlelog where  username=\""+list.get(j)+"\" and catelog=\""+Integer.toString(i+1)+"\"";
    			 prestmt=conn.prepareStatement(sql1);
    	   		 rs=prestmt.executeQuery(sql1);    	
    	   		if(rs.next()) {
    	   			if(rs.getInt(1)>0) {
    	   				list_n[i]++;}}
    		}		 
    	}
    		prestmt.close();
    		for(int i=0;i<7;i++) {
        		double ss=round(Math.log(1.0*N/list_n[i]),4);
        		listDouble.add(ss);
        }
    }
    	catch (Exception e2) {
    		e2.printStackTrace();
    }
		return listDouble;
	    }
	public void insertUserCommendTable(Connection conn,double[] weight_lable,String username)  throws SQLException{
		//加入或者更新用户的商品推荐表记录
		int index1=getMaxIndex(weight_lable);
		int index2=-1;//-1代表为任何都可以推荐，属于新用户
		if(index1!=-1) {
			weight_lable[index1]=-1;
			index2=getMaxIndex(weight_lable);
		}
		String sql="insert into shop.usercommend values(?,?,?) ON DUPLICATE KEY UPDATE  lable1=\""+Integer.toString(index1+1)+
							"\",lable2=\""+Integer.toString(index2+1)+"\"";
	     PreparedStatement prestmt= null;	
	     int bool=0;
	    	 try {
	    	 prestmt=conn.prepareStatement(sql );
	    	 prestmt.setString(1,username);
	    	 prestmt.setString(2, ""+index1);
	    	 prestmt.setString(3, ""+index2);
	    	 bool=prestmt.executeUpdate();
	    	 prestmt.close();

	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	     }
	     return ;
	    }
	public void getUserLable( ) throws SQLException {
		//获得用户的标签，将其插入到上一个函数的表中
		 List<Integer> list=new ArrayList<Integer>();	
		 List<Double> listIDF=new ArrayList<Double>();	
		 List<Double> listTF=new ArrayList<Double>();	
		 List<String> list_username=new ArrayList<String>();	
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     Connection conn=null;
	     conn=DBDao.GetConnection();
	     String sql1=null;
	     String sql2=null;
	     UserBean user=new UserBean();
	     String name=null;
	     try {
	   		 sql1="select * from shop.user; " ;
	   		 prestmt=conn.prepareStatement(sql1);
	   		 rs=prestmt.executeQuery(sql1);    	
		     while(rs.next()) {
	            user.setUsername(rs.getString("name"));
	            name=user.getUsername();
	            list_username.add(name);
	    		}
		    	 prestmt.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	     
	     int size_list_username=list_username.size();
	     listIDF=getIDF(conn);
    try {
    	for(int i=0;i<size_list_username;i++) {
    		listTF=getTFbyUsername(conn, list_username.get(i));
    		double[] weight_lable= {0,0,0,0,0,0,0};
    		for(int j=0;j<7;j++) {
    			int index=j+1;
    			String catelog=index+"";
    			sql2="select *  from shop.userdraw where uname=\""+list_username.get(i)+"\" and lable=\""+catelog+"\";" ;
    			prestmt=conn.prepareStatement(sql2);
    	   		 rs=prestmt.executeQuery(sql2);    	
    	   		 while(rs.next()) {
    	   			 int actiontime=rs.getInt("actiontime");
    	   			 double weight=rs.getDouble("weight");
    	   			 double timereduce=rs.getDouble("timereduce");
    	   			 weight_lable[j]=round(actiontime*weight*timereduce*listIDF.get(j)*listTF.get(j),4);//最重要的权重公式
    	   		 }
    	   		prestmt.close();
    		}
    		for(int ii=0;ii<7;ii++) {
			System.out.println(weight_lable[ii]);
		}
    		insertUserCommendTable(conn,weight_lable,list_username.get(i));
    	}
    }

    catch (Exception e) {
    	e.printStackTrace();
    }
    finally {
    	conn.close();
    	DBDao.CloseConnection(conn);
    }
		return ;
	    }
	public List<UserdrawBean> getUserDraw() throws SQLException {
		 List<UserdrawBean> list=new ArrayList<UserdrawBean>();		
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     Connection conn=null;
      try {
      	String sql="select *  from shop.usercommend ;";
      	conn=DBDao.GetConnection();
     	    prestmt=conn.prepareStatement(sql);
  	    rs=prestmt.executeQuery();
  	   while(rs.next()) {
  		 UserdrawBean m=new UserdrawBean();
  		   m.setUsername(rs.getString("username"));
 		   m.setLable(getCatellog(rs.getString("lable1")));
 		   m.setOtherlable(getCatellog(rs.getString("lable2")));
  		   list.add(m);
      		}
	    	 prestmt.close();
      }
      catch (Exception e) {
      	e.printStackTrace();
      }
		return list;
	    }

	public List<UserdrawBean> getProducttrend() {
		// TODO Auto-generated method stub
		 List<UserdrawBean> list=new ArrayList<UserdrawBean>();		
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     Connection conn=null;
    try {
    	String sql="select *  from shop.productcommend ;";
    	conn=DBDao.GetConnection();
   	    prestmt=conn.prepareStatement(sql);
	    rs=prestmt.executeQuery();
	   while(rs.next()) {
		 UserdrawBean m=new UserdrawBean();
		   m.setLable(getCatellog(rs.getString("catelog")));
		   m.setOtherlable(rs.getString("p1"));
		   m.setProduct(rs.getString("p2"));
		   m.setUsername(rs.getString("p3"));
		   list.add(m);
    		}
	    	 prestmt.close();
    }
    catch (Exception e) {
    	e.printStackTrace();
    }
		return list;
	}
	public List<UserdrawBean> getDrawTable() {
		// TODO Auto-generated method stub
		 List<UserdrawBean> list=new ArrayList<UserdrawBean>();		
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     Connection conn=null;
    try {
    	String sql="select *  from shop.userdraw ;";
    	conn=DBDao.GetConnection();
   	    prestmt=conn.prepareStatement(sql);
	    rs=prestmt.executeQuery();
	   while(rs.next()) {
		 UserdrawBean m=new UserdrawBean();
		 m.setUsername(rs.getString("uname"));
		 m.setProduct(rs.getString("pname"));
		 m.setAction(rs.getString("action"));
		 m.setActiontime(String.valueOf(rs.getInt("actiontime")));
		 m.setDate(rs.getString("date"));
		 m.setWeight(String.valueOf(rs.getDouble("weight")));
		 m.setTimereduce(String.valueOf(rs.getDouble("timereduce")));
		 m.setLable(getCatellog(rs.getString("lable")));
		 
		   list.add(m);
    		}
	    	 prestmt.close();
    }
    catch (Exception e) {
    	e.printStackTrace();
    }
		return list;
	}
}
