package com.cota.cotaboot;

import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by cota on 22/11/15.
 */
@RestController
public class AlumnosController {
    private final AtomicLong counter = new AtomicLong();
    private Map<String,Alumnos> mapa= new HashMap<>();



    @RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseBody
    public String home (){
        return "BIENVENIDO A GESTION \n\n" +
                "[SELECT METHOD POST]    Dirijase a  'http://localhost:8080/add' para agregar un alumno \n" +
                "[SELECT METHOD PUT]     Dirijase a  'http://localhost:8080/modify/nºDeLegajo'  para modificar algun dato del alumno con ese legajo \n" +
                "[SELECT METHOD GET]     Dirijase a  'http://localhost:8080/alumnos'  para ver un listado completo de los alumnos existentes \n" +
                "[SELECT METHOD DELETE]  Dirijase a  'http://localhost:8080/delete/nºDeLegajo'  para eliminar el alumno con ese nº de legajo \n\n\n\n" +
                "URL parameter key: name      -     Value: name\n" +
                "URL parameter key: surname   -     Value: surname\n"+
                "URL parameter key: file      -     Value: file\n"+
                "URL parameter key: career    -     Value: career\n"+
                "URL parameter key: subjects  -     Value: subjects\n";
    }




    @RequestMapping(value="/add", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String add(@RequestParam(value="name",defaultValue = "" )String name,
                      @RequestParam(value="surname",defaultValue = "" )String surname,
                      @RequestParam(value="file",defaultValue = "" )String file,
                      @RequestParam(value="subjects",defaultValue = "" ) String subjects,
                      @RequestParam(value="career",defaultValue = "" ) String career) {

        try {

        if((name.equals(""))||(surname.equals(""))||(file.equals(""))||(subjects.equals(""))||(career.equals(""))){
            throw new ExcepcionFaltanDatos("ExcepcionFaltanDatos : Es necesario completar todos los datos para crear un elemento nuevo");
        }

            Alumnos auxiliar = new Alumnos(String.format(name), String.format(surname),
                                           String.format(file), String.format(subjects), String.format(career));

            counter.incrementAndGet();

            if (!mapa.containsKey(file)) {
                mapa.put(file, auxiliar);
            }else {
                return "Ya existe un dato con el mismo file";
            }

            return String.valueOf(mapa.get(file)) + String.valueOf(counter);

        }catch (ExcepcionFaltanDatos ex){

          /*  //Utilizado para convertir StackTrace a String
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);*/
            //+sw.toString()
            return ex.getMessage();
        }

    }



    @RequestMapping(value="/alumnos", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,Alumnos> alumnos(){
        if(mapa.isEmpty()){

            //este mensaje es mostrado en la consola.
            System.out.print("Aun no existen datos");
        }
            return mapa;

    }




    @RequestMapping(value="/modify/{file}", method={RequestMethod.PUT,RequestMethod.GET})
    @ResponseBody
    public Alumnos modify(@PathVariable String file,@RequestParam(value="name",defaultValue = "" )String name,
                          @RequestParam(value="surname",defaultValue = "" )String surname,
                          @RequestParam(value="subjects",defaultValue = "" ) String subjects,
                          @RequestParam(value="career",defaultValue = "" ) String career){

        if(!name.equals("")){
            mapa.get(file).setName(name);
        }
        if(!surname.equals("")){
            mapa.get(file).setSurname(surname);
        }
        if(!subjects.equals("")){
            mapa.get(file).setFile(file);
        }
        if(!career.equals("")){
            mapa.get(file).setFile(file);
        }

        return mapa.get(file);
    }




    @RequestMapping(value="/delete/{file}", method={RequestMethod.DELETE,RequestMethod.GET})
    @ResponseBody
    public String delete(@PathVariable String file){
        mapa.remove(file);
        return "El Alumno con legajo "+file+" fue eliminado con éxito";
    }

}

