package com.aurionpro.connect.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.connect.dto.FlightBookingAcknowledgement;
import com.aurionpro.connect.dto.FlightBookingRequest;
import com.aurionpro.connect.entity.PassengerInfo;
import com.aurionpro.connect.entity.PaymentInfo;
import com.aurionpro.connect.repository.PassengerInfoRepository;
import com.aurionpro.connect.repository.PaymentInfoRepository;
import com.aurionpro.connect.utils.PaymentUtils;

import jakarta.transaction.Transactional;

@Service
public class FlightBookingService {

	@Autowired
	private PassengerInfoRepository passengerInfoRepository;
	@Autowired
	private PaymentInfoRepository paymentInfoRepository;

	@Transactional // (readOnly = false,isolation = Isolation.READ_COMMITTED,propagation =
					// Propagation.REQUIRED)

	public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request) {

		PassengerInfo passengerInfo = request.getPassengerInfo();
		passengerInfo = passengerInfoRepository.save(passengerInfo);

		PaymentInfo paymentInfo = request.getPaymentInfo();

		PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());

		paymentInfo.setPassengerId(passengerInfo.getPassengerId());
		paymentInfo.setAmount(passengerInfo.getFare());
		paymentInfoRepository.save(paymentInfo);
		return new FlightBookingAcknowledgement("SUCCESS", passengerInfo.getFare(),
				UUID.randomUUID().toString().split("-")[0], passengerInfo);
	}
}