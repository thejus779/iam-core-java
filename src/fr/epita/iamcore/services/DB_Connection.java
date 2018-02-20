package fr.epita.iamcore.services;
//package fr.epita.iam.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import fr.epita.iamcore.logger.Logger_Core;


/**
 * Enterprise data object for the DB connection
 * 
 * @author Thejus and Bhanuja
 *
 */

public class DB_Connection {

		
		/** The connection object. */
		private static Connection con;
		
		/** The properties file. */
		private static Properties properties;
		
		/** The properties variables */
		private static final String DRIVER="DRIVER";
		private static final String DB_URL="URL";
		private static final String DB_USER="USER";
		private static final String DB_PASSWORD="PASSWORD";
		private static final String CONF="conf";
		
		
		/** The input Object. */
		private static InputStream inputStream;
		
		/** The Logger */
		private static final Logger_Core logger = new Logger_Core(DB_Connection.class);

		static {
			properties = initialiseProperties();
		}
		
		
		/**
		 * A method to initialize the properties
		 * 
		 * @param CONF configuration
		 * @return Properties the properties object
		 */
		private static Properties initialiseProperties() {
			
			properties = new Properties();
			
			try {
				if (System.getProperty(CONF) == null) {
					System.out.println("System property needs to be set.");
				}
				
				inputStream = new FileInputStream(System.getProperty(CONF));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return properties;
		}
		
		
		/**
		 * A method to create  a database connection
		 * 
		 * @param properties The properties object
		 * @return connection The connection object
		 */
		public static Connection getConnection(){
			
				try {
					Class.forName(properties.getProperty(DRIVER)).newInstance();
					con=DriverManager.getConnection(properties.getProperty(DB_URL), properties.getProperty(DB_USER), 
							properties.getProperty(DB_PASSWORD));
					
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					logger.error("Error while loading the configuration", e);
				}
				
			return con;
		}


}
