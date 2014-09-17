#!/usr/bin/env bash

pushd tmp
unzip -q typesafe-activator-1.2.10.zip -d /home/vagrant/opt/
popd

chown -R vagrant:vagrant /home/vagrant/opt/activator-1.2.10
chmod -R 700 /home/vagrant/opt/activator-1.2.10

exit 0
