package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spnCat;
    Spinner spnSub;
    Button btnGo;
    ArrayList<String> alNumber;
    ArrayAdapter<String> aaNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnCat = findViewById(R.id.spinner);
        spnSub = findViewById(R.id.spinner2);
        btnGo = findViewById(R.id.buttonGo);

        alNumber = new ArrayList<>();
        aaNumber = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alNumber);
        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] strNumber;
                alNumber.clear();
                switch(i){
                    case 0:
                        strNumber=getResources().getStringArray(R.array.rparray);
                        alNumber.addAll(Arrays.asList(strNumber));
                        spnSub.setAdapter(aaNumber);
                        break;
                    case 1:
                        strNumber=getResources().getStringArray(R.array.soiarray);
                        alNumber.addAll(Arrays.asList(strNumber));
                        spnSub.setAdapter(aaNumber);
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url;

                String[][] sites = {
                        {
                            "http://www.rp.edu.sg", "http://www.rp.edu.sg/student-life"
                        },
                        {
                            "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"
                        }
                };
                url = sites[spnCat.getSelectedItemPosition()][spnSub.getSelectedItemPosition()];
                /*
                int main = spnCat.getSelectedItemPosition();
                int sub = spnSub.getSelectedItemPosition();
                if (main == 1){
                    if (sub == 1){
                        url += "http://www.rp.edu.sg";
                    }else if (sub == 2){
                        url += "https://www.rp.edu.sg/student-life";
                    }
                }else if (main == 1){
                    if (sub == 1){
                        url += "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
                    }else if (sub == 2){
                        url += "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
                    }
                }
                */
                Intent intent = new Intent(getBaseContext(),SiteActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        int cat = spnCat.getSelectedItemPosition();
        int sub = spnSub.getSelectedItemPosition();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putInt("cat", cat);
        prefEdit.putInt("sub", sub);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int cat = prefs.getInt("cat", 0);
        int sub = prefs.getInt("sub", 0);
        spnSub.setSelection(sub);
        spnCat.setSelection(cat);
    }
}
