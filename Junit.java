import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.Test;

public class Junit {
    @Test
    public void agregarDatosAlGrafo(){
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<String> nombres2 = new ArrayList<>();
        ArrayList<String> nombres3 = new ArrayList<>();
        ArrayList<String> nombres4 = new ArrayList<>();
        ArrayList<ArrayList<String>> obj = new ArrayList<>();
        //primer valor
        nombres.add("Guate");
        nombres.add("Mixco");
        nombres.add("8");
        obj.add(nombres);
        //nombres.clear();
        //segundo valor
        nombres2.add("Guate");
        nombres2.add("SanLucas");
        nombres2.add("5");
        obj.add(nombres2);
        //nombres.clear();
        //tercer valor
        nombres3.add("SanLucas");
        nombres3.add("Mixco");
        nombres3.add("2");
        obj.add(nombres3);
        //nombres.clear();
        //cuarto valor
        nombres4.add("Mixco");
        nombres4.add("Guate");
        nombres4.add("3");
        obj.add(nombres4);
        //nombres.clear();
        ArrayList<Integer> respuesta = new ArrayList<>();
        respuesta.add(0);
        respuesta.add(8);
        respuesta.add(5);
        respuesta.add(3);
        respuesta.add(0);
        respuesta.add(0);
        respuesta.add(0);
        respuesta.add(2);
        respuesta.add(0);
        ArrayList<Integer> datos =Main.addgrafo(obj);
        assertEquals(respuesta, datos);
    }
}
