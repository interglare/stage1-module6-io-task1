package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath())){
            int ch;
            StringBuilder key= new StringBuilder();
            StringBuilder value = new StringBuilder();

            while ((ch=fileInputStream.read())!=-1){
                char c = (char)ch;

                if (key.toString().equals("Name") || key.toString().equals("Age") || key.toString().equals("Email") || key.toString().equals("Phone")) {
                    if (c =='\n'){
                        switch (key.toString()) {
                            case "Name":
                                profile.setName(value.toString());
                                break;
                            case "Age":
                                profile.setAge(Integer.parseInt(value.toString()));
                                break;
                            case "Email":
                                profile.setEmail(value.toString());
                                break;
                            default:
                                profile.setPhone(Long.parseLong(value.toString()));
                                break;
                        }
                        key.delete(0,key.length());
                        value.delete(0,value.length());
                    } else if (c != ':' && c != ' ' && c != '\r'){
                        value.append(c);
                    }
                } else {
                    key.append(c);
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
