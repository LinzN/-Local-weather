/*
 * Copyright (C) 2020. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.localWeather;

import de.linzn.zSocket.components.events.IListener;
import de.linzn.zSocket.components.events.ReceiveDataEvent;
import de.linzn.zSocket.components.events.handler.EventHandler;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class DataListener implements IListener {

    public static JSONObject sensorData;


    @EventHandler(channel = "sensor_data")
    public void onData(ReceiveDataEvent event) {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getDataInBytes()));
        try {
            sensorData = new JSONObject(in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
