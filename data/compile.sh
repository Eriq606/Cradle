cd [appname]_src
find src -type f -name "*.java" > sources.txt
javac -cp .:lib/* -d bin @sources.txt
rm sources.txt