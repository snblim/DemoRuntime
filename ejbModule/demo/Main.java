/**
 * Main.java
 *
 * Aug 14, 2012
 * @author Viktor Pekar
 */
package demo;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Viktor Pekar
 * 
 *         Aug 14, 2012
 */
public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	private EJBContainer ejbContainer;

	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}
	
	public void run(){
		try {
			logger.info("############### Starting EJBContainer!");

			ejbContainer = EJBContainer.createEJBContainer();
			Context context = ejbContainer.getContext();
			context.bind("inject", this);

			logger.info("############### EJBContainer gestartet: "
					+ ejbContainer.toString());

			IDemo demo = (IDemo) new InitialContext()
					.lookup("java:global/DemoService/Demo");

			logger.info("############### Lookup done: " + demo.toString());

			demo.printHelloWorld();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ejbContainer.close();
			logger.info("############### EJBContainer has been closed!");
		}
	}
}
