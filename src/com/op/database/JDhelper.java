package com.op.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDhelper {
	public static final String url = "jdbc:mysql://localhost/studentmanager";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "jyhcjk";

	public Connection conn = null;
	public PreparedStatement pst = null;

	/*
	 * 连接数据
	 */
	public void DataBase() {
		try {
			Class.forName(name); // 指定连接类型
			conn = DriverManager.getConnection(url, user, password); // 获取连接
		} catch (ClassNotFoundException ex) {
			System.err.println("JDBC/ODBC not load fail");
			ex.printStackTrace();
		} catch (SQLException ex) {
			System.err.println("enable connection database");
			ex.printStackTrace();
		}
	}

	/**
	 * 单次处理时，str可以置为null 批量处理时，str数组添加每一次处理的关键值
	 * 
	 * @param sql
	 *            sql语句
	 * @param str
	 *            查询关键值的值
	 * @return 查询结果集
	 */
	public ResultSet Search(String sql, String[] str) {
		ResultSet ret = null;

		DataBase();
		try {
			pst = conn.prepareStatement(sql);
			if (str != null) {
				for (int i = 0; i < str.length; i++) {
					pst.setString(i + 1, str[i]);
				}
			}
			ret = pst.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ret;
	}

	/**
	 * 单次处理时，str可以置为null 批量处理时，str数组添加每一次处理的关键值
	 * 
	 * @param sql
	 * @param str
	 * @return 更新结果集
	 */
	public int Update(String sql, String[] str) {
		int ret = 0;

		DataBase();
		try {
			pst = conn.prepareStatement(sql);

			if (str != null) {
				for (int i = 0; i < str.length; i++) {
					pst.setString(i + 1, str[i]);
				}
			}
			ret = pst.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ret;
	}

	/*
	 * 关闭连接
	 */
	public void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
