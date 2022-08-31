#!/usr/bin/env bash

spring init \
--boot-version=2.6.3.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=images-service \
--package-name=com.assignment1.images \
--groupId=com.assignment1.images \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
images-service

spring init \
--boot-version=2.6.3.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=posts-service \
--package-name=com.assignment1.posts \
--groupId=com.assignment1.posts \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
posts-service

spring init \
--boot-version=2.6.3.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=api-gateway \
--package-name=com.assignment1.apigateway \
--groupId=com.assignment1.apigateway \
--dependencies=web \
--version=1.0.0-SNAPSHOT \
api-gateway

