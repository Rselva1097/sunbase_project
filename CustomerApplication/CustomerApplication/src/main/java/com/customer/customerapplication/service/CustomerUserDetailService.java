package com.customer.customerapplication.service;

import com.customer.customerapplication.model.Customer;
import com.customer.customerapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomerUserDetailService implements UserDetailsService {

	private CustomerRepository customerrepo;

	
	public CustomerUserDetailService(CustomerRepository customerrepo) {	
		this.customerrepo = customerrepo;
	}

	 @Override
	    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
	          Customer customer = customerrepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
	                 .orElseThrow(() ->
	                         new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));

	        Set<GrantedAuthority> authorities = customer
	                .getRole()
	                .stream()
	                .map((role) -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toSet());

	        return new org.springframework.security.core.userdetails.User(customer.getEmail(),
					customer.getPassword(),
	                authorities);
	    }

}
