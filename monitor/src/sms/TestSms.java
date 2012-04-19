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
		String subject = "Test sms";
		String content = "Ceci est un sms bis";
		String number = "819 581 9150";
		String operator = "fido";
		boolean res = sms.sendSms(number, operator,subject,content);
		assertTrue(res == true);
	}
}
