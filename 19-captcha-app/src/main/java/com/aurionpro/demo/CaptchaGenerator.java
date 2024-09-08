package com.aurionpro.demo;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;

public class CaptchaGenerator {
//	Takes width and height to create captcha of that size
	public static Captcha generateCaptcha(Integer width, Integer height) {

//		We set the width and height and setting the background color
		return new Captcha.Builder(width, height).addBackground(new GradiatedBackgroundProducer())
				.addText(new DefaultTextProducer(), new DefaultWordRenderer()).addNoise(new CurvedLineNoiseProducer())
				.build();
	}

//	We take input as captcha
	public static String encodeCaptchatoBinary(Captcha captcha) {
		String image = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			pass the image of captcha
			ImageIO.write(captcha.getImage(), "jpg", bos);
			byte[] byteArray = Base64.getEncoder().encode(bos.toByteArray());
			image = new String(byteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Here we return the captcha image
		return image;
	}

}