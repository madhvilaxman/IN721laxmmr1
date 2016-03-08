package bit.laxmmr1.eventhandler;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnClick; //Declaring Variable
    TextView editText;
    public class KeyTypeHandler implements View.OnKeyListener {

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_AT)){

            EditText txtUserName=(EditText)findViewById(R.id.editText);

            Toast.makeText(MainActivity.this, "@ Don't Type @ ", Toast.LENGTH_LONG).show();
            return true;

        }
        return false;}
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick =(Button)findViewById(R.id.btnClick);

        EditText txtUserName = (EditText)findViewById(R.id.editText);
        KeyTypeHandler handler = new KeyTypeHandler();
        txtUserName.setOnKeyListener(handler);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "You pressed SingleClick Button!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } );
        btnClick.setOnLongClickListener((new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "You pressed the Long Click Button!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return true;
            }
        }));

    }
}
