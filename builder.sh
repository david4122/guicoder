cd src
if [ -f ../libs/lib.jar ];
then
	javac -cp ../libs/lib.jar -d ../build/ org/GUICoder/*.java
	cd ../build/
	jar -xf ../libs/lib.jar
else
	javac -d ../build/ org/GUICoder/*.java
	cd ../build/
fi
jar -cfm ../dist/GUICoder.jar ../META-INF/MANIFEST.MF org
