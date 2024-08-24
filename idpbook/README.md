# Instruções para executar o projetdo IDPbook

## Tecnologias utilizadas
* VS Code 1.92.2
* Java 21
* Maven +3.6.3
* Spring Boot 3.3.3
* Thymeleaf
* Bootstrap 5.3.0
* Postgress 16.4
* pgAdmin 4 8.11
* Docker 27.1.1

## Ambiente de desenvolvimento recomendado
* Linux ou Windows com WSL (Ubuntu 22.04.4 LTS)

## Configuração do ambiente

* Para configurar seu ambiente rode os seguintes comandos

### Atualizar o sistema operacional
```
sudo apt update -y && sudo apt upgrade -y
```

### Instalar JDK 21

```
sudo apt install openjdk-21-jdk -y
``` 

* Verificar se a instação ocorreu corretamente

```
java -version
```

* A saída deve ser algo parecido com:

```
openjdk 21.0.4 2024-07-16
OpenJDK Runtime Environment (build 21.0.4+7-Ubuntu-1ubuntu222.04)
OpenJDK 64-Bit Server VM (build 21.0.4+7-Ubuntu-1ubuntu222.04, mixed mode, sharing)
```

* Caso seu sistema tenha várias versões do java, é possível altenar entre diferentes versões rodando o comando

```
sudo update-alternatives --config java

```

### Instalar Maven

* Alternativamente à instalação do maven, é possível utilizar o maven wrapper (./mvnw). Para usar o mvnw, é necessário dar permissão de excução no arquivo, todos as operções realizadas com o mvn podem ser realizadas da mesma forma com mvnw. Para isso, execute o seguinte comando

```
chmod +X mvnw
```

* Para instalar o maven, execute os comandos abaixo

```
wget https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz

tar -xvf apache-maven-3.9.9-bin.tar.gz

mvn --version

sudo mv apache-maven-3.9.9 /opt/

rm apache-maven-3.9.9-bin.tar.gz 
```

* Inclua o maven no PATH

```
code ~/.bashrc
```

* Com o .bashrc aberto, inclua ao final do arquivo as linhas abaixo e salve o arquivo

```
export MAVEN_HOME=/home/fabricio/maven/apache-maven-3.9.9/bin
export PATH=$MAVEN_HOME:$PATH
```

* Após salvar o arquvio .bashrc execute o comando abaixo

```
source ~/.bashrc
```

* Verifique se a instalação foi realizada com sucesso

```
mvn --version
```

* Se a instalção foi bem sucedida o terminal exibirá a mensagem abaixo

```
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: /opt/apache-maven-3.9.9
Java version: 21.0.4, vendor: Ubuntu, runtime: /usr/lib/jvm/java-21-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.15.153.1-microsoft-standard-wsl2", arch: "amd64", family: "unix"
```

### Instalar o Docker

* Siga as instruções disponívels na [documentação do docker](https://docs.docker.com/engine/install/ubuntu/){:target="_blank"} para instalação

### Executar a aplicação

* Para executar a aplicação rode os seguintes comandos

```
docker compose up -d
```

```
mvn clean install -DskipTests

mvn spring-boot:run
```