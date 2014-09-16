#!/usr/bin/env bash

pushd tmp
#wget -q -O typesafe-activator-1.2.10.zip http://downloads.typesafe.com/typesafe-activator/1.2.10/typesafe-activator-1.2.10.zip?_ga=1.96836056.639002318.1409750382
unzip typesafe-activator-1.2.10.zip -d /home/vagrant/opt/
popd

chown -R vagrant:vagrant /home/vagrant/opt/activator-1.2.10
chmod -R 700 /home/vagrant/opt/activator-1.2.10

cat << 'EOF' >> /home/vagrant/.bashrc
export ACTIVATOR_HOME=$HOME/opt/activator-1.2.10
if [ -d "$ACTIVATOR_HOME" ] ; then
    PATH="$ACTIVATOR_HOME:$PATH"
fi
EOF

. /home/vagrant/.bashrc

exit 0
