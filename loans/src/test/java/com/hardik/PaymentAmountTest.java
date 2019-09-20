package com.hardik;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import com.hardik.LoanApplication;
import com.hardik.LoanCalculatorController;
import com.hardik.LoanRepository;

import static org.mockito.Mockito.*;

public class PaymentAmountTest {
	@Spy
	LoanApplication loanApplication;
	LoanCalculatorController controller;
	@Before
	public void setup(){
		
		controller=new LoanCalculatorController();
	
		LoanRepository repository = mock(LoanRepository.class);
		JavaMailSender mailSender=mock(JavaMailSender.class);
		RestTemplate restTemplate=mock(RestTemplate.class);
		
		controller.setData(repository);
		controller.setMailSender(mailSender);
		controller.setRestTemplate(restTemplate);
	}
	
	
	@Test
	public void test1YearLoanWholePounds(){
	loanApplication=spy(new LoanApplication());
	loanApplication.setPrincipal(1200);
	loanApplication.setTermInMonths(12);
	
	//TODO: set the interest rate to 10%
	doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();
	
	
	
	controller.processNewLoanApplication(loanApplication);
	assertEquals(new BigDecimal(110),loanApplication.getRepayment());
	
	}
	
	
	@Test
	public void test2YearLoanWholePounds(){
	loanApplication=spy(new LoanApplication());
	loanApplication.setPrincipal(1200);
	loanApplication.setTermInMonths(24);
	
	//TODO: set the interest rate to 10%
	doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();
	controller.processNewLoanApplication(loanApplication);
	assertEquals(new BigDecimal(60),loanApplication.getRepayment());
	
	}
	
	@Test
	public void test5YearLoanWholePounds(){
	loanApplication=spy(new LoanApplication());
	loanApplication.setPrincipal(5000);
	loanApplication.setTermInMonths(60);
	
	//TODO: set the interest rate to 10%
	doReturn(new BigDecimal(6.5)).when(loanApplication).getInterestRate();
	controller.processNewLoanApplication(loanApplication);
	assertEquals(new BigDecimal(111),loanApplication.getRepayment());
	
	}
	
			

}
