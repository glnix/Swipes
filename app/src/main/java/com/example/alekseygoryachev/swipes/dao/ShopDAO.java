package com.example.alekseygoryachev.swipes.dao;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "Shops")
public class ShopDAO extends Model {

    @Column(name = "ServerId")
    private long serverId;
    @Column(name = "Name")
    private String name;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Column(name = "Address")
    private String addr;
    @Column(name = "Lat")
    private double lat;
    @Column(name = "Lng")
    private double lng;

    public ShopDAO() {
        super();
    }

    public ShopDAO(long serverId, String name, String addr, double lat, double lng) {
        this.serverId = serverId;
        this.name = name;
        this.addr = addr;
        this.lat = lat;
        this.lng = lng;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public static ShopDAO getShopById(long serverId) {
        return new Select().from(ShopDAO.class).where("ServerId = ?", serverId).executeSingle();
    }
}
