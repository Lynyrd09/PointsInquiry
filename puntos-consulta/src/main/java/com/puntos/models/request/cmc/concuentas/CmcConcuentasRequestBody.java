package com.puntos.models.request.cmc.concuentas;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.puntos.models.request.ork.consulta.OrkConsultaRequestBody;
import com.puntos.models.request.ork.movimientos.OrkMovimientosRequestBody;

public class CmcConcuentasRequestBody {

	private List<String> cardNumber;
	private String accountNumber;

	public CmcConcuentasRequestBody() {

	}

	public CmcConcuentasRequestBody(Object body) {

		if (body instanceof OrkConsultaRequestBody con) {

			List<String> card = con.getCardNumber();
			List<String> account = con.getAccountNumber();

			for (int i = 0; i < card.size(); i++)
				card.set(i, StringUtils.leftPad(String.format(card.get(i)), 19, "0"));

			if (CollectionUtils.isEmpty(account))
				account.add("");

			this.cardNumber = card;
			this.accountNumber = StringUtils.leftPad(String.format(account.get(0)), 19, "0");

		}

		if (body instanceof OrkMovimientosRequestBody mov) {
			
			List<String> terms = new ArrayList<>();
			if (!"".equals(mov.getCardNumber())) {
				terms.add(StringUtils.leftPad(String.format(mov.getCardNumber()), 19, "0"));
			}
			
			String terms2 = mov.getAccountNumber();

			this.cardNumber = terms;
			this.accountNumber = StringUtils.leftPad(String.format(terms2), 19, "0");

		}

	}

	public List<String> getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(List<String> cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
