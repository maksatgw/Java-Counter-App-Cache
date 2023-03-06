package com.maksat.counterappcache;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Adım 1 -> Kullanacağımız component ve değişkenler
    //1[1]Ana ekranda gördüğümüz textView
    TextView textView;
    //1[2]Cache ve local storage için kullandığımız shared preferences paketi
    SharedPreferences sharedPreferences;
    //1[3]Ana ekrandaki gördüğümüz textView'ın değerini tutan değişken
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Adım 2 -> Değişkenlerin atanmaları
        //2[1] textView Değişkeni
        textView = findViewById(R.id.textView);
        //2[3] sharedPreferences
        //2[3][1] tanımladığımız sharedpreferences değişkenini, bu context içinde getSharedPreferences kullanarak tanımlıyoruz. Bu aşamada bu aşamada bizden package name istiyor. Uygulamamızın paket adını veriyoruz. Sadece bu class içinde erişlebilmesi için Context modunu private olarak veriyoruz.
        sharedPreferences = this.getSharedPreferences("com.maksat.counterappcache", Context.MODE_PRIVATE);
        //2[4] Cache'de tutacağımız değişken int bir değer olduğu için sharedpreferences'in getInt metodunu kullanıyoruz. Atadığımız değişkenin int olması gerekiyor. getInt metodu bizden bir key ve default value ister. key, içine kaydedeceğimiz veriyi çağırmamızı sağlar. Default Value, eğer key içinde bir değer yoksa döndüreceği varsayılan değeri temsil eder.
        int storedData = sharedPreferences.getInt("storedData", 0);
        //2[5]textView'e verilecek değeri storedData içindeki değere eşitliyoruz. Böylelikle count, sharedPreferences içindeki sayıya eşitleniyor. Uygulama başladığında metodlar uygulanırken kaydedilen sayı üzerinden işlem yapar.
        count = storedData;
        //2[6] Değişkenimizi Stringe dönüştürüp TextView'a atıyoruz. Bunu onCreate altında yaptığımız için uygulama açıldığında kaydettiğimiz sayı ekrana geliyor.
        String scount = String.valueOf(storedData);
        textView.setText(scount);
    }

    //Adım 3 -> Metodlar
    //3[1] Arttırma metodu
    public void increment(View view){
        //3[1][1] Butona tıklandığında, textView'e atanan değer olan count değişkenini 1 arttırıyoruz.
        count++;
        //3[1][2] değeri string'e dönüştürüyoruz ve textView'a atamasını yapıyoruz.
        String scount = String.valueOf(count);
        textView.setText(scount);
        //3[1][3] Değiştirdiğimiz count değerini, sharedpreferences'in edit ve (değişkenimiz int olduğu için) putInt metodları ile kaydediyoruz. Bu esnada putInt bizden key ve kaydedilecek değeri ister. apply metoduyla kaydetmeyi bitirir.
        sharedPreferences.edit().putInt("storedData", count).apply();
    }
    //3[2] eksiltme metodu
    public void decrement(View view){
        //3[2][1] Butona tıklandığında, textView'e atanan değer olan count değişkenini 1 azaltıyoruz.
        count--;
        //GOTO --> 3[1][2] & 3[1][3]
        String scount = String.valueOf(count);
        textView.setText(scount);
        sharedPreferences.edit().putInt("storedData", count).apply();
    }
    //3[3] sıfırlama metodu
    public void reset(View view){
        //3[3][1] Butona tıklandığında, textView'e atanan değer olan count değişkenini 0'a eşitliyoruz.
        count = 0;
        //GOTO --> 3[1][2] & 3[1][3]
        String scount = String.valueOf(count);
        textView.setText(scount);
        sharedPreferences.edit().putInt("storedData", count).apply();
        //3[3][2] Küçük bir Toast mesajı.
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        
    }
}