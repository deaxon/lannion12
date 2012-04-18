/**
 * 
 */
package sms;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * @author Charlotte
 * 
 */
public class TestSms {

	@Test
	public void testSendSms() {
		Sms sms = new Sms();
		String number = "819 581 9150";
		String operator = "fido";
		boolean res = sms.sendSms(number, operator);
		assertTrue(res == true);
	}
}
