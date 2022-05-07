package com.example.ccmindtest1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.ccmindtest1.Draw.CurNode;

public class Controller implements Initializable {
    @FXML
    private AnchorPane A1;//添加节点的地方
    private TreeNode root;
    @FXML
    private Button Add_Button;
    @FXML
    private Button Del_Button;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //初始化根节点
        root = new TreeNode("124154");
        root.setIsroot(true);
        root.setLayoutX(200);
        root.setLayoutY(200);
        A1.getChildren().add(root);
        root.initNode();
        //

        Add_Button.setOnAction(event -> {//添加节点按键
            if (CurNode == null) return;
            TreeNode tmp = new TreeNode("ci");
            tmp.initNode();
            if (CurNode.isRoot()) {
                ROOT.getRchildren().add(tmp);
                tmp.setParent(CurNode);
            } else {
                CurNode.getchildren().add(tmp);
                tmp.setParent(CurNode);
            }
            A1.getChildren().add(tmp);
            A1.getChildren().add(tmp.getLine());
            Draw.update_len(root);
            Draw.update(root, 1);
        });
        Del_Button.setOnAction(event -> {//删除节点按键
            if (CurNode == null || CurNode.isRoot()) return;
            Draw.DelNode(CurNode, A1);
            if (CurNode.getparent().isRoot()) {
                if (ROOT.getLchildren().contains(CurNode)) {
                    ROOT.getLchildren().remove(CurNode);
                } else {
                    ROOT.getRchildren().remove(CurNode);
                }
            } else {
                CurNode.getparent().getchildren().remove(CurNode);
            }
            Draw.update_len(root);
            Draw.update(root, 1);
        });
        //
    }

}