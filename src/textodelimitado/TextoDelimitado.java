package textodelimitado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ** @author oracle ***
 */
public class TextoDelimitado {

    private final String ruta = "/home/oracle/Desktop/Pruebas/texto10.txt";
    private final File file = new File(ruta);

    String cod[] = {"p1", "p2", "p3"};
    String desc[] = {"parafusos", "cravos", "tachas"};
    float prezo[] = {3.00f, 4.00f, 5.00f};
    String prezoOut;

   
    BufferedReader br;
    PrintWriter pw;
    Product product;
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);

    public void Escribir() {
        try {
            pw = new PrintWriter(ruta);
            int i = 0;
            while (i < cod.length) {
                pw.println(cod[i] + "\t" + desc[i] + "\t" + prezo[i]);
                i++;
            }
            pw.flush();
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextoDelimitado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Leer() {

        try {
            br = new BufferedReader(new FileReader(ruta));
            

            while (br.ready()) {
                String[] contenido = br.readLine().split("\t");
                product = new Product(contenido[0], contenido[1], Float.parseFloat(contenido[2]));
                System.out.println(""
                        + "\nCódigo: " + product.getCodigo()
                        + "\nDescripción: " + product.getDescripcion()
                        + "\nPrecio: " + nf.format((double) product.getPrezo()));

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextoDelimitado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextoDelimitado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        TextoDelimitado pr = new TextoDelimitado();
        pr.Escribir();
        pr.Leer();
    }
}
