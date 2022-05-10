package com.example.ccmindtest1;

import javafx.scene.layout.AnchorPane;

public class Pane {
    public static void update_pane(AnchorPane A,TreeNode root){
        double last = Math.min(root.getLayoutY() + Draw.RecH / 2 - TreeNode.LBlockLen / 2,root.getLayoutY() + Draw.RecH / 2 - TreeNode.RBlockLen / 2);
        if(last<0){
            root.setLayoutY(root.getLayoutY()+Math.abs(last));
        }
        last=Math.min(250 + Draw.RecH / 2 - TreeNode.LBlockLen / 2,250 + Draw.RecH / 2 - TreeNode.RBlockLen / 2);
        System.out.println(root.getLayoutY()+" "+TreeNode.LBlockLen / 2+" "+TreeNode.RBlockLen / 2);
        if(last>0){
            root.setLayoutY(250);
        }
        last=Math.max(TreeNode.LBlockLen / 2,TreeNode.RBlockLen / 2);
        if(last>250){
            root.setLayoutY(last);
        }
        last=Math.max(root.getLayoutY() + Draw.RecH / 2 + TreeNode.LBlockLen / 2,root.getLayoutY() + Draw.RecH / 2 + TreeNode.RBlockLen / 2);
        A.setPrefHeight(last);
        double l=root.getLayoutX()-TreeNode.LMaxLinkLen;
        if(l<0){
            root.setLayoutX(root.getLayoutX()+Math.abs(l));
        }
        l=300-TreeNode.LMaxLinkLen;
        if(l>0)root.setLayoutX(300);
        if(TreeNode.LMaxLinkLen>300)root.setLayoutX(TreeNode.LMaxLinkLen);
        double r=root.getLayoutX()+root.getTextLen()+TreeNode.RMaxLinkLen;
        A.setPrefWidth(r);
    }
}
