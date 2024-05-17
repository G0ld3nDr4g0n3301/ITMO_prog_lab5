javac -cp server/src/main/java/ru/ifmo/se/server/:server/src/main/java/ru/ifmo/se/server/**:server/src/main/java/ru/ifmo/se/common/** -d target/classes/ server/src/main/java/ru/ifmo/se/server/*.java server/src/main/java/ru/ifmo/se/server/**/*.java server/src/main/java/ru/ifmo/se/common/**/*.java
cd target/classes/
jar -cf ../../server.jar ru/ifmo/se/server/*.class ru/ifmo/se/server/**/*.class ru/ifmo/se/common/**/*.class
echo "jar built!"
cd ../..
./rserver.sh
