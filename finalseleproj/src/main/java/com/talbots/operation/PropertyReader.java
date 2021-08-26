/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talbots.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mo Wan
 */
public class PropertyReader {

    Properties p = new Properties();

    public Properties getObjectRepository(String propFileName) {
        //Read object repository file
        InputStream stream;
        try {
                stream = new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\objects\\" + propFileName));
                //load all objects
                p.load(stream);
        } catch (IOException ex) {
            Logger.getLogger(PropertyReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
}
