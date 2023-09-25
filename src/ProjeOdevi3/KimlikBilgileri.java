package ProjeOdevi3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.filechooser.FileSystemView;

import com.mysql.cj.jdbc.ha.LoadBalancedMySQLConnection;

public abstract class KimlikBilgileri {
	public Scanner scIslem;
	public String Ad;
	public String Soyad;
	public String Address;
	public String tcNo;
	public String nickname;
	public String password;
	public String telNo;
	public int hesapNo = 345800000;

	public String sqlQueryHesapNoDondur ="SELECT hesapno,hesapno2 FROM deneme.musteri WHERE tcno=?";
	public String sqlQueryMusteriGiris = "SELECT count(tcno) as giris FROM deneme.musteri WHERE tcno=? and sifre=?";
	public String sqlQueryPersonelGiris = "SELECT count(tcno) as giris FROM deneme.personel WHERE username=? and password=?";
	public String sqlQueryMudurGiris = "SELECT count(id) as giris FROM deneme.mudur WHERE username=? and password=?";
	public String sqlQueryMusteriSayisi = "SELECT count(*) as musteriSayisi FROM deneme.musteri";
	public String sqlQueryBakiyeGet = "SELECT bakiye,bakiye2 FROM deneme.musteri WHERE tcno=?";
	//public String sqlQueryBakiyeGet2 = "SELECT bakiye,bakiye2 FROM deneme.musteri WHERE tcno=?";
	public String sqlQueryBakiyeGet1H = "SELECT bakiye,bakiye2 FROM deneme.musteri WHERE hesapno=?";
	public int hesapNoOlustur() {
		int kisi = getMusteriSayisi();
		hesapNo += kisi;
		return hesapNo;
	}

	public int getMusteriSayisi() {
		ResultSet res = null;
		int sayi = 0;
		res = Baglanti.yap(sqlQueryMusteriSayisi);
		try {
			if (res.next()) {
				sayi = res.getInt("musteriSayisi");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sayi;

	}

	public int getHesapNo(String tcno, String sqlQuery, int which) {
		int no = 0;
		ResultSet res = null;
		PreparedStatement ps = null;

		try {
			ps = Baglanti.myConn.prepareStatement(sqlQuery);
			ps.setString(1, tcno);
			res = ps.executeQuery();
			if (res.next()) {
				if (which == 1)
					no = res.getInt("hesapno");
				else
					no = res.getInt("hesapno2");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return no;
	}
	public int getBakiye(String tcno,String sqlQuery,int which) {
		int bakiye = 0;
		ResultSet res = null;
		PreparedStatement ps = null;
		try {
			ps = Baglanti.myConn.prepareStatement(sqlQuery);
			ps.setString(1,tcno);
			res = ps.executeQuery();
			if (res.next()) {
				if (which == 1)
					bakiye = res.getInt("bakiye");
				else
					bakiye = res.getInt("bakiye2");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bakiye;
	}
	public int getBakiyeWithHesapNo(int hesapnum,String sqlQuery,int which) {
		int bakiye = 0;
		ResultSet res = null;
		PreparedStatement ps = null;
		try {
			ps = Baglanti.myConn.prepareStatement(sqlQuery);
			ps.setInt(1,hesapnum);
			res = ps.executeQuery();
			if (res.next()) {
				if (which == 1)
					bakiye = res.getInt("bakiye");
				else
					bakiye = res.getInt("bakiye2");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bakiye;
	}
}
