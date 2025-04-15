package com.filesaver;

import com.filesaver.domain.core.DTO.Username;
import com.filesaver.sevrvices.JacksonAdapter;

import java.io.File;

import com.filesaver.domain.core.ApplicationRoot;
import com.filesaver.domain.core.Bin;
import com.filesaver.domain.core.Directory;
import com.filesaver.domain.core.Node;
import com.filesaver.domain.core.Root;
import com.filesaver.domain.core.User;
import com.filesaver.domain.core.DTO.Name;
import com.filesaver.domain.core.DTO.Password;

public class Main {
    public static void main(String[] args) {
        try{
            initializer();
            System.out.println("------------\n\n");
            
            Root root = new Root(setUser());
            // Bin bin = new Bin(root);
            
            // userDirTest();
            // testBin(bin, root);
            
            printChildren(root, 0);
        } catch (Exception error){
            System.err.println(error);
        }

    }

    private static void initializer() throws Exception{
        JacksonAdapter.createInstance();
        JacksonAdapter JacksonInstance = JacksonAdapter.getInstance();
        
        File configFile = new File("filesaver/config/config.json");
        JacksonInstance.getDataFromConfigs(configFile);

        ApplicationRoot.createInstance(new Name("APP_ROOT"), JacksonInstance);
        
    }

    private static User setUser(){
        Username username = new Username("john_doe");
        Password password = new Password("abc$ABC$123");

        return new User(username, password);
    }

    private static void userDirTest(){
        try{
            User user = setUser();
            
            System.out.println("nusername ->" + user.getUsername().toString());
            System.out.println("password -> " + user.getPassword().toString()+"\n\n");

            Node rootNode = new Directory(new Name("root"));
            System.out.println("root type Node path: " + rootNode.getPath());


            Directory root = new Directory(new Name("@root"));
            System.out.println("root path: " + root.getPath());

            Directory dir = new Directory(new Name("Images"), root);
            System.out.println("Images path: " + dir.getPath());

            Directory dirVid = new Directory(new Name("Videos"), dir);
            System.out.println("Videos path: " + dirVid.getPath());


            Directory dirDoc = new Directory(new Name("Documents"), root);
            System.out.println("Document path: " + dirDoc.getPath());

            Directory dirDown = new Directory(new Name("Downloads"), dirDoc);
            System.out.println("Downloads path: " + dirDown.getPath()+"\n\n");

            


            root.addChild(dirVid);
            System.out.println("Add to root: Videos path: " + dirVid.getPath());

            

            dirDown.setParent(root);
            System.out.println("SetParent to Downloads: Downloads path: " + dirDown.getPath()+"\n\n");


            printChildren(root,0);


        } catch(Exception error){
            System.err.println(error);
        }
    }

    private static void printChildren(Node node, int layer) {
        if(layer == 0){
            System.out.println("Children of: " + node.getPath());
        }
        for(Node child: node.getChildren()){
            System.out.print(" ");
            for(int i = 1; i <= layer; i++){
                System.out.print("-");
            }
            System.out.print("-> " + child.getName().toString() + "\n");
            if(child.getChildren().size() > 0){
                printChildren(child, layer + 1);
            }
        }
    }

    private static void testBin(Bin bin, Root root) throws Exception{
        Directory dirImg = new Directory(new Name("Image"), root);
            Directory dirVid = new Directory(new Name("Videos"), root);
            Directory dirDoc = new Directory(new Name("Documents"), root);

            Directory dir1 = new Directory(new Name("Nova pasta"), dirImg);
            Directory dir2 = new Directory(new Name("Nova pasta2"), dirImg);
            Directory dir3 = new Directory(new Name("Para deletar"), dirImg);

            bin.addChild(dir3);
            bin.addChild(dir1);
            
            bin.addChild(dirVid);
            bin.restore(dirVid);

            bin.clear();
    }
}
