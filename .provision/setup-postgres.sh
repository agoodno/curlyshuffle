#!/usr/bin/env bash

sudo apt-get install postgresql postgresql-contrib -y

cat << 'EOF' > /tmp/postgres-set-password.in
ALTER USER postgres WITH PASSWORD 'shufflin';
EOF

sudo -u postgres psql postgres -f /tmp/postgres-set-password.in

sudo -u postgres createdb curlyshuffle
#sudo -u postgres createdb curlyshuffle_test
#sudo -u postgres createdb curlyshuffle_prod

exit 0
