package cmpe494.yemektenevar.Yiyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cmpe494.yemektenevar.Pisiren.Model.Pisiren;
import cmpe494.yemektenevar.Pisiren.PisirenGiris;
import cmpe494.yemektenevar.Pisiren.Restoran;
import cmpe494.yemektenevar.Pisiren.Sabit;
import cmpe494.yemektenevar.R;
import cmpe494.yemektenevar.Yiyen.Model.Yiyen;

public class YiyenGiris extends AppCompatActivity {

    TextInputEditText telephone,password;
    MaterialButton button;
    FirebaseDatabase database;
    DatabaseReference yiyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yiyen_giris);

        telephone = findViewById(R.id.telefon);
        password = findViewById(R.id.sifre);


        database = FirebaseDatabase.getInstance();
        yiyen = database.getReference("Yiyen");

        button = findViewById(R.id.yiyen_giris);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInAppAdministator(telephone.getText().toString(),password.getText().toString());
            }
        });
    }

    private void signInAppAdministator(String t, String s) {
        ProgressDialog mDialog = new ProgressDialog(YiyenGiris.this);
        mDialog.setMessage("Lütfen Bekleyin");
        mDialog.show();

        String localphone = t;
        String localpw = s;

        yiyen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child(localphone).exists())
                {
                    mDialog.dismiss();
                    Yiyen yiyen = snapshot.child(localphone).getValue(Yiyen.class);
                    yiyen.setTelephone(localphone);

                    if(yiyen.getPassword().equals(localpw)){
                        Intent login = new Intent(YiyenGiris.this, Yemekler.class);
                        YiyenSabit.currYiyen = yiyen;
                        startActivity(login);
                        finish();
                    }else
                    {
                        Toast.makeText(YiyenGiris.this,"Kullanıcı Adı veya Şifre Hatalı",Toast.LENGTH_SHORT).show();

                    }


                }else
                    Toast.makeText(YiyenGiris.this,"Kullanıcı Bulunamadı",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}