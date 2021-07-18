package com.example.novi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.novi.adapter.CategoryAdapter;
import com.example.novi.adapter.DiscountedProductAdapter;
import com.example.novi.adapter.RecentlyViewedAdapter;
import com.example.novi.model.Category;
import com.example.novi.model.DiscountedProducts;
import com.example.novi.model.RecentlyViewed;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.example.novi.R.drawable.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView discountRecyclerView, categoryRecyclerView, recentlyViewedRecycler;
    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;

    TextView allCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        discountRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        allCategory = findViewById(R.id.allCategoryImage);
        recentlyViewedRecycler = findViewById(R.id.recently_item);


        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AllCategory.class);
                startActivity(i);
            }
        });

        // adding data to model
        discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new DiscountedProducts(1, itachi));
        discountedProductsList.add(new DiscountedProducts(2, kimetsu));
        discountedProductsList.add(new DiscountedProducts(3, kyoukai));
        discountedProductsList.add(new DiscountedProducts(4, rezero));
        discountedProductsList.add(new DiscountedProducts(5, violet));
        discountedProductsList.add(new DiscountedProducts(6, boku));

        // adding data to model
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, kimetsu));
        categoryList.add(new Category(2, kyoukai));
        categoryList.add(new Category(3, rezero));
        categoryList.add(new Category(4, itachi));
        categoryList.add(new Category(5, violet));
        categoryList.add(new Category(6, boku));
        categoryList.add(new Category(7, kimetsu));
        categoryList.add(new Category(8, itachi));

        // adding data to model
        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Itachi Shinden", "Bagian dimana Itachi harus membantai seluruh anggota Klannya, Uchiha. Dan Itachi lebih melulai dari Uchiha Izumi. Gadis yang mencintainya", "Genre", "Drama", "Romance", itachi, itachi));
        recentlyViewedList.add(new RecentlyViewed("Violet Evergarden", "Dikisahkan Violet Evergarden adalah seorang perempuan yang pernah bergabung dengan militer. Pada masa perang, ia dikenal sebagai prajurit berdarah dingin.", "Genre", "Slice Of Life", "Romance", violet, violet));
        recentlyViewedList.add(new RecentlyViewed("Kyoukai No Kanata", "Mengisahkan pemuda yang dalam dirinya terdapat monster", "Genre", "Action", "Romance", kyoukai, kyoukai));
        recentlyViewedList.add(new RecentlyViewed("Oregairu", "Menceritakan permasalahan yang ada pada sekolah Hachiman", "Genre", "Romance", "Slice Of Life", rezero, rezero));

        setDiscountedRecycler(discountedProductsList);
        setCategoryRecycler(categoryList);
        setRecentlyViewedRecycler(recentlyViewedList);

    }

    private void setDiscountedRecycler(List<DiscountedProducts> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discountRecyclerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscountedProductAdapter(this,dataList);
        discountRecyclerView.setAdapter(discountedProductAdapter);
    }


    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this,recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }
    //Now again we need to create a adapter and model class for recently viewed items.
    // lets do it fast.
}
