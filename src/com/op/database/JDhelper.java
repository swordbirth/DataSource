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
	 * ��������
	 */
	public void DataBase() {
		try {
			Class.forName(name); // ָ����������
			conn = DriverManager.getConnection(url, user, password); // ��ȡ����
		} catch (ClassNotFoundException ex) {
			System.err.println("JDBC/ODBC not load fail");
			ex.printStackTrace();
		} catch (SQLException ex) {
			System.err.println("enable connection database");
			ex.printStackTrace();
		}
	}

	/**
	 * ���δ���ʱ��str������Ϊnull ��������ʱ��str�������ÿһ�δ���Ĺؼ�ֵ
	 * 
	 * @param sql
	 *            sql���
	 * @param str
	 *            ��ѯ�ؼ�ֵ��ֵ
	 * @return ��ѯ�����
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
	 * ���δ���ʱ��str������Ϊnull ��������ʱ��str�������ÿһ�δ���Ĺؼ�ֵ
	 * 
	 * @param sql
	 * @param str
	 * @return ���½����
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
	 * �ر�����
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
