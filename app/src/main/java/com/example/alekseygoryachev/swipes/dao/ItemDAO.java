package com.example.alekseygoryachev.swipes.dao;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "Items")
public class ItemDAO extends Model {

    @Column(name = "Cart")
    private CartDAO cart;

    @Column(name = "Name")
    private String name;

    @Column(name = "ServerId")
    private int serverId;

    @Column(name = "Count")
    private long count;

    @Column(name = "ShopId")
    private long shopId;

    @Column(name = "Price")
    private double price;

    @Column(name = "Shop")
    private ShopDAO shopDAO;

    public ShopDAO getShopDAO() {
        return new Select().from(ShopDAO.class).where("Id = ?", this.shopDAO.getId()).executeSingle();
    }

    public void setShopDAO(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    public ItemDAO(CartDAO cart, String name, int serverId, long count, long shopId, double price, ShopDAO shopDAO) {
        this.cart = cart;
        this.name = name;
        this.serverId = serverId;
        this.count = count;
        this.shopId = shopId;
        this.price = price;
        this.shopDAO = shopDAO;
    }


    public ItemDAO() {
        super();
    }

    public CartDAO getCart() {
        return new Select().from(CartDAO.class).where("Id = ?", this.cart.getId()).executeSingle();
    }

    public void setCart(CartDAO cart) {
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //���������� * ���������
    public double getPriceAmount() {
        return getPrice() * getCount();
    }

    @Override
    public String toString() {
        return "ItemDAO{" +
                "cart=" + cart +
                ", name='" + name + '\'' +
                ", serverId=" + serverId +
                ", count=" + count +
                ", shopId=" + shopId +
                '}';
    }

}
