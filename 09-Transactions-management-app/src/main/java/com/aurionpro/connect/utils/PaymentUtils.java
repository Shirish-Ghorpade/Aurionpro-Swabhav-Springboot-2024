package com.aurionpro.connect.utils;

import java.util.HashMap;
import java.util.Map;

import com.aurionpro.connect.exception.InsufficientAmountException;

public class PaymentUtils {

	private static Map<String, Double> paymentMap = new HashMap<>();
	
	static{
		paymentMap.put("acc1", 12000.0);
		paymentMap.put("acc2", 110000.0);
		paymentMap.put("acc3", 156660.0);
		paymentMap.put("acc4", 1200.0);
		paymentMap.put("acc5", 10000.0);
	}
	
	public static boolean validateCreditLimit(String accNo, double paidAmount) {
		if(paidAmount>paymentMap.get(accNo)) {
			throw new InsufficientAmountException("Insufficient Fund......");
		}
		return true;
	}
}
