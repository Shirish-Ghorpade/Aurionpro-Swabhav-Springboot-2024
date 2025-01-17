package com.aurionpro.model.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/bankapp")
public class CaptchaController {

        private final DefaultKaptcha defaultKaptcha;

        public CaptchaController(DefaultKaptcha defaultKaptcha) {
            this.defaultKaptcha = defaultKaptcha;
        }

    @GetMapping("/captcha")
        public void getCaptcha(HttpServletResponse response,HttpServletRequest request) throws IOException {

            String text = defaultKaptcha.createText();
            BufferedImage image = defaultKaptcha.createImage(text);

             request.getSession().setAttribute("captcha", text);

            response.setContentType("image/jpeg");
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream.close();
        }


//    @PostMapping("/captcha")
//    public String verifyCaptcha(HttpServletRequest request, @RequestParam("captcha") String captcha) {
//        String captchaSession = (String) request.getSession().getAttribute("captcha");
//        if (captcha.equals(captchaSession)) {
//            return "captcha-success";
//        } else {
//            return "captcha-failure";
//        }
//    }

}