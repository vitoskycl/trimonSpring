package cl.trimons.security.utils;

import java.util.List;
import java.util.stream.Collectors;

import cl.trimons.security.entity.Rol;

public class FunctionUtils {

	public static String formatListToJWTClaims(List<Rol> roles) {
		if (roles!=null) {
			return roles.stream().
		    		map(rol -> String.valueOf(rol.getNombre())).collect(Collectors.joining(",", "[", "]"));		
		}else {
			return "[]";
		}
	}

	
}
