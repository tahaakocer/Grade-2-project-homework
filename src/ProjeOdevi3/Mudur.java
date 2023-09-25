package ProjeOdevi3;

import java.util.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Mudur extends KimlikBilgileri {

	public Date iseBaslamaTarihi;
	public String personelTuru;
	private String sqlQueryKontrol = "SELECT count(tcno) as varmi FROM deneme.personel WHERE tcno=?";
	private String sqlQuerySil = "DELETE FROM deneme.personel WHERE tcno=?";
	private String sqlQueryOku = "select * from deneme.personel";
	private int secim;
	public void MudurEkrani() {
		scIslem = new Scanner(System.in);
		System.out.print("\n\n--------------------\n"
				+ "Mudur Paneli\n" 
				+ "--------------------\n"
				+ "1- Personel Ekle\n"
				+ "2- Personel Sil\n" 
				+ "3- Personel Listele\n"
				+ "--------------------\n"
				+ "Yapilacak islemi giriniz: ");
		secim = scIslem.nextInt();
		switch (secim) {
		case 1:
			PersonelEkle();
			break;
		case 2:
			PersonelSil();
			break;
		case 3:
			PersonelListele();
			break;
		default:
			System.out.println("Yanlis Giris Yaptiniz!");
			
		}
		scIslem.close();
	}
	public void PersonelEkle() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n--------------------\n"
							 + "---Personel Kayit---\n"
							 + "--------------------\n\n");
		System.out.print("TC No: ");
		tcNo = scan.next();
		System.out.print("Ad: ");
		Ad = scan.next();
		System.out.print("Soyad: ");
		Soyad = scan.next();
		System.out.print("Tel No: ");
		telNo = scan.next();
		System.out.print("Personel icin Kullanici Adi: ");
		nickname = scan.next();
		System.out.print("Personel icin Sifre: ");
		password = scan.next();
		iseBaslamaTarihi = new Date();
		if(Baglanti.kontrolEt(tcNo,sqlQueryKontrol)==0) {
			Baglanti.personelEkle(nickname, password, Ad, Soyad, telNo, tcNo, iseBaslamaTarihi);
			System.out.println("Personel basariyla kaydedilmistir.");
			scan.nextLine();
			MudurEkrani();			
		}else {
			System.out.println("Bu kimlik numarasi zaten kayitli!");
			scan.nextLine();
			PersonelEkle();
		}
		
		
	}

	public void PersonelSil() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n------------------\n"
				 			 + "---Personel Sil---\n"
				 			 + "-------------------\n\n");
		System.out.println("Silinecek personelin TC nosunu giriniz: ");
		tcNo = scan.next();
		if(Baglanti.kontrolEt(tcNo, sqlQueryKontrol)==1) {
			Baglanti.Sil(tcNo, sqlQuerySil);
			System.out.println("Personel basariyla silinmistir.");
			scan.nextLine();
			MudurEkrani();
		}else {
			System.out.println("Bu tc numarasinda kayitli personel yok!\n"
					+ "Lutfen tekrar deneyin...");
			PersonelSil();	
		}
	}

	public void PersonelListele() {
		Scanner scan = new Scanner(System.in);
		Baglanti.oku(sqlQueryOku,1);
		scan.nextLine();
		MudurEkrani();
	}

	
}
