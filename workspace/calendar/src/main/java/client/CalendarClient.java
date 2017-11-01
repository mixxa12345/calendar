/**
 * Warit Siasakul  5810405339
 */
package client;

import common.ActionComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@SpringBootApplication
public class CalendarClient
{
    @Bean
    RmiProxyFactoryBean service(){
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:1099/ActionComponent");
        rmiProxyFactory.setServiceInterface(ActionComponent.class);
        rmiProxyFactory.afterPropertiesSet();
        return rmiProxyFactory;
    }


    public static void main( String[] args )
    {
        //prevent HeadlessException
            //SpringApplicationBuilder builder
        // = new SpringApplicationBuilder(CalendarClient.class);
           // builder.headless(false).run(args);

        //----
        MainController mc = new MainController();
        ActionComponent actionComponent =
                SpringApplication.run(CalendarClient.class, args).getBean(ActionComponent.class);

        mc.setAC(actionComponent);
        mc.startApplication();

        System.out.println(actionComponent.out("=================================="));
    }
}
