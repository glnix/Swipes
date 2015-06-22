package com.example.alekseygoryachev.swipes.dao;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Carts")
public class CartDAO extends Model {

    @Column(name = "Name")
    private String name;

    public CartDAO(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CartDAO() {
        super();
    }

    public List<ItemDAO> getItems() {
        return getMany(ItemDAO.class, "Cart");
    }


    @Override
    public String toString() {
        return name;
    }


    public double getPriceAmount() {
        double priceAmountResult = 0;
        List<ItemDAO> items = this.getItems();
        for (ItemDAO item : items) {
            priceAmountResult = priceAmountResult + item.getPriceAmount();
        }
        return priceAmountResult;
    }

    public int getCartSize() {
        return this.getItems().size();
    }

    public ItemDAO getItemByServerId(int serverId) {
        List<ItemDAO> itemDAOList = getItems();
        for (ItemDAO item : itemDAOList) {
            if (item.getServerId() == serverId) return item;
        }

        return null;
    }
}
