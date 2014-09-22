#!/usr/bin/env bash

JDK_FILE=$1
JDK_EXTRACTED_DIR=$2

pushd tmp
tar xzf $JDK_FILE.tar.gz

sudo mkdir -p /usr/lib/jvm
sudo mv ./$JDK_EXTRACTED_DIR /usr/lib/jvm/

sudo update-alternatives --install "/usr/bin/java" "java" "/usr/lib/jvm/$JDK_EXTRACTED_DIR/bin/java" 1
sudo update-alternatives --install "/usr/bin/javac" "javac" "/usr/lib/jvm/$JDK_EXTRACTED_DIR/bin/javac" 1
sudo update-alternatives --install "/usr/bin/javaws" "javaws" "/usr/lib/jvm/$JDK_EXTRACTED_DIR/bin/javaws" 1

sudo update-alternatives --set "java" "/usr/lib/jvm/$JDK_EXTRACTED_DIR/bin/java"
sudo update-alternatives --set "javac" "/usr/lib/jvm/$JDK_EXTRACTED_DIR/bin/javac"
sudo update-alternatives --set "javaws" "/usr/lib/jvm/$JDK_EXTRACTED_DIR/bin/javaws"

popd

exit 0
