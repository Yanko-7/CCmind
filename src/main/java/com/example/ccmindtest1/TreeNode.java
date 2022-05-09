package com.example.ccmindtest1;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import static com.example.ccmindtest1.Draw.*;

public class TreeNode extends TextField {
    private double BlockLen;//块长度
    private double TextLen;//文本宽度
    private double MaxLinkLen;//最长子链
    private int type;//该节点向左还是向右 type =-1 向左，=1向右
    private boolean isroot;
    private TreeNode parent;
    private TreeItem<String> view;//该节点在TreeView上的视图
    private ArrayList<TreeNode> children;
    private Line line;

    TreeNode(String txt) {
        super(txt);
        BlockLen = Draw.RecH;
        super.setPrefHeight(Draw.RecH);
        super.setPrefWidth(Draw.RecW);
        setTextLen(RecW);
        type = 1;
        children = new ArrayList<>();
        line = new Line();
        view = new TreeItem<>(txt);
        view.setExpanded(true);
    }
    public TreeItem<String> getView() {
        return view;
    }
    public TreeNode getparent() {
        return parent;
    }
    public double getMaxLinkLen() {
        return MaxLinkLen;
    }
    public double getTextLen() {
        return TextLen;
    }

    public void setTextLen(double textLen) {
        TextLen = textLen;
    }

    public void setMaxLinkLen(double maxLinkLen) {
        MaxLinkLen = maxLinkLen;
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

    public void initNode(TreeNode root,AnchorPane A1) {
        this.setOnMouseClicked(event -> {
            CurNode = this;
            for (TreeNode tmp : CurNode.getchildren()) {
                System.out.println(tmp.getLayoutX() + " " + tmp.getLayoutY() + " " + tmp.getBlockLen());
            }
        });
        super.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                view.setValue(TreeNode.super.getText());
            }
        });
        super.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                //动态长度
                TreeNode.super.setPrefWidth(Math.max(TreeNode.super.getText().length()*13.5,48));
                setTextLen(TreeNode.super.getPrefWidth());
                update(root,A1);
                //System.out.println(TreeNode.super.getText().length());
            }
        });
        //设置节点的背景颜色
        super.setStyle("-fx-control-inner-background:#909020");
    }

    public Line getLine() {
        return line;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getBlockLen() {
        return BlockLen;
    }

    public void setBlockLen(double blockLen) {
        BlockLen = blockLen;
    }
}
