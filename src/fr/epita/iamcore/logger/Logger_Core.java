package fr.epita.iamcore.logger;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Enterprise data object for logging all time stamps and errors/exceptions to an external file
 * 
 * @author Thejus and Bhanuja
 * 
 *
 */
public class Logger_Core {


		/** The Print Writer */
		private static PrintWriter pw;
		
		
		/** Initializing the file into which the log is to be written into the logging file */
		static {
			try {
				pw = new PrintWriter("application.log");
			}catch(final FileNotFoundException e) {
				System.out.println("Error while initializing the log file");
			}
		
		}
		
		
		private Class cls;
		
		public Logger_Core(Class loggingClass) {
			this.cls = loggingClass;
		}
		
		/** Method to write exceptions and error messages of type INFORMATION
		 * 
		 *  @param message The message 
		 * 
		 *  */
		public void info(String message) {
			
			printMessage(message, "INFO");
			
		}

		
		/** Method to write exceptions and error messages of type ERROR 
		 * 
		 * @param message,e The message and the Exception
		 * 
		 * */
		public void error(String message, Exception e) {
			printMessage(message, "ERROR");
			e.printStackTrace(pw);
			pw.flush();
		}
		

		/** Method to write exceptions and error messages of type ERROR 
		 * 
		 * @param message The message and the Exception
		 * 
		 * */
		public void error(String message) {
			printMessage(message, "ERROR");
		}

		/** Method to write exceptions and error messages of type DEBUG 
		 * 
		 * @param message The message and the Exception
		 * 
		 * */
		public void debug(String message) {
			printMessage(message, "DEBUG");
		}

		/** Method to write the message to the File
		 * 
		 * @param message,level The message and the Type of message i.e the level
		 * 
		 * */
		private void printMessage(String message, String level) {
			final Date date = new Date();
			final String timeStamp = new SimpleDateFormat("yyyyMMdd:HH:mm:ss.SSS").format(date);
			pw.println(date +"INFO - "+ timeStamp +"Cause :" + message + "Level:"+ level);	
			pw.flush();
		}
		
	
}
