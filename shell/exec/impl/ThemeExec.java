package shell.exec.impl;

import net.minecraft.client.Minecraft;
import shell.Log;
import shell.LogType;
import shell.conf.ShellConfig;
import shell.exec.Exec;

import java.io.File;

public class ThemeExec extends Exec {

    public ThemeExec() {
        super(new String[]{"theme", "th"}, new String[]{"name"}, "Loads Theme");
    }

    @Override
    public void runExec(String fullText, String[] splitted) {


        if (!hasInput("name")) {
            write(new Log("Tema adi girilmedi", LogType.ERROR));
            return;
        }
        String name = getInput("name");

        if (name.equalsIgnoreCase("list")) {
            File file = ShellConfig.getShellConfig();
            try {
                String themes = "";
                for (File theme : file.listFiles()) {
                    themes += theme.getName().replace(".txt", "") + " ";
                }
                write(new Log("Themes: §b" + themes, LogType.SUCCESS));
                return;
            } catch (Exception ex) {
                write(new Log("No theme found", LogType.WARNING));
                return;
            }
        }
        Minecraft.getMinecraft().displayGuiScreen(null);


        getShell().useConfig(ShellConfig.loadTheme(ShellConfig.getTheme(name)));
        write(new Log("Theme loaded", LogType.SUCCESS));
        getShell().openShell();
    }
}
