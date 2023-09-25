package ProjeOdevi3;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.filechooser.FileSystemView;

public class Musteri extends KimlikBilgileri {
	public long hesapNo;
	public int bakiye;
	private int secim;
	private int hesapSayisi;
	private String tcno;
	private	String sqlQueryHesapEkle1 = "UPDATE deneme.musteri SET hesapno=? WHERE tcno=?";
	private String sqlQueryHesapEkle2 = "UPDATE deneme.musteri SET hesapno2=? WHERE tcno=?";
	private String sqlQueryHesapSil1 = "UPDATE deneme.musteri SET hesapno=NULL WHERE tcno = ?";
	private String sqlQueryHesapSil2 = "UPDATE deneme.musteri SET hesapno2=NULL WHERE tcno = ?";
	private String sqlQueryBakiye1 = "UPDATE deneme.musteri SET bakiye=? WHERE tcno = ?";
	private String sqlQueryBakiye2 = "UPDATE deneme.musteri SET bakiye2=? WHERE tcno = ?";
	
	Musteri(String tcno) {
		this.tcno = tcno;
	}

	
	public void MusteriEkrani() {
		scIslem = new Scanner(System.in);
		System.out.print("\n\n--------------------\n"
							+ "Musteri Paneli\n" 
						   + "--------------------\n"
						   + "1- Hesap Ekle\n"
						   + "2- Hesap Sil\n" 
						   + "3- Para Cek\n"
						   + "4- Para Yatir\n"
						   + "5- Havale\n"			
						   + "--------------------\n"
						   + "Yapilacak islemi giriniz: ");
		secim = scIslem.nextInt();
		switch (secim) {
		case 1:
			HesapEkle();
			break;
		case 2:
			HesapSil();
			break;
		case 3:
			paraCek();
			break;
		case 4:
			paraYatir();
			break;
		case 5:
			Havale();
			break;
		case 6:
			//HavaleRaporu();
			break;
		default:
			System.out.println("Yanlis Giris Yaptiniz!");
			
		}
		scIslem.close();
	}
	
	public void HesapEkle() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Adiniza hesap numarasi olusturmak icin 1,geri donmek icin 2 girin:");
		int secim = scan.nextInt();
		switch(secim) {
		case 1:
			int no = hesapNoOlustur();
			if(hesapSayisi==0) {
				Baglanti.hesapEkle(no,tcno,sqlQueryHesapEkle1);
				System.out.println("Hesap basariyla olusturulmustur.\n"
						+ "Hesap numaraniz: " + no);
				hesapSayisi++;
				scan.nextLine();
				MusteriEkrani();
			}
			else if(hesapSayisi ==1) {
				Baglanti.hesapEkle(no,tcno,sqlQueryHesapEkle2);
				System.out.println("Hesap basariyla olusturulmustur.\n"
						+ "Hesap numaraniz: " + no);
				hesapSayisi++;
				scan.nextLine();
				MusteriEkrani();
			}
			else {
				System.out.println("En fazla 2 adet hesap olusturabilirsiniz.");
				scan.nextLine();
				MusteriEkrani();
			}
			break;
		case 2:
			MusteriEkrani();
			break;
		default:
			System.out.println("Hatali giris yaptiniz!");
			scan.nextLine();
			HesapEkle();
		}
		
		
		
	}
	
	public void HesapSil() {
		int secim;
		Scanner scan = new Scanner(System.in);
		int hesap1,hesap2;
		hesap1 = getHesapNo(tcno,sqlQueryHesapNoDondur,1);
		hesap2 = getHesapNo(tcno,sqlQueryHesapNoDondur,2);
		
		System.out.println("1- " + hesap1 + "\n" +"2- " + hesap2);
		System.out.print("\nSilmek istediginiz hesabi secin: \n"
				+ "Geri donmek icin 3 girin:");
		secim = scan.nextInt();
		switch(secim) {
		case 1:
			Baglanti.hesapSil(tcno, sqlQueryHesapSil1);
			System.out.println("Sectiginiz hesap basariyla silinmistir.");
			scan.nextLine();
			MusteriEkrani();
			break;
		case 2:
			Baglanti.hesapSil(tcno, sqlQueryHesapSil2);
			System.out.println("Sectiginiz hesap basariyla silinmistir.");
			scan.nextLine();
			MusteriEkrani();
			break;
		case 3:
			MusteriEkrani();
			break;
		default:
			System.out.println("Hatali giris yaptiniz!");
			scan.nextLine();
			HesapSil();
		}
		}
		
	public void paraYatir() {
		Scanner scan = new Scanner(System.in);
		int secim,bakiye,hesap1,hesap2,yatir;
	
		hesap1 = getHesapNo(tcno,sqlQueryHesapNoDondur,1);
		hesap2 = getHesapNo(tcno,sqlQueryHesapNoDondur,2);
		
		System.out.println("1- " + hesap1 + " :"+getBakiye(tcno,sqlQueryBakiyeGet,1)+ "\n" 
						  +"2- " + hesap2 + " :"+getBakiye(tcno,sqlQueryBakiyeGet,2)+ "\n");
		System.out.print("\nYatirmak istediginiz hesabi secin: ");
		secim = scan.nextInt();
		System.out.println("Yatirmak istediginiz miktari girin: ");
		yatir = scan.nextInt();
		switch(secim) {
		case 1:
			bakiye = getBakiye(tcno, sqlQueryBakiyeGet,1);
			bakiye +=yatir;
			Baglanti.bakiyeGuncelle(tcno,sqlQueryBakiye1,bakiye);
			System.out.println("Islemeniz basariyla gerceklesmistir.");
			scan.nextLine();
			MusteriEkrani();
			break;
		case 2:
			bakiye = getBakiye(tcno, sqlQueryBakiyeGet,2);
			bakiye +=yatir;
			Baglanti.bakiyeGuncelle(tcno,sqlQueryBakiye2,bakiye);
			System.out.println("Islemeniz basariyla gerceklesmistir.");
			scan.nextLine();
			MusteriEkrani();
			break;
		default:
			System.out.println("Hatali giris yaptiniz!");
			scan.next();
			paraYatir();
		}
	}

	public void paraCek() {
		Scanner scan = new Scanner(System.in);
		int secim,bakiye,hesap1,hesap2,cek;
	
		hesap1 = getHesapNo(tcno,sqlQueryHesapNoDondur,1);
		hesap2 = getHesapNo(tcno,sqlQueryHesapNoDondur,2);
		
		System.out.println("1- " + hesap1 + " :"+getBakiye(tcno,sqlQueryBakiyeGet,1)+ "\n" 
						  +"2- " + hesap2 + " :"+getBakiye(tcno,sqlQueryBakiyeGet,2)+ "\n");
		System.out.print("\n Islem yapacaginiz hesabi secin: ");
		secim = scan.nextInt();
		System.out.println("Cekmek istediginiz miktari girin: ");
		cek = scan.nextInt();
		switch(secim) {
		case 1:
			bakiye = getBakiye(tcno, sqlQueryBakiyeGet,1);
			bakiye -=cek;
			if(bakiye >0) {
				Baglanti.bakiyeGuncelle(tcno,sqlQueryBakiye1,bakiye);
				System.out.println("Islemeniz basariyla gerceklesmistir.");
				//scan.next();
				MusteriEkrani();
			}
			else {
				System.out.println("Yeterli bakiyeniz yok!");
				MusteriEkrani();
			}
			
			break;
		case 2:
			bakiye = getBakiye(tcno, sqlQueryBakiyeGet,2);
			bakiye -=cek;
			if(bakiye > 0) {
				Baglanti.bakiyeGuncelle(tcno,sqlQueryBakiye2,bakiye);
				System.out.println("Islemeniz basariyla gerceklesmistir.");
				scan.next();
				MusteriEkrani();
			}
			else {
				System.out.println("Yeterli bakiyeniz yok!");
			}
			break;
		case 3:
			MusteriEkrani();
			break;
		default:
			System.out.println("Hatali giris yaptiniz!");
			scan.next();
			paraYatir();
		}

	}
	
	public void Havale() {
		Scanner scan = new Scanner(System.in);
		int hesap1,hesap2,hesapnum,miktar,bakiyeVeren,bakiyeAlan;
		hesap1 = getHesapNo(tcno,sqlQueryHesapNoDondur,1);
		hesap2 = getHesapNo(tcno,sqlQueryHesapNoDondur,2);
		System.out.println("1- " + hesap1 + " :"+getBakiye(tcno,sqlQueryBakiyeGet,1)+ "\n" 
				  +"2- " + hesap2 + " :"+getBakiye(tcno,sqlQueryBakiyeGet,2)+ "\n");
		System.out.print("\nGeri donmek icin 3 giriniz.\n Islem yapacaginiz hesabi secin: ");
		secim = scan.nextInt();
		System.out.println("Havale yapmak istediginiz hesap numarasini girin: ");
		hesapnum = scan.nextInt();
		System.out.println("Havale miktarini giriniz: ");
		miktar = scan.nextInt();
		switch(secim) {
		case 1:
			bakiyeVeren = getBakiye(tcno,sqlQueryBakiyeGet,1);
			bakiyeAlan = getBakiye(tcno,sqlQueryBakiyeGet,2);
			bakiyeVeren -= miktar;
			bakiyeAlan +=miktar;
			if(bakiyeVeren>0)
			{
				Baglanti.bakiyeGuncelle(tcno, sqlQueryBakiye1, bakiyeVeren);
				Baglanti.bakiyeGuncelle(tcno, sqlQueryBakiye2, bakiyeAlan);
				System.out.println("Havale basarili!");
				scan.nextLine();
				MusteriEkrani();
			}
			else {
				System.out.println("Yetersiz bakiye");
			}
			break;
		case 2:
			bakiyeVeren = getBakiye(tcno,sqlQueryBakiyeGet,1);
			bakiyeAlan = getBakiyeWithHesapNo(hesapnum,sqlQueryBakiyeGet,1);
			bakiyeVeren -= miktar;
			bakiyeAlan +=miktar;
			if(bakiyeVeren>0)
			{
				Baglanti.bakiyeGuncelle(tcno, sqlQueryBakiye1, bakiyeVeren);
				Baglanti.bakiyeGuncelle(tcno, sqlQueryBakiye2, bakiyeAlan);
				System.out.println("Havale basarili!");
				scan.nextLine();
				MusteriEkrani();
			}
			else {
				System.out.println("Yetersiz bakiye");
			}
			break;
		case 3:
			MusteriEkrani();
			break;
		default:
			System.out.println("Hatali giris yaptiniz!");
			scan.nextLine();
			Havale();
		}	
	}
}
