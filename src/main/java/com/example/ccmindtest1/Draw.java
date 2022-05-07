package com.example.ccmindtest1;
import javafx.scene.layout.AnchorPane;
import static com.example.ccmindtest1.ROOT.*;
import static java.lang.Math.max;

public class Draw {
    public static double RecH=24;//固定矩阵高
    public static double RecW=50;//固定矩阵长
    public static double Block_dis=24;
    public static TreeNode CurNode;
    public static void DelNode(TreeNode a, AnchorPane A){//递归删所有子节点矩阵
        if(a.getchildren().isEmpty()==true)return;
        for(TreeNode tmp:a.getchildren()){
            DelNode(tmp,A);
        }
    }
    public static void update_len(TreeNode a){
        if(a.isRoot()){
            double len=0;
            for (TreeNode tmp : ROOT.getLchildren()) {
                update_len(tmp);
                len += tmp.getBlockLen();
            }
            len += (ROOT.getLchildren().size() - 1) * Block_dis;
            ROOT.LBlockLen=(max(len, RecH));
            len=0;
            for (TreeNode tmp : ROOT.getRchildren()) {
                update_len(tmp);
                len += tmp.getBlockLen();
            }
            len += (ROOT.getRchildren().size() - 1) * Block_dis;
            ROOT.RBlockLen=(max(len, RecH));
        }
        else {
            double len=0;
            for (TreeNode tmp : a.getchildren()) {
                update_len(tmp);
                len += tmp.getBlockLen();
            }
            len += (a.getchildren().size() - 1) * Block_dis;
            a.setBlockLen(max(len, RecH));
        }
    }
    public static void update(TreeNode a,int op){
        if(!a.isRoot()&&a.getchildren().isEmpty())return;
        if(op==1)a.setBlockLen(RBlockLen);
        if(op==-1)a.setBlockLen(LBlockLen);
        double last=a.getLayoutY()+Draw.RecH/2-a.getBlockLen()/2;
        if(op!=0){
            if(op==-1) {
                for (TreeNode tmp : ROOT.getLchildren()) {
                    double pos = last + tmp.getBlockLen() / 2 - Draw.RecH / 2;
                    tmp.setLayoutY(pos);
                    tmp.setLayoutX(a.getLayoutX() + 100 * tmp.getType());
                    last = pos + tmp.getBlockLen() / 2 + Block_dis + Draw.RecH / 2;
                }
                for (TreeNode tmp : ROOT.getLchildren()) {
                    update(tmp, 0);
                }
            }
            else{
                for (TreeNode tmp : ROOT.getRchildren()) {
                    double pos = last + tmp.getBlockLen() / 2 - Draw.RecH / 2;
                    tmp.setLayoutY(pos);
                    tmp.setLayoutX(a.getLayoutX() + 100 * tmp.getType());
                    last = pos + tmp.getBlockLen() / 2 + Block_dis + Draw.RecH / 2;
                }
                for (TreeNode tmp : ROOT.getRchildren()) {
                    update(tmp, 0);
                }
            }
        }
        else {
            for (TreeNode tmp : a.getchildren()) {
                double pos = last + tmp.getBlockLen() / 2 - Draw.RecH / 2;
                tmp.setLayoutY(pos);
                tmp.setLayoutX(a.getLayoutX() + 100 * tmp.getType());
                last = pos + tmp.getBlockLen() / 2 + Block_dis + Draw.RecH / 2;
            }
            for (TreeNode tmp : a.getchildren()) {
                update(tmp, 0);
            }
        }
    }
    public static void Del(TreeNode a,AnchorPane A){
        if(a.isRoot())return;
        for(TreeNode tmp: a.getchildren()){
            Del(tmp,A);
        }
        A.getChildren().remove(a);
    }
}