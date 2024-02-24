#!/bin/bash

echo -e "\n"
echo "==================="
echo "JAR"
echo "==================="

binDirectory="./bin/"
classesDirectory="tec"
jarFile="hello-world.jar"
# jarFile="hello-world-swing.jar"
manifestFile="../App-Manifest.txt"


cd $binDirectory
#jar -cvf $jarFile ./$classesDirectory
jar -cvmf $manifestFile $jarFile $classesDirectory
cd ..