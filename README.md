# Doctors' Office ~KIND OF UNFINISHED~

##### _Integration Testing with Databases practice for Epicodus, 25 August 2015_

#### By **Andrea Padgett & Molly Waggett**

## Description

This app allows the user to view medical specialties, doctors within each specialty, and each doctor's patients. The user may also add, edit, and delete specialties, doctors, and patients. _**Methods for all functionality exist, but the web interface does not accommodate editing and deleting yet.**_

## Setup

* Set up the database in PostgreSQL by running the following commands in your terminal:
```
  psql
  CREATE DATABASE doctors_office;
  \c doctors_office;
  CREATE TABLE patients (id serial PRIMARY KEY, name varchar, birthday varchar, doctor_id int);
  CREATE TABLE doctors (id serial PRIMARY KEY, name varchar, specialty_id int);
  CREATE TABLE specialties (id serial PRIMARY KEY, specialty varchar);
```
* If you wish to run tests, create a test database:
```
  CREATE DATABASE doctors_office_test WITH TEMPLATE doctors_office;
```
* Clone this repository.
* Using the command line, navigate to the top level of the cloned directory.
* Make sure you have gradle installed. Then run the following command in your terminal:
```
  gradle run
```
* Go to localhost:4567.
* Go!

## Technologies Used

* Java
* PostgreSQL
* Spark
* Velocity
* Gradle
* JUnit
* FluentLenium

### Legal

Copyright (c) 2015 **Andrea Padgett & Molly Waggett**

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
