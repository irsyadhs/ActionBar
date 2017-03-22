package irsyadhhs.cs.upi.edu.actionbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items = new ArrayList<>();
    ListView listNama;

    //penghubung antara data dengan listview
    ArrayAdapter adapter;
    String pesan;
    int posisi;
    static final int ACT2_REQUEST = 99; //request code
    static final int ACT3_REQUEST = 100; //request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        listNama = (ListView) findViewById(R.id.listNama);
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, items);

        //set adapter ke list view
        listNama.setAdapter(adapter);

        listNama.setOnItemClickListener( new myAdapterView() );
        /*Intent i2 = getIntent();
        String ambil = i2.getStringExtra("stringg");
        if(ambil!=null){
            items.set(posisi, ambil);
        }*/

    }

    private class myAdapterView implements AdapterView.OnItemClickListener {
        //int mSelectedItem;
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            String isiBaris = (String) listNama.getItemAtPosition(position);
            pesan = /*"Posisi:"+position +"->"+*/ isiBaris;
            /*Toast toast = Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_SHORT);
            toast.show();*/
            //mSelectedItem = position;
           // listNama.setSelection(posisi);
            posisi = position;
            adapter.notifyDataSetChanged();
        }

        /*@Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View view = View.inflate(listNama, R.layout.activity_main, null);

            if (position == mSelectedItem) {
                // set your color
            }

            return view;
        }*/
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);


        // /cek request code
        if(requestCode == ACT2_REQUEST){
            String terima = data.getStringExtra("string");
            //tambah item
            items.add(terima);
            //JANGAN LUPA refresh listview, agar layar terupdate
            adapter.notifyDataSetChanged();
        }

        if(requestCode == ACT3_REQUEST){
            String terima = data.getStringExtra("stringg");
            //tambah item
            items.set(posisi, terima);
            //JANGAN LUPA refresh listview, agar layar terupdate
            adapter.notifyDataSetChanged();
        }
    }


    //action bar code
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mTambah:
                /*Intent intent2 = new Intent(this, Main2Activity.class);
                intent2.putExtra("mode", "ADD");
                startActivity(intent2);*/
                Intent intent3 = new Intent(this, Main2Activity.class);
                startActivityForResult(intent3, ACT2_REQUEST);

                //Toast.makeText(getApplicationContext(), "Tambah", Toast.LENGTH_LONG).show();
                return true;
            case R.id.mHapus:
                items.remove(posisi);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.mEdit:
                Intent intent2 = new Intent(this, Main2Activity.class);
                intent2.putExtra("pesanedit", pesan);
                startActivityForResult(intent2, ACT3_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
