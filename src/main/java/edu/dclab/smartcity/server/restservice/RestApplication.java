package edu.dclab.smartcity.server.restservice;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestApplication extends Application {
	
	private Set<Object> svc_singletons = new HashSet<Object>();	
	private Set<Class<?>> svc_classes  = new HashSet<Class<?>>();

	public RestApplication() {
		System.out.println("application init");
		svc_singletons.add(ServiceService.getInstance());
	}
	
	@Override
	public Set<Object> getSingletons() {
		return svc_singletons;
	}
	 
	@Override
	public Set<Class<?>> getClasses() {
		return svc_classes;
	}


	
}
