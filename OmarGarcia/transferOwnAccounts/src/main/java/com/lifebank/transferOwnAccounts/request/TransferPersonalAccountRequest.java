package com.lifebank.transferOwnAccounts.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "idAccountOrigin",
    "idAccountDestination",
    "transferAmount"
})
public class TransferPersonalAccountRequest implements Serializable {

	@JsonProperty("idAccountOrigin")
	private String idAccountOrigin;
	
	@JsonProperty("idAccountDestination")
	private String idAccountDestination;
	
	@JsonProperty("transferAmount")
	private double transferAmount;
	
	public String getIdAccountOrigin() {
		return idAccountOrigin;
	}
	public void setIdAccountOrigin(String idAccountOrigin) {
		this.idAccountOrigin = idAccountOrigin;
	}
	public String getIdaccountDestination() {
		return idAccountDestination;
	}
	public void setIdaccountDestination(String idaccountDestination) {
		this.idAccountDestination = idaccountDestination;
	}
	public double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	
	
}
