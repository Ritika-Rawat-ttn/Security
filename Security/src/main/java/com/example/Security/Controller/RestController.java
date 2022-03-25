package com.example.Security.Controller;

import com.example.Security.Enities.Coupon;
import com.example.Security.repo.CouponRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/couponapi")
public class RestController {
    @Autowired
    CouponRepo repository;

    @GetMapping("/hello")
    public String hello(){
        return "Spring Security";
    }


    @PostMapping(value = "/coupons")
    public Coupon create(@RequestBody Coupon coupon){


        return repository.save(coupon);
    }

    @GetMapping(value ="/coupon/{code}")
    public Coupon getCoupon(@PathVariable("code") String code){

        return repository.findByCode(code);

    }




}

