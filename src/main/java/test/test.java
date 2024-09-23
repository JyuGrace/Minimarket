
package test;

import datos.ClienteDaoJDBC;
import datos.FacturaDaoJDBC;
import dominio.Cliente;
import dominio.Factura;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {
    public static void main(String[] args) {
        String cedula = "0";
        Cliente cliente = new Cliente(cedula);
        Cliente clienteEncontrar= new ClienteDaoJDBC().encontrarByCedula(cliente);
        
        System.out.println("clienteEncontrar = " + clienteEncontrar.toString());
        
        
        
        String fecha = "2024-07-14";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = null;
        try {
            fechaUtil = formatter.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
        
        List<Factura> facturas = new FacturaDaoJDBC().encontrarFacturaOnes(fechaSQL);
        System.out.println("facturas = " + facturas);
        
        
    }
}
