package bit.laxmmr1.week81task3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase demoDb;
    private Spinner spinnerCountryNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoDb = openOrCreateDatabase("demoDB", MODE_PRIVATE,null);
        spinnerCountryNames = (Spinner) findViewById(R.id.spnCountryNames);

        String [] countryNames = {""};

        String createQuery = "CREATE TABLE IF NOT EXISTS tblCity(" + "cityID INTEGER PRIMARY KEY AUTOINCREMENT, " + "cityNames TEXT NOT NULL, " + "countryName TEXT NOT NULL);";
        demoDb.execSQL(createQuery);

        demoDb.execSQL("INSERT INTO tblCity VALUES(null, 'Amsterdam', 'The Netherlands')");
        demoDb.execSQL("INSERT INTO tblCity VALUES(null, 'Berlin', 'Germany')");
        demoDb.execSQL("INSERT INTO tblCity VALUES(null, 'Cairo', 'Egypt')");
        demoDb.execSQL("INSERT INTO tblCity VALUES(null, 'Dunedin', 'New Zealand')");
        demoDb.execSQL("INSERT INTO tblCity VALUES(null, 'DarEsSalaam', 'Tanzania')");

        String selectQuery = "SELECT * FROM tblCity";
        Cursor recordSet = demoDb.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();
        String[] displayStringArray = new String [recordCount];

        int cityNameIndex = recordSet.getColumnIndex("cityNames");
        int countryNameIndex = recordSet.getColumnIndex("countryName");

        recordSet.moveToFirst();

        for(int r = 0; r<recordCount; r++)
        {
            String cityName = recordSet.getString(cityNameIndex);
            String countryName = recordSet.getString(countryNameIndex);
            displayStringArray[r] = cityName + "," + countryName;

            recordSet.moveToNext();
        }//End for
    }//End Oncreate
}//End MainClass
