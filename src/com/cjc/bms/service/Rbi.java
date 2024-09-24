package com.cjc.bms.service;

public interface Rbi {
	public void createAccountantTbl() throws Exception;
    public void createAccount() throws Exception;
    public void displayAllDetails() throws Exception;
    public void depositeMoney() throws Exception;
    public void withdrawal() throws Exception;
    public void balanceCheck() throws Exception;
}
