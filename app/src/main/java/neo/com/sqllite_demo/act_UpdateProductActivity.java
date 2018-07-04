package neo.com.sqllite_demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class act_UpdateProductActivity extends Activity {
    EditText editTextName;
    EditText editTextPrice;
    EditText editTextDes;
    DataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_update_product_activity);
        editTextName = (EditText) findViewById(R.id.edtName);
        editTextPrice = (EditText) findViewById(R.id.edtPrice);
        editTextDes = (EditText) findViewById(R.id.edtDes);
        db = new DataBaseHandler(this);
        if (getIntent().getSerializableExtra("KEY_ID") != null) {
            ProductEntity product = (ProductEntity) getIntent().getSerializableExtra("KEY_ID");
            editTextName.setText(product.getName());
            editTextPrice.setText(product.getPrice() + "");
            editTextDes.setText(product.getDescription());
        }
    }

    public void update(View view) {

        String name = editTextName.getText().toString();
        String price = editTextPrice.getText().toString();
        String des = editTextDes.getText().toString();
        if (name.length() == 0) {
            new AlertDialog.Builder(act_UpdateProductActivity.this)
                    .setMessage("Bạn chưa nhập name")
                    .setCancelable(false)
                    .setNegativeButton("Ok", null)
                    .show();
        } else if (price.length() == 0) {
            new AlertDialog.Builder(act_UpdateProductActivity.this)
                    .setMessage("Bạn chưa nhập price")
                    .setCancelable(false)
                    .setNegativeButton("Ok", null)
                    .show();
        } else if (des.length() == 0) {
            new AlertDialog.Builder(act_UpdateProductActivity.this)
                    .setMessage("Bạn chưa nhập description")
                    .setCancelable(false)
                    .setNegativeButton("Ok", null)
                    .show();
        } else {
            if (getIntent().getSerializableExtra("KEY_ID") != null) {
                ProductEntity product = (ProductEntity) getIntent().getSerializableExtra("KEY_ID");
                product.setName(name);
                product.setPrice(Integer.parseInt(price));
                product.setDescription(des);
                db.updateProduct(product);
                Toast.makeText(this, "Update product successfully", Toast.LENGTH_SHORT).show();
                finish();
            }

        }

    }
}
