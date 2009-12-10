package com.vady.editor;

import com.vady.editor.gui.Property;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;


public class PropertiesHolder {

    public static final PropertiesHolder instance = new PropertiesHolder();

    public static final Logger LOGGER = Logger.getLogger(PropertiesHolder.class);


    private Configuration labelConfiguration;
    private Configuration programConfiguration;


    private PropertiesHolder() {

        try {
            labelConfiguration = new PropertiesConfiguration("label.properties");
            programConfiguration = new PropertiesConfiguration("program.properties");
        } catch (ConfigurationException e) {
            LOGGER.error(e);
        }
    }

    public Configuration getLabels() {
        return labelConfiguration;
    }

    public Configuration getProgramConfiguration() {
        return programConfiguration;
    }


    public Float asFloat(Property property) {
        return programConfiguration.getFloat(property.toString());
    }


    public int asInt(Property property) {
        return programConfiguration.getInt(property.toString());
    }

    public String asString(Property property) {
        return programConfiguration.getString(property.toString());
    }

    public double asDouble(Property property) {
        return programConfiguration.getDouble(property.toString());
    }

}
