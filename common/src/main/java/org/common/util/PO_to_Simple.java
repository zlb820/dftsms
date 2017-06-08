package org.common.util;

import org.common.RBAC.domain.Account;
import org.common.RBAC.domain.simple.AccountSimple;

public class PO_to_Simple {
	public static AccountSimple AccountToSimple(Account account){
		AccountSimple simple=new AccountSimple();
		simple.setId(account.getId());
		simple.setName(account.getName());
		simple.setPhone(account.getPhone());
		simple.setSex(account.getSex());
		if(null!=account.getEmail()){
			simple.setEmail(account.getEmail());
		}
		simple.setRegistDate(account.getRegistTime());
		simple.setAccountStatu(account.getStatus());
		if(null!=account.getPicture()){
			simple.setPicURL(ReadPropertyOf.loadPropertiesOfSys().getServerAddress()+account.getPicture().getPic_url());
		}
		if(null!=account.getRoles()){
			simple.setRoleID(account.getRoles().getId());
		}
		simple.seteMailStatu(account.getEm_status());
		return simple;
	}
}
