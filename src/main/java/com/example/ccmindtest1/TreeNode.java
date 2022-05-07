package com.example.ccmindtest1;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

import static com.example.ccmindtest1.Draw.*;

public class TreeNode extends TextField {
    private boolean Isroot;
    private double BlockLen;//块长度
    private int type;//该节点向左还是向右 type =-1 向左，=1向右
    private boolean isroot;
    private TreeNode parent;
    private ArrayList<TreeNode> children;
    TreeNode(String txt){
        super(txt);
        BlockLen=Draw.RecH;
        super.setPrefHeight(Draw.RecH);
        super.setPrefWidth(Draw.RecW);
        type=1;
        children=new ArrayList<TreeNode>();
    }
    public TreeNode getparent(){
        return parent;
    }
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public boolean isRoot() {
        return isroot;
    }
    public void setIsroot(boolean flag) {
        isroot = flag;
    }
    public ArrayList<TreeNode> getchildren() {
        return children;
    }
    public void initNode(){
        this.setOnMouseClicked(event->{
            CurNode=this;
            for(TreeNode tmp:CurNode.getchildren()){
                System.out.println(tmp.getLayoutX()+" "+tmp.getLayoutY()+" "+tmp.getBlockLen());
            }
        });
    }
    public int getType() {
        return type;
    }
    public double getBlockLen() {
        return BlockLen;
    }

    public void setBlockLen(double blockLen) {
        BlockLen = blockLen;
    }
}
