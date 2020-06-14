package utils;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class IpadrUtils {
	private static final String NGINX_IP_HEADER = "X-Real-IP";
    private static final String NGINX_X_FORWARDED_FOR = "X-Forwarded-For";
    /**
     * 功能描述: 获取ip（兼容nginx转发）
     *
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ips = request.getHeader(NGINX_X_FORWARDED_FOR);
        String[] ipArray = StringUtils.split(ips, ",");
        if (ArrayUtils.isNotEmpty(ipArray)) {
            return StringUtils.trim(ipArray[0]);
        } else {
            String ip = request.getHeader(NGINX_IP_HEADER);
            if (StringUtils.isEmpty(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        }
    }
    public static String getTime(){
		String urlDate = null;
		String url="http://time.tianqi.com";
		try {
			URL url1 = new URL(url);
			URLConnection  conn = url1.openConnection();  //生成连接对象
			conn.connect();  //连接对象网页
			Date date = new Date(conn.getDate());  //获取对象网址时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置日期格式
			urlDate = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return urlDate;
	}
    @SuppressWarnings("deprecation")
	public static String getAutoLogoutTime(String dateString) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置日期格式
    	Date date = df.parse(dateString);
    	String urlDate = null;
        Calendar n=Calendar.getInstance();  
        n.setTime(date);  
    	n.add(Calendar.MINUTE,60);
    	Date date1=n.getTime();
		try {
			urlDate = df.format(date1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlDate;
	}
    public static int subTime(String date1){
    	String date2=getTime().substring(0, 10);
    	int y1=Integer.parseInt(date1.substring(0,4));
    	int y2=Integer.parseInt(date2.substring(0,4));
    	int m1=Integer.parseInt(date1.substring(5,7));
    	int m2=Integer.parseInt(date2.substring(5,7));
    	int d1=Integer.parseInt(date1.substring(8,date1.length()));
    	int d2=Integer.parseInt(date2.substring(8,date1.length()));
    	int ans=(y2*365+m2*30+d2-d1-m1*30-y1*365);
    	return ans;
    }
}
