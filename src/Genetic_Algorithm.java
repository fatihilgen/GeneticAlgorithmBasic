/*
*YAPAY ZEKA- GENETİK ALGORİTMA ÖDEVİ
* Bu kod ile Genetik Algoritmanin temelleri öğrenilmeye çalışılmaktadır
* Tarih:11.05.2018
* Yazar: Fatih İLGEN
* Ödev hakkında:f(x)=x*x olan fonksiyonun (amaç fonksiyonu ) [0,31] aralığında maksimum degerinin elde edilmeye calisilmaktadir
*
*
*Kodlarımın sonuna doğru Caprazlama islemlerinde sıkıntılar cıkmaya basladı Normalde çift olması gereken popülasyon sayısı
*tek sayi olmasından dolayı sıkıntı almama neden oldu iterasyon şu an için 10 nesil hesabı yapacaktır.
*
*
* */

import java.math.BigDecimal;

import java.util.Random;

public class Genetic_Algorithm
{
    public static void main(String args[]) {
        int nesil=0;
        for (int iterasyon = 0; iterasyon<=  10; iterasyon++) {
            double CaprazlamaOrani = 0.4, MutasyonOrani = 0.1;
            char[] Bireyler = new char[5];
            int Deger[] = new int[5];
            double ToplamUygDeger = 0;
            double OrtalamaUyDeger = 0;
            String Kromozomlar[] = new String[5];
            String YeniKromozomlar[] = new String[6];

            int KopyaSayisi[] = new int[5];
            int Uygunlukdegeri[] = new int[5];
            double SecimDegeri[] = new double[5];
            double BeklenenDeger[] = new double[5];
            double ToplamSecimDegeri = 0;

            Random Rastgele = new Random();
            for (int i = 0; i <= 4; i++) {

                int a;
                a = Rastgele.nextInt(31);
                Deger[i] = a;


                int k = Deger[i];
                //Degerleri Binary e cevirmek icin alttaki fonksiyonu kullanmamin amaci
                //diger türde kullandigim binary cevirmede başında 0 olmuyordu.
                Kromozomlar[i] = intToString(Deger[i], 5);


                Uygunlukdegeri[i] = Deger[i] * Deger[i];

                ToplamUygDeger = ToplamUygDeger + Uygunlukdegeri[i];
                OrtalamaUyDeger = ToplamUygDeger / 5;

                SecimDegeri[i] = Uygunlukdegeri[i] / ToplamUygDeger;

                ToplamSecimDegeri = SecimDegeri[i] + ToplamSecimDegeri;


            }
            for (int k = 0; k <= 4; k++) {
                System.out.println("deger  = " + Deger[k]);
            }
            for (int k = 0; k <= 4; k++) {
                System.out.println("Kromozom lar    =    " + Kromozomlar[k]);
            }
            for (int i = 0; i <= 4; i++) {
                System.out.println("uygunluk degeri  =  " + Uygunlukdegeri[i]);
            }
            for (int i = 0; i <= 4; i++) {
                System.out.println("Secim Degeri =  " + virguldensonra(SecimDegeri[i], 3));
            }
            for (int i = 0; i <= 4; i++) {
                BeklenenDeger[i] = Uygunlukdegeri[i] / OrtalamaUyDeger;
            }//bunu burada boyle kullanmamin sebebi yukarıdaki
            //ortalama uygunluk degeri her defasında degişiyor olması
            //eger yukarıda kullansaydım beklenen degerin ilk elemani her zaman aynı olacaktı

            System.out.println("Toplam uygunluk degeri  = " + ToplamUygDeger);
            System.out.println("Toplam secim degeri =  " + virguldensonra(ToplamSecimDegeri, 3));
            System.out.println("Son  Toplam uygunluk degeri" + ToplamUygDeger);

            System.out.println("ortalama uygunluk degeri  = " + OrtalamaUyDeger);
            for (int i = 0; i <= 4; i++)

            {

                System.out.println("Beklenen deger =  " + virguldensonra(BeklenenDeger[i], 3));

            }
            for (int i = 0; i <= 4; i++)//bu for döngüsüyle beklenen deger ile kopya sayısını belirlemek
            //eger beklenen deger optimum degerin altındaysa bunu yuvarlayıp kopya sayımızı belirleyecez
            {
                int temp = (int) Math.round(BeklenenDeger[i]);
                KopyaSayisi[i] = temp;
                System.out.println("Kopya sayisi  = " + KopyaSayisi[i]);

            }
            //Alttaki kodumda ise kopya sayiyi kadar Kromozom alip ebevyn sayisini belirledikten sonra çaprazlama islemine gidecem
            //kromozom-1
            int topKopyaSayisi = 0;
            for (int a = 0; a <= 4; a++) {
                topKopyaSayisi = topKopyaSayisi + KopyaSayisi[a];

            }
            System.out.println("Toplam kopya sayisi =  " + topKopyaSayisi);
            int j = 0;
            for (int i = 0; i < 5; i++) {

                while (KopyaSayisi[i] != 0) {
                    YeniKromozomlar[j] = Kromozomlar[i];
                    j++;
                    KopyaSayisi[i]--;
                }


            }
            for (int i = 0; i < 5; i++) {

                System.out.println("Caprazlanacak olan yeni kromozom  = " + YeniKromozomlar[i]);

            }
nesil++;
            System.out.println("--------------------------------------------------------------------------------------------"+nesil+". Nesil");

        }






    }


    public static String intToString(int numara, int gruplama) {//bu fonksiyonum iki parametre alıyor :
        StringBuilder sonuc = new StringBuilder();             //numara gelecek olan decimal sayi, gruplama ise kac bit olacagina karar veriyor

        for(int i = 4; i >= 0 ; i--) {//eger istedigimiz Binary sayi 32 bit ise for dongusunu 31 e kadar devam ettiririz.
            int gizle = 1 << i;
            sonuc.append((numara & gizle) != 0 ? "1" : "0");

            if (i % gruplama == 0)
                sonuc.append(" ");
        }
        sonuc.replace(sonuc.length() - 1, sonuc.length(), "");

        return sonuc.toString();
    }
    public static double virguldensonra(double sayi, int digit_sayisi)//virgulden sonra istedigim sayi kadar belirliyorum
    {                                                         //iki parametrem var ilki double olan sayi ikincisi virgülden sonraki digit sayim
                                                              //eger vigulden sonra 4 istersem 4 tane digit gosterecektir
        BigDecimal uzunsayi = new BigDecimal(sayi);
        uzunsayi = uzunsayi.setScale(digit_sayisi,
                BigDecimal.ROUND_HALF_UP);

        return uzunsayi.doubleValue();
    }
    }







