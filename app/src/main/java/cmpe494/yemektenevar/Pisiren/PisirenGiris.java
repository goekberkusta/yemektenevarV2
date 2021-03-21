package cmpe494.yemektenevar.Pisiren;

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
import cmpe494.yemektenevar.R;

public class PisirenGiris extends AppCompatActivity {

    TextInputEditText tel,pw;
    MaterialButton but;
    FirebaseDatabase db;
    DatabaseReference pisiren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pisiren_giris);

        tel = findViewById(R.id.ptelefon);
        pw = findViewById(R.id.psifre);


        db = FirebaseDatabase.getInstance();
        pisiren = db.getReference("Pisiren");

        but = findViewById(R.id.pisiren_giris);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInAppAdministator(tel.getText().toString(),pw.getText().toString());
            }
        });
    }

    private void signInAppAdministator(String t, String s) {
        ProgressDialog mDialog = new ProgressDialog(PisirenGiris.this);
        mDialog.setMessage("Lütfen Bekleyin");
        mDialog.show();

        String localphone = t;
        String localpw = s;

        pisiren.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child(localphone).exists())
                {
                    mDialog.dismiss();
                    Pisiren pisir = snapshot.child(localphone).getValue(Pisiren.class);
                    pisir.setTel(localphone);

                    if(pisir.getPw().equals(localpw)){
                        Intent login = new Intent(PisirenGiris.this,Restoran.class);
                        Sabit.currPisir = pisir;
                        startActivity(login);
                        finish();
                    }else
                    {
                        Toast.makeText(PisirenGiris.this,"Kullanıcı Adı veya Şifre Hatalı",Toast.LENGTH_SHORT).show();

                    }


                }else
                    Toast.makeText(PisirenGiris.this,"Kullanıcı Bulunamadı",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}