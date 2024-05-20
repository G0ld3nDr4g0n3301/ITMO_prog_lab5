javac -cp client/src/main/java/ru/ifmo/se/client/:client/src/main/java/ru/ifmo/se/client/**:client/src/main/java/ru/ifmo/se/common/** -d target/classes/ client/src/main/java/ru/ifmo/se/client/*.java client/src/main/java/ru/ifmo/se/client/**/*.java client/src/main/java/ru/ifmo/se/common/**/*.java
cd target/classes/
jar -cf ../../client.jar ru/ifmo/se/client/*.class ru/ifmo/se/client/**/*.class ru/ifmo/se/common/**/*.class
echo "jar built!"
cd ../..
./rclient.sh $1
