package neo.com.sqllite_demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class act_AddProductActivity extends Activity {
    EditText editTextName;
    EditText editTextPrice;
    EditText editTextDes;
    DataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_add_product_activity);
        editTextName = (EditText) findViewById(R.id.edtName);
        editTextPrice = (EditText) findViewById(R.id.edtPrice);
        editTextDes = (EditText) findViewById(R.id.edtDes);
        db = new DataBaseHandler(this);


    }

    public void add(View view) {
        String name = editTextName.getText().toString();
        String price = editTextPrice.getText().toString();
        String des = editTextDes.getText().toString();
        if (name.length() == 0) {
            new AlertDialog.Builder(act_AddProductActivity.this)
                    .setMessage("Bạn cần nhập Tên sản phẩm")
                    .setCancelable(false)
                    .setNegativeButton("Ok", null)
                    .show();
        } else if (price.length() == 0) {
            new AlertDialog.Builder(act_AddProductActivity.this)
                    .setMessage("Bạn cần nhập giá sản phẩm")
                    .setCancelable(false)
                    .setNegativeButton("Ok", null)
                    .show();
        } else if (des.length() == 0) {
            new AlertDialog.Builder(act_AddProductActivity.this)
                    .setMessage("Bạn cần nhập mô tả cho sản phẩm")
                    .setCancelable(false)
                    .setNegativeButton("Ok", null)
                    .show();
        } else {
            ProductEntity product = new ProductEntity(name, Integer.parseInt(price), des);
            db.addProduct(product);
            Toast.makeText(this, "Thêm sản phẩm thành công!!!", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
