package com.ibay.backend.service;

import com.ibay.backend.model.AuthUser;
import com.ibay.backend.security.ApplicationUserRole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class CommonFunctions {

    public static AuthUser getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return (AuthUser)principal;
        }
        return null;
    }

    public static String getUserAuthoritiesString(List<ApplicationUserRole> roles) {
        StringBuilder sb = new StringBuilder();
        for (ApplicationUserRole role : roles) {
            role.getGrantedAuthorities().forEach(authority -> sb.append(authority.getAuthority()));
        }
        return sb.toString();
    }




}
