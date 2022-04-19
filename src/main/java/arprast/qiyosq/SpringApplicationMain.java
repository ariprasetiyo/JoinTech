package arprast.qiyosq;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/**
 * Enable proxyTargetClass=true in @EnableTransactionManagement if there isn't
 * have class interface E.g cases UserDaoImpl
 * 
 * @see {https://github.com/spring-projects/spring-boot/issues/5423} 
 * We should use @EnableTransactionManagement(proxyTargetClass = true) to
 * prevent nasty proxy issues when people aren't using interfaces.
 */
@EnableTransactionManagement(proxyTargetClass = true)
@ImportResource("classpath:app-config.xml")
public class SpringApplicationMain {

	static Logger log = LoggerFactory.getLogger(SpringApplicationMain.class);

	public static void main(String[] args) {

		/*
		 * If use this, by default configuration application.properties
		 */
		/*
		 * ApplicationContext ctx =
		 * SpringApplication.run(SpringApplicationMain.class, args);
		 * SpringApplication.run(SpringApplicationMain.class, args);
		 */	
		new SpringApplicationBuilder(SpringApplicationMain.class).properties("spring.config.name:application").build()
				.run(args);

		Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
		if (auth2 != null) {

			Collection<?> auths = auth2.getAuthorities();

			for (Object da : auths) {
				log.debug("auth check {}",da);
			}

			log.debug("name : " + auth2.getName());
			log.debug("principle : " + auth2.getPrincipal());
			log.debug("principle : " + auth2.getDetails());
			log.debug("principle : " + auth2.getCredentials());
		}
	}
}
