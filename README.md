# ♞ Move Your Knight

This is a Java-based console application that simulates knight movements on a given board and by given command parameters.

---

## 🧰 Requirements

- Java 17+
- Maven 3.6+
- Docker
- Git

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/maheeshan/jobrapido-test.git
cd jobrapido-test
```

### 2. Build Docker Image

```bash
docker build -t knight_board:latest .
```

### 2. Test with parameters

```bash
docker run \
 -e BOARD_API=https://storage.googleapis.com/jobrapido-backend-test/board.json \
 -e COMMANDS_API=https://storage.googleapis.com/jobrapido-backend-test/commands.json \
 knight_board:latest

```
---

## 👤 Author

[Sanju Maheeshan](https://github.com/maheeshan)