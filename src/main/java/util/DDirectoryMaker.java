package util;

import com.lichenaut.dashapi.DashAPI;

import java.io.File;

public class DDirectoryMaker {

    private final DashAPI plugin;

    public DDirectoryMaker(DashAPI plugin) {this.plugin = plugin;}

    public void makeDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {if (!dir.mkdirs()) {plugin.getLog().severe("Could not create file '" + path + "'! SecurityException?");}}
    }
}