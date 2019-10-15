package com.flightApp.demo.services;

import com.flightApp.demo.cache.SimpleCache;
import com.flightApp.demo.entities.Coupon;
import com.flightApp.demo.exceptions.CouponNotFoundException;
import com.flightApp.demo.repositories.CouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultCouponServiceImplt implements CouponService {

    @Autowired
    private CouponsRepository couponsRepository;

    @Override
    public boolean exists(String couponId) {
        return couponsRepository.findCouponById(couponId).isPresent();
    }

    @Override
    public double applyDiscount(String couponId, double initialPrice) {
        Optional<Coupon> optionalCoupons = couponsRepository.findCouponById(couponId);
        if (!optionalCoupons.isPresent()) {
            throw new CouponNotFoundException();
        }
        return initialPrice - optionalCoupons.get().getDiscountPercentage() * initialPrice;
    }
}
