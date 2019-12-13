/**
 * ShapeChange - processing application schemas for geographic information
 *
 * This file is part of ShapeChange. ShapeChange takes a ISO 19109 
 * Application Schema from a UML model and translates it into a 
 * GML Application Schema or other implementation representations.
 *
 * Additional information about the software can be found at
 * http://shapechange.net/
 *
 * (c) 2002-2019 interactive instruments GmbH, Bonn, Germany
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact:
 * interactive instruments GmbH
 * Trierer Strasse 70-72
 * 53115 Bonn
 * Germany
 */
package de.interactive_instruments.ShapeChange;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

/**
 * @author Johannes Echterhoff (echterhoff <at> interactive-instruments <dot>
 *         de)
 *
 */
public class BasicTestSCXML extends BasicTest {

    protected ShapeChangeResult executeScxml(String configPath) {

	String pathToRelevantConfig;
	try {
	    pathToRelevantConfig = scxmlConverter.updateSCXMLTestResources(configPath);
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Exception occurred while updating SCXML test resources.", e);
	    return null;
	}

	return execute(pathToRelevantConfig);
    }

    @Override
    protected void multiTest(String configPath, String[] fileFormatsToCheck, String basedirResults,
	    String basedirReference) {

	String pathToRelevantConfig;
	try {
	    pathToRelevantConfig = scxmlConverter.updateSCXMLTestResources(configPath);
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Exception occurred while updating SCXML test resources.", e);
	    return;
	}

	super.multiTest(pathToRelevantConfig, fileFormatsToCheck, basedirResults, basedirReference);
    }

    @Override
    protected void executeAndError(String config, String detailsOnExpectedError) {

	String pathToRelevantConfig;
	try {
	    pathToRelevantConfig = scxmlConverter.updateSCXMLTestResources(config);
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Exception occurred while updating SCXML test resources.", e);
	    return;
	}

	super.executeAndError(pathToRelevantConfig, detailsOnExpectedError);
    }

    @Override
    protected void jsonTest(String config, String[] typenames, String basedirResults, String basedirReference) {

	String pathToRelevantConfig;
	try {
	    pathToRelevantConfig = scxmlConverter.updateSCXMLTestResources(config);
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Exception occurred while updating SCXML test resources.", e);
	    return;
	}

	super.jsonTest(pathToRelevantConfig, typenames, basedirResults, basedirReference);
    }

    @Override
    protected void docxTest(String config, String[] docxs, String basedirResults, String basedirReference) {

	String pathToRelevantConfig;
	try {
	    pathToRelevantConfig = scxmlConverter.updateSCXMLTestResources(config);
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Exception occurred while updating SCXML test resources.", e);
	    return;
	}

	super.docxTest(pathToRelevantConfig, docxs, basedirResults, basedirReference);
    }

    @Override
    protected void htmlTest(String config, String[] htmls, String basedirResults, String basedirReference) {

	String pathToRelevantConfig;
	try {
	    pathToRelevantConfig = scxmlConverter.updateSCXMLTestResources(config);
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Exception occurred while updating SCXML test resources.", e);
	    return;
	}

	super.htmlTest(pathToRelevantConfig, htmls, basedirResults, basedirReference);
    }

    @Override
    protected void ldproxyTest(String config, String[] files, String basedirResults, String basedirReference) {

	String pathToRelevantConfig;
	try {
	    pathToRelevantConfig = scxmlConverter.updateSCXMLTestResources(config);
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Exception occurred while updating SCXML test resources.", e);
	    return;
	}

	super.ldproxyTest(pathToRelevantConfig, files, basedirResults, basedirReference);
    }

    @Override
    protected void rdfTest(String config, String[] rdfs, String basedirResults, String basedirReference) {

	String pathToRelevantConfig;
	try {
	    pathToRelevantConfig = scxmlConverter.updateSCXMLTestResources(config);
	} catch (Exception e) {
	    fail("Exception occurred while updating SCXML test resources.", e);
	    e.printStackTrace();
	    return;
	}

	super.rdfTest(pathToRelevantConfig, rdfs, basedirResults, basedirReference);
    }

    protected void sqlTest(String config, String[] sqls, HashMap<String, String> replacevalues, String basedirResults,
	    String basedirReference, boolean noErrors) {

	String pathToRelevantConfig;

	try {
	    pathToRelevantConfig = scxmlConverter.updateSCXMLTestResources(config);
	} catch (Exception e) {
	    e.printStackTrace();
	    fail("Exception occurred while updating SCXML test resources.", e);
	    return;
	}

	super.sqlTest(pathToRelevantConfig, sqls, replacevalues, basedirResults, basedirReference, noErrors);
    }

    @Override
    protected void xsdTest(String config, String[] xsds, String[] schs, HashMap<String, String> replacevalues,
	    String basedirResults, String basedirReference, boolean noErrors) {

	String pathToRelevantConfig;
	if (config == null) {
	    // so the default config shall be executed
	    pathToRelevantConfig = null;
	} else {
	    try {
		pathToRelevantConfig = scxmlConverter.updateSCXMLTestResources(config);
	    } catch (Exception e) {
		e.printStackTrace();
		fail("Exception occurred while updating SCXML test resources.", e);
		return;
	    }
	}

	super.xsdTest(pathToRelevantConfig, xsds, schs, replacevalues, basedirResults, basedirReference, noErrors);
    }
}
