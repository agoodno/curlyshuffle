#!/usr/bin/env bash

if [ ! -f jdk-7u67-linux-x64.tar.gz ]; then
  wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/7u67-b01/jdk-7u67-linux-x64.tar.gz
fi

if [ ! -f typesafe-activator-1.2.10.zip ]; then
  wget -O typesafe-activator-1.2.10.zip http://downloads.typesafe.com/typesafe-activator/1.2.10/typesafe-activator-1.2.10.zip?_ga=1.197388586.484302643.1380080629
fi
