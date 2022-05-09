package com.example.ccmindtest1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.ccmindtest1.Draw.*;

public class Controller implements Initializable {
    @FXML
    private AnchorPane A1;//添加节点的地方
    private static TreeNode root;
    @FXML
    private TreeView treeview;
    @FXML
    private Button Add_Button;
    @FXML
    private Button Del_Button;
    @FXML
    private Button left_layout_button;
    @FXML
    private Button right_layout_button;
    @FXML
    private Button Automatic_layout_button;
    @FXML
    private ScrollPane Scrollpane;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //初始化根节点
        root = new TreeNode("主题1");
        Scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        //Scrollpane.setFitToHeight(true);
        //Scrollpane.setFitToHeight(true);
        root.setIsroot(true);
        root.setLayoutX(300);
        root.setLayoutY(250);
        A1.getChildren().add(root);
        treeview.setRoot(root.getView());
        root.initNode(root,A1);
        //
        Add_Button.setOnAction(event -> {//添加节点按键
            if (CurNode == null) return;
            TreeNode tmp = new TreeNode("子主题");
            tmp.initNode(root,A1);
            if (CurNode.isRoot()) {
                if(ROOT.getLchildren().size()<ROOT.getRchildren().size()){
                    ROOT.getLchildren().add(tmp);
                    tmp.setType(-1);
                }
                else {
                    ROOT.getRchildren().add(tmp);
                    tmp.setType(1);
                }
                tmp.setParent(CurNode);
            } else {
                CurNode.getchildren().add(tmp);
                tmp.setParent(CurNode);
                tmp.setType(CurNode.getType());
            }
            A1.getChildren().add(tmp);//添加节点
            A1.getChildren().add(tmp.getLine());//添加线
            CurNode.getView().getChildren().add(tmp.getView());//添加试图
            Draw.update(root,A1);
        });
        Del_Button.setOnAction(event -> {//删除节点按键
            if (CurNode == null || CurNode.isRoot()) return;
            Draw.DelNode(CurNode, A1);
            if (CurNode.getparent().isRoot()) {
                if (ROOT.getLchildren().contains(CurNode)) {
                    ROOT.getLchildren().remove(CurNode);
                    root.getView().getChildren().remove(CurNode.getView());
                } else {
                    ROOT.getRchildren().remove(CurNode);
                    root.getView().getChildren().remove(CurNode.getView());
                }
            } else {
                CurNode.getparent().getchildren().remove(CurNode);
                CurNode.getparent().getView().getChildren().remove(CurNode.getView());
            }
            Draw.update(root,A1);
            CurNode=null;
        });
        left_layout_button.setOnAction(event ->{
            for(TreeNode tmp: ROOT.getRchildren()){
                ROOT.getLchildren().add(tmp);
            }
            ROOT.getRchildren().clear();
            Draw.update(root,A1);
        });
        right_layout_button.setOnAction(event ->{
            for(TreeNode tmp: ROOT.getLchildren()){
                ROOT.getRchildren().add(tmp);
            }
            ROOT.getLchildren().clear();
            Draw.update(root,A1);
        });
        Automatic_layout_button.setOnAction(event ->{
            while(ROOT.getLchildren().size()>ROOT.getRchildren().size()){
                ROOT.getRchildren().add(ROOT.getLchildren().get(ROOT.getLchildren().size()-1));
                ROOT.getLchildren().remove(ROOT.getLchildren().size()-1);
            }
            while(ROOT.getLchildren().size()<ROOT.getRchildren().size()){
                ROOT.getLchildren().add(ROOT.getRchildren().get(ROOT.getRchildren().size()-1));
                ROOT.getRchildren().remove(ROOT.getRchildren().size()-1);
            }
            Draw.update(root,A1);
        });
    }

}