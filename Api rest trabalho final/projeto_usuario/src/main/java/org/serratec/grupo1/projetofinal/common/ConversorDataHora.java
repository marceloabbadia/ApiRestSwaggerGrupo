package org.serratec.grupo1.projetofinal.common;

import java.util.Date;
import java.text.*;

public class ConversorDataHora {
	
	public static String converterDateParaDataHora (Date data) {
		
		
		SimpleDateFormat formatador = new SimpleDateFormat ("dd/MM/YYYY HH:mm:ss");
		
		return formatador.format(data);	
		
	}

}
