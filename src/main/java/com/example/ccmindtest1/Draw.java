package com.example.ccmindtest1;

import javafx.scene.layout.AnchorPane;

import static com.example.ccmindtest1.ROOT.*;
import static java.lang.Math.max;

public class Draw {
    public static final double RecH = 24;//固定矩阵高
    public static final double RecW = 50;//固定矩阵长
    public static final double Block_dis = 24;////固定块距
    public static TreeNode CurNode;

    public static void update_len(TreeNode a) {//用于更新整棵树的所有节点的块大小，传入一个根节点即可
        double len = 0;
        if (a.isRoot()) {
            for (TreeNode tmp : ROOT.getLchildren()) {
                tmp.setType(-1);
                update_len(tmp);
                len += tmp.getBlockLen();
            }
            len += (ROOT.getLchildren().size() - 1) * Block_dis;
            ROOT.LBlockLen = (max(len, RecH));
            len = 0;
            for (TreeNode tmp : ROOT.getRchildren()) {
                tmp.setType(1);
                update_len(tmp);
                len += tmp.getBlockLen();
            }
            len += (ROOT.getRchildren().size() - 1) * Block_dis;
            ROOT.RBlockLen = (max(len, RecH));
        } else {
            for (TreeNode tmp : a.getchildren()) {
                tmp.setType(a.getType());
                update_len(tmp);
                len += tmp.getBlockLen();
            }
            len += (a.getchildren().size() - 1) * Block_dis;
            a.setBlockLen(max(len, RecH));
        }
    }

    public static void update(TreeNode a, int op) {//更新树在图上的位置，需要先执行update_len
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
                    update(tmp, 0);
                }
            } else {
                for (TreeNode tmp : ROOT.getRchildren()) {
                    last = SetSon(a, last, tmp);
                }
                for (TreeNode tmp : ROOT.getRchildren()) {
                    update(tmp, 0);
                }
            }
        } else {
            for (TreeNode tmp : a.getchildren()) {
                last = SetSon(a, last, tmp);
            }
            for (TreeNode tmp : a.getchildren()) {
                update(tmp, 0);
            }
        }
    }

    private static double SetSon(TreeNode a, double last, TreeNode tmp) {
        double pos = last + tmp.getBlockLen() / 2 - Draw.RecH / 2;
        tmp.setLayoutY(pos);
        tmp.setLayoutX(a.getLayoutX() + 100 * tmp.getType());
        if(tmp.getType()==1) {//右边节点画线
            tmp.getLine().SetLine(tmp.getparent().getLayoutX() + Draw.RecW, tmp.getparent().getLayoutY() + Draw.RecH / 2, tmp.getLayoutX(), tmp.getLayoutY() + Draw.RecH / 2);
        }
        else{//左边节点画线
            tmp.getLine().SetLine(tmp.getparent().getLayoutX(), tmp.getparent().getLayoutY() + Draw.RecH / 2, tmp.getLayoutX()+Draw.RecW, tmp.getLayoutY() + Draw.RecH / 2);
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

}