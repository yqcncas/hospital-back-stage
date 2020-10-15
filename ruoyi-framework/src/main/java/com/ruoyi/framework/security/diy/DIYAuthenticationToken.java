package com.ruoyi.framework.security.diy;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;
/**
 * 自定义token
 * @author gu
 */
public class DIYAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // ~ Instance fields
    // ================================================================================================

    private final Object principal;
    private Object credentials;
    private Integer type;

    // ~ Constructors
    // ===================================================================================================

    /**
     *
     */
    public DIYAuthenticationToken(Object principal, Object credentials,Integer type) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.type =type;
        setAuthenticated(false);
    }

    /**
     *
     * @param principal
     * @param credentials
     * @param authorities
     */
    public DIYAuthenticationToken(Object principal, Object credentials,Integer type,
                                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.type = type;
        // must use super, as we override
        super.setAuthenticated(true);
    }

    // ~ Methods
    // ========================================================================================================
    @Override
    public Object getCredentials() {
        return this.credentials;
    }
    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public Integer getType() {
        return type;
    }
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
