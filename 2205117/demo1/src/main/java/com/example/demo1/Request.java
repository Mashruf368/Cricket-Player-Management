package com.example.demo1;

import java.io.Serializable;

public class Request implements Serializable {
    private String command; // e.g., "TRANSFER", "BUY"
    private Object data;    // Associated data (e.g., a Player object)
    private double offerPrice;
    private String username;

    public Request(String command, Object data, double offerPrice) {
        this.command = command;
        this.data = data;
        this.offerPrice = offerPrice;
    }

    public String getCommand() {
        return command;
    }

    public Object getData() {
        return data;
    }
    public double getOfferPrice() {
        return offerPrice;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", data=" + data +
                ", offerPrice=" + offerPrice +
                '}';
    }
}

