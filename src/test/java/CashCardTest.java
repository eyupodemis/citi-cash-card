

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.citi.cashcard.repository.CashCard;

public class CashCardTest {

	CashCard cashCard = new CashCard();

	@Test
	public void withdrawInsufficientBalanceTest() {
		assertEquals(false, cashCard.withdraw(new BigDecimal("76.00")));
	}

	@Test
	public void withdrawSufficientBalanceTest() {
		cashCard.deposit(new BigDecimal("180.00"));
		assertEquals(true, cashCard.withdraw(new BigDecimal("76.00")));
	}

	@Test
	public void withdrawTest() {
		cashCard.deposit(new BigDecimal("180.00"));
		cashCard.withdraw(new BigDecimal("76.00"));
		assertEquals(new BigDecimal("104.00"), cashCard.getBalance());
	}

	@Test
	public void depositUnexpectedAmountTest() {
		assertEquals(false, cashCard.deposit(new BigDecimal("-13.25")));
	}

	@Test
	public void depositTest() {
		cashCard.deposit(new BigDecimal("112.00"));
		assertEquals(new BigDecimal("112.00"), cashCard.getBalance());
	}
}
