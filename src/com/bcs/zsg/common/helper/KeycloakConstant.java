package com.bcs.zsg.common.helper;

public class KeycloakConstant {

	// Auth Body Grant Type
	public enum GrantType {

        CLIENT_CREDENTIALS("client_credentials"),
        PASSWORD("password"),
        AUTH_CODE("authorization_code");

        private final String value;
		GrantType(String value) {this.value = value;}
		public String getValue() {return value;}
    }
	
	// Execute Actions Email Type
	public enum ExecuteActionsEmailType {

		UPDATE_PASSWORD("UPDATE_PASSWORD"),
		VERIFY_EMAIL("VERIFY_EMAIL");

        private final String value;
        ExecuteActionsEmailType(String value) {this.value = value;}
		public String getValue() {return value;}
    }
}
