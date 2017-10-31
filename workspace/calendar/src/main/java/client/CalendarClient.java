/**
 * Warit Siasakul  5810405339
 */
package client;

import common.ActionComponent;
import controllers.MainController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import server.ActionComponentImpl;

@SpringBootApplication
public class CalendarClient
{
    @Bean
    RmiProxyFactoryBean service() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:1099/ActionComponent");
        rmiProxyFactory.setServiceInterface(ActionComponent.class);
        return rmiProxyFactory;
    }


    public static void main( String[] args )
    {
        //prevent HeadlessException
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CalendarClient.class);
        builder.headless(false).run(args);

        //----Init Block
        MainController mc = new MainController();
        mc.startApplication();
        ActionComponent actionComponent =
                SpringApplication.run(CalendarClient.class, args).getBean(ActionComponent.class);
        //----

        mc.setAC(actionComponent);
        String text = actionComponent.out("TestTest");
        System.out.println(text);
    }
}
