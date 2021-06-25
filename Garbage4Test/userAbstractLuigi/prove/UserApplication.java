package prove;



import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class UserApplication extends ResourceConfig {

	public static void main(String[] args) {
		SpringApplication.run( UserApplication.class, args);
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		
		}));
		
	}
	
	public UserApplication() {

		register(new UserAPI());
	}


}
