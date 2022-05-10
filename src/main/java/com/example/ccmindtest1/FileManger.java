package com.example.ccmindtest1;

import javafx.scene.control.TreeTableRow;

import java.io.*;
import java.util.ArrayList;

public class FileManger {
    public void Save_File(TreeNode a,File file){
        WriteObject(a,file);
    }
    public Object Open_File(File file){
        return readObject(file);
    }
    private void WriteObject(Object obj, File file){
        FileOutputStream out;
        try{
            out =new FileOutputStream(file);
            ObjectOutputStream objout=new ObjectOutputStream(out);
            objout.writeObject(TreeNode.LMaxLinkLen);
            objout.writeObject(TreeNode.RMaxLinkLen);
            objout.writeObject(TreeNode.LBlockLen);
            objout.writeObject(TreeNode.RBlockLen);
            objout.writeObject(TreeNode.getLchildren());
            objout.writeObject(TreeNode.getRchildren());
            objout.writeObject(obj);
            objout.flush();objout.close();
            System.out.println("write success");
        } catch (IOException e) {
            System.out.println("write failed");
            e.printStackTrace();
        }
    }
    private Object readObject(File file){
        Object tmp=null;
        FileInputStream in;
        try {
            in=new FileInputStream(file);
            ObjectInputStream objin=new ObjectInputStream(in);
            TreeNode.LMaxLinkLen= (double) objin.readObject();
            TreeNode.RMaxLinkLen= (double) objin.readObject();
            TreeNode.LBlockLen= (double) objin.readObject();
            TreeNode.RBlockLen= (double) objin.readObject();
            TreeNode.setLchildren((ArrayList<TreeNode>) objin.readObject());
            TreeNode.setRchildren((ArrayList<TreeNode>) objin.readObject());
            tmp=objin.readObject();
            objin.close();
            System.out.println("read success");
        } catch (IOException e){
            System.out.println("read failed");
            e.printStackTrace();
            return  null;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return  null;
        }
        return  tmp;
    }
}
