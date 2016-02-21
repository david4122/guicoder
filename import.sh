echo PROJECT NAME\>
read pr
cp ../$pr/dist/$pr.jar ./libs/
cd libs/
if [ -f lib.jar ]; then
	jar -xf lib.jar
fi
jar -xf $pr.jar
rm *.jar
rm -r META-INF/
jar -cvf lib.jar org 
