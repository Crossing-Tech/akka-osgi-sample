if [ ! -f ./pax-runner-1.8.5/bin/pax-run.sh ];
then
echo "pax-runner download"
wget http://search.maven.org/remotecontent?filepath=org/ops4j/pax/runner/pax-runner-assembly/1.8.5/pax-runner-assembly-1.8.5-jdk15.zip -O pax-runner.zip -q
echo "pax-runner unzip"
   unzip pax-runner.zip
   fi
cp pax-run.sh pax-runner-1.8.5/bin
echo "pax-runner is ready, please run ./pax-runner-1.8.5/bin/pax-run.sh after having set  the custom bundle in the bundle directory"
#bash ./pax-runner-1.8.5/bin/pax-run.sh --platform=felix bundles/*.jar
