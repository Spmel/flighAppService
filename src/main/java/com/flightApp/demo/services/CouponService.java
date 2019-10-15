package com.flightApp.demo.services;

import com.flightApp.demo.exceptions.CouponNotFoundException;

public interface CouponService {
    boolean exists(String coupon);
    double applyDiscount(String coupon, double initialPrice) throws CouponNotFoundException;
}
