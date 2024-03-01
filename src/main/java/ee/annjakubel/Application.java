package ee.annjakubel;

import ee.annjakubel.controller.OpenUVController;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);

        OpenUVController controller = new OpenUVController();

        System.out.println(controller.getUVData("55.5", "66"));

    }
}