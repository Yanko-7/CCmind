package com.example.ccmindtest1;

import java.util.ArrayList;
import java.util.List;

public class ROOT {
    public static double LBlockLen=Draw.RecH;
    public static double RBlockLen=Draw.RecH;
    private static ArrayList<TreeNode> Lchildren=new ArrayList<>();//根节点的左子树
    private static ArrayList<TreeNode> Rchildren=new ArrayList<>();//根节点的右子树子树
    public static ArrayList<TreeNode> getLchildren() {
        return Lchildren;
    }
    public static ArrayList<TreeNode> getRchildren() {
        return Rchildren;
    }
}
