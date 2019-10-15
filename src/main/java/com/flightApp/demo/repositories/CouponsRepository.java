package com.flightApp.demo.repositories;

import com.flightApp.demo.entities.Coupon;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class CouponsRepository {

    private List<Coupon> coupons = sampleCoupons();

    public Optional<Coupon> findCouponById(String id){
        System.out.println(id);
        return coupons.stream().filter(cp -> cp.getId().equals(id)).findFirst();
    }

    private static List<Coupon> sampleCoupons() {
        Coupon c = new Coupon("UIC12", 0.6);
        Coupon c1 = new Coupon("UI12", 0.5);
        Coupon c2 = new Coupon("UIC1", 0.2);
        return Arrays.asList(c, c1, c2);
    }
}
