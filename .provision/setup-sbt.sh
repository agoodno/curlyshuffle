#!/usr/bin/env bash

pushd bin
wget -q -O sbt-launch.jar http://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/0.13.5/sbt-launch.jar?_ga=1.265609484.2068458402.1410661224
popd

cat << 'EOF' > /home/vagrant/bin/sbt
SBT_OPTS="-Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled"
java $SBT_OPTS -jar `dirname $0`/sbt-launch.jar "$@"
EOF

chown vagrant:vagrant /home/vagrant/bin/sbt
chmod 700 /home/vagrant/bin/sbt

exit 0
