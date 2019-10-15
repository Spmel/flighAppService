package com.flightApp.demo.rest;

import com.flightApp.demo.cache.SimpleCache;
import com.flightApp.demo.services.BaggageService;
import com.flightApp.demo.services.CouponService;
import com.flightApp.demo.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Apis {

    @Autowired
    private BaggageService baggageService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private TicketService ticketService;

    /**
     * expires in : 10 seconds
     * max size: 100
     */
    private SimpleCache<String, Object> cache = new SimpleCache<>(10_000, 100);

    @GetMapping("/tickets")
    public ResponseEntity<Object> getTikcet(@RequestParam("ticketId") Long ticketId) {
        Map<String, Boolean> response = new HashMap();
        System.out.println(ticketId);
        String key = ticketId.toString();
        Boolean cached = (Boolean) cache.get(key);
        if (cached != null) {
            response.put("exists", cached);
        } else {
            boolean exists = ticketService.exists(ticketId);
            cache.put(key, exists);
            response.put("exists", exists);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/baggages")
    public ResponseEntity<Object> checkBaggages(@RequestParam("baggageId") Long baggageId, @RequestParam("destinationId") Long destinationId) {
        Map<String, Boolean> response = new HashMap();
        String key = baggageId.toString() + destinationId.toString();
        Boolean cached = (Boolean) cache.get(key);

        if (cached != null) {
            response.put("checkedIn", cached);
        } else {
            boolean checkedIn = baggageService.checkinStatus(baggageId, destinationId);
            cache.put(key, checkedIn);
            response.put("checkedIn", checkedIn);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/coupons")
    public ResponseEntity<Object> applyCoupon(@RequestParam("couponId") String couponId, @RequestParam("price") Long initialPrice) {
        Map<String, Object> response = new HashMap();
        String key = couponId + initialPrice.toString();
        Double cached = (Double) cache.get(key);

        if (!couponService.exists(couponId)) {
            response.put("message", "coupon not valid");
            response.put("finalPrice", initialPrice);
            return ResponseEntity.ok(response);
        }

        if (cached != null) {
            response.put("finalPrice", cached);
        } else {
            double price = couponService.applyDiscount(couponId, initialPrice);
            cache.put(key, price);
            response.put("finalPrice", price);
        }
        return ResponseEntity.ok(response);
    }
}
