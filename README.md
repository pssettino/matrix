# Matrix API

## Tech Stack

* Kotlin
* JDK 8
* Spring Boot
* JUnit + Mockk

## Run/Debug locally
You can start up the local environment running this script `bootRun`.

If you are in the root directory, just type:
```
./gradlew bootRun
``` 
You could test App endpoints with Postman (or whatever tool), using the host and port `localhost:8080`.

## Test App

* To run all tests (unit and integration)

        ./gradlew test

## Endpoints

### 1.- Echo (Return the matrix as a string in matrix format)

`curl --request GET \
--url http://localhost:8080/echo \
--header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
--form file=@/Users/psettino/Downloads/matrix.csv`

#### Response

```json
1,2,3
4,5,6
7,8,9
```

---

### 2.- Invert (Return the matrix as a string in matrix format where the columns and rows are inverted)

`curl --request GET \
--url http://localhost:8080/invert \
--header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
--form file=@/Users/psettino/Downloads/matrix.csv`

#### Response

```json
1,4,7
2,5,8
3,6,9
```

---
### 3.- Flatten (Return the matrix as a 1 line string, with values separated by commas)

`curl --request GET \
--url http://localhost:8080/flatten \
--header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
--form file=@/Users/psettino/Downloads/matrix.csv`

#### Response

```json
1,2,3,4,5,6,7,8,9
```

---

### 4.- Sum (Return the sum of the integers in the matrix)

```
curl --request GET \
  --url http://localhost:8080/sum \
  --header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
  --form file=@/Users/psettino/Downloads/matrix.csv
```

#### Response

```json
45
```

---

### 5.- Multiply (Return the product of the integers in the matrix)


```
curl --request GET \
  --url http://localhost:8080/product \
  --header 'Content-Type: multipart/form-data; boundary=---011000010111000001101001' \
  --form file=@/Users/psettino/Downloads/matrix.csv
```

#### Response

```json
362880
```

