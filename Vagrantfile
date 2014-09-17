# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.provider "virtualbox" do |v|
    v.memory = 4096
    v.cpus = 2
    v.customize ["modifyvm", :id, "--cpuexecutioncap", "50"]
  end
  # config.vm.provision "chef_solo" do |chef|
  #   chef.add_recipe "apache"
  # end
#  config.ssh.forward_agent = true
  config.vm.provision :shell, path: "bootstrap.sh"
  config.vm.provision "file", source: "~/.gitconfig", destination: ".gitconfig"
  config.vm.provision "file", source: "jdk-7u67-linux-x64.tar.gz", destination: "tmp/jdk-7u67-linux-x64.tar.gz"
#  config.vm.provision "file", source: "jdk-8u20-linux-x64.tar.gz", destination: "tmp/jdk-8u20-linux-x64.tar.gz"
  config.vm.provision "file", source: "typesafe-activator-1.2.10.zip", destination: "tmp/typesafe-activator-1.2.10.zip"
#  config.vm.provision :shell, path: "config-ssh.sh"
  config.vm.provision :shell, path: "setup-jdk.sh", args: "jdk-7u67-linux-x64 jdk1.7.0_67"
  config.vm.provision :shell, path: "setup-sbt.sh"
  config.vm.provision :shell, path: "setup-play.sh"
  config.vm.provision :shell, path: "setup-postgres.sh"
  config.vm.provision :shell, path: "setup-app.sh"
  config.vm.network :forwarded_port, host: 9000, guest: 9000
end
