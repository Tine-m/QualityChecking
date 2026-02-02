# Unit Test & JUnit – den korte version

## Formål med unit tests
Unit tests bruges til at:
- kontrollere at **kode virker som forventet**
- finde fejl **hurtigt**
- gøre ændringer **sikre**
- give klar feedback: *virker / virker ikke*

👉 En unit test tester **én lille del af programmet** (typisk én metode).

![3 teknikker](approaches.png)

---

## 🧰 Hvad er JUnit?
**JUnit** er et Java-bibliotek til at:
- skrive tests i Java
- køre dem automatisk
- vise tydeligt om noget er **grønt (OK)** eller **rødt (fejl)**

JUnit bruges typisk sammen med Maven:

Skal defineres i `pom.xml`:

```
<dependencies>
        <!-- JUnit Jupiter API for writing tests -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.12.2</version>
            <scope>test</scope>
        </dependency>
        <!-- JUnit Jupiter Engine for executing tests -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.12.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <!-- Surefire plugin to run JUnit 5 tests -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.5.4</version>
            </plugin>
        </plugins>
    </build>
```

---

## 🧠 AAA-tilgangen (Arrange – Act – Assert)
De fleste tests følger dette mønster:

```java
@Test
void deposit_increasesBalance() {

    // Arrange (forbered data)
    BankAccount account = new BankAccount(100);

    // Act (udfør handling)
    account.deposit(50);

    // Assert (tjek resultat)
    assertEquals(150, account.getBalance());
}
```

### AAA betyder:
- **Arrange**: opret objekter og startværdier
- **Act**: kald metoden, der testes
- **Assert**: tjek om resultatet er korrekt

👉 Hvis du er i tvivl: **mangler din test en af de tre, er noget galt**

---

## ✅ Mest brugte assert-metoder

### `assertEquals(expected, actual)`
Tjekker at to værdier er ens.
```java
assertEquals(70, account.getBalance());
```

---

### `assertTrue(condition)` / `assertFalse(condition)`
Tjekker en regel eller betingelse.
```java
assertTrue(password.length() >= 8);
assertFalse(isLoggedIn);
```

---

### `assertNotNull(value)` / `assertNull(value)`
Tjekker om noget findes eller ikke findes.
```java
assertNotNull(user);
assertNull(service.findUser(99));
```

---

### `assertThrows(Exception.class, code)`
Tjekker at en fejl **skal** opstå.
```java
assertThrows(IllegalArgumentException.class,
        () -> calculator.divide(10, 0));
```

👉 Bruges når noget **ikke er tilladt**.

---

### `@Disabled`
Slår en test fra midlertidigt.
```java
@Disabled("Enable in Story S3")
@Test
void XXXTest() {
}
```

👉 Bruges til **planlagt arbejde**, ikke til at skjule fejl.

---

## 🧠 Gode principper (meget vigtige)

### ✔ Én test = én ting
❌ Dårligt:
```java
@Test
void testEverything() { ... }
```

✔ Godt:
```java
@Test
void feed_decreasesHunger() { ... }

@Test
void feed_neverMakesHungerNegative() { ... }
```

---

### ✔ Test logik – ikke input/output
❌ Test **ikke**:
- `Scanner`
- `System.out.println`
- menuer og UI

✔ Test:
- forretningslogik
- beregninger
- regler

👉 UI testes manuelt – logik testes automatisk.

---

### ✔ Små tests er bedre end store
- Små tests er nemmere at forstå
- Små tests er nemmere at debugge
- Små tests giver bedre feedback

---

### ✔ Brug tests til debugging
- Når en test fejler → debug **testen**
- Sæt breakpoint i metoden, testen kalder
- Debug én metode ad gangen

👉 Debug aldrig hele programmet, hvis en test kan isolere fejlen.
