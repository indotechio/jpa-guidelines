package com.guidelines.demo.util.interceptor;

import lombok.Data;
import java.util.Arrays;

@Data
public class HeaderHolder {
    private String nip;
    private String name;
    private String givenName;
    private String username;
    private String remoteAddress;
    private String kodeKantor;
    private String[] roles;

    public boolean havingOneOfRoles(String[] neededRoles) {
        for (String role: neededRoles) {
            if ( Arrays.asList(this.roles).contains(role) ) {
                return true;
            }
        }
        return false;
    }
}
