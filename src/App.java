import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        FileUtils utils=new FileUtils();
        String projectName, withweb, withFlamework;
        try(Scanner scanner=new Scanner(System.in)){
            System.out.print("Nom du projet: ");
            projectName=scanner.next();
            System.out.print("Application web? (O/n):");
            withweb=scanner.next();
            System.out.print("Utiliser Flamework? (O/n):");
            withFlamework=scanner.next();
        }
        File project=new File(projectName);
        project.mkdir();

        File projectsrc=new File(project, projectName+"_src");
        projectsrc.mkdir();

        File vscode=new File(projectsrc, ".vscode");
        vscode.mkdir();
        File vscodesett=new File(vscode, "settings.json");
        String vscodesettsource=utils.getFileContentFromData("/data/settings_java.json");
        FileUtils.transcript(vscodesettsource, vscodesett);
        vscodesett.createNewFile();
        File srcsource=new File(projectsrc, "src");
        srcsource.mkdir();
        File srclib=new File(projectsrc, "lib");
        srclib.mkdir();
        File readmesrc=new File(projectsrc, "README.md");
        String readmesource=utils.getFileContentFromData("/data/README.md");
        FileUtils.transcript(readmesource, readmesrc);
        readmesrc.createNewFile();

        File toolsdir=new File(project, "tools");
        toolsdir.mkdir();
        File compilebat=new File(toolsdir, "compile.bat");
        String compilebatsource=utils.getFileContentFromData("/data/compile.bat");
        compilebatsource=compilebatsource.replace("[appname]", projectName);
        FileUtils.transcript(compilebatsource, compilebat);
        compilebat.createNewFile();
        File compilesh=new File(toolsdir, "compile.sh");
        String compileshsource=utils.getFileContentFromData("/data/compile.sh");
        compileshsource=compileshsource.replace("[appname]", projectName);
        FileUtils.transcript(compileshsource, compilesh);
        compilesh.createNewFile();
        
        if(withweb.equalsIgnoreCase("n")){
            return;
        }
        File projectweb=new File(project, projectName+"_web");
        projectweb.mkdir();
        File webinf=new File(projectweb, "WEB-INF");
        webinf.mkdir();
        File webxml=new File(webinf, "web.xml");
        String webxmlsource=null;
        if(withFlamework.equalsIgnoreCase("O")){
            webxmlsource=utils.getFileContentFromData("/data/web_flamework.xml");
        }else if(withFlamework.equalsIgnoreCase("n")){
            webxmlsource=utils.getFileContentFromData("/data/web_vanilla.xml");
        }
        webxmlsource=webxmlsource.replace("[appname]", projectName);
        webxmlsource=webxmlsource.replace("[appnamemin]", StringUtils.minStart(projectName));
        FileUtils.transcript(webxmlsource, webxml);
        webxml.createNewFile();

        File weblib=new File(webinf, "lib");
        weblib.mkdir();
        File indexjsp=new File(projectweb, "index.jsp");
        String indexsource=utils.getFileContentFromData("/data/index.jsp");
        indexsource=indexsource.replace("[appname]", projectName);
        FileUtils.transcript(indexsource, indexjsp);
        indexjsp.createNewFile();

        File pageslib=new File(projectweb, "pages");
        pageslib.mkdir();
        File homejsp=new File(pageslib, "home.jsp");
        String homesource=utils.getFileContentFromData("/data/home.jsp");
        FileUtils.transcript(homesource, homejsp);
        homejsp.createNewFile();

        File layoutlib=new File(pageslib, "layout");
        layoutlib.mkdir();
        File layout=new File(layoutlib, "layout.jsp");
        String layoutsource=utils.getFileContentFromData("/data/layout.jsp");
        layoutsource=layoutsource.replace("[appname]", projectName);
        FileUtils.transcript(layoutsource, layout);
        layout.createNewFile();

        File configdir=new File(projectweb, "config");
        configdir.mkdir();
        File config=new File(configdir, "cradle_config.json");
        String configsource=utils.getFileContentFromData("/data/cradle_route.json");
        configsource=configsource.replace("[appname]", projectName);
        String home="home";
        if(withFlamework.equalsIgnoreCase("O")){
            home+=".do";
        }
        configsource=configsource.replace("[Home]", home);
        FileUtils.transcript(configsource, config);
        config.createNewFile();

        File apppackage=new File(srcsource, StringUtils.minStart(projectName));
        apppackage.mkdir();
        File controllerpackage=new File(apppackage, "controllers");
        controllerpackage.mkdir();
        String controllerhomename="Home";
        String controllersource="";
        if(withFlamework.equalsIgnoreCase("O")){
            controllerhomename+="Controller.java";
            controllersource=utils.getFileContentFromData("/data/HomeController.java");
            controllersource=controllersource.replace("[appname]", projectName);
        }else if(withFlamework.equalsIgnoreCase("n")){
            controllerhomename+="Servlet.java";
            controllersource=utils.getFileContentFromData("/data/HomeServlet.java");
        }
        controllersource=controllersource.replace("[appnamemin]", StringUtils.minStart(projectName));
        File controllerhome=new File(controllerpackage, controllerhomename);
        FileUtils.transcript(controllersource, controllerhome);
        controllerhome.createNewFile();

        File initservlet=new File(controllerpackage, "InitServlet.java");
        String initsource=utils.getFileContentFromData("/data/InitServlet.java");
        initsource=initsource.replace("[appnamemin]", StringUtils.minStart(projectName));
        FileUtils.transcript(initsource, initservlet);
        initservlet.createNewFile();

        File compressbat=new File(toolsdir, "compress.bat");
        String compressbatsource=utils.getFileContentFromData("/data/compress.bat");
        compressbatsource=compressbatsource.replace("[appname]", projectName);
        FileUtils.transcript(compressbatsource, compressbat);
        compressbat.createNewFile();

        File compresssh=new File(toolsdir, "compress.sh");
        String compressshsource=utils.getFileContentFromData("/data/compress.sh");
        compressshsource=compressshsource.replace("[appname]", projectName);
        FileUtils.transcript(compressshsource, compresssh);
        compresssh.createNewFile();

        File deploybat=new File(toolsdir, "deploy.bat");
        String deploybatsource=utils.getFileContentFromData("/data/deploy.bat");
        deploybatsource=deploybatsource.replace("[appname]", projectName);
        FileUtils.transcript(deploybatsource, deploybat);
        deploybat.createNewFile();

        File deploysh=new File(toolsdir, "deploy.sh");
        String deployshsource=utils.getFileContentFromData("/data/deploy.sh");
        deployshsource=deployshsource.replace("[appname]", projectName);
        FileUtils.transcript(deployshsource, deploysh);
        deploysh.createNewFile();
        
        File executebat=new File(project, "execute.bat");
        String executebatsource=utils.getFileContentFromData("/data/execute.bat");
        executebatsource=executebatsource.replace("[appname]", projectName);
        FileUtils.transcript(executebatsource, executebat);
        executebat.createNewFile();

        File executesh=new File(project, "execute.sh");
        String executeshsource=utils.getFileContentFromData("/data/execute.sh");
        executeshsource=executeshsource.replace("[appname]", projectName);
        FileUtils.transcript(executeshsource, executesh);
        executesh.createNewFile();

        utils.copyFile("/data/Cradle.jar", srclib.getPath()+"/Cradle.jar");
        utils.copyFile("/data/Cradle.jar", weblib.getPath()+"/Cradle.jar");
        utils.copyFile("/data/Genesis.jar", toolsdir.getPath()+"/Genesis.jar");
        utils.extractDir("/data/data_genesis.zip", toolsdir.getPath());
        utils.copyFile("/data/Exodus.jar", toolsdir.getPath()+"/Exodus.jar");
        utils.copyFile("/data/Facade.jar", toolsdir.getPath()+"/Facade.jar");
        utils.copyFile("/data/Facade.jar", srclib.getPath()+"/Facade.jar");
        utils.copyFile("/data/Facade.jar", weblib.getPath()+"/Facade.jar");
        utils.extractDir("/data/data_facade.zip", toolsdir.getPath());
        utils.copyFile("/data/Veda.jar", srclib.getPath()+"/Veda.jar");
        utils.copyFile("/data/Veda.jar", weblib.getPath()+"/Veda.jar");
        utils.extractDir("/data/data_exodus.zip", toolsdir.getPath());
        utils.copyFile("/data/servlet-api-jakarta.jar", srclib.getPath()+"/servlet-api-jakarta.jar");
        utils.copyFile("/data/servlet-api-jakarta.jar", weblib.getPath()+"/servlet-api-jakarta.jar");
        utils.copyFile("/data/gson-2.8.2.jar", srclib.getPath()+"/gson-2.8.2.jar");
        utils.copyFile("/data/gson-2.8.2.jar", weblib.getPath()+"/gson-2.8.2.jar");
        if(withFlamework.equalsIgnoreCase("O")){
            utils.copyFile("/data/Flamework.jar", srclib.getPath()+"/Flamework.jar");
            utils.copyFile("/data/Flamework.jar", weblib.getPath()+"/Flamework.jar");
        }
        utils.extractDir("/data/assets.zip", projectweb.getPath());
    }
}
