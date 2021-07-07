package com.wustzdy.springboot.validateCode.demo.bean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class KaptchaController {
    private HttpServletRequest request;
    private HttpServletResponse response;

    // 生成验证码图片
    @RequestMapping("/getCaptchaImage")
    @ResponseBody
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        try {

            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");

            ValidateCode validateCode = new ValidateCode();

            // 直接返回图片
            validateCode.getRandomCodeImage(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 生成验证码,返回的是 base64
    @RequestMapping("/getCaptchaBase64")
    @ResponseBody
    public String getCaptchaBase64(HttpServletRequest request, HttpServletResponse response) {

        Map result = new HashMap();
        try {

            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");

            ValidateCode validateCode = new ValidateCode();

            // 直接返回图片
            // validateCode.getRandomCode(request, response);

            // 返回base64
            String base64String = validateCode.getRandomCodeBase64(request, response);
            result.put("url", "data:image/png;base64," + base64String);
            result.put("message", "created successfull");
            System.out.println("test=" + result.get("url"));

        } catch (Exception e) {
            System.out.println(e);
        }

        return (String) result.get("url");
    }

    /**
     * 校对验证码
     */
    @RequestMapping("/Validate")
    public ModelAndView imgvrifyControllerDefaultKaptcha(HttpServletRequest request, String tryCode) {
        ModelAndView model = new ModelAndView();
        String rightCode = (String) request.getSession().getAttribute("rightCode");
        System.out.println("rightCode:" + rightCode + " ———— tryCode:" + tryCode);
        if (!rightCode.equals(tryCode)) {
            model.addObject("info", "验证码错误,请再输一次!");
            model.setViewName("login");
        } else {
            model.addObject("info", "登陆成功");
            model.setViewName("index");
        }
        return model;
    }

    /**
     * 返回首页
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("login");
    }

    @RequestMapping("/ValidateCode")
    @ResponseBody
    public Map<String, Object> ValidateCode(HttpServletRequest request, String code) {
        String validateCode = request.getSession().getAttribute("rightCode").toString();
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (validateCode == null) {
            map.put("status", null);//验证码过期
        } else if (validateCode.equals(code)) {
            map.put("status", true);//验证码正确
        } else if (!validateCode.equals(validateCode)) {
            map.put("status", false);//验证码不正确
        }
        map.put("code", 200);
        return map;
    }
}
