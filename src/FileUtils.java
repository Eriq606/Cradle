import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {
    public static void transcript(String source, File target) throws IOException{
        Scanner scanner=new Scanner(source);
        BufferedWriter writer=new BufferedWriter(new FileWriter(target));
        try{
            while(scanner.hasNextLine()){
                writer.append(scanner.nextLine());
                writer.newLine();
            }
            writer.flush();
        }finally{
            scanner.close();
            writer.close();
        }
    }
    public void copyFile(String source, String target) throws IOException{
        Path targetPath=Path.of(target);
        try(InputStream stream=getClass().getResourceAsStream(source)){
            Files.copy(stream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    public void extractDir(String sourcedir, String target) throws IOException{
        byte[] buffer = new byte[4096];
        try (ZipInputStream zis = new ZipInputStream(getClass().getResourceAsStream(sourcedir))) {
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(target + File.separator + fileName);
                if(ze.isDirectory()){
                    newFile.mkdir();
                    zis.closeEntry();
                    ze = zis.getNextEntry();
                    continue;
                }
                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getFileContentFromData(String path) throws IOException{
        String content="";
        try(Scanner reader=new Scanner(getClass().getResourceAsStream(path))){
            while(reader.hasNextLine()){
                content+=reader.nextLine()+"\n";
            }
        }
        return content;
    }
}
