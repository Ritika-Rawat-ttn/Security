package com.example.Security.repo;

import com.example.Security.Enities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {


    Coupon findByCode(String code);
}
