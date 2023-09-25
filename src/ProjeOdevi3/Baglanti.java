package ProjeOdevi3;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

import com.mysql.jdbc.Driver;

public class Baglanti extends KimlikBilgileri{

	private static String username = "root";
	private static String password = "123456";
	private static String url = "jdbc:mysql://localhost:3306/deneme";
	private static String driver = "com.mysql.cj.jdbc.Driver";
	public static Connection myConn;
	private static Statement myStat;
	private static ResultSet myRes;
	private String sqlQueryMusteriOku = "select * from deneme.musteri";
	
	Baglanti() {

	}

	static {
		try {
			Class.forName(driver);
			myConn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//overloading
	static ResultSet yap() {
		try {

			myStat = myConn.createStatement();
			myRes = myStat.executeQuery("select * from deneme.musteri");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return myRes;
	}
	
	static ResultSet yap(String sqlQuery) {
		try {

			myStat = myConn.createStatement();
			myRes = myStat.executeQuery(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myRes;
	}
	
	static void hesapEkle(int hesapNo,String tcno,String sqlQuery) {
		try {
			PreparedStatement ps = myConn.prepareStatement(sqlQuery);
			ps.setInt(1,hesapNo);
			ps.setString(2,tcno);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void hesapSil(String tcno,String sqlQuery) {
		PreparedStatement ps;
		try {
			ps = myConn.prepareStatement(sqlQuery);
			ps.setString(1, tcno);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void musteriEkle(String tcno, String isim, String soyisim, String telno, String adres,String sifre) {
		String sql_sorgu;
		sql_sorgu = "INSERT INTO deneme.musteri (tcno,isim,soyisim,telno,adres,sifre,bakiye,bakiye2) VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = myConn.prepareStatement(sql_sorgu);
			// ps.setInt(1, id);
			ps.setString(1, tcno);
			ps.setString(2, isim);
			ps.setString(3, soyisim);
			ps.setString(4, telno);
			ps.setString(5, adres);
			ps.setString(6, sifre);
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void personelEkle(String username,String password,String isim,String soyisim,String telno,String tcno,Date dt) {
		String sqlQuery = "INSERT INTO deneme.personel (username,password,isim,soyisim,telno,tcno,iseBaslamaTarihi) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = myConn.prepareStatement(sqlQuery);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, isim);
			ps.setString(4, soyisim);
			ps.setString(5, telno);
			ps.setString(6, tcno);
			Timestamp timestamp = new Timestamp(dt.getTime());
			ps.setTimestamp(7, timestamp);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void Sil(String tcno,String sql_sorgu) {
		try {
			sql_sorgu = "DELETE FROM deneme.musteri WHERE tcno=?";
			PreparedStatement ps = myConn.prepareStatement(sql_sorgu);
			ps.setString(1, tcno);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//overloading
	static int kontrolEt(String tcno) {
		ResultSet res = null;
		int varMi = 0;
		String sqlQuery = "SELECT count(tcno) as varmi FROM deneme.musteri WHERE tcno=?";
		PreparedStatement ps;
		try {
			ps = myConn.prepareStatement(sqlQuery);
			ps.setString(1, tcno);
			res = ps.executeQuery();
			if(res.next()) {
				varMi = res.getInt("varmi");
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return varMi;
	}
	
	static int kontrolEt(String tcno,String sqlQuery) {
		ResultSet res = null;
		int varMi = 0;
		PreparedStatement ps;
		try {
			ps = myConn.prepareStatement(sqlQuery);
			ps.setString(1, tcno);
			res = ps.executeQuery();
			if(res.next()) {
				varMi = res.getInt("varmi");
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return varMi;
	}

	static void personelSil(String no) {
		String sql_sorgu, sql_sorgu2;
		try {
			sql_sorgu = "DELETE FROM deneme.personel WHERE tcno=?";
		
			PreparedStatement ps = myConn.prepareStatement(sql_sorgu);
			ps.setString(1, no);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//overloading
	static ResultSet sorgula(String no) {
		ResultSet res = null;
		String sql_sorgu = "select * from deneme.musteri where tcno=?";
		PreparedStatement ps;
		try {
			ps = myConn.prepareStatement(sql_sorgu);
			ps.setString(1, no);
			res = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}
	static ResultSet sorgula(String no,String sql_sorgu) {
		ResultSet res = null;
		
		PreparedStatement ps;
		try {
			ps = myConn.prepareStatement(sql_sorgu);
			ps.setString(1, no);
			res = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	static void oku(String sqlQuery,int personel) {
		ResultSet res = Baglanti.yap(sqlQuery);
		
		try {
			if(personel==1) {
				while (res.next()) {
					// System.out.println(res.getString("id"));
					System.out.println(res.getString("tcno"));
					System.out.println(res.getString("isim"));
					System.out.println(res.getString("soyisim"));
					System.out.println(res.getString("telno"));
					System.out.println(res.getTimestamp("iseBaslamaTarihi"));
					System.out.println("\n");
				}
			}
			else if(personel==2){
				while (res.next()) {
					// System.out.println(res.getString("id"));
					System.out.println(res.getString("tcno"));
					System.out.println(res.getString("isim"));
					System.out.println(res.getString("soyisim"));
					System.out.println(res.getString("telno"));
					System.out.println(res.getString("adres"));
					System.out.println("\n");
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static int Giris(String kullaniciadi, String sifre,String sqlQuery) {
		ResultSet res = null;
		int girdiMi = 0;
		PreparedStatement ps;
		
		try {
			ps= myConn.prepareStatement(sqlQuery);
			ps.setString(1, kullaniciadi);
			ps.setString(2, sifre);
			res = ps.executeQuery();
			if (res.next()) {
				girdiMi = res.getInt("giris");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return girdiMi;
	}
	
	static void bakiyeGuncelle(String tcno,String sqlQuery,int bakiye) {
		PreparedStatement ps;
		try {
			ps = myConn.prepareStatement(sqlQuery);
			ps.setInt(1, bakiye);
			ps.setString(2, tcno);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

