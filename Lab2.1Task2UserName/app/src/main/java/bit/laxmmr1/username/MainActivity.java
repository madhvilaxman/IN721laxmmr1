package bit.laxmmr1.username;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public class KeyTypeHandler implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_UP)) {
                EditText txtUserName = (EditText) findViewById(R.id.editText);
                Editable userName = txtUserName.getText();
                if (userName.length() != 7) {
                    Toast.makeText(MainActivity.this, "Username must be 8 characters long , " + userName.toString(), Toast.LENGTH_LONG).show();
                    return true;
                } else {
                    Toast.makeText(MainActivity.this, "Thank you, " + userName.toString(), Toast.LENGTH_LONG).show();
                    return true;
                }
            }
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtUserName = (EditText) findViewById(R.id.editText);
        KeyTypeHandler handler = new KeyTypeHandler();
        txtUserName.setOnKeyListener(handler);
    }
}
