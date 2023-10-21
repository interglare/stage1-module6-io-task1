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
                    if (c == '\r'){
                        continue;
                    } else if (c =='\n'){
                        if (key.toString().equals("Name")){
                            profile.setName(value.toString());
                        } else if (key.toString().equals("Age")) {
                            profile.setAge(Integer.parseInt(value.toString()));
                        } else if (key.toString().equals("Email")){
                            profile.setEmail(value.toString());
                        } else {
                            profile.setPhone(Long.parseLong(value.toString()));
                        }
                        key.delete(0,key.length());
                        value.delete(0,value.length());
                    } else if (c != ':' && c != ' '){
                        value.append(c);
                    }
                } else {
                    key.append(c);
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e){

        }
        return profile;
    }
}
