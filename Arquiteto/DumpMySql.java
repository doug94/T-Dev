/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

/**
 *
 * @author Andre
 */
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import javax.swing.JOptionPane;
import java.security.ProtectionDomain;
 
public class DumpMySql {
  


//https://stackoverflow.com/questions/14924770/simple-backup-and-restore-for-mysql-database-from-java 


public static void Backupdbtosql() {
    try {

        /*NOTE: Getting path to the Jar file being executed*/
        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
        CodeSource codeSource = DumpMySql.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();


        /*NOTE: Creating Database Constraints*/
        String dbName = "YourDBName";
        String dbUser = "YourUserName";
        String dbPass = "YourUserPassword";

        /*NOTE: Creating Path Constraints for folder saving*/
        /*NOTE: Here the backup folder is created for saving inside it*/
        String folderPath = jarDir + "\\backup";

        /*NOTE: Creating Folder if it does not exist*/
        File f1 = new File(folderPath);
        f1.mkdir();

        /*NOTE: Creating Path Constraints for backup saving*/
        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
         String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";

        /*NOTE: Used to create a cmd command*/
        String executeCmd = "mysqldump -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;

        /*NOTE: Executing the command here*/
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();

        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
        if (processComplete == 0) {
            System.out.println("Backup Complete");
        } else {
            System.out.println("Backup Failure");
        }

    } catch (URISyntaxException | IOException | InterruptedException ex) {
        JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
    }
}


public static void Restoredbfromsql(String s) {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = DumpMySql.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
             String dbName = "YourDBName";
             String dbUser = "YourUserName";
             String dbPass = "YourUserPassword";

            /*NOTE: Creating Path Constraints for restoring*/
            String restorePath = jarDir + "\\backup" + "\\" + s;

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String[] executeCmd = new String[]{"mysql", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Successfully restored from SQL : " + s);
            } else {
                JOptionPane.showMessageDialog(null, "Error at restoring");
            }


        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }

    
}








// Constantes da classe
  private static String VERSION = "4.0.3";
 
  private static String SEPARATOR = File.separator;
 
  private static String MYSQL_PATH =
    "G:" + SEPARATOR +
    "Arquivos de programas" + SEPARATOR +
    "MySQL" + SEPARATOR +
    "MySQL Server 5.0" + SEPARATOR +
    "bin" + SEPARATOR;
 
  private static String PRESENTATION =
    "==========================================================\n" +
    "  Backup do banco de dados MySQL - Versao " + VERSION + "\n" +
    "  Autor: Marcelo Carvalho Pinto da Cunha\n\n" +
    "  Desenvolvido em 07/09/2009\n\n" +
    "  MarcWare Software, 2009-2012\n" +
    "==========================================================\n\n";
 
  // Lista dos bancos de dados a serem "backupeados"; se desejar adicionar mais,
  // basta colocar o nome separado por espaços dos outros nomes
  private static String DATABASES =
    "agenda cultos webcheckadmin projectmanager calendario webfinance mysql";
 
  private List<String> dbList = new ArrayList<String>();
 
  public MySQLBackup() {
    String command = MYSQL_PATH + "mysqldump.exe";
 
    String[] databases = DATABASES.split(" ");
 
    for (int i = 0; i < databases.length; i++)
      dbList.add(databases[i]);
 
    // Mostra apresentação
    System.out.println(PRESENTATION);
 
    System.out.println("Iniciando backups...\n\n");
 
    // Contador
    int i = 1;
 
    // Tempo
    long time1, time2, time;
 
    // Início
    time1 = System.currentTimeMillis();
 
    for (String dbName : dbList) {
      ProcessBuilder pb = new ProcessBuilder(
        command,
        "--user=root",
        "--password=trotski123",
        dbName,
        "--result-file=" + "." + SEPARATOR + "Backup" + SEPARATOR + dbName + ".sql");
 
      try {
        System.out.println(
          "Backup do banco de dados (" + i + "): " + dbName + " ...");
 
        pb.start();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
 
      i++;
    }
 
    // Fim
    time2 = System.currentTimeMillis();
 
    // Tempo total da operação
    time = time2 - time1;
 
    // Avisa do sucesso
    System.out.println("\nBackups realizados com sucesso.\n\n");
    System.out.println("Tempo total de processamento: " + time + " ms\n");
    System.out.println("Finalizando...");
 
    try {
      // Paralisa por 2 segundos
      Thread.sleep(2000);
    }
    catch (Exception e) {}
 
    // Termina o aplicativo
    System.exit(0);
  }
 
  public static void main(String[] args) {
    MySQLBackup app = new MySQLBackup();
  } 
}