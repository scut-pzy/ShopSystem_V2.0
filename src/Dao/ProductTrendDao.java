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
public class ProductTrendDao {
	public static Double round(Double d, int newScale) {

        Double retValue = null;
        if (d != null) {
            BigDecimal bd = new BigDecimal(d);
            retValue = bd.setScale(newScale,BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return retValue;
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
	public List<String> getListofProductname(Connection conn ,int lable) throws SQLException {
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     String sql1=null;
	     List<String> pnamelist=new ArrayList<String>();	
  		 String catelog=(lable+1)+"";
  		try {
	   		 sql1="select * from shop.product where catelog=\""+getCatellog(catelog)+"\";" ;
	   		 prestmt=conn.prepareStatement(sql1);
	   		 rs=prestmt.executeQuery(sql1);    	
		     while(rs.next()) {
		    	 String name=rs.getString("name");
		    	 pnamelist.add(name);
	    		}
		    	 prestmt.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		return pnamelist;
	    }
	public List<Double> getTFbyProductname(Connection conn ,int lable,List<String> pnamelist) throws SQLException {
		 List<Integer> list=new ArrayList<Integer>();	
		 List<Double> listDouble=new ArrayList<Double>();	
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     String sql2=null;
	     String sql1=null;
   		 String catelog=(lable+1)+"";
   		 try {
   			 for(int i =0;i<pnamelist.size();i++) {
   				 sql2="select count(*)  from shop.middlelog where pname=\""+pnamelist.get(i)+"\" and catelog=\""+catelog+"\";" ;
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
   		 sql1="select count(*)  from shop.middlelog where  catelog=\""+catelog+"\" group by pname order by count(*) desc limit 1 ; ";
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
	public void insertUserCommendTable(Connection conn,double[] weight_lable,String catelog, List<String> pnamelist)  throws SQLException{
		//加入或者更新用户的商品推荐表记录
		int index1=-1;
		int index2=-1;//-1代表为任何都可以推荐，属于新用户\
		int index3=-1;
		try {
			index1=getMaxIndex(weight_lable);
		if(index1!=-1) {
			weight_lable[index1]=0;
			index2=getMaxIndex(weight_lable);
			if(index2!=-1) {
				weight_lable[index2]=0;
				index3=getMaxIndex(weight_lable);
			}}}
		catch(Exception e) {
			
		}
		String p1="null";
		String p2="null";
		String p3="null";
		if(index1!=-1) {p1=pnamelist.get(index1);}
		if(index2!=-1) {p2=pnamelist.get(index2);}
		if(index3!=-1) {p3=pnamelist.get(index3);}
		String sql="insert into shop.productcommend values(?,?,?,?) ON DUPLICATE KEY UPDATE  p1=\""+p1+
							"\",p2=\""+p2+"\",p3=\""+p3+"\";";
	     PreparedStatement prestmt= null;	
	     int bool=0;
	    	 try {
	    	 prestmt=conn.prepareStatement(sql );
	    	 prestmt.setString(1,catelog);
	    	 prestmt.setString(2, p1);
	    	 prestmt.setString(3, p2);
	    	 prestmt.setString(4, p3);
	    	 bool=prestmt.executeUpdate();
	    	 prestmt.close();

	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	     }
	     return ;
	    }
	public void getProductTrend( ) throws SQLException {
		//获得用户的标签，将其插入到上一个函数的表中
		 List<Integer> list=new ArrayList<Integer>();	
		 List<Double> listTF=new ArrayList<Double>();	
		 List<String> pnamelist=new ArrayList<String>();	
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
	     Connection conn=null;
	     conn=DBDao.GetConnection();
	     String sql1=null;
	     String sql2=null;
	     String name=null;
    try {
    	for(int i=0;i<7;i++) {
			int index=i+1;
			String catelog=index+"";
    		pnamelist=getListofProductname(conn, i);
    		//System.out.print(pnamelist.size());
    		listTF=getTFbyProductname(conn, i,pnamelist);
    		//System.out.print(listTF.size());
    		double[] weight_lable=new double[listTF.size()];
    		for(int j=0;j<listTF.size();j++) {
    			sql2="select *  from shop.userdraw where pname=\""+pnamelist.get(j)+"\" and lable=\""+catelog+"\";" ;
    			prestmt=conn.prepareStatement(sql2);
    	   		 rs=prestmt.executeQuery(sql2);    	
    	   		 while(rs.next()) {
    	   			 int actiontime=rs.getInt("actiontime");
    	   			 double weight=rs.getDouble("weight");
    	   			 double timereduce=rs.getDouble("timereduce");
    	   			 weight_lable[j]=round(actiontime*weight*timereduce*listTF.get(j),4);//最重要的权重公式
    	   		 }
    	   		prestmt.close();
    		}
    		for(int ii=0;ii<pnamelist.size();ii++) {
    			System.out.println(weight_lable[ii]);
    		}
    		insertUserCommendTable(conn,weight_lable,catelog,pnamelist);
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
}
