package in.ashokit.service;

// Aspect oriented Programming(Aop)
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;




@Service
@Slf4j // the second way is using this way we have to installed lombog also fo this 
public class UserService {

//  First way :----	
//  when you go for Lombok you don't need to create write this you just have to mention the Annotation
//	this is logger object to get the logger file here we give name which class you want logger file
//	private Logger log = LoggerFactory.getLogger(UserService.class);
//  AccessSpecifer  Logger-Interface reference = class.method(classYouWantLoggerFileToGernate.class);
	
	public void saveUser() {
		
		
		log.trace("this is trace msg");
		log.debug("this is debug msg");
		log.info("this is info msg");
		log.warn("this is warn msg");
		log.error("this is error msg");
		// logic to save user with database
				
	}
	
	
}
