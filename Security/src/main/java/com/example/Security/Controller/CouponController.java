package com.example.Security.Controller;

import com.example.Security.Enities.Coupon;
import com.example.Security.repo.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CouponController {
    @Autowired
    CouponRepo repo;


    @GetMapping("/showCoupon")
    public String showCreateCoupon(){
        return "Copon";
    }

    @PostMapping("/SaveCoupon")
    public String save(Coupon coupon) {
        repo.save(coupon);
        return "createresponse";
    }

    @GetMapping("/GetCoupon")
    public String showGetCoupon() {
        return "getCopon";
    }

    @PostMapping("/getCopon")
    public ModelAndView getCoupon(String code) {
        ModelAndView modelAndView = new ModelAndView("DETAILS");
        modelAndView.addObject(repo.findByCode(code));
        return modelAndView;
    }


}

