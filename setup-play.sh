#!/usr/bin/env bash

pushd tmp
unzip -q typesafe-activator-1.2.10.zip -d /home/vagrant/opt/
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
