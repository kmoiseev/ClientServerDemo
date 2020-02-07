package com.kmoiseev.demo.springserver.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
public class SecurityRoleProperties {
  @Value("${spring.security.user.roles}")
  private final String[] securityRoles;
}
