/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boletic.web.controller;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.JDBCException;

/**
 *
 * @author Administrador
 */
public class ExtJSReturn {
    
    public static Map<String, Object> mapOk(){
        Map<String, Object> map = new HashMap();
        map.put("success", true);
        return map;
    }
    
    public static Map<String, Object> mapError(Exception e){
        Map<String, Object> map = new HashMap();
        map.put("success", false);
        map.put("message", (e instanceof JDBCException ? ((JDBCException) e).getSQLException() : e).getMessage());
        return map;
    }
    
}
