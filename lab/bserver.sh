cd server
mvn package
cd ..
cp db.properties server/target/
cd server/target/
rm server-0.0.1.jar
mv server-0.0.1-jar-with-dependencies.jar server-0.0.1.jar
cd ../..
./rserver.sh $1
