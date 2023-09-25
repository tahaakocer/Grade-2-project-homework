package ProjeOdevi3;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.Console;

class GirisSistemi extends KimlikBilgileri {
	Scanner sc, scp, scm;
	File fileMusteri, filePersonel, fileMudur;
	String filePath;
	String text;
	String text2;

	public GirisSistemi() {
		
	}
	class GirisEkrani {
		int secim;
		Console console;
		Scanner scan = new Scanner(System.in);
		public GirisEkrani() {
		}

		public void Secim() {
			System.out.print("--------------------\n" 
					+ "Kocerbank'a Hosgeldiniz..\n"
					+ "--------------------\n" 
					+ "1- Musteri\n"
					+ "2- Personel\n" 
					+ "3- Mudur\n" 
					+ "--------------------\n" 
					+ "Seciminizi giriniz: ");
			secim = scan.nextInt();
			switch (secim) {
			case 1:
				MusteriGiris();
				break;
			case 2:
				PersonelGiris();
				break;
			case 3:
				MudurGiris();
				break;
			default:
				System.out.println("Yanlis giris Yaptiniz!");
			}
			
		}

		private void MusteriGiris() {

			Scanner scanMusteri = new Scanner(System.in);
			System.out.print("--------------------\n" + "TC No Giriniz: ");
			nickname = scanMusteri.next();
			System.out.print("Sifre: ");
			password = scanMusteri.next();
			if (Baglanti.Giris(nickname, password,sqlQueryMusteriGiris)==1) {
				System.out.println("\n\n\nGiris Basarili..");
				Musteri musteri = new Musteri(nickname);
				musteri.MusteriEkrani();
				
			} else {
				System.out.println("\n\n\nGiris Basarisiz..\n\n\n");
				MusteriGiris();
			}

		}

		private void PersonelGiris() {
			Scanner scanPersonel = new Scanner(System.in);
			System.out.print("--------------------\n"
					+ "Kullanici Adi: ");
			nickname = scanPersonel.next();
			System.out.print("Sifre: ");
			password = scanPersonel.next();
			if(Baglanti.Giris(nickname, password,sqlQueryPersonelGiris) == 1) {
				System.out.println("\n\n\nGiris Basarili..\n\n");
				Personel personel = new Personel();
				scanPersonel.nextLine();
				personel.PersonelEkrani();
			}
			else {
				System.out.println("\n\n\nGiris Basarisiz..\n\n\n");
				PersonelGiris();
			}
		}
		
		private void MudurGiris() {
			Scanner scanMudur = new Scanner(System.in);
			System.out.print("--------------------\n"
					+ "Kullanici Adi: ");
			nickname = scanMudur.next();
			System.out.print("Sifre: ");
			password = scanMudur.next();
			if(Baglanti.Giris(nickname, password,sqlQueryMudurGiris)==1) {
				System.out.println("\n\n\nGiris Basarili..");
				Mudur mudur = new Mudur();
				scanMudur.nextLine();
				mudur.MudurEkrani();
			}
			else {
				System.out.println("\n\n\nGiris Basarisiz..\n\n\n");
				PersonelGiris();
			}
		}
	}
}
