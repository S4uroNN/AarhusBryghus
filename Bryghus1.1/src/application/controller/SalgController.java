package application.controller;

import storage.Storage;

public class SalgController {
    private Storage storage;
    private static SalgController controller;

    private SalgController(){
        storage = Storage.getInstance();
    }
    public static SalgController getSalgController(){
        if(controller == null){
            controller = new SalgController();
        }
        return controller;
    }

}
