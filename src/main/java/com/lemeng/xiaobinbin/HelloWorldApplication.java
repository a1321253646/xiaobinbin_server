package com.lemeng.xiaobinbin;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.lemeng.xiaobinbin.TemplateHealthCheck;
import com.lemeng.xiaobinbin.configuration.HelloWorldConfiguration;
import com.lemeng.xiaobinbin.resource.BuyResource;
import com.lemeng.xiaobinbin.resource.GetBuilderInfoResource;
import com.lemeng.xiaobinbin.resource.GetUserLocalResource;
import com.lemeng.xiaobinbin.resource.ReplaceIdResource;
import com.lemeng.xiaobinbin.resource.SaleResource;
import com.lemeng.xiaobinbin.resource.WechatActionResource;

import io.dropwizard.Application;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


/**
 * Created by Ryan Miao on 3/14/2017.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }


    
    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		 try {
	            // The newInstance() call is a work around for some
	            // broken Java implementations

	            //Class.forName("com.mysql.cj.jdbc.Driver");com.mysql.jdbc.
	            Class.forName("com.mysql.jdbc.Driver");
	            System.out.println("开始");
	        } catch (Exception ex) {
	        	System.out.println("拉起服务失败");
	            // handle the error
	        }
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {

    	FilterRegistration.Dynamic filter = environment.servlets().addFilter("CrossOriginFilter", CrossOriginFilter.class);
    	filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    	filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
    	filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
    	filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
    	filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
    	filter.setInitParameter("allowCredentials", "true");
   	
        final GetUserLocalResource getlocal = new GetUserLocalResource(configuration);
        final GetBuilderInfoResource changeBuilder = new GetBuilderInfoResource(configuration);
        final SaleResource zichangBuilder = new SaleResource(configuration);
        final WechatActionResource wx = new WechatActionResource(configuration);
        final ReplaceIdResource rep = new ReplaceIdResource(configuration);
        final BuyResource buy = new BuyResource(configuration);
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(getlocal);
        environment.jersey().register(changeBuilder);
        environment.jersey().register(zichangBuilder);
        environment.jersey().register(wx);
        environment.jersey().register(rep);
        environment.jersey().register(buy);
        
        
       // environment.jersey().register(healthCheck);


    }
}
