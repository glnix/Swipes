package com.example.alekseygoryachev.swipes;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.daimajia.swipe.SwipeLayout;
import com.example.alekseygoryachev.swipes.dao.CartDAO;
import com.example.alekseygoryachev.swipes.dao.ItemDAO;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class CartsAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    List<ItemDAO> items;
    Context ctx;
    LayoutInflater inflater;
    Map<Integer, View> integerViewHashMap = new HashMap<>();
    private final static String LOGTAG = "CartsAdapter";

    public CartsAdapter(Context ctx) {
        updateItems();
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);

    }

    //get items from DB
    private void updateItems() {
        this.items = new Select().from(ItemDAO.class).orderBy("Cart").execute();
    }


    @Override
    public long getHeaderId(int i) {
        return items.get(i).getCart().getId();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ItemDAO getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ItemDAO item = items.get(position);
        Log.d(LOGTAG, "Set view for item: " + item.getId());
        TextView name;
        TextView count;
        TextView price;
        TextView priceAmount;
        SwipeLayout swipeLayout;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cart_item, parent, false);

        }
        name = (TextView) convertView.findViewById(R.id.name);
        count = (TextView) convertView.findViewById(R.id.count);
        price = (TextView) convertView.findViewById(R.id.price);
        priceAmount = (TextView) convertView.findViewById(R.id.price_amount);
        swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipeLayout);
        swipeLayout.setDragEdges(SwipeLayout.DragEdge.Left, SwipeLayout.DragEdge.Right);
        swipeLayout.setBottomViewIds(R.id.bottom_wrapper, R.id.bottom_wrapper2, -1, -1);
        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout swipeLayout) {

            }

            @Override
            public void onOpen(SwipeLayout swipeLayout) {
                Log.d(LOGTAG, "onOpen position "+position);
                items.get(position).delete();
                notifyDataSetChanged();
            }

            @Override
            public void onStartClose(SwipeLayout swipeLayout) {

            }

            @Override
            public void onClose(SwipeLayout swipeLayout) {

            }

            @Override
            public void onUpdate(SwipeLayout swipeLayout, int i, int i2) {

            }

            @Override
            public void onHandRelease(SwipeLayout swipeLayout, float v, float v2) {

            }
        });
        name.setText(item.getName());
        count.setText(String.valueOf(item.getCount()) + "piece");
        price.setText(new DecimalFormat("##.##").format(item.getPrice()));
        priceAmount.setText(new DecimalFormat("##.##").format(item.getPriceAmount()));
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        ItemDAO item = items.get(position);
        Log.d(LOGTAG, "Set view for header: " + item.getCart().getId());
        TextView header;
        TextView priceAmountTextView;
        TextView cartSize;
        double priceAmount;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cart_header, parent, false);
        }
        header = (TextView) convertView.findViewById(R.id.header);
        priceAmountTextView = (TextView) convertView.findViewById(R.id.amountPrice);
        cartSize = (TextView) convertView.findViewById(R.id.cart_size);
        integerViewHashMap.put(position, convertView);
        CartDAO cart = item.getCart();
        header.setText(cart.getName());
        cartSize.setText(cart.getCartSize() + " goods in cart");
        priceAmount = item.getCart().getPriceAmount();
        priceAmountTextView.setText(new DecimalFormat("##.##").format(priceAmount));
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        updateItems();
    }

    class HeaderViewHolder {
    }

    class ViewHolder {
    }
}
