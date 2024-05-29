cd client
mvn package
cd target
rm client-0.0.1.jar
mv client-0.0.1-jar-with-dependencies.jar client-0.0.1.jar
cd ../..
./rclient.sh $1
