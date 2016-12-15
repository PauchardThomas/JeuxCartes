package fr.ascadis.resources;

import javax.faces.application.FacesMessage;
import javax.websocket.OnClose;
import javax.websocket.RemoteEndpoint;

import org.primefaces.push.EventBus;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

import com.google.gson.Gson;

@PushEndpoint("/partie")
public class PartieRessource {
    @OnMessage(encoders = { JSONEncoder.class })
    public String onMessage(Object message) {
        Gson gson = new Gson();
        String jsonInString = gson.toJson(message);
        return jsonInString;
    }
}
