package com.example.ccmindtest1;

public class Pane {
    public static void update_pane(){
        double last = Math.min(Controller.getRoot().getLayoutY() + Draw.RecH / 2 - ROOT.LBlockLen / 2,Controller.getRoot().getLayoutY() + Draw.RecH / 2 - ROOT.RBlockLen / 2);
        if(last<0){
            Controller.getRoot().setLayoutY(Controller.getRoot().getLayoutY()+Math.abs(last));
        }
        //last=Math.max(Controller.getRoot().getLayoutY() + Draw.RecH / 2 + ROOT.LBlockLen / 2,Controller.getRoot().getLayoutY() + Draw.RecH / 2 + ROOT.RBlockLen / 2);
        //Controller C1=new Controller();
        //if(last>C1.getScrollpane().getPrefHeight()){
        //    C1.getScrollpane().setPrefHeight(last);
        //}
    }
}
