package com.example.alekseygoryachev.swipes;

import android.app.Activity;
import android.content.ClipData;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.alekseygoryachev.swipes.dao.CartDAO;
import com.example.alekseygoryachev.swipes.dao.ItemDAO;
import com.example.alekseygoryachev.swipes.dao.ShopDAO;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);
        //fill DB
        fillDb();
        //create adapter
        CartsAdapter adapter = new CartsAdapter(this);
        ((StickyListHeadersListView)findViewById(R.id.list)).setAdapter(adapter);
    }

    //fill DB
    private void fillDb(){
        //create 2 carts
        CartDAO cart1 = new CartDAO("Cart 1");
        cart1.save();
        CartDAO cart2 = new CartDAO("Cart 2");
        cart2.save();

        //create 2 shops
        ShopDAO shop1 = new ShopDAO(1, "Shop 1", "Addr 1", 0.0, 0.0);
        ShopDAO shop2 = new ShopDAO(2, "Shop 2", "Addr 2", 1.0, 1.0);

        //create some items
        ItemDAO item1 = new ItemDAO(cart1, "Item 1", 111, 5, 1, 50.0, shop1);
        item1.save();
        ItemDAO item2 = new ItemDAO(cart1, "Item 1", 222, 2, 2, 2.0, shop2);
        item2.save();
        ItemDAO item3 = new ItemDAO(cart1, "Item 1", 333, 1, 1, 50.0, shop1);
        item3.save();
        ItemDAO item4 = new ItemDAO(cart2, "Item 1", 444, 7, 2, 5.0, shop2);
        item4.save();
        ItemDAO item5 = new ItemDAO(cart2, "Item 1", 555, 3, 1, 40.0, shop1);
        item5.save();
        ItemDAO item6 = new ItemDAO(cart2, "Item 1", 666, 4, 2, 30.0, shop2);
        item6.save();

    }
}
