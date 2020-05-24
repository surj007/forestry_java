package com.forestry.config.Security.MultiParam;
 
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
 
import java.util.Collection;
import java.util.Map;
 
public class MyAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1L;
    private final Object principal;
    private Object credentials;
    // 除username、password以外的自定义参数都放在map中
    private Map<String, Object> map;
 
    public MyAuthenticationToken(Object principal, Object credentials) {
        super((Collection)null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }
 
    public MyAuthenticationToken(
        Object principal, 
        Object credentials, 
        Collection<? extends GrantedAuthority> authorities, 
        Map<String, Object> map
    ) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
        this.map = map;
    }
 
    public Object getCredentials() {
        return this.credentials;
    }
 
    public Object getPrincipal() {
        return this.principal;
    }
 
    public Map<String, Object> getParam() {
        return map;
    }
 
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } 
        else {
            super.setAuthenticated(false);
        }
    }
 
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}