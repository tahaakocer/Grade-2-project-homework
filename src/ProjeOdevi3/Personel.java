package ProjeOdevi3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Personel extends KimlikBilgileri {
	
	public String hesapTuru;
	public Date kayitTarihi;
	private int kisiSayisi;
	private int secim;
	private String sqlQueryKontrol = "SELECT count(tcno) as varmi FROM deneme.musteri WHERE tcno=?";
	private String sqlQuerySil = "DELETE FROM deneme.musteri WHERE tcno=?";
	private String sqlQueryOku = "select * from deneme.musteri";
	public Personel() {
	
	}

	public void PersonelEkrani() {
		scIslem = new Scanner(System.in);
		System.out.print("\n\n--------------------\n"
				+ "Personel Paneli\n" 
				+ "--------------------\n"
				+ "1- Musteri Ekle\n"
				+ "2- Musteri Sil\n" 
				+ "3- Musteri Listele\n" 
				+ "--------------------\n"
				+ "Yapilacak islemi giriniz: ");
		secim = scIslem.nextInt();
		switch (secim) {
		case 1:
			MusteriEkle();
			break;
		case 2:
			MusteriSil();
			break;
		case 3:
			MusteriListele();
			break;
		default:
			System.out.println("Yanlis Giris Yaptiniz!");
			
		}
		scIslem.close();
	}

	public void MusteriEkle() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n-------------------\n"
							 + "---Musteri Kayit---\n"
							 + "-------------------\n\n");
		System.out.print("TC No: ");
		tcNo = scan.next();
		System.out.print("Ad: ");
		Ad = scan.next();
		System.out.print("Soyad: ");
		Soyad = scan.next();
		System.out.print("Tel No: ");
		telNo = scan.next();
		System.out.println("Adres: ");
		Address = scan.nextLine();
		System.out.println("Musteri icin sifre: ");
		password = scan.next();
		if(Baglanti.kontrolEt(tcNo,sqlQueryKontrol)==0) {
			Baglanti.musteriEkle(tcNo, Ad, Soyad, telNo,Address,password);
			System.out.println("\nMusteri basariyla kaydedilmistir.\n");
			scan.nextLine();
			PersonelEkrani();
		}else {
			System.out.println("Bu kimlik numarası zaten kayitli!\n"
					+ "Lutfen tekrar deneyin...");
			MusteriEkle();
		}
		
	}

	public void MusteriSil() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n-------------------\n"
							 + "---Musteri Silme---\nn"
							 + "-------------------\n\n");
		System.out.println("Tc No: ");
		tcNo = scan.next();
		if(Baglanti.kontrolEt(tcNo,sqlQueryKontrol)==1) {
			Baglanti.Sil(tcNo,sqlQuerySil);
			System.out.println("Musteri basariyla silinmistir.");
			scan.nextLine();
			PersonelEkrani();
		}
		else {
			System.out.println("Bu TC numarasinda kayitli musteri yok!\n"
					+ "Lutfen tekrar deneyin...");
			MusteriSil();
		}

	}

	public void MusteriListele() {
		Scanner scan = new Scanner(System.in);
		Baglanti.oku(sqlQueryOku,2);
		scan.nextLine();
		PersonelEkrani();
		
		
	}
}
