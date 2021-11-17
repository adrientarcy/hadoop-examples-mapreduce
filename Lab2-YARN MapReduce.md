# Lab2 - YARN MapReduce
## Configuraition

Afin de simplifier le rendu et la mise a jour du jar, un dossier git à été init sur le edge
```Powershell
[adrien.tarcy@hadoop-edge01 hadoop-examples-mapreduce]$ ls
hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar  src
hadoop-examples-mapreduce.iml                                     README.md
```
La mise a jour du jar se fait donc via un simple
```Powershell
[adrien.tarcy@hadoop-edge01 hadoop-examples-mapreduce]$ git fetch
[...]
[adrien.tarcy@hadoop-edge01 hadoop-examples-mapreduce]$ git pull
```
Une fois que le jar a bien importé, on associe son éxecution à un raccourcis 
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ alias mapreduce="yarn jar ~/hadoop-examples-mapreduce/target/hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar"
```
Ce qui permet de lancer nos jobs plus facilement 
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ mapreduce non_du_job path_sur_le_dfs
```
Une fois nos jobs executés on vérifier leur contenu ainsi
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ hdfs dfs -cat path_sur_le_dfs/part-r-00000
```


## Réalisation du projet
### 1.8 Remarkable trees of Paris
```Powershell

```
### 1.8.1 Districts containing trees
```Powershell

```
### 1.8.2 Show all existing species
```Powershell

```
### 1.8.3 Number of trees by kinds
```Powershell

```
### 1.8.4 Maximum height per kind of tree
```Powershell

```
### 1.8.5 Sort the trees height from smallest to largest 
```Powershell

```
### 1.8.6 District containing the oldest tree
```Powershell

```
