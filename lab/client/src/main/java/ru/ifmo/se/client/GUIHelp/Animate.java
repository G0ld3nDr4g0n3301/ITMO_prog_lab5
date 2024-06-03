package ru.ifmo.se.client.GUIHelp;

import javafx.scene.shape.Rectangle;
import ru.ifmo.se.common.collections.Person;

public class Animate implements Runnable{

    private Person p;
    private Rectangle rec;

    public Animate(Person p){
        this.p = p;
    }

    @Override
    public void run() {
        
    }
    

    public void remove(){
        AnimationHandler.getPane().getChildren().remove(rec);
    }

    public Rectangle getRec(){
        return this.rec;
    }
}
