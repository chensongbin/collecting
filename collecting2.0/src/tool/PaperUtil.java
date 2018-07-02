package tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

import javabean.Column;
import javabean.Paper;
import javabean.PaperItem;




public class PaperUtil {

	//测试 fastjson的使用
	public static void main(String[] args) {
		System.out.println(getPaperSubmitData("111528357676291"));
	}
	
	//将创建的问卷存入数据库
	public static void saveToDatabase(int userId, String paperName, String paperHTML, String paperStatus, String createDate, String createTimeStamp, String titles) {		
		//插入paper表
		String papersql = "INSERT INTO `paper`(`uniqueKey`, `paperName`, `userId`, `createDate`, `paperStatus`, `paperHTML`) VALUE(?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = DbUtil.getConn().prepareStatement(papersql);
			ps.setString(1, userId+createTimeStamp);
			ps.setString(2, paperName);
			ps.setInt(3, userId);
			ps.setString(4, createDate);
			ps.setString(5, paperStatus);
			ps.setString(6, paperHTML);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//
		ArrayList<String> arr = new ArrayList<String>();
		arr = (ArrayList<String>) JSON.parseArray(titles, String.class); 
		
		String sql = "CREATE TABLE `" + userId + createTimeStamp + "`(";	
		sql += "`id` INT AUTO_INCREMENT PRIMARY KEY";
		for(int i=0;i<arr.size();++i) {
			sql = sql + ",`" + arr.get(i) + "` TEXT";
		}
		sql += ")ENGINE=INNODB  DEFAULT CHARSET=utf8;";		
		//打印创建问卷的sql语句
		//System.out.println(sql);
		DbUtil.update(sql);
	}
	
	
	//通过唯一识别码删除问卷
	public static boolean deleteByUK(String uk) {
		boolean flag = true;
		String sql = "DELETE FROM paper WHERE uniqueKey=?;";
		PreparedStatement ps = null;
		try {
			ps = DbUtil.getConn().prepareStatement(sql);
			ps.setString(1, uk);
			ps.executeUpdate();
		} catch (SQLException e) {
			flag = false;
			System.out.println("数据库:删除问卷失败！");
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "drop table `" + uk + "`;";
		DbUtil.update(sql);		
		return flag;
	}
	

	//通过唯一识别码改变问卷状态
	public static boolean changePaperStatusByUK(String uk, String status) {
		boolean ok = true;
		String sql = "update paper set paperStatus=? where uniqueKey=?";
		PreparedStatement ps = null;
		try {
			ps = DbUtil.getConn().prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, uk);
			ps.executeUpdate();
		} catch (SQLException e) {
			ok = false;
			System.out.println("数据库：改变问卷状态！失败");
			e.printStackTrace();
		}	
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	
	//获得问卷的所有问题，并转化为json格式
	public static String getColumn(String uk) {
		String sql = "select COLUMN_NAME from information_schema.COLUMNS where table_name =\""+uk+"\" and table_schema = \"collectingdb\";"; 
		ArrayList<Column> arr = new ArrayList<Column>();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			conn = DbUtil.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			int i=1;
			while(rs.next()) {
				Column column = new Column(Integer.toString(i) ,rs.getString(1));
				if(i==1) {
					column.setFormatter("idFormatter");
				}
				arr.add(column);
				++i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{    
            try {    
                rs.close();    
                st.close();    
                conn.close();    
            } catch (SQLException e) {        
                e.printStackTrace();    
            }    
        }    
		String ans = JSON.toJSONString(arr);
		//
		//System.out.println("column:" + ans);
		return ans;
	}
	
	
	//获得问卷所有提交的数据
	public static String getPaperSubmitData(String uk) {
		String ans = null;
		ArrayList<HashMap<String, String>> arr = new ArrayList<HashMap<String, String>>();
		String sql = "select * from `" + uk + "`;";
		int colNum;
		HashMap<String, String> hm = null;
		System.out.println(sql);
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			conn = DbUtil.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			colNum = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				hm = new HashMap<String, String>();
				for(int i=1;i<=colNum;++i) {
					hm.put(Integer.toString(i), rs.getString(i));
				}
				arr.add(hm);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally{    
            try {    
                rs.close();    
                st.close();    
                conn.close();    
            } catch (SQLException e) {        
                e.printStackTrace();    
            }    
        }     
		ans = JSON.toJSONString(arr);
		return ans;
	}
	
	
	//获取问卷信息
	public static Paper getPaperByUk(String uk) {
		Paper paper = new Paper();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from paper where uniqueKey=?;";
		try {
			conn = DbUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uk);
			rs = ps.executeQuery();
			if(rs.next()) {	
				if(rs.getString(5).equals("0")) {
					paper.setFlag(0);
				}else {
					paper.setFlag(1);
					paper.setTitle(rs.getString(2));
					paper.setHtml(rs.getString(6));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{    
            try {    
                rs.close();    
                ps.close();    
                conn.close();    
            } catch (SQLException e) {        
                e.printStackTrace();    
            }    
        }     
		return paper;
	}
	
	
	//获取指定用户的所有问卷
	public static String getData(int userId) {
		String data=null;
		ArrayList<PaperItem> dataArr = new ArrayList<PaperItem>();
		String sql = "select * from paper where userId=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbUtil.getConn();
			ps =  conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String createDate = rs.getString(4);
				String paperName = rs.getString(2);
				String uniqueKey = rs.getString(1);
				String paperStatus = rs.getString(5)+"+"+uniqueKey;
				String id = rs.getString(1);
				PaperItem paperItem = new PaperItem(createDate, paperName, uniqueKey, paperStatus, id);
				dataArr.add(paperItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			 try {    
	                rs.close();    
	                ps.close();    
	                conn.close();    
	            } catch (SQLException e) {        
	                e.printStackTrace();    
	            }    
		}
		data = JSON.toJSONString(dataArr);
		//System.out.println(data);
		return data;
	}
	
	
	//问卷提交，即数据库表新增一行
	public static int insertRow(HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		String uk_key = (String)names.nextElement();
		String uk = request.getParameter(uk_key);
		String sql = "insert into `" + uk + "` value(null";
		
		while(names.hasMoreElements()) {
			String key = (String)names.nextElement();
			String[] values = request.getParameterValues(key);
			String temp = "";
			if(values.length>1) {
				for(int i=0;i<values.length;++i) {
					temp = temp + "选择" + (i+1) + ":" + values[i] + "  "; 
				}
			}else {
				temp = values[0];
			}
			sql = sql + ",\'" + temp + "\'";
		}
		sql += ")";	
		return DbUtil.update(sql);
		//System.out.println(DbUtil.update(sql));	
	}
	
	
	// 删除指定问卷的指定行
	public static boolean deleteRowByUK(String uk, String id) {
		int returnMsg = 0;
		String sql = "delete from `" + uk + "` where id = ?;";
		Connection conn = null;
		PreparedStatement ps = null;
		conn = DbUtil.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			returnMsg = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(returnMsg ==1 ) return true;
		else return false;
	}
}
