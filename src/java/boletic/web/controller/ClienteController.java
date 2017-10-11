/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boletic.web.controller;

import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import boletic.web.facade.ClienteFacade;
import boletic.web.vo.ClienteVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Administrador
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteFacade clienteFacade;

    @RequestMapping(value = "/crear")
    public @ResponseBody
    Map<String, Object> crearCliente(@RequestBody String jsonCliente) {
        try {
            ClienteVO client = new ClienteVO();

            JsonParser jParser = new JsonParser();

            JsonObject json =  jParser.parse(jsonCliente).getAsJsonObject();
            json.getAsJsonObject(jsonCliente);
 
            client.setCedula(json.get("cedula").getAsString());
            client.setCorreo(json.get("nombre").getAsString());
            client.setNombre(json.get("telefono").getAsString());
            client.setTelefono(json.get("correo").getAsString());
            
            clienteFacade.crearCliente(client);
            return ExtJSReturn.mapOk();
        } catch (Exception e) {
            return ExtJSReturn.mapError(e);
        }
    }

    @RequestMapping(value = "/list")
    public @ResponseBody
    List crearCliente() {
        
        List<String> list = new ArrayList<>();
        list.add("aasdad");
        list.add("aasdad");
        
        return list;
        
    }
}
