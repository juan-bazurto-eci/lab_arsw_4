package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class Main {


    public static void main(String a[]) throws BlueprintNotFoundException {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = appContext.getBean(BlueprintsServices.class);
        Point[] pts = new Point[] { new Point(0, 0), new Point(10, 10), new Point(10, 10), new Point(11, 10),
                new Point(15, 10), new Point(15, 10), new Point(0, 0) };
        Blueprint currentBp = new Blueprint("Miguel", "casa#38", pts);
        bps.addNewBlueprint(currentBp);
        bps.addNewBlueprint(new Blueprint("Juan", "casa#39"));
        bps.addNewBlueprint(new Blueprint("Miguel", "casa#12"));
        bps.addNewBlueprint(new Blueprint("Miguel", "casa#8"));
        Set<Blueprint> authorBP = bps.getBlueprintsByAuthor(currentBp.getAuthor());
        System.out.println("getCurrentBluePrintByAuthor");
        for (Blueprint b : authorBP) {
            System.out.println(b);
        }
        System.out.println("getCurrentBluePrint");
        System.out.println(bps.getBlueprint(currentBp.getAuthor(), currentBp.getName()));
        ((ClassPathXmlApplicationContext) appContext).close();


        System.out.println("Get blueprint con redundancy");
        Point[] ptsFilter = new Point[]{new Point(10, 10), new Point(10, 10), new Point(10,10), new Point(10,10)};
        bps.addNewBlueprint(new Blueprint("Juan", "casa#38", ptsFilter));
        Blueprint blueprint1 = bps.getBlueprint("Juan","casa#38");
        for (Point p: blueprint1.getPoints()) {
            System.out.println("Punto: " + p);
        }
    }

}