package irsyadhhs.cs.upi.edu.actionbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText etAdd = (EditText) findViewById(R.id.etData);
        Intent intent2 = getIntent();
        String pesan = intent2.getStringExtra("pesanedit");
        Button button = (Button) findViewById(R.id.button);
        button.setText("ADD");
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("ADD DATA");

        if(pesan!=null){
            button.setText("EDIT");
            tvTitle.setText("EDIT DATA");
            etAdd.setText(pesan);
            edit = true;
        }
    }

    public void klikButton(View v){
        EditText etAdd = (EditText) findViewById(R.id.etData);
        String add = etAdd.getText().toString();
        if(!edit) {
            Intent intent2 = getIntent();
            intent2.putExtra("string", add);
            setResult(RESULT_OK, intent2);
            finish();
        }else{
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("stringg", add);
            setResult(RESULT_OK, i);
            finish();
        }
    }
}
