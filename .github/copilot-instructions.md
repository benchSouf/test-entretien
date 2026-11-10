# Règles du projet
- Java 25, architecture hexagonale : packages domain, application, infrastructure.
- domain et application n'importent ni Spring, ni JPA, ni Jackson.
- Records pour objets valeur et commandes, sealed interfaces pour les variantes métier.
- switch à patterns exhaustif, sans default sur un type sealed.
- Tests : JUnit 5 + AssertJ, Given/When/Then, un comportement par test.
- BigDecimal pour les montants, jamais double.