package com.example.ccmindtest1;

import javafx.scene.layout.AnchorPane;

import static com.example.ccmindtest1.ROOT.*;
import static java.lang.Math.max;

public class Draw {
    public static final double RecH = 24;//固定矩阵高
    public static final double RecW = 54;//固定矩阵长
    public static final double Block_dis = 24;////固定块距
    public static TreeNode CurNode;

    public static void update_len(TreeNode a) {//用于更新整棵树的所有节点的块上下大小，传入一个根节点即可
        double len = 0;
        if (a.isRoot()) {
            LMaxLinkLen=0;
            for (TreeNode tmp : ROOT.getLchildren()) {
                tmp.setType(-1);
                update_len(tmp);
                LMaxLinkLen=max(LMaxLinkLen,tmp.getMaxLinkLen()+(100-Draw.RecW)+tmp.getTextLen());
                len += tmp.getBlockLen();
            }
            len += (ROOT.getLchildren().size() - 1) * Block_dis;
            ROOT.LBlockLen = (max(len, RecH));
            len = 0;
            RMaxLinkLen=0;
            for (TreeNode tmp : ROOT.getRchildren()) {
                tmp.setType(1);
                update_len(tmp);
                RMaxLinkLen=max(RMaxLinkLen,tmp.getMaxLinkLen()+(100-Draw.RecW)+tmp.getTextLen());
                len += tmp.getBlockLen();
            }
            len += (ROOT.getRchildren().size() - 1) * Block_dis;
            ROOT.RBlockLen = (max(len, RecH));
        } else {
            a.setMaxLinkLen(0);
            for (TreeNode tmp : a.getchildren()) {
                tmp.setType(a.getType());
                update_len(tmp);
                a.setMaxLinkLen(max(a.getMaxLinkLen(),tmp.getMaxLinkLen()+(100-Draw.RecW)+tmp.getTextLen()));
                len += tmp.getBlockLen();
            }
            len += (a.getchildren().size() - 1) * Block_dis;
            a.setBlockLen(max(len, RecH));
        }
    }

    public static void update_pos(TreeNode a, int op) {//更新树在图上的位置，需要先执行update_len
        if (!a.isRoot() && a.getchildren().isEmpty()) return;
        if (op == 1) a.setBlockLen(RBlockLen);
        if (op == -1) a.setBlockLen(LBlockLen);
        double last = a.getLayoutY() + Draw.RecH / 2 - a.getBlockLen() / 2;
        if (op != 0) {
            if (op == -1) {
                for (TreeNode tmp : ROOT.getLchildren()) {
                    last = SetSon(a, last, tmp);
                }
                for (TreeNode tmp : ROOT.getLchildren()) {
                    update_pos(tmp, 0);
                }
            } else {
                for (TreeNode tmp : ROOT.getRchildren()) {
                    last = SetSon(a, last, tmp);
                }
                for (TreeNode tmp : ROOT.getRchildren()) {
                    update_pos(tmp, 0);
                }
            }
        } else {
            for (TreeNode tmp : a.getchildren()) {
                last = SetSon(a, last, tmp);
            }
            for (TreeNode tmp : a.getchildren()) {
                update_pos(tmp, 0);
            }
        }
    }

    private static double SetSon(TreeNode a, double last, TreeNode tmp) {
        double pos = last + tmp.getBlockLen() / 2 - Draw.RecH / 2;
        tmp.setLayoutY(pos);
        if(tmp.getType()==1) {//右边节点画线
            tmp.setLayoutX(a.getLayoutX()+a.getTextLen()-Draw.RecW + 100 * tmp.getType());
            tmp.getLine().SetLine(a.getLayoutX() + a.getTextLen(), a.getLayoutY() + Draw.RecH / 2, tmp.getLayoutX(), tmp.getLayoutY() + Draw.RecH / 2);
        }
        else{//左边节点画线
            tmp.setLayoutX(a.getLayoutX() + (100-Draw.RecW) * tmp.getType()-tmp.getTextLen());
            tmp.getLine().SetLine(a.getLayoutX(), a.getLayoutY() + Draw.RecH / 2, tmp.getLayoutX()+tmp.getTextLen(), tmp.getLayoutY() + Draw.RecH / 2);
        }
        last = pos + tmp.getBlockLen() / 2 + Block_dis + Draw.RecH / 2;
        return last;
    }

    public static void DelNode(TreeNode a, AnchorPane A) {//只是删除了图上的还没删除list里的
        if (a.isRoot()) return;
        for (TreeNode tmp : a.getchildren()) {
            DelNode(tmp, A);
        }
        A.getChildren().remove(a);
        A.getChildren().remove(a.getLine());
    }
    public static void update(TreeNode root,AnchorPane A1){
        Draw.update_len(root);
        Pane.update_pane(A1,root);
        Draw.update_pos(root, 1);
        Draw.update_pos(root,-1);
    }
}