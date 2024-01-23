package ru.geekbrains.lesson5;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeakUpDir {
    public static void main(String[] args) {
        try {
            backUpFiles();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    static void backUpFiles() throws IOException {

        Path sourcePathDir = Paths.get(".");
        Path backupDir = Paths.get("./.backup");
        if (!Files.exists(backupDir)) {
            Files.createDirectory(backupDir);
        }else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm ss");
            String backupDirName = "./.backup_" + dateFormat.format(new Date());
            backUpFiles(backupDirName);
            return;
        }
            Files.walk(sourcePathDir).forEach(path -> {
                    String relativePath = sourcePathDir.relativize(path).toString();
                    Path targetPath = backupDir.resolve(relativePath);
                    if (relativePath.matches(".*\\bbackup\\b.*")){
                        return;
                    }
                    if (Files.isDirectory(path)){
                        try {

                                Files.createDirectories(targetPath);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        try {
                            Files.copy(path,targetPath);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            });
        System.out.println("Backup complete" + backupDir);
    }
    static void backUpFiles(String backupDirName) throws IOException {
        Path sourcePathDir = Paths.get(".");
        Path backupDir = Paths.get(".",backupDirName);
        if (!Files.exists(backupDir)) {
            Files.createDirectory(backupDir);
        }else {
            System.out.println("");
            return;
        }
        Files.walk(sourcePathDir).forEach(path -> {
            String relativePath = sourcePathDir.relativize(path).toString();
            Path targetPath = backupDir.resolve(relativePath);
            if (relativePath.matches(".*\\bbackup\\b.*")){
                return;
            }
            if (Files.isDirectory(path)){
                try {

                    Files.createDirectories(targetPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    Files.copy(path,targetPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("Backup complete" + backupDir);
    }

//    static void pathTest(Path s, Path o, Path p){
//        String relativePath = s.relativize(p).toString();
//        Path targetPath = o.resolve(relativePath);
//        if (Files.isDirectory(p)){
//            try {
//
//                Files.createDirectories(targetPath);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }else {
//            try {
//                Files.copy(p,targetPath);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }

}
