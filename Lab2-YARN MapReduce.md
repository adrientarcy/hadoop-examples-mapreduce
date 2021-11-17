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
[adrien.tarcy@hadoop-edge01 ~]$ alias mapreduce="yarn jar ~/hadoop-examples-mapreduce/hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar" 
```
Ce qui permet de lancer nos jobs plus facilement 
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ mapreduce nom_du_job fichier_a_traiter path_sur_le_dfs
```
Une fois nos jobs executés on vérifier leur contenu ainsi
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ hdfs dfs -cat path_sur_le_dfs/part-r-00000
```


## Réalisation du projet -  Remarkable trees of Paris
### 1.8.1 Districts containing trees
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ mapreduce district_trees trees.csv dst_out; hdfs dfs -cat path_sur_le_dfs/part-r-00000
```
```
21/11/12 14:38:54 INFO mapreduce.Job:  map 0% reduce 0%
21/11/12 14:39:03 INFO mapreduce.Job:  map 100% reduce 0%
21/11/12 14:39:13 INFO mapreduce.Job:  map 100% reduce 100%

11	
12	
13	
14	
15	
16	
17	
18	
19	
20	
3	
4	
5	
6	
7	
8	
9	
```
### 1.8.2 Show all existing species
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ mapreduce existing_species trees.csv exsp_out; hdfs dfs -cat exsp_out/part-r-00000
```
```
21/11/12 14:42:54 INFO mapreduce.Job:  map 0% reduce 0%
21/11/12 14:43:23 INFO mapreduce.Job:  map 100% reduce 0%
21/11/12 14:43:53 INFO mapreduce.Job:  map 100% reduce 100%

araucana
atlantica
australis
baccata
bignonioides
biloba
bungeana
cappadocicum
carpinifolia
colurna
coulteri
decurrens
dioicus
distichum
excelsior
fraxinifolia
giganteum
giraldii
glutinosa
[...]
```
### 1.8.3 Number of trees by kinds
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ mapreduce tree_kinds trees.csv trkds_out; hdfs dfs -cat trkds_out/part-r-00000
```
```
21/11/12 14:45:24 INFO mapreduce.Job:  map 0% reduce 0%
21/11/12 14:45:53 INFO mapreduce.Job:  map 100% reduce 0%
21/11/12 14:46:13 INFO mapreduce.Job:  map 100% reduce 100%

araucana	1
atlantica	2
australis	1
baccata	2
bignonioides	1
biloba	5
bungeana	1
cappadocicum	1
carpinifolia	4
colurna	3
coulteri	1
decurrens	1
dioicus	1
distichum	3
excelsior	1
fraxinifolia	2
giganteum	5
giraldii	1
glutinosa	1
```
### 1.8.4 Maximum height per kind of tree
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ mapreduce height_species_max trees.csv maxhgt_out; hdfs dfs -cat maxhgt_out/part-r-00000
```
```
21/11/12 14:48:57 INFO mapreduce.Job:  map 0% reduce 0%
21/11/12 14:49:09 INFO mapreduce.Job:  map 100% reduce 0%
21/11/12 14:49:23 INFO mapreduce.Job:  map 100% reduce 100%

araucana	9.0
atlantica	25.0
australis	16.0
baccata	13.0
bignonioides	15.0
biloba	33.0
bungeana	10.0
cappadocicum	16.0
carpinifolia	30.0
colurna	20.0
coulteri	14.0
[...]
```
### 1.8.5 Sort the trees height from smallest to largest 
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ mapreduce sort_tree trees.csv sort_out; hdfs dfs -cat sort_out/part-r-00000
```
```
21/11/12 14:59:06 INFO mapreduce.Job:  map 0% reduce 0%
21/11/12 14:59:23 INFO mapreduce.Job:  map 100% reduce 0%
21/11/12 14:59:57 INFO mapreduce.Job:  map 100% reduce 100%


3 - Fagus sylvatica (Fagaceae)	2.0
89 - Taxus baccata (Taxaceae)	5.0
62 - Cedrus atlantica (Pinaceae)	6.0
39 - Araucaria araucana (Araucariaceae)	9.0
44 - Styphnolobium japonicum (Fabaceae)	10.0
32 - Quercus suber (Fagaceae)	10.0
95 - Pinus bungeana (Pinaceae)	10.0
61 - Gymnocladus dioicus (Fabaceae)	10.0
63 - Fagus sylvatica (Fagaceae)	10.0
4 - Robinia pseudoacacia (Fabaceae)	11.0
93 - Diospyros virginiana (Ebenaceae)	12.0
66 - Magnolia grandiflora (Magnoliaceae)	12.0
50 - Zelkova carpinifolia (Ulmaceae)	12.0
7 - Eucommia ulmoides (Eucomiaceae)	12.0
48 - Acer monspessulanum (Sapindacaees)	12.0
58 - Diospyros kaki (Ebenaceae)	12.0
33 - Broussonetia papyrifera (Moraceae)	12.0
71 - Davidia involucrata (Cornaceae)	12.0
36 - Taxus baccata (Taxaceae)	13.0
[...]
```
### 1.8.6 District containing the oldest tree
```Powershell
[adrien.tarcy@hadoop-edge01 ~]$ mapreduce oldest_tree_district trees.csv old_out; hdfs dfs -cat old_out/part-r-00000
```
```
21/11/12 16:23:54 INFO mapreduce.Job:  map 0% reduce 0%
21/11/12 16:25:03 INFO mapreduce.Job:  map 100% reduce 0%
21/11/12 16:25:57 INFO mapreduce.Job:  map 100% reduce 100%

1601	5
1601	3
```
