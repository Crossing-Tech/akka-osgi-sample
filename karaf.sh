if [ -f ./apache-karaf-2.3.0 ];
then
rm -rf  apache-karaf-2.3.0
   fi
echo "karaf download"
wget -q http://mirror.switch.ch/mirror/apache/dist/karaf/2.3.0/apache-karaf-2.3.0.tar.gz -O karaf.tar.gz
echo "karaf untar"
   tar xf karaf.tar.gz
rm karaf.tar.gz
