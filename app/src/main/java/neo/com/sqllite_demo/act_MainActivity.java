package neo.com.sqllite_demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class act_MainActivity extends AppCompatActivity {
    ListView lstView;
    ListProductAdapter mobjProductAdapter;
    ArrayList<ProductEntity> mArrProducts;
    DataBaseHandler mobjDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_activity_main);
        initControl();
        mobjDbHandler = new DataBaseHandler(this);
        mArrProducts = mobjDbHandler.getAllProduct();
        mobjProductAdapter = new
                ListProductAdapter(this, mArrProducts);
        lstView.setAdapter(mobjProductAdapter);
        mobjProductAdapter.notifyDataSetChanged();
        registerForContextMenu(lstView);
    }

    private void initControl() {
        lstView = (ListView) findViewById(R.id.listview_product);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listview_product) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.contextmenu, menu);
            menu.setHeaderTitle("Context Menu");
            menu.setHeaderIcon(R.drawable.menuimg);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        // Toast.makeText(this, info.position + "", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.idUpdate:
                /*Toast.makeText(this, "UPDATE", Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(act_MainActivity.this,
                        act_UpdateProductActivity.class);
                ProductEntity product = mArrProducts.get(info.position);

                intent.putExtra("KEY_ID", product);
                startActivity(intent);
                return true;
            case R.id.idDelete:
                new AlertDialog.Builder(act_MainActivity.this)
                        .setMessage("Bạn có chắc chắn muốn xóa không?")
                        .setCancelable(false)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteProduct(info.position);
                                Toast.makeText(act_MainActivity.this,
                                        "DELETE SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteProduct(int pos) {
        // delete in db
        ProductEntity product = mArrProducts.get(pos);
        mobjDbHandler.deleteProduct(product);
        // delete in listview
        mArrProducts.remove(pos);
        mobjProductAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.idAdd) {
            //Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(act_MainActivity.this, act_AddProductActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mArrProducts = mobjDbHandler.getAllProduct();
        mobjProductAdapter = new ListProductAdapter(this, mArrProducts);
        lstView.setAdapter(mobjProductAdapter);
        //listProductAdapter.notifyDataSetChanged();
    }
}
