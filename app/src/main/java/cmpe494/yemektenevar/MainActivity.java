package cmpe494.yemektenevar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MaterialButton pisiren,yiyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pisiren = findViewById(R.id.pisiren);
        pisiren.setOnClickListener(this);
        yiyen = findViewById(R.id.yiyen);
        yiyen.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view == pisiren)
        {
            Toast.makeText(this,"Pisiren",Toast.LENGTH_SHORT).show();
        }else if(view == yiyen)
        {
            Toast.makeText(this,"Yiyen",Toast.LENGTH_SHORT).show();

        }
    }
}