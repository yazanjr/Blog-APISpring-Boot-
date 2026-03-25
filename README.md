# Blog Platform API

## Overview

A RESTful backend system for a blog platform built with Spring Boot.
Supports user authentication, post management, and tagging system.

## Tech Stack

* Java
* Spring Boot
* Spring Security (JWT)
* PostgreSQL
* Hibernate (JPA)

## Features

* User registration & login (JWT authentication)
* Create, update, delete blog posts
* Tag and category management
* Secure endpoints with role-based access
* Input validation and error handling

## Architecture

* Controller → Service → Repository
* DTO pattern with MapStruct
* Stateless authentication using JWT

## API Documentation

Swagger / OpenAPI available at:
http://localhost:8080/swagger-ui.html

## How to Run

1. Clone repo
2. Configure application.properties
3. Run application
4. Access API at localhost:8080

## Future Improvements

* Pagination & filtering
* Comments system
* Likes & reactions
