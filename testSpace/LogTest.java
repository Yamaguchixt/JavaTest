package testSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
		Logger logger = LoggerFactory.getLogger(LogTest.class);
		Integer t;
		Integer oldT;
		public void setTemperature (Integer temperature){
			oldT = t;
			t = temperature;
			logger.debug("Temerature set to {}. Old temperature was {}.",t,oldT);
			if(temperature.intValue() > 50){
				logger.info("Temperature has risen above 50 degrees.:w");
			}
		}

}
