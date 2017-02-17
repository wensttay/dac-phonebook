#!/bin/bash
sudo docker kill dac-phonebook-web dac-phonebook-core dac-phonebook-core-db
sudo docker rm dac-phonebook-web dac-phonebook-core dac-phonebook-core-db
sudo docker rmi dac-phonebook-web dac-phonebook-core dac-phonebook-core-db
