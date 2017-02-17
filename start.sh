#!/bin/bash
sudo mvn clean install
sudo docker-compose up -d --build
