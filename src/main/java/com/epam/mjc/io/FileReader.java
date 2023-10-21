package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath())){
            int ch;
            StringBuilder line = new StringBuilder();

            while ((ch=fileInputStream.read())!=-1){
                char c = (char)ch;
                if (c == '\n'){
                    String[] strings = line.toString().split(":");
                    String key = strings[0].trim();
                    String value = strings[1].trim();

                    switch (key) {
                        case "Name":
                            profile.setName(value);
                            break;
                        case "Age":
                            profile.setAge(Integer.parseInt(value));
                            break;
                        case "Email":
                            profile.setEmail(value);
                            break;
                        default:
                            profile.setPhone(Long.parseLong(value));
                            break;
                    }
                    line.delete(0, line.length());
                } else {
                    line.append(c);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e){
            System.out.println("IO exception");
        }
        return profile;
    }
}
