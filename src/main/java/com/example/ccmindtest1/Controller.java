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
    private TreeNode root;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //初始化根节点
        root = new TreeNode("124154");
        root.setIsroot(true);
        root.setLayoutX(200);
        root.setLayoutY(200);
        A1.getChildren().add(root);
        treeview.setRoot(root.getView());
        root.initNode();
        //
        Add_Button.setOnAction(event -> {//添加节点按键
            if (CurNode == null) return;
            TreeNode tmp = new TreeNode("ci");
            tmp.initNode();
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
            A1.getChildren().add(tmp);
            A1.getChildren().add(tmp.getLine());
            CurNode.getView().getChildren().add(tmp.getView());
            Draw.update_len(root);
            Draw.update(root, 1);
            Draw.update(root,-1);
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
            Draw.update_len(root);
            Draw.update(root, 1);
            Draw.update(root,-1);
            CurNode=null;
        });
        left_layout_button.setOnAction(event ->{
            for(TreeNode tmp: ROOT.getRchildren()){
                ROOT.getLchildren().add(tmp);
            }
            ROOT.getRchildren().clear();
            update_len(root);
            update(root,-1);
            update(root,1);
        });
        right_layout_button.setOnAction(event ->{
            for(TreeNode tmp: ROOT.getLchildren()){
                ROOT.getRchildren().add(tmp);
            }
            ROOT.getLchildren().clear();
            update_len(root);
            update(root,-1);
            update(root,1);
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
            update_len(root);
            update(root,-1);
            update(root,1);
        });
    }

}