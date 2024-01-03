projectJar="../[appname]_web/WEB-INF/lib/[appname].jar"

echo "Menu principal:"
echo "1) Compiler"
echo "2) Compresser vers l'app web"
echo "3) Deployer vers tomcat"
echo "4) Generer une classe a partir de la base"
echo "5) Generer un controller"
echo "6) Generer une page"
read -p "Votre choix: " choice

case $choice in
    1)
        bash ./tools/compile.sh
    ;;
    2)
        bash ./tools/compress.sh
    ;;
    3)
        bash ./tools/deploy.sh
    ;;
    4)
        cd tools
        java -jar ./Genesis.jar
    ;;
    5)
        cd tools
        java -jar ./Exodus.jar
    ;;
    6)
        cd tools
        java -jar ./Facade.jar "${projectJar}"
    ;;
esac