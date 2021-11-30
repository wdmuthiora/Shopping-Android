package com.moringaschool.shopping.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moringaschool.shopping.Activity.Domain.FoodDomain;
import com.moringaschool.shopping.Activity.Helper.ManagementCart;
import com.moringaschool.shopping.R;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleText,priceText,numberOfOrdersText,descriptionText;
    private ImageView minusBtn,foodPic,plusBtn;

    private ManagementCart managementCart;

    private FoodDomain object;

    private int numberOfOrders=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        managementCart=new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object=(FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPic(), "drawable",this.getPackageName());

        Glide.with(this).load(drawableResourceId).into(foodPic);

        titleText.setText(object.getTitle());
        priceText.setText("$"+object.getFee());
        descriptionText.setText((object.getDescription()));
        numberOfOrdersText.setText(String.valueOf(numberOfOrders));

        //buttons
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfOrders = numberOfOrders + 1;
                numberOfOrdersText.setText(String.valueOf(numberOfOrders));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOfOrders > 1) {
                    numberOfOrders = numberOfOrders - 1;
                }
                numberOfOrdersText.setText(String.valueOf(numberOfOrders));
            }
        });
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOfOrders);
                managementCart.insertFood(object);
            }
        });

    }

    private void initView() {
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleText=findViewById(R.id.titleText);
        priceText=findViewById(R.id.priceText);
        numberOfOrdersText=findViewById(R.id.numberOfOrdersText);
        descriptionText=findViewById(R.id.descriptionText);
        minusBtn=findViewById(R.id.minusBtn);
        plusBtn=findViewById(R.id.plusBtn);
        foodPic=findViewById(R.id.foodPic);
    }
}