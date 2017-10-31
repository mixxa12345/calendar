package server;

import common.ActionComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@SpringBootApplication
public class CalendarServer {

    @Bean ActionComponent act(){
        return new ActionComponentImpl();
    }

    @Bean
    RmiServiceExporter exporter(ActionComponent implementation) {

        // Expose a service via RMI. Remote object URL is:
        // rmi://<HOST>:<PORT>/<SERVICE_NAME>
        // 1099 is the default port

        Class<ActionComponent> serviceInterface = ActionComponent.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1099);
        return exporter;
    }

    public static void main(String[] args) {

        SpringApplication.run(CalendarServer.class, args);

        /*	load EventList to Editor
            change Editor in Client to ActionComponent

            Client >> AC >> Server
                  (request)

	*/
    }

}
