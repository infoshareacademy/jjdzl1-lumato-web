## fuel-calculator-logic
---

#### Database configuration:
1. Go to server root
    - for Wildfly: wildfly-(version)-(Final)/bin
2. Create new directory called: **fuel-calculator-config**
3. Create new file called **db.properties** inside fuel-calculator-config
4. Provide db.config file with data: url, user, password

For example, 
If I am on Windows and I have my wildfly-16.0.0.Final installed in D:\Wildfly, then I should have a file:
```
D:\Wildfly\wildfly-16.0.0.Final\bin\fuel-calculator-config\db.properties
```
which contains: 
```
url = jdbc:mysql://192.168.99.100:9001/lumato
password = root
user = root
```
