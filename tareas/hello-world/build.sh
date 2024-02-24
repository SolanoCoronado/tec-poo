#!/bin/bash

echo -e "\n"
echo "==================="
echo "BUILD"
echo "==================="

directory="./bin/"

javac -verbose -d $directory src/tec/poo/hello/HelloWorld.java src/tec/poo/hello/HelloWorldSwing.java 