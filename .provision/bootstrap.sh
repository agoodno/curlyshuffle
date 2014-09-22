#!/usr/bin/env bash

sudo apt-get update -q
sudo apt-get install unzip -y

mkdir -p app
chown vagrant:vagrant app
chmod 700 app

mkdir -p bin
chown vagrant:vagrant bin
chmod 700 bin

mkdir -p opt
chown vagrant:vagrant opt
chmod 700 opt

mkdir -p tmp
chown vagrant:vagrant tmp
chmod 700 tmp
