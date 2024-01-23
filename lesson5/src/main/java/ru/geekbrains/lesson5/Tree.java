package ru.geekbrains.lesson5;



import java.io.File;
import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.*;


public class Tree {

    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        PrintStream out = new PrintStream(System.out, true, UTF_8);
        out.print(indent);
        if (isLast){
            out.print("└─");
            indent += "  ";
        }
        else {
            out.print("├─");
            indent += "│ ";
        }
        out.println(file.getName());
//        BeakUpDir.beakUpFiles(file);
        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++){
//            if (files[i].isDirectory())
//            {
                subDirTotal++;
//            }
        }

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++){
//            if (files[i].isDirectory())
//            {
                print(files[i], indent, subDirTotal == ++subDirCounter);
//            }
        }


    }

}
