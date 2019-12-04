package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.adapter.ProductAdapter;
import com.example.projectswd.contract.ServiceActivityContract;
import com.example.projectswd.model.CartObject;
import com.example.projectswd.model.FilterObj;
import com.example.projectswd.model.FilterProduct;
import com.example.projectswd.model.Product;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.ServiceActivityPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity implements ServiceActivityContract.view {

    List<Product> list;

    ListView lvProduct;
    EditText edtSearch;
    private User user;
    private String token;
    private ServiceActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        list = new ArrayList<>();

        lvProduct = findViewById(R.id.lvProduct);
        edtSearch = findViewById(R.id.edtSearch);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("USERINFO");
        token = intent.getStringExtra("TOKEN");
        initPresenter();
        FilterProduct filterObj = new FilterProduct();
        filterObj.setName("");
        presenter.getListProduct(token,filterObj);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String search = s.toString();
                FilterProduct filterObj = new FilterProduct();
                filterObj.setName(search);
                searchProduct(filterObj);
            }
        });
    }


    private void initPresenter(){
        presenter = new ServiceActivityPresenter(this);
    }

    private void searchProduct(FilterProduct filterProduct){
        presenter = new ServiceActivityPresenter(this);
        presenter.getListProduct(token,filterProduct);


    }

    private void addToCart(CartObject cartObject){

//        cartObjects.add(cartObject);
        MenuActivity.productList.add(cartObject);

    }

    @Override
    public void getListProductSuccess(List<Product> products) {
        final ProductAdapter adapter = new ProductAdapter();
        adapter.setProductList(products);
        lvProduct.setAdapter(adapter);
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Product  product = (Product) adapter.getItem(position);

                if(!MenuActivity.productsToCompare.contains(product)){
                    CartObject cartObject = new CartObject();
                    cartObject.setId(((Product) adapter.getItem(position)).getId());
                    cartObject.setName(((Product) adapter.getItem(position)).getName());
                    cartObject.setPrice(((Product) adapter.getItem(position)).getPrice());
                    cartObject.setQuantity(((Product) adapter.getItem(position)).getQuantity());

                    cartObject.setQuantityOfCart(1);

                    addToCart(cartObject);
                    MenuActivity.productsToCompare.add(product);
                    Toast.makeText(ServiceActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                }else{
                    MenuActivity.productList.get(MenuActivity.productsToCompare.indexOf(product))
                            .setQuantityOfCart( MenuActivity.productList.get(MenuActivity.productsToCompare.indexOf(product)).getQuantityOfCart()+1);

                }

            }
        });
    }

    @Override
    public void getListProductFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void clickToCart(View view) {
        Intent intent = new Intent(getApplicationContext(),CartActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("PRODUCT", (Serializable) MenuActivity.productList);
        intent.putExtra("BUNDLE",bundle);
        startActivity(intent);
//        intent.putExtra("LISTPRO", list);
    }
}
