package com.virtualpairprogrammers;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

public class PaymentAmountTest {
	@Spy
	LoanApplication loanApplication;
	
	@Test
	public void test1YearLoanWholePounds(){
	loanApplication=spy(new LoanApplication());
	loanApplication.setPrincipal(1200);
	loanApplication.setTermInMonths(12);
	
	//TODO: set the interest rate to 10%
	doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();
	
	LoanCalculatorController controller=new LoanCalculatorController();
	controller.processNewLoanApplication(loanApplication);
	assertEquals(new BigDecimal(110),loanApplication.getRepayment());
	
	
	
	
	}
	
			

}
