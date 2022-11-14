package org.fugerit.java.ext.doc.config;

import java.io.File;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.log.LogFacade;
import org.fugerit.java.core.web.servlet.config.BasicConfig;
import org.fugerit.java.core.xml.dom.DOMIO;
import org.fugerit.java.ext.doc.filter.DocRequestFacade;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DocServletConfig extends BasicConfig {

	/*
	 * 
	 */
	private static final long serialVersionUID = -5671884706527956909L;
	
	public static final String ATT_NAME_DOCFACADE = "DocServletConfig.ATT_NAME_DOCFACADE";
	
	public static final String VERSION = "1.0.0 (2018-01-12)";
	
	@Override
	public void configure(Properties props) throws ConfigException {
		try {
			this.getLogger().info( "Doc Module Config : VERSION : "+VERSION );
			String configPath = props.getProperty( "config" );
			File configFile = this.getConfigContext().resolvePath( configPath );
			Document doc = DOMIO.loadDOMDoc( configFile );
			Element root = doc.getDocumentElement();
			DocRequestFacade configFacade = new DocRequestFacade();
			configFacade.configure( root , this.getConfigContext() );
			this.getConfigContext().setAttribute( ATT_NAME_DOCFACADE , configFacade );
		} catch (Throwable t) {
			LogFacade.handleError( this.getLogger() , t );
		}	
	}

}
